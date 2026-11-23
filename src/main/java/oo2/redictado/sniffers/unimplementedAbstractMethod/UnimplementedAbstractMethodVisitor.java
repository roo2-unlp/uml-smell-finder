package oo2.redictado.sniffers.unimplementedAbstractMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

/**
 * Visitor que busca clases concretas que no implementan los métodos abstractos
 * de su superclase directa.
 *
 * Recorre el diagrama armando objetos {@link Clase} y {@link Metodo}, y recién
 * al final conecta cada clase con su superclase y le pregunta qué métodos le
 * faltan, porque las flechas de herencia pueden aparecer antes que las clases.
 */
public class UnimplementedAbstractMethodVisitor extends PlantUmlVisitor {

    // Clases del diagrama por nombre, en el orden en que aparecen
    private final Map<String, Clase> clases = new LinkedHashMap<>();

    // Dónde se reporta cada clase: su declaración, o donde apareció por primera vez
    private final Map<String, ParserRuleContext> lugares = new HashMap<>();

    // Nombres de las superclases de cada clase, por extends o por flechas
    private final Map<String, Set<String>> padres = new HashMap<>();

    // Interfaces, enums y demás tipos que no son clases: quedan fuera del análisis
    private final Set<String> noClases = new HashSet<>();

    // ANALIZAR LAS CLASES UNA VEZ RECORRIDO TODO EL DIAGRAMA
    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        conectarSuperclases();
        for (Clase clase : clases.values()) {
            List<Metodo> faltantes = clase.metodosSinImplementar();
            if (!faltantes.isEmpty()) {
                String firmas = faltantes.stream().map(Metodo::toString).collect(Collectors.joining(", "));
                smell("La clase concreta " + clase.getNombre() + " no implementa los métodos abstractos de su superclase "
                        + clase.getSuperclase().getNombre() + ": " + firmas, lugares.get(clase.getNombre()));
            }
        }
        return null;
    }

    // RECOLECTAR CLASES, HERENCIAS CON EXTENDS Y MÉTODOS DEL CUERPO
    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        String nombre = nombre(ctx.className());
        if (ctx.classKind() != null && ctx.classKind().CLASS() == null) {
            noClases.add(nombre);
            return visitChildren(ctx);
        }
        Clase clase = clase(nombre, ctx);
        // Si antes apareció solo en una flecha, el aroma pasa a marcar la declaración
        if (!(lugares.get(nombre) instanceof PlantUmlParser.ClassDeclarationContext)) {
            lugares.put(nombre, ctx);
        }
        if (esAbstracta(ctx)) {
            clase.marcarAbstracta();
        }

        PlantUmlParser.ClassTailContext tail = ctx.classTail();
        if (tail.extendsClause() != null) {
            for (PlantUmlParser.TypeContext padre : tail.extendsClause().typeList().type()) {
                heredar(nombre, padre.IDENTIFIER().getText());
            }
        }
        if (tail.classBody() != null) {
            for (PlantUmlParser.BodyItemContext item : tail.classBody().bodyItem()) {
                PlantUmlParser.MemberContext miembro = item.member();
                if (miembro != null) {
                    agregarMetodo(clase, miembro.visibility(), miembro.memberModifier(), miembro.memberSignature());
                }
            }
        }
        return visitChildren(ctx);
    }

    // RECOLECTAR MÉTODOS DECLARADOS FUERA DEL CUERPO (Clase : +metodo())
    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        Clase clase = clase(nombre(ctx.className()), ctx);
        agregarMetodo(clase, ctx.visibility(), ctx.memberModifier(), ctx.memberSignature());
        return visitChildren(ctx);
    }

    // RECOLECTAR HERENCIAS CON FLECHAS (Padre <|-- Hija, Hija --|> Padre)
    @Override
    public Void visitRelationship(PlantUmlParser.RelationshipContext ctx) {
        String flecha = ctx.ARROW().getText();
        // Las flechas punteadas (..|>) son realizaciones de interfaces, no herencia
        if (!flecha.contains(".")) {
            String izquierda = nombre(ctx.relEndpoint(0).className());
            String derecha = nombre(ctx.relEndpoint(1).className());
            if (flecha.startsWith("<|")) {
                clase(izquierda, ctx);
                clase(derecha, ctx);
                heredar(derecha, izquierda);
            } else if (flecha.endsWith("|>")) {
                clase(izquierda, ctx);
                clase(derecha, ctx);
                heredar(izquierda, derecha);
            }
        }
        return visitChildren(ctx);
    }

    // Metodos Auxiliares

    /**
     * Conecta cada clase con su superclase. Las que no son clases, las que
     * tienen más de un padre y las que heredan de algo que no es una clase del
     * diagrama quedan sin superclase, y por lo tanto sin obligaciones.
     */
    private void conectarSuperclases() {
        padres.forEach((hija, suyos) -> {
            if (noClases.contains(hija) || suyos.size() != 1) {
                return;
            }
            String nombrePadre = suyos.iterator().next();
            Clase padre = clases.get(nombrePadre);
            if (padre != null && !noClases.contains(nombrePadre)) {
                clases.get(hija).heredaDe(padre);
            }
        });
    }

    private void heredar(String hija, String padre) {
        padres.computeIfAbsent(hija, n -> new LinkedHashSet<>()).add(padre);
    }

    private Clase clase(String nombre, ParserRuleContext dondeAparece) {
        lugares.putIfAbsent(nombre, dondeAparece);
        return clases.computeIfAbsent(nombre, Clase::new);
    }

    private boolean esAbstracta(PlantUmlParser.ClassDeclarationContext ctx) {
        // "abstract X" deja el token en la declaración, pero "abstract class X" lo deja en un classModifier
        if (ctx.ABSTRACT() != null || ctx.classModifier().stream().anyMatch(mod -> mod.ABSTRACT() != null)) {
            return true;
        }
        PlantUmlParser.StereotypeContext estereotipo = ctx.classTail().stereotype();
        return estereotipo != null && estereotipo.getText().toLowerCase().contains("abstract");
    }

    // Arma un Metodo con lo que dice el árbol; los atributos se ignoran
    private void agregarMetodo(Clase clase, PlantUmlParser.VisibilityContext visibilidad,
            List<PlantUmlParser.MemberModifierContext> modificadores, PlantUmlParser.MemberSignatureContext miembro) {
        PlantUmlParser.MethodSignatureContext firma = miembro.methodSignature();
        if (firma == null) {
            return; // es un atributo
        }
        List<String> tiposParams = new ArrayList<>();
        if (firma.paramList() != null) {
            for (PlantUmlParser.ParamContext p : firma.paramList().param()) {
                tiposParams.add(p.type() != null ? p.type().getText() : "");
            }
        }
        clase.agregarMetodo(new Metodo(
                visibilidad != null ? visibilidad.getText() : null,
                firma.IDENTIFIER().getText(),
                tiposParams,
                retorno(firma),
                modificadores.stream().anyMatch(mod -> mod.ABSTRACT_MOD() != null),
                modificadores.stream().anyMatch(mod -> mod.STATIC_MOD() != null)));
    }

    private String retorno(PlantUmlParser.MethodSignatureContext firma) {
        if (firma.type().isEmpty()) {
            return null;
        }
        // Estilo UML: nombre() : Tipo. Estilo Java: Tipo nombre()
        return firma.COLON() != null ? firma.type(firma.type().size() - 1).getText() : firma.type(0).getText();
    }

    private String nombre(PlantUmlParser.ClassNameContext ctx) {
        String text = ctx.getText();
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }
}
