package oo2.redictado.sniffers.variablewithouttype;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that detects variables without a type. Stub: it does not detect anything yet.
 */
public class VariableWithoutTypeSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PlantUmlVisitor();
    }
}
