package ar.edu.unlp.info.roo2.interfacecounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        // Inicialización a través del framework SmellFinder (Opción B)
        finder = new SmellFinder<>(new PlantUml(), new InterfaceCounterSniffer());
    }

    @Test
    public void testDiagramaConInterfacesDuplicadas() throws IOException {
        Path path = Path.of("documentation/explicacionDelEjemplo/ejemplo.puml");
        String code = Files.readString(path);

        AromaReport report = finder.sniff(code);

        // Si hay duplicadas, report.stinks() es true
        assertTrue(report.stinks(), "El reporte debe indicar que el diagrama tiene mal olor");
        assertEquals(2, report.getAromas().size(), "Debe detectar exactamente 2 interfaces duplicadas");

        Aroma aroma1 = report.getAromas().get(0);
        assertEquals("InterfaceCounterSniffer", aroma1.name());
        assertEquals(20, aroma1.line());
        assertEquals("Interface duplicada: Repositorio", aroma1.message());

        Aroma aroma2 = report.getAromas().get(1);
        assertEquals("InterfaceCounterSniffer", aroma2.name());
        assertEquals(25, aroma2.line());
        assertEquals("Interface duplicada: Notificador", aroma2.message());

        // Verificamos el formateo de texto plano
        String formatted = new PlainTextFormatter().format(report);
        assertTrue(formatted.contains("[InterfaceCounterSniffer] line 20: Interface duplicada: Repositorio"));
        assertTrue(formatted.contains("[InterfaceCounterSniffer] line 25: Interface duplicada: Notificador"));
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

        // Si no hay duplicadas, report.stinks() es false e imprime "The diagram smells good."
        assertFalse(report.stinks(), "No debe tener olores si las interfaces son únicas");
        assertEquals(0, report.getAromas().size());

        String formatted = new PlainTextFormatter().format(report);
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
        assertEquals(2, report.getAromas().size(), "Las apariciones 2 y 3 deben reportarse como duplicadas");
    }

    /**
     * Ejecución directa para probar la Opción B por consola:
     */
    public static void main(String[] args) throws IOException {
        Path path = Path.of("documentation/explicacionDelEjemplo/ejemplo.puml");
        if (args.length > 0) {
            path = Path.of(args[0]);
        }

        SmellFinder<DiagramContext> finder = new SmellFinder<>(new PlantUml(), new InterfaceCounterSniffer());
        AromaReport report = finder.sniff(Files.readString(path));

        // Si no hay duplicadas, report.stinks() es false e imprime "The diagram smells good."
        // Si hay duplicadas, imprime cada línea con su olor:
        System.out.print(new PlainTextFormatter().format(report));
    }
}

