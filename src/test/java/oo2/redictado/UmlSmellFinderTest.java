package oo2.redictado;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;

public class UmlSmellFinderTest {
    String code = """
           @startuml
           class Printer {
               +print()
           }
           @enduml
           """;

    @Test
    public void testKnowsTheSniffersOfTheProject() {
        assertTrue(UmlSmellFinder.catalog().names().contains("PrintSniffer"));
    }

    @Test
    public void testRunsTheChosenSniffers() {
        AromaReport report = new SmellFinder<>(new PlantUml(), UmlSmellFinder.catalog().sniffers("PrintSniffer")).sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals("PrintSniffer", report.getAromas().get(0).name());
    }

    @Test
    public void testRunsAllTheSniffers() {
        AromaReport report = new SmellFinder<>(new PlantUml(), UmlSmellFinder.catalog().all()).sniff(code);

        assertEquals(1, report.getAromas().size());
    }
}
