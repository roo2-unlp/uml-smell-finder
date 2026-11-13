package oo2.redictado.sniffers.variablewithoutvisibility;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that detects variables without explicit visibility. Stub: it does not detect anything yet.
 */
public class VariableWithoutVisibilitySniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PlantUmlVisitor() {};
    }
}