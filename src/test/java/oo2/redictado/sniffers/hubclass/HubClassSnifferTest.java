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

    @Test
    public void test10AgregacionHaciaHCuenta() {
        assertHubDetected("""
                @startuml
                class H
                class A
                class B
                class C
                A o-- H
                B o-- H
                C o-- H
                @enduml
                """, 3);
    }

    @Test
    public void test11ComposicionHaciaHCuenta() {
        assertHubDetected("""
                @startuml
                class H
                class A
                class B
                class C
                A *-- H
                B *-- H
                C *-- H
                @enduml
                """, 3);
    }

    @Test
    public void test12TipoDeAtributoCuenta() {
        assertHubDetected("""
                @startuml
                class H
                class A {
                    -dato : H
                }
                class B {
                    -dato : H
                }
                class C {
                    -dato : H
                }
                @enduml
                """, 3);
    }

    @Test
    public void test13TipoDeParametroCuenta() {
        assertHubDetected("""
                @startuml
                class H
                class A {
                    +procesar(dato : H) : void
                }
                class B {
                    +procesar(dato : H) : void
                }
                class C {
                    +procesar(dato : H) : void
                }
                @enduml
                """, 3);
    }

    @Test
    public void test14TipoDeRetornoCuenta() {
        assertHubDetected("""
                @startuml
                class H
                class A {
                    +crear() : H
                }
                class B {
                    +crear() : H
                }
                class C {
                    +crear() : H
                }
                @enduml
                """, 3);
    }

    @Test
    public void test15HerenciaConFlechaNoCuenta() {
        assertNoHub("""
                @startuml
                class H
                class A
                class B
                class C
                H <|-- A
                H <|-- B
                H <|-- C
                @enduml
                """);
    }

    @Test
    public void test16HerenciaConExtendsNoCuenta() {
        assertNoHub("""
                @startuml
                class H
                class A extends H
                class B extends H
                class C extends H
                @enduml
                """);
    }

    @Test
    public void test17ImplementacionDeInterfazNoCuenta() {
        assertNoHub("""
                @startuml
                interface H
                class A implements H
                class B implements H
                class C implements H
                @enduml
                """);
    }

    @Test
    public void test18AutorreferenciaNoCuenta() {
        assertNoHub("""
                @startuml
                class H
                class A
                class B
                class C
                A --> H
                B --> H
                H --> H
                @enduml
                """);
    }

    @Test
    public void test19RelacionesSalientesNoCuentan() {
        assertNoHub("""
                @startuml
                class H
                class A
                class B
                class C
                H --> A
                H --> B
                H --> C
                @enduml
                """);
    }

    @Test
    public void test20ReferenciasDeTiposDistintosDesdeClasesDistintasCuentan() {
        assertHubDetected("""
                @startuml
                class H
                class A
                class B {
                    -dato : H
                }
                class C {
                    +procesar(dato : H) : void
                }
                A o-- H
                @enduml
                """, 3);
    }

    @Test
    public void test21VariasFormasDeReferenciaDesdeUnaClaseCuentanUnaVez() {
        assertNoHub("""
                @startuml
                class H
                class A {
                    -dato : H
                    +procesar(dato : H) : H
                }
                class B
                class C
                A --> H
                A o-- H
                A ..> H
                @enduml
                """);
    }

    private void assertHubDetected(String code, int knownBy) {
        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("HubClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("H es una Hub Class: la conocen " + knownBy + " clases.", aroma.message());
    }

    private void assertNoHub(String code) {
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }
}
