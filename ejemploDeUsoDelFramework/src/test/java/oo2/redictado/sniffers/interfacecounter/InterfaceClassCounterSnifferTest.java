package oo2.redictado.sniffers.interfacecounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.PlainTextFormatter;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.core.SnifferCatalog;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

class InterfaceClassCounterSnifferTest {
    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    void setUp() {
        this.finder = new SmellFinder<>(new PlantUml(), new InterfaceClassCounterSniffer());
    }

    @Test
    void cuentaInterfacesYClasesYReportaAromasEnElFramework() {
        String puml = """
                @startuml
                interface Repositorio {
                  + guardar(entidad : Object) : void
                  + buscarPorId(id : int) : Object
                }
                class Autenticable <<interface>>
                class Usuario {
                  + getNombre() : String
                }
                abstract class BaseEntity
                enum Estado {
                  ACTIVO
                }
                @enduml
                """;

        AromaReport report = this.finder.sniff(puml);

        assertTrue(report.stinks());
        List<Aroma> aromas = report.getAromas();
        assertEquals(5, aromas.size());

        assertEquals(
                new Aroma(
                        "InterfaceClassCounterSniffer",
                        "Interfaz Repositorio: + guardar(entidad : Object) : void, + buscarPorId(id : int) : Object",
                        2),
                aromas.get(0));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Interfaz Autenticable: (sin métodos)", 6),
                aromas.get(1));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Clase Usuario: + getNombre() : String", 7),
                aromas.get(2));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Clase BaseEntity: (sin métodos)", 10),
                aromas.get(3));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Interfaces encontradas: 2, Clases encontradas: 2", 1),
                aromas.get(4));
    }

    @Test
    void incluyeMetodosDeclaradosExternamenteAntesYDespues() {
        String puml = """
                @startuml
                Notificador : + inicializar() : void
                interface Notificador
                Notificador : + enviar(mensaje : String) : boolean
                @enduml
                """;

        AromaReport report = this.finder.sniff(puml);
        List<Aroma> aromas = report.getAromas();

        assertEquals(2, aromas.size());
        assertEquals(
                new Aroma(
                        "InterfaceClassCounterSniffer",
                        "Interfaz Notificador: + inicializar() : void, + enviar(mensaje : String) : boolean",
                        3),
                aromas.get(0));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Interfaces encontradas: 1, Clases encontradas: 0", 1),
                aromas.get(1));
    }

    @Test
    void promueveClaseAInterfazMedianteDoubleDispatchSinDuplicar() {
        String puml = """
                @startuml
                class Servicio {
                  + ejecutar() : void
                }
                class Servicio <<interface>> {
                  + detener() : void
                }
                @enduml
                """;

        AromaReport report = this.finder.sniff(puml);
        List<Aroma> aromas = report.getAromas();

        assertEquals(2, aromas.size());
        assertEquals(
                new Aroma(
                        "InterfaceClassCounterSniffer",
                        "Interfaz Servicio: + ejecutar() : void, + detener() : void",
                        2),
                aromas.get(0));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Interfaces encontradas: 1, Clases encontradas: 0", 1),
                aromas.get(1));
    }

    @Test
    void retornaCeroSiNoHayInterfaces() {
        String puml = """
                @startuml
                class Cliente
                class Factura
                @enduml
                """;

        AromaReport report = this.finder.sniff(puml);
        List<Aroma> aromas = report.getAromas();

        assertEquals(3, aromas.size());
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Clase Cliente: (sin métodos)", 2),
                aromas.get(0));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Clase Factura: (sin métodos)", 3),
                aromas.get(1));
        assertEquals(
                new Aroma("InterfaceClassCounterSniffer", "Interfaces encontradas: 0, Clases encontradas: 2", 1),
                aromas.get(2));
    }

    @Test
    void seIntegraConSnifferCatalogYPlainTextFormatter() {
        String puml = """
                @startuml
                interface Repositorio {
                    + guardar()
                }
                @enduml
                """;

        SnifferCatalog<DiagramContext> catalog = new SnifferCatalog<>();
        catalog.add("InterfaceClassCounterSniffer", params -> new InterfaceClassCounterSniffer());

        SmellFinder<DiagramContext> catalogFinder = new SmellFinder<>(
                new PlantUml(),
                catalog.sniffers("InterfaceClassCounterSniffer"));
        String formatted = new PlainTextFormatter().format(catalogFinder.sniff(puml));

        assertTrue(formatted.contains("[InterfaceClassCounterSniffer] line 2: Interfaz Repositorio: + guardar()"));
        assertTrue(formatted.contains("[InterfaceClassCounterSniffer] line 1: Interfaces encontradas: 1, Clases encontradas: 0"));
    }

    @Test
    void lanzaExcepcionAnteErrorDeSintaxis() {
        assertThrows(IllegalArgumentException.class, () -> this.finder.sniff("class Invalida {"));
    }
}
