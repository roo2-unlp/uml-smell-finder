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

    private static String mensaje(String clase) {
        return clase + " es una Data Class: expone sus datos y no tiene comportamiento.";
    }

    /** Verifica que se reporten exactamente esas clases, en ese orden. */
    private void assertDataClasses(String code, String... clases) {
        assertEquals(Stream.of(clases).map(DataClassSnifferTest::mensaje).toList(),
                finder.sniff(code).getAromas().stream().map(Aroma::message).toList());
    }

    @Test
    public void testEsUnaClase() {
        assertDataClasses("""
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
                """, "Concreta", "Abstracta");
    }

    @Test
    public void testTieneAtributosDeInstancia() {
        assertDataClasses("""
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
                """, "ConAtributoDeInstancia");
    }

    @Test
    public void testExponeSusDatos() {
        assertDataClasses("""
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
                @enduml
                """, "AccessorPublico", "AccessorProtegido", "AccessorDePaquete", "AccessorSinVisibilidad",
                "AtributoPublico");
    }

    @Test
    public void testNoTieneComportamiento() {
        assertDataClasses("""
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
                class ConAtributoPublicoStatic {
                    +{static} version : String
                    -dato : int
                }
                @enduml
                """, "SoloAccessors", "ConMetodoStatic");
    }

    @Test
    public void testDefinicionDeAccessor() {
        assertDataClasses("""
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
                """, "Getter", "GetterBooleano", "Setter");
    }

    @Test
    public void testDefinicionDeConstructor() {
        assertDataClasses("""
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
                """, "ConConstructor");
    }

    @Test
    public void testMiembrosDentroYAfueraDelCuerpo() {
        assertDataClasses("""
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
                """, "AccessorAfuera", "AtributoAfuera");
    }

    @Test
    public void testAtributosPrivadosSinMetodosNoSeReporta() {
        assertDataClasses("""
                @startuml
                class Materia {
                    -codigo : String
                }
                @enduml
                """);
    }

    @Test
    public void testUnAromaPorCadaDataClassConLaLineaDeLaClase() {
        AromaReport report = finder.sniff("""
                @startuml
                class Alumno {
                    -dato : int
                }
                Alumno : +getDato() : int

                class Direccion {
                    +calle : String
                }
                @enduml
                """);

        assertEquals(List.of(
                new Aroma("DataClassSniffer", mensaje("Alumno"), 2),
                new Aroma("DataClassSniffer", mensaje("Direccion"), 7)), report.getAromas());
    }

    @Test
    public void testSinDataClassesHueleBien() {
        AromaReport report = finder.sniff("""
                @startuml
                class Curso {
                    -nombre : String
                    +cerrar()
                }
                @enduml
                """);

        assertEquals("The diagram smells good.\n", new PlainTextFormatter().format(report));
    }

    @Test
    public void testEjemploDeLaDocumentacion() throws IOException {
        String code = Files.readString(Path.of("documentation/DataClassSniffer/ejemplo.puml"));

        AromaReport report = new SmellFinder<>(new PlantUml(), UmlSmellFinder.catalog().sniffers("DataClassSniffer"))
                .sniff(code);

        assertEquals("""
                [DataClassSniffer] line 5: Alumno es una Data Class: expone sus datos y no tiene comportamiento.
                [DataClassSniffer] line 15: Direccion es una Data Class: expone sus datos y no tiene comportamiento.
                [DataClassSniffer] line 32: Persona es una Data Class: expone sus datos y no tiene comportamiento.
                """, new PlainTextFormatter().format(report));
    }
}
