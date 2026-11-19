package oo2.redictado.sniffers.duplicatedsignature;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

/**
 * Busca subclases de una misma superclase que declaran un método con la misma
 * firma, cuando ese método no está declarado en la superclase.
 *
 * Primero anota las clases del diagrama y al final busca las firmas duplicadas,
 * porque las flechas de herencia pueden aparecer antes que las clases.
 */
public class DuplicatedSignatureVisitor extends PlantUmlVisitor {
    // Para cada clase, los nombres de sus superclases
    private final Map<String, Set<String>> superclases = new LinkedHashMap<>();
    // Para cada clase, sus firmas y el lugar donde está declarado cada método
    private final Map<String, Map<String, ParserRuleContext>> metodos = new LinkedHashMap<>();
    private String claseActual;

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        this.buscarFirmasDuplicadas();
        return null;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        this.claseActual = this.nombre(ctx.className());
        PlantUmlParser.ExtendsClauseContext extendsClause = ctx.classTail().extendsClause();
        if (extendsClause != null) {
            for (PlantUmlParser.TypeContext superclase : extendsClause.typeList().type()) {
                this.agregarSuperclase(this.claseActual, superclase.IDENTIFIER().getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        this.claseActual = this.nombre(ctx.className());
        return visitChildren(ctx);
    }

    @Override
    public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
        this.metodos.computeIfAbsent(this.claseActual, clase -> new LinkedHashMap<>())
                .putIfAbsent(this.firma(ctx), ctx);
        return null;
    }

    @Override
    public Void visitRelationship(PlantUmlParser.RelationshipContext ctx) {
        String flecha = ctx.ARROW().getText();
        String izquierda = this.nombre(ctx.relEndpoint(0).className());
        String derecha = this.nombre(ctx.relEndpoint(1).className());
        if (flecha.startsWith("<|") && flecha.endsWith("-")) {
            this.agregarSuperclase(derecha, izquierda);
        } else if (flecha.startsWith("-") && flecha.endsWith("|>")) {
            this.agregarSuperclase(izquierda, derecha);
        }
        return null;
    }

    private void agregarSuperclase(String subclase, String superclase) {
        this.superclases.computeIfAbsent(subclase, clase -> new LinkedHashSet<>()).add(superclase);
    }

    /**
     * @return el texto de la firma, por ejemplo "acelerar(Integer) : void"
     */
    private String firma(PlantUmlParser.MethodSignatureContext ctx) {
        List<String> tipos = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (PlantUmlParser.ParamContext parametro : ctx.paramList().param()) {
                tipos.add(this.tipo(parametro));
            }
        }
        String retorno = ctx.type().isEmpty() ? "void" : ctx.type(ctx.type().size() - 1).getText();
        return ctx.IDENTIFIER().getText() + "(" + String.join(", ", tipos) + ") : " + retorno;
    }

    private String tipo(PlantUmlParser.ParamContext parametro) {
        // En cargar(Real) la gramática lee "Real" como un nombre: lo tomamos como el tipo
        return parametro.type() != null ? parametro.type().getText() : parametro.IDENTIFIER().getText();
    }

    private String nombre(PlantUmlParser.ClassNameContext ctx) {
        return ctx.getText().replace("\"", "");
    }

    private void buscarFirmasDuplicadas() {
        Map<String, List<String>> subclases = this.subclasesPorSuperclase();
        for (String superclase : subclases.keySet()) {
            this.compararSubclases(superclase, subclases.get(superclase));
        }
    }

    /**
     * Las clases con más de una superclase no se cuentan como subclases.
     */
    private Map<String, List<String>> subclasesPorSuperclase() {
        Map<String, List<String>> subclases = new LinkedHashMap<>();
        for (String clase : this.superclases.keySet()) {
            Set<String> suyas = this.superclases.get(clase);
            if (suyas.size() == 1) {
                subclases.computeIfAbsent(suyas.iterator().next(), superclase -> new ArrayList<>()).add(clase);
            }
        }
        return subclases;
    }

    private void compararSubclases(String superclase, List<String> subclases) {
        Map<String, List<String>> subclasesPorFirma = new LinkedHashMap<>();
        for (String subclase : subclases) {
            for (String firma : this.metodosDe(subclase).keySet()) {
                subclasesPorFirma.computeIfAbsent(firma, f -> new ArrayList<>()).add(subclase);
            }
        }
        for (String firma : subclasesPorFirma.keySet()) {
            List<String> conLaFirma = subclasesPorFirma.get(firma);
            if (conLaFirma.size() >= 2 && !this.metodosDe(superclase).containsKey(firma)) {
                String mensaje = "Firma duplicada: " + firma + " en " + this.enumerar(conLaFirma)
                        + ", y no está en " + superclase;
                this.smell(mensaje, this.metodosDe(conLaFirma.get(0)).get(firma));
            }
        }
    }

    private Map<String, ParserRuleContext> metodosDe(String clase) {
        return this.metodos.getOrDefault(clase, Map.of());
    }

    /**
     * @return los nombres en forma de texto, por ejemplo "Auto, Moto y Camion"
     */
    private String enumerar(List<String> nombres) {
        int ultimo = nombres.size() - 1;
        return String.join(", ", nombres.subList(0, ultimo)) + " y " + nombres.get(ultimo);
    }
}
