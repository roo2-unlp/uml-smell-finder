package oo2.redictado.sniffers.interfacecounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.PlainTextFormatter;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class InterfaceCounterSnifferTest {

    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new InterfaceCounterSniffer());
    }

    @Test
    public void testDiagramaConInterfacesDuplicadas() throws IOException {
        Path path = Path.of("documentation/Interface Counter/ejemplo.puml");
        String code = Files.readString(path);

        AromaReport report = finder.sniff(code);

        // Olores por duplicados y reporte final de cantidad de interfaces
        assertTrue(report.stinks());
        assertEquals(3, report.getAromas().size());

        Aroma aroma1 = report.getAromas().get(0);
        assertEquals("InterfaceCounterSniffer", aroma1.name());
        assertEquals(20, aroma1.line());
        assertEquals("Interface duplicada: Repositorio", aroma1.message());

        Aroma aroma2 = report.getAromas().get(1);
        assertEquals("InterfaceCounterSniffer", aroma2.name());
        assertEquals(25, aroma2.line());
        assertEquals("Interface duplicada: Notificador", aroma2.message());

        Aroma aroma3 = report.getAromas().get(2);
        assertEquals("InterfaceCounterSniffer", aroma3.name());
        assertEquals(1, aroma3.line());
        assertEquals("Cantidad de interfaces: 3", aroma3.message());

        String formatted = new PlainTextFormatter().format(report);
        System.out.print(formatted);
        assertTrue(formatted.contains("[InterfaceCounterSniffer] line 20: Interface duplicada: Repositorio"));
        assertTrue(formatted.contains("[InterfaceCounterSniffer] line 25: Interface duplicada: Notificador"));
        assertTrue(formatted.contains("[InterfaceCounterSniffer] line 1: Cantidad de interfaces: 3"));
    }

    @Test
    public void testDiagramaSinDuplicadasHueleBien() {
        String code = """
                @startuml
                interface Repositorio
                interface Autenticable
                class Notificador <<interface>>
                class Usuario
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertEquals(0, report.getAromas().size());

        String formatted = new PlainTextFormatter().format(report);
        System.out.print(formatted);
        assertEquals("The diagram smells good.\n", formatted);
    }

    @Test
    public void testMultiplesDuplicacionesDeMismaInterface() {
        String code = """
                @startuml
                interface Servicio
                interface Servicio
                interface Servicio
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(3, report.getAromas().size());
        assertEquals("Interface duplicada: Servicio", report.getAromas().get(0).message());
        assertEquals("Interface duplicada: Servicio", report.getAromas().get(1).message());
        assertEquals("Cantidad de interfaces: 1", report.getAromas().get(2).message());
    }

    @Test
    public void testVisitorDirecto() {
        InterfaceCounterVisitor visitor = new InterfaceCounterVisitor();
        PlantUml plantUml = new PlantUml();
        visitor.visit(plantUml.parse("""
                @startuml
                interface A
                interface B
                interface C
                @enduml
                """));

        assertEquals(3, visitor.getInterfaceCount());
        assertEquals(Set.of("A", "B", "C"), visitor.getInterfaces());
        assertFalse(visitor.hasDuplicateInterfaces());
    }
}
