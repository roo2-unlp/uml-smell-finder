package oo2.redictado.sniffers.classinterfacemismatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.AromaReport;
import oo2.redictado.core.PlainTextFormatter;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class ClassInterfaceMismatchTest {

    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new ClassInterfaceMismatchSniffer());
    }

    @Test
    public void testEjemploConIncumplimientosDelContrato() throws IOException {
        Path path = Path.of("documentation/Class-interfaz Mismatch/ejemplo.puml");
        String code = Files.readString(path);

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(6, report.getAromas().size());

        assertContainsAroma(report, 52, "RepositorioIncompleto realiza Repositorio, pero no ofrece buscar(id : String) : Entidad (operación ausente)");
        assertContainsAroma(report, 23, "RepositorioConFirmaIncompatible realiza Repositorio, pero buscar(id : Integer) : Entidad no coincide con buscar(id : String) : Entidad (firma incompatible)");
        assertContainsAroma(report, 29, "RepositorioConVisibilidadReducida realiza Repositorio, pero buscar(id : String) : Entidad tiene visibilidad privada y la interfaz la declara pública (visibilidad reducida)");
        assertContainsAroma(report, 35, "RepositorioConOperacionEstatica realiza Repositorio, pero buscar(id : String) : Entidad es estática y la interfaz requiere una operación de instancia (estaticidad incompatible)");
        assertContainsAroma(report, 41, "RepositorioConOperacionAbstracta realiza Repositorio, pero buscar(id : String) : Entidad está declarada abstracta en una clase concreta (implementación diferida)");
        assertContainsAroma(report, 47, "RepositorioConRetornoIncompatible realiza Repositorio, pero buscar(id : String) : String no tiene un retorno compatible con Entidad (retorno incompatible)");

        String formatted = new PlainTextFormatter().format(report);
        System.out.print(formatted);
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 52:"));
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 23:"));
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 29:"));
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 35:"));
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 41:"));
        assertTrue(formatted.contains("[ClassInterfaceMismatchSniffer] line 47:"));
    }

    @Test
    public void testContratoCumplidoHueleBien() {
        String code = """
                @startuml
                class Repositorio <<interface>> {
                  +buscar(id : String) : Entidad
                }
                class RepositorioMemoria {
                  +buscar(id : String) : Entidad
                  +limpiar() : void
                }
                Repositorio <|.. RepositorioMemoria
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertEquals(0, report.getAromas().size());
        assertEquals("The diagram smells good.\n", new PlainTextFormatter().format(report));
    }

    @Test
    public void testAceptaOperacionHeredadaYNombreDeParametroDistinto() {
        String code = """
                @startuml
                class Repositorio <<interface>> {
                  +buscar(id : String) : Entidad
                }
                class RepositorioBase {
                  +buscar(codigo : String) : Entidad
                }
                class RepositorioMemoria
                RepositorioBase <|-- RepositorioMemoria
                Repositorio <|.. RepositorioMemoria
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertEquals(0, report.getAromas().size());
    }

    @Test
    public void testClaseAbstractaPuedeDiferirLaImplementacion() {
        String code = """
                @startuml
                class Repositorio <<interface>> {
                  +buscar(id : String) : Entidad
                }
                abstract class RepositorioBase
                Repositorio <|.. RepositorioBase
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertEquals(0, report.getAromas().size());
    }

    @Test
    public void testAceptaRetornoDeTipoSubtipo() {
        String code = """
                @startuml
                class Animal
                class Perro
                Animal <|-- Perro
                class Fabrica <<interface>> {
                  +crear() : Animal
                }
                class FabricaDePerros {
                  +crear() : Perro
                }
                Fabrica <|.. FabricaDePerros
                @enduml
                """;

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertEquals(0, report.getAromas().size());
    }

    private void assertContainsAroma(AromaReport report, int line, String message) {
        assertTrue(report.getAromas().stream().anyMatch(aroma ->
                ClassInterfaceMismatchSniffer.equals(aroma.name())
                        && line == aroma.line()
                        && message.equals(aroma.message())),
                "No se encontró el aroma esperado en la línea " + line + ": " + message);
    }
}
