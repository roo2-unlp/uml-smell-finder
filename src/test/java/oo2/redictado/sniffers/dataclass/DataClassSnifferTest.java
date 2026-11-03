package oo2.redictado.sniffers.dataclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.UmlSmellFinder;
import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.PlainTextFormatter;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

/**
 * Un test por cada punto de documentation/DataClassSniffer/DataClassSniffer.md.
 */
public class DataClassSnifferTest {

    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new DataClassSniffer());
    }

    private String mensaje(String clase) {
        return clase + " es una Data Class: expone sus datos y no tiene comportamiento.";
    }

    private void assertDataClasses(String codePlantUML, String... clases) {
        List<String> loQueEspero = Stream.of(clases)
                .map(clase -> this.mensaje(clase))
                .toList();

        List<String> loQueEncuentro = finder.sniff(codePlantUML)
                .getAromas()
                .stream()
                .map(aroma -> aroma.message())
                .toList();

        assertEquals(loQueEspero, loQueEncuentro);
    }

    @Test
    public void testEsUnaClase() {
        String codePlantUML = """
                @startuml
                class Concreta {
                    +dato : int
                }
                abstract class Abstracta {
                    +dato : int
                }
                interface Interfaz {
                    +dato : int
                }
                enum Enumeracion {
                    +dato : int
                }
                entity Entidad {
                    +dato : int
                }
                struct Estructura {
                    +dato : int
                }
                exception Excepcion {
                    +dato : int
                }
                annotation Anotacion {
                    +dato : int
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "Concreta", "Abstracta");
    }

    @Test
    public void testTieneAtributosDeInstancia() {
        String codePlantUML = """
                @startuml
                class ConAtributoDeInstancia {
                    -dato : int
                    +getDato() : int
                }
                class SinAtributos {
                    +getDato() : int
                }
                class SoloAtributosStatic {
                    - {static} dato : int
                    +getDato() : int
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "ConAtributoDeInstancia");
    }

    @Test
    public void testExponeSusDatos() {
        String codePlantUML = """
                @startuml
                class AccessorPublico {
                    -dato : int
                    +getDato() : int
                }
                class AccessorProtegido {
                    -dato : int
                    # getDato() : int
                }
                class AccessorDePaquete {
                    -dato : int
                    ~getDato() : int
                }
                class AccessorSinVisibilidad {
                    -dato : int
                    getDato() : int
                }
                class AtributoPublico {
                    +dato : int
                }
                class AccessorPrivado {
                    -dato : int
                    -getDato() : int
                }
                class AtributoProtegido {
                    # dato : int
                }
                class AtributoDePaquete {
                    ~dato : int
                }
                class AtributoSinVisibilidad {
                    dato : int
                }
                class ConAtributoPublicoStatic {
                    +{static} version : String
                    -dato : int
                }
                class GetterStatic {
                    -dato : int
                    +{static} getDato() : int
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "AccessorPublico", "AccessorProtegido", "AccessorDePaquete",
                "AccessorSinVisibilidad", "AtributoPublico");
    }

    @Test
    public void testNoTieneComportamiento() {
        String codePlantUML = """
                @startuml
                class SoloAccessors {
                    -dato : int
                    +getDato() : int
                    +setDato(dato : int)
                }
                class ConMetodoStatic {
                    +dato : int
                    +{static} crear() : ConMetodoStatic
                }
                class ConComportamiento {
                    -dato : int
                    +getDato() : int
                    +calcular()
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "SoloAccessors", "ConMetodoStatic");
    }

    @Test
    public void testDefinicionDeAccessor() {
        String codePlantUML = """
                @startuml
                class Getter {
                    -dato : int
                    +getDato() : int
                }
                class GetterBooleano {
                    -dato : boolean
                    +isDato() : boolean
                }
                class Setter {
                    -dato : int
                    +setDato(dato : int)
                }
                class GetConParametro {
                    +dato : int
                    +getDato(formato : String) : int
                }
                class IsConParametro {
                    +dato : boolean
                    +isDato(fecha : Date) : boolean
                }
                class SetSinParametro {
                    +dato : int
                    +setDato()
                }
                class SetConDosParametros {
                    +dato : int
                    +setDato(uno : int, otro : int)
                }
                class SinMayusculaDespuesDelPrefijo {
                    +dato : int
                    +getter() : int
                    +issue() : boolean
                    +setup(dato : int)
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "Getter", "GetterBooleano", "Setter");
    }

    @Test
    public void testDefinicionDeConstructor() {
        String codePlantUML = """
                @startuml
                class ConConstructor {
                    +dato : int
                    +ConConstructor(dato : int)
                }
                class ConMetodoDeOtroNombre {
                    +dato : int
                    +OtraClase(dato : int)
                }
                class SoloConstructorNoExpone {
                    -dato : int
                    +SoloConstructorNoExpone(dato : int)
                }
                @enduml
                """;

        assertDataClasses(codePlantUML, "ConConstructor");
    }

    @Test
    public void testMiembrosDentroYAfueraDelCuerpo() {
        String codePlantUML = """
                @startuml
                class AccessorAfuera {
                    -dato : int
                }
                AccessorAfuera : +getDato() : int
                class AtributoAfuera
                AtributoAfuera : +dato : int
                class ComportamientoAfuera {
                    +dato : int
                }
                ComportamientoAfuera : +calcular()
                @enduml
                """;

        assertDataClasses(codePlantUML, "AccessorAfuera", "AtributoAfuera");
    }

    @Test
    public void testAtributosPrivadosSinMetodosNoSeReporta() {
        String codePlantUML = """
                @startuml
                class Materia {
                    -codigo : String
                }
                @enduml
                """;

        assertDataClasses(codePlantUML);
    }

    @Test
    public void testUnAromaPorCadaDataClassConLaLineaDeLaClase() {
        String codePlantUML = """
                @startuml
                class Alumno {
                    -dato : int
                }
                Alumno : +getDato() : int

                class Direccion {
                    +calle : String
                }
                @enduml
                """;

        List<Aroma> loQueEspero = List.of(
                new Aroma("DataClassSniffer", this.mensaje("Alumno"), 2),
                new Aroma("DataClassSniffer", this.mensaje("Direccion"), 7));

        List<Aroma> loQueEncuentro = finder.sniff(codePlantUML).getAromas();

        assertEquals(loQueEspero, loQueEncuentro);
    }

    @Test
    public void testSinDataClassesHueleBien() {
        String codePlantUML = """
                @startuml
                class Curso {
                    -nombre : String
                    +cerrar()
                }
                @enduml
                """;

        AromaReport report = finder.sniff(codePlantUML);

        assertEquals("The diagram smells good.\n", new PlainTextFormatter().format(report));
    }

    @Test
    public void testEjemploDeLaDocumentacion() throws IOException {
        String codePlantUML = Files.readString(Path.of("documentation/DataClassSniffer/ejemplo.puml"));

        SmellFinder<DiagramContext> finderDelCatalogo = new SmellFinder<>(new PlantUml(),
                UmlSmellFinder.catalog().sniffers("DataClassSniffer"));

        String loQueEspero = """
                [DataClassSniffer] line 5: Alumno es una Data Class: expone sus datos y no tiene comportamiento.
                [DataClassSniffer] line 15: Direccion es una Data Class: expone sus datos y no tiene comportamiento.
                [DataClassSniffer] line 32: Persona es una Data Class: expone sus datos y no tiene comportamiento.
                """;

        String loQueEncuentro = new PlainTextFormatter().format(finderDelCatalogo.sniff(codePlantUML));

        assertEquals(loQueEspero, loQueEncuentro);
    }
}
