package oo2.redictado.sniffers.hubclass;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer que detecta clases conocidas por muchas otras clases del diagrama (Hub Class).
 */
public class HubClassSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new HubClassSnifferVisitor();
    }
}
