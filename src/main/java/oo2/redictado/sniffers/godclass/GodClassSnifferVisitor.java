package oo2.redictado.sniffers.godclass;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

public class GodClassSnifferVisitor extends PlantUmlVisitor {

    public GodClassSnifferVisitor(int minClasses, int minMethods, int minAttributes, double iqrFactor) {
        super();
    }

    @Override
    public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
        return null;
    }
}
