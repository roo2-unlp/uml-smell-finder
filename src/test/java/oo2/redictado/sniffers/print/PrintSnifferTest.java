package oo2.redictado.sniffers.print;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class PrintSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new PrintSniffer());
    }

    @Test
    public void testPrints() {
        String code = """
               @startuml
               class Printer {
                   +print()
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("PrintSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @Test
    public void testMorePrints() {
        String code = """
               @startuml
               title Sistema de facturación

               abstract class Documento {
                   -numero : int
                   +{abstract} print()
               }

               class Factura extends Documento {
                   -total : double
                   +print() : void
                   +void print(String formato)
               }

               interface Imprimible {
                   print()
               }

               Factura : +print(Impresora impresora)
               Factura ..|> Imprimible

               note right of Factura
                   El método print() en una nota no cuenta.
               end note
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(5, report.getAromas().size());
    }

    @Test
    public void testNoPrints() {
        String code = """
               @startuml
               class Persona {
                   -nombre : String
                   -printer : Printer
                   +getNombre() : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testSyntaxError() {
        String code = "class Printer { +print() }";
        assertThrows(IllegalArgumentException.class, () -> {
            finder.sniff(code);
        });
    }
}
