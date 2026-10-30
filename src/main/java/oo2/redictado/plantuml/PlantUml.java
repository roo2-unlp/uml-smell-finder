package oo2.redictado.plantuml;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import oo2.redictado.core.Language;
import oo2.redictado.plantuml.antlr4.PlantUmlLexer;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

/**
 * PlantUML class diagrams.
 */
public class PlantUml implements Language<DiagramContext> {

    @Override
    public DiagramContext parse(String code) {
        PlantUmlLexer lexer = new PlantUmlLexer(CharStreams.fromString(code));
        PlantUmlParser parser = new PlantUmlParser(new CommonTokenStream(lexer));

        DiagramContext diagram = parser.diagram();
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new IllegalArgumentException("Syntax error");
        }
        return diagram;
    }
}
