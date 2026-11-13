package oo2.redictado.sniffers.methodwithoutreturntype;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer that detects methods without a return type. Stub: it does not detect anything yet.
 */
public class MethodWithoutReturnTypeSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PlantUmlVisitor();
    }
}
