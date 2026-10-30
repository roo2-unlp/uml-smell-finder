package oo2.redictado.plantuml;

import oo2.redictado.core.VisitorSniffer;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

/**
 * Base class for sniffers of PlantUML class diagrams. Subclasses only need to
 * provide the visitor that looks for their bad smell.
 */
public abstract class PlantUmlSniffer extends VisitorSniffer<DiagramContext> {
    @Override
    protected abstract PlantUmlVisitor createVisitor();
}
