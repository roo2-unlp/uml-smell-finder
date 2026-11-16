package oo2.redictado.sniffers.dataclass;

import java.util.ArrayList;
import java.util.List;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;


public class DataClassVisitor extends PlantUmlVisitor {

    private final List<PlantUmlParser.ClassDeclarationContext> clases = new ArrayList<>();

    @Override //  con V mayúscula, que es un tipo que solo puede valer null
            //  El framework eligió Void porque los visitors no devuelven nada útil; solo recorren y anotan.
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        for (PlantUmlParser.ClassDeclarationContext clase : clases) {
           smell(clase.className().getText() + " es una Data Class: expone sus datos y no tiene comportamiento.", clase);
        }
        return null;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        if (esClase(ctx)){
            clases.add(ctx);
        }
        return visitChildren(ctx);
    }

    private boolean esClase(PlantUmlParser.ClassDeclarationContext ctx) {
        boolean abstractaSinClass = ctx.ABSTRACT() != null;
        boolean esClass = ctx.classKind() != null && ctx.classKind().CLASS() != null;
        return abstractaSinClass || esClass;
    }
}
