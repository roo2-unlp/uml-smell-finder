package oo2.redictado.sniffers.print;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

public class PrintSnifferVisitor extends PlantUmlVisitor {
    @Override
    public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
        if (ctx.IDENTIFIER().getText().equals("print")) {
            this.smell("The diagram has print methods.", ctx);
        }
        return visitChildren(ctx);
    }
}
