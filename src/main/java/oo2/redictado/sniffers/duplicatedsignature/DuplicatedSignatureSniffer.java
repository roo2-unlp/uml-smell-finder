package oo2.redictado.sniffers.duplicatedsignature;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;
// import oo2.redictado.sniffers.print.PrintSnifferVisitor;

public class DuplicatedSignatureSniffer extends PlantUmlSniffer{
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new DuplicatedSignatureVisitor();
    }
}
