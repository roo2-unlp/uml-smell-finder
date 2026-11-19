package oo2.redictado.sniffers.classinterfacemismatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

// Primero recorre todo el diagrama juntando clases, interfaces y relaciones.
// Recien al final compara cada interfaz con las clases que la implementan.
public class ClassInterfaceMismatchVisitor extends PlantUmlVisitor {
    private final Map<String, Clasificador> clasificadores = new LinkedHashMap<>();
    private final Map<String, Realizacion> realizaciones = new LinkedHashMap<>();

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        realizaciones.values().forEach(this::analizar);
        return null;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        Clasificador clasificador = clasificador(nombreDe(ctx.className()));
        if (esInterfaz(ctx)) {
            clasificador.marcarComoInterfaz();
        }
        if (esAbstracta(ctx)) {
            clasificador.marcarComoAbstracta();
        }
        PlantUmlParser.ClassTailContext cola = ctx.classTail();
        if (cola.extendsClause() != null) {
            cola.extendsClause().typeList().type().forEach(tipo -> clasificador.agregarPadre(nombreDe(tipo)));
        }
        if (cola.implementsClause() != null) {
            cola.implementsClause().typeList().type().forEach(tipo ->
                    registrarRealizacion(clasificador.getNombre(), nombreDe(tipo), cola.implementsClause()));
        }
        if (cola.classBody() != null) {
            for (PlantUmlParser.BodyItemContext item : cola.classBody().bodyItem()) {
                PlantUmlParser.MemberContext miembro = item.member();
                if (miembro != null) {
                    agregarOperacion(clasificador, miembro.visibility(), miembro.memberModifier(), miembro.memberSignature());
                }
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        agregarOperacion(clasificador(nombreDe(ctx.className())), ctx.visibility(), ctx.memberModifier(), ctx.memberSignature());
        return visitChildren(ctx);
    }

    @Override
    public Void visitRelationship(PlantUmlParser.RelationshipContext ctx) {
        String flecha = ctx.ARROW().getText();
        String izquierda = nombreDe(ctx.relEndpoint(0).className());
        String derecha = nombreDe(ctx.relEndpoint(1).className());
        String padre;
        String hijo;
        // <|-- o <|.. : el padre es el de la izquierda. --|> o ..|> : el de la derecha
        if (flecha.startsWith("<|")) {
            padre = izquierda;
            hijo = derecha;
        } else if (flecha.endsWith("|>")) {
            padre = derecha;
            hijo = izquierda;
        } else {
            return visitChildren(ctx);
        }
        // con puntos es realizacion, con guiones es herencia
        if (flecha.contains(".")) {
            registrarRealizacion(hijo, padre, ctx);
        } else {
            clasificador(padre);
            clasificador(hijo).agregarPadre(padre);
        }
        return visitChildren(ctx);
    }

    // ---- comparacion ----

    private void analizar(Realizacion realizacion) {
        Clasificador clase = clasificadores.get(realizacion.clase());
        Clasificador interfaz = clasificadores.get(realizacion.interfaz());
        // las abstractas y los implements sobre algo que no es interfaz no se analizan
        if (!interfaz.esInterfaz() || !clase.esClaseConcreta()) {
            return;
        }
        List<Operacion> disponibles = operacionesConHerencia(clase);
        for (Operacion requerida : operacionesConHerencia(interfaz)) {
            compararOperacion(clase, interfaz, requerida, disponibles, realizacion);
        }
    }

    // a lo sumo un aroma por cada operacion del contrato
    private void compararOperacion(Clasificador clase, Clasificador interfaz, Operacion requerida,
            List<Operacion> disponibles, Realizacion realizacion) {
        String inicio = clase.getNombre() + " realiza " + interfaz.getNombre() + ", pero ";

        List<Operacion> conElMismoNombre = disponibles.stream().filter(requerida::tieneElMismoNombreQue).toList();
        if (conElMismoNombre.isEmpty()) {
            // 4.1 no hay ninguna con ese nombre: se marca la linea de la realizacion
            smell(inicio + "no ofrece " + requerida + " (operación ausente)", realizacion.dondeSeDeclara());
            return;
        }

        List<Operacion> conLaMismaFirma = conElMismoNombre.stream().filter(requerida::tieneLaMismaFirmaQue).toList();
        if (conLaMismaFirma.isEmpty()) {
            // 4.2 esta el nombre pero ninguna version tiene los mismos parametros
            Operacion ofrecida = conElMismoNombre.get(0);
            smell(inicio + ofrecida + " no coincide con " + requerida + " (firma incompatible)", ofrecida.dondeSeDeclara());
            return;
        }

        // si alguna sobrecarga cumple todo, esta bien
        if (conLaMismaFirma.stream().anyMatch(ofrecida -> problemaEntre(requerida, ofrecida) == null)) {
            return;
        }
        Operacion ofrecida = conLaMismaFirma.get(0);
        smell(inicio + ofrecida + " " + problemaEntre(requerida, ofrecida), ofrecida.dondeSeDeclara());
    }

    // devuelve el primer problema que encuentra (4.3 a 4.6), o null si la operacion cumple
    private String problemaEntre(Operacion requerida, Operacion ofrecida) {
        if (tieneVisibilidadReducida(requerida, ofrecida)) {
            return "tiene visibilidad " + NOMBRES_DE_VISIBILIDAD.get(ofrecida.visibilidad()) + " y la interfaz la declara "
                    + NOMBRES_DE_VISIBILIDAD.get(requerida.visibilidad()) + " (visibilidad reducida)";
        }
        if (ofrecida.esEstatica() && !requerida.esEstatica()) {
            return "es estática y la interfaz requiere una operación de instancia (estaticidad incompatible)";
        }
        if (ofrecida.esAbstracta()) {
            return "está declarada abstracta en una clase concreta (implementación diferida)";
        }
        if (!retornoCompatible(requerida.retorno(), ofrecida.retorno())) {
            return "no tiene un retorno compatible con " + requerida.retorno() + " (retorno incompatible)";
        }
        return null;
    }

    // escala de la documentacion: + > ~ > # > -. Si falta la visibilidad en algun lado no se reporta
    private static final Map<String, Integer> NIVEL_DE_VISIBILIDAD = Map.of("+", 4, "~", 3, "#", 2, "-", 1);
    private static final Map<String, String> NOMBRES_DE_VISIBILIDAD =
            Map.of("+", "pública", "~", "de paquete", "#", "protegida", "-", "privada");

    private boolean tieneVisibilidadReducida(Operacion requerida, Operacion ofrecida) {
        if (requerida.visibilidad() == null || ofrecida.visibilidad() == null) {
            return false;
        }
        return NIVEL_DE_VISIBILIDAD.get(ofrecida.visibilidad()) < NIVEL_DE_VISIBILIDAD.get(requerida.visibilidad());
    }

    // vale el mismo tipo o un subtipo segun las herencias del diagrama. Si falta alguno, no se reporta
    private boolean retornoCompatible(String requerido, String ofrecido) {
        if (requerido == null || ofrecido == null || requerido.equals(ofrecido)) {
            return true;
        }
        return esSubtipo(ofrecido, requerido, new HashSet<>());
    }

    private boolean esSubtipo(String tipo, String posiblePadre, Set<String> visitados) {
        Clasificador clasificador = clasificadores.get(tipo);
        if (clasificador == null || !visitados.add(tipo)) {
            return false;
        }
        for (String padre : clasificador.getPadres()) {
            if (padre.equals(posiblePadre) || esSubtipo(padre, posiblePadre, visitados)) {
                return true;
            }
        }
        return false;
    }

    // operaciones propias + las de los padres (en una clase las heredadas, en una interfaz las que extiende)
    private List<Operacion> operacionesConHerencia(Clasificador clasificador) {
        List<Operacion> operaciones = new ArrayList<>();
        juntarOperaciones(clasificador, operaciones, new HashSet<>());
        return operaciones;
    }

    private void juntarOperaciones(Clasificador clasificador, List<Operacion> operaciones, Set<String> visitados) {
        // por si hay ciclos en la herencia
        if (!visitados.add(clasificador.getNombre())) {
            return;
        }
        operaciones.addAll(clasificador.getOperaciones());
        clasificador.getPadres().forEach(padre -> juntarOperaciones(clasificador(padre), operaciones, visitados));
    }

    // ---- recoleccion ----

    private Clasificador clasificador(String nombre) {
        return clasificadores.computeIfAbsent(nombre, Clasificador::new);
    }

    private void registrarRealizacion(String clase, String interfaz, ParserRuleContext dondeSeDeclara) {
        clasificador(clase);
        clasificador(interfaz);
        realizaciones.putIfAbsent(clase + " -> " + interfaz, new Realizacion(clase, interfaz, dondeSeDeclara));
    }

    private void agregarOperacion(Clasificador clasificador, PlantUmlParser.VisibilityContext visibilidad,
            List<PlantUmlParser.MemberModifierContext> modificadores, PlantUmlParser.MemberSignatureContext firma) {
        PlantUmlParser.MethodSignatureContext metodo = firma.methodSignature();
        if (metodo == null) {
            return; // si es un atributo no nos interesa
        }
        List<Parametro> parametros = new ArrayList<>();
        if (metodo.paramList() != null) {
            metodo.paramList().param().forEach(param -> parametros.add(new Parametro(
                    param.IDENTIFIER().getText(), param.type() == null ? null : param.type().getText())));
        }
        clasificador.agregarOperacion(new Operacion(
                metodo.IDENTIFIER().getText(),
                parametros,
                tipoDeRetorno(metodo),
                visibilidad == null ? null : visibilidad.getText(),
                modificadores.stream().anyMatch(m -> m.STATIC_MOD() != null),
                modificadores.stream().anyMatch(m -> m.ABSTRACT_MOD() != null),
                metodo));
    }

    // puede venir como nombre() : Tipo o como Tipo nombre()
    private String tipoDeRetorno(PlantUmlParser.MethodSignatureContext metodo) {
        if (metodo.COLON() != null) {
            return metodo.type(metodo.type().size() - 1).getText();
        }
        return metodo.type().isEmpty() ? null : metodo.type(0).getText();
    }

    private boolean esInterfaz(PlantUmlParser.ClassDeclarationContext ctx) {
        if (ctx.classKind() != null && ctx.classKind().INTERFACE() != null) {
            return true;
        }
        PlantUmlParser.StereotypeContext estereotipo = ctx.classTail().stereotype();
        return estereotipo != null && estereotipo.getText().toLowerCase().contains("interface");
    }

    private boolean esAbstracta(PlantUmlParser.ClassDeclarationContext ctx) {
        return ctx.ABSTRACT() != null || ctx.classModifier().stream().anyMatch(m -> m.ABSTRACT() != null);
    }

    private String nombreDe(PlantUmlParser.ClassNameContext ctx) {
        return ctx.getText().replace("\"", "");
    }

    private String nombreDe(PlantUmlParser.TypeContext ctx) {
        return ctx.IDENTIFIER().getText();
    }
}
