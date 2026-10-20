package oo2.redictado.AnosmicCodeSniffer;

import oo2.redictado.antlr4.PlantUmlParserBaseVisitor;
import oo2.redictado.antlr4.PlantUmlParser;

public class AnosmicVisitor extends PlantUmlParserBaseVisitor<Boolean> {
    @Override
    public Boolean visitDiagram(PlantUmlParser.DiagramContext ctx) {
        return false;
    }
}
