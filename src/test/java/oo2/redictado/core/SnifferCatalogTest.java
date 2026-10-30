package oo2.redictado.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class SnifferCatalogTest {
    SnifferCatalog<DiagramContext> catalog;
    DiagramContext diagram = new PlantUml().parse("""
           @startuml
           class Printer
           @enduml
           """);

    @BeforeEach
    public void setUp() {
        catalog = new SnifferCatalog<>();
        // Reports its parameters as an aroma, so tests can check they arrived
        catalog.add("Configurable", params -> {
            String label = params.getString("label", "default");
            int line = params.getInt("line", 1);
            return (tree, report) -> report.addAroma(new Aroma("Configurable", label, line));
        });
        catalog.add("Plain", params -> (tree, report) -> report.addAroma(new Aroma("Plain", "It stinks", 1)));
    }

    List<Aroma> sniff(CodeSniffer<DiagramContext> sniffer) {
        AromaReport report = new AromaReport();
        sniffer.sniff(diagram, report);
        return report.getAromas();
    }

    @Test
    public void testKnowsTheNamesInOrder() {
        assertEquals(List.of("Configurable", "Plain"), List.copyOf(catalog.names()));
    }

    @Test
    public void testFailsWithRepeatedNames() {
        assertThrows(IllegalArgumentException.class,
                () -> catalog.add("Plain", params -> (tree, report) -> {}));
    }

    @Test
    public void testAllRunsEverySnifferWithDefaults() {
        List<Aroma> aromas = sniff(catalog.all());

        assertEquals(2, aromas.size());
        assertEquals(new Aroma("Configurable", "default", 1), aromas.get(0));
        assertEquals("Plain", aromas.get(1).name());
    }

    @Test
    public void testRunsTheChosenSniffersInOrder() {
        List<Aroma> aromas = sniff(catalog.sniffers("Plain, Configurable"));

        assertEquals("Plain", aromas.get(0).name());
        assertEquals("Configurable", aromas.get(1).name());
    }

    @Test
    public void testPassesTheParameters() {
        List<Aroma> aromas = sniff(catalog.sniffers("Configurable:label=custom:line=7"));

        assertEquals(new Aroma("Configurable", "custom", 7), aromas.get(0));
    }

    @Test
    public void testCreatesWithParameters() {
        CodeSniffer<DiagramContext> sniffer = catalog.create("Configurable", new SnifferParams(Map.of("line", "3")));

        assertEquals(new Aroma("Configurable", "default", 3), sniff(sniffer).get(0));
    }

    @Test
    public void testFailsWithUnknownSniffers() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> catalog.sniffers("Plain,Nope"));

        assertEquals("Unknown sniffer: Nope. Available: Configurable, Plain", e.getMessage());
    }

    @Test
    public void testFailsWithUnknownParameters() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> catalog.sniffers("Configurable:lable=custom"));

        assertEquals("Unknown parameters for Configurable: lable", e.getMessage());
    }

    @Test
    public void testFailsWithParametersForSniffersWithoutThem() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> catalog.sniffers("Plain:line=3"));

        assertEquals("Unknown parameters for Plain: line", e.getMessage());
    }

    @Test
    public void testFailsWithWrongValues() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> catalog.sniffers("Configurable:line=many"));

        assertEquals("Configurable: Parameter line must be an integer, not 'many'", e.getMessage());
    }

    @Test
    public void testFailsWithoutValues() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> catalog.sniffers("Configurable:line"));

        assertEquals("Expected name=value but got 'line' in Configurable:line", e.getMessage());
    }
}
