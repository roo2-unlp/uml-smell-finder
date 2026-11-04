package oo2.redictado.sniffers.duplicatedsignature;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;
// import oo2.redictado.sniffers.duplicatedsignature.DuplicatedSignatureSniffer;

import org.junit.jupiter.api.BeforeEach;
import oo2.redictado.core.SmellFinder;

public class DuplicatedSignatureSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach 
    public void setup(){
        finder = new SmellFinder<>(new oo2.redictado.plantuml.PlantUml(), new DuplicatedSignatureSniffer());
    }
}
