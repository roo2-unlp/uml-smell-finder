package oo2.redictado.sniffers.publicvariable;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that detects public variables. Stub: it does not detect anything yet.
 */
public class PublicVariableSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PlantUmlVisitor() {};
    }
}