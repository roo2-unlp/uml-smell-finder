package oo2.redictado.sniffers.hubclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class HubClassSnifferTest {
    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new HubClassSniffer());
    }

    @Test
    public void testDiagramaVacio() {
        String code = """
                @startuml
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testUnaSolaClase() {
        String code = """
                @startuml
                class H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testClasesQueNoSeConocen() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testConocidaPorElMinimoEsHub() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C {
                    +obtener() : H
                }
                class D
                class E
                class F
                A ..> H
                B --> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("HubClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("H es una Hub Class: la conocen 3 clases.", aroma.message());
    }

    @Test
    public void testConocidaPorUnaMenosQueElMinimo() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                A ..> H
                B --> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testSuperaElMinimoPeroNoLlegaALaMitad() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                class D
                class E
                class F
                class G
                class I
                class J
                A --> H
                B o-- H
                C *-- H
                D ..> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testConocidaPorLaMitadEnUnDiagramaGrande() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                class D
                class E
                class F
                class G
                class I
                class J
                class K
                A --> H
                B o-- H
                C *-- H
                D ..> H
                E --> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("HubClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("H es una Hub Class: la conocen 5 clases.", aroma.message());
    }

    @Test
    public void testVariasReferenciasDesdeUnaClaseCuentanUnaVez() {
        String code = """
                @startuml
                class H
                class A {
                    -principal : H
                    -respaldo : H
                    +convertir(x : H) : H
                }
                class B
                class C
                class D
                class E
                class F
                A --> H
                B ..> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testAsociacionDirigidaCuenta() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                A --> H
                B --> H
                C --> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("HubClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("H es una Hub Class: la conocen 3 clases.", aroma.message());
    }

    @Test
    public void testDependenciaCuenta() {
        String code = """
                @startuml
                class H
                class A
                class B
                class C
                A ..> H
                B ..> H
                C ..> H
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("HubClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("H es una Hub Class: la conocen 3 clases.", aroma.message());
    }
}
