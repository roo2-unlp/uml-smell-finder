package oo2.redictado.plantuml;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.core.SmellCollector;
import oo2.redictado.core.SmellVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;
import oo2.redictado.plantuml.antlr4.PlantUmlParserBaseVisitor;

/**
 * Visitor that walks a PlantUML parse tree looking for bad smells.
 *
 * Subclasses override the visitXX methods they care about and call
 * {@link #smell(String, ParserRuleContext)} for each bad smell they find. On
 * its own it visits the whole tree and finds nothing.
 */
public class PlantUmlVisitor extends PlantUmlParserBaseVisitor<Void> implements SmellVisitor<DiagramContext> {
    private SmellCollector smells;

    /**
     * Called by the framework: visits the diagram and adds the bad smells
     * found to the collector.
     */
    @Override
    public final void sniff(DiagramContext diagram, SmellCollector smells) {
        this.smells = smells;
        this.visit(diagram);
    }

    /**
     * Records a bad smell found in the diagram.
     *
     * @param message the message that describes the bad smell
     * @param where the node of the tree where the bad smell was found
     */
    protected void smell(String message, ParserRuleContext where) {
        this.smells.add(message, where);
    }
}
