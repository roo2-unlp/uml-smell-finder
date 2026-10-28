package oo2.redictado.sniffers.print;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that detects methods named print.
 */
public class PrintSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PrintSnifferVisitor();
    }
}
