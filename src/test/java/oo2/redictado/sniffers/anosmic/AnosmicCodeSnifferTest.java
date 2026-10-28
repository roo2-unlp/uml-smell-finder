package oo2.redictado.sniffers.anosmic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class AnosmicCodeSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new AnosmicCodeSniffer());
    }

    @Test
    public void testSmell() {
        String code = """
               @startuml
               class Printer {
                   +print()
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);
        assertFalse(report.stinks());
    }

    @Test
    public void testSmellSyntaxError() {
        String code = "class Printer { +print() }";
        assertThrows(IllegalArgumentException.class, () -> {
            finder.sniff(code);
        });
    }
}
