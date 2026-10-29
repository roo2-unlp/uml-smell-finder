package oo2.redictado.sniffers.interfacecounter;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer del framework {@code uml-smell-finder} que contabiliza las interfaces
 * y clases de un diagrama PlantUML y detalla los métodos de cada una.
 */
public class InterfaceClassCounterSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new InterfaceCounterVisitor();
    }
}
