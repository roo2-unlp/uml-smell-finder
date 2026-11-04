package oo2.redictado.sniffers.duplicatedsignature;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Detecta subclases de una misma superclase que declaran un método con la
 * misma firma, cuando ese método no está declarado en la superclase.
 */
public class DuplicatedSignatureSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new DuplicatedSignatureVisitor();
    }
}
