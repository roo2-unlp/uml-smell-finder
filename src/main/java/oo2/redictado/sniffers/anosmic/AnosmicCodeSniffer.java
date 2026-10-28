package oo2.redictado.sniffers.anosmic;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that never detects bad smells.
 */
public class AnosmicCodeSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PlantUmlVisitor();
    }
}
