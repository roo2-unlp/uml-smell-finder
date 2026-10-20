package oo2.redictado;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.PrintSniffer.PrintSniffer;

public class PrintSnifferTest {
    PrintSniffer codeSniffer;

    @BeforeEach
    public void setUp() {
        codeSniffer = new PrintSniffer();
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
        AromaReport report = new AromaReport(code);
        codeSniffer.sniff(code, report);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
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
        AromaReport report = new AromaReport(code);
        codeSniffer.sniff(code, report);

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
        AromaReport report = new AromaReport(code);
        codeSniffer.sniff(code, report);

        assertFalse(report.stinks());
    }

    @Test
    public void testSyntaxError() {
        String code = "class Printer { +print() }";
        AromaReport report = new AromaReport(code);

        assertThrows(IllegalArgumentException.class, () -> {
            codeSniffer.sniff(code, report);
        });
    }
}
