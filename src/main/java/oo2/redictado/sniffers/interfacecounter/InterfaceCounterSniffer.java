package oo2.redictado.sniffers.interfacecounter;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer que analiza diagramas de clases PlantUML, cuenta las interfaces
 * y detecta si existen interfaces duplicadas reportándolas como mal olor.
 */
public class InterfaceCounterSniffer extends PlantUmlSniffer {

    @Override
    protected PlantUmlVisitor createVisitor() {
        return new InterfaceCounterVisitor();
    }
}
