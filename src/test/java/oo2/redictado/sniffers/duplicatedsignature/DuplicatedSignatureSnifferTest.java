package oo2.redictado.sniffers.duplicatedsignature;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.UmlSmellFinder;
import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class DuplicatedSignatureSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new DuplicatedSignatureSniffer());
    }

    // Casos que SE reportan

    @Test
    public void testReportaDosSubclasesConLaMismaFirma() {
        String code = """
                @startuml
                class Vehiculo {
                    +getPatente() : String
                }
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("DuplicatedSignatureSniffer", aroma.name());
        assertEquals("Firma duplicada: arrancarMotor() : void en Auto y Moto, y no está en Vehiculo",
                aroma.message());
        assertEquals(6, aroma.line());
    }

    @Test
    public void testReportaAunqueCambienLosNombresDeLosParametros() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +acelerar(kmh : Integer) : void
                }
                class Moto extends Vehiculo {
                    +acelerar(velocidad : Integer) : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertTrue(report.getAromas().get(0).message().contains("acelerar(Integer) : void"));
    }

    @Test
    public void testReportaAunqueCambieLaVisibilidad() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    -arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaConEstiloJavaYEstiloUml() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +void acelerar(kmh : Integer)
                }
                class Moto extends Vehiculo {
                    +acelerar(Integer kmh) : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaSinTipoDeRetornoComoVoid() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor()
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaDosDeTresSubclases() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Bicicleta extends Vehiculo {
                    +pedalear() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        String message = report.getAromas().get(0).message();
        assertTrue(message.contains("Auto"));
        assertTrue(message.contains("Moto"));
        assertFalse(message.contains("Bicicleta"));
    }

    @Test
    public void testReportaConHerenciaPorFlechasYSuperclaseNoDeclarada() {
        String code = """
                @startuml
                Vehiculo <|-- Auto
                Moto --|> Vehiculo
                class Auto {
                    +arrancarMotor() : void
                }
                class Moto {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaMetodosDeclaradosFueraDeLaClase() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo
                class Moto extends Vehiculo
                Auto : +arrancarMotor() : void
                Moto : +arrancarMotor() : void
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaEnUnNivelInferiorDeLaJerarquia() {
        String code = """
                @startuml
                class Vehiculo
                class Bicicleta extends Vehiculo
                class Motorizado extends Vehiculo
                class Auto extends Motorizado {
                    +arrancarMotor() : void
                }
                class Moto extends Motorizado {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertTrue(report.getAromas().get(0).message().contains("no está en Motorizado"));
    }

    @Test
    public void testReportaAunqueLoDeclareUnaSuperclaseIndirecta() {
        // Consecuencia de analizar solo la superclase directa
        String code = """
                @startuml
                class Vehiculo {
                    +avanzar() : void
                }
                class Terrestre extends Vehiculo
                class Auto extends Terrestre {
                    +avanzar() : void
                }
                class Moto extends Terrestre {
                    +avanzar() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
    }

    @Test
    public void testReportaUnAromaPorCadaFirmaDuplicada() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                    +getVelocidad() : Integer
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                    +getVelocidad() : Integer
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(2, report.getAromas().size());
    }

    @Test
    public void testReportaTresSubclasesEnUnSoloAroma() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Camion extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals("Firma duplicada: arrancarMotor() : void en Auto, Moto y Camion, y no está en Vehiculo",
                report.getAromas().get(0).message());
    }

    // Casos que NO se reportan

    @Test
    public void testNoReportaSiLaSuperclaseDeclaraElMetodo() {
        String code = """
                @startuml
                class Vehiculo {
                    +arrancarMotor() : void
                }
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaSiEsAbstractoEnLaSuperclase() {
        String code = """
                @startuml
                abstract class Vehiculo {
                    +{abstract} arrancarMotor() : void
                }
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                class Moto extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaSinHerencia() {
        String code = """
                @startuml
                class Auto {
                    +arrancarMotor() : void
                }
                class Moto {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConDistintosTiposDeParametros() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +acelerar(kmh : Integer) : void
                }
                class Moto extends Vehiculo {
                    +acelerar(kmh : Real) : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConDistintaCantidadDeParametros() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +acelerar(kmh : Integer) : void
                }
                class Moto extends Vehiculo {
                    +acelerar(kmh : Integer, marcha : Integer) : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConDistintoOrdenDeParametros() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +cargar(litros : Real, tanque : Integer) : void
                }
                class Moto extends Vehiculo {
                    +cargar(tanque : Integer, litros : Real) : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConDistintoTipoDeRetorno() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +getVelocidad() : Integer
                }
                class Moto extends Vehiculo {
                    +getVelocidad() : Real
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConSuperclasesDistintas() {
        String code = """
                @startuml
                class Vehiculo
                class Transporte
                class Auto extends Vehiculo {
                    +avanzar() : void
                }
                class Bicicleta extends Transporte {
                    +avanzar() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaConUnaSolaSubclase() {
        String code = """
                @startuml
                class Vehiculo
                class Auto extends Vehiculo {
                    +arrancarMotor() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaPorImplementarLaMismaInterfaz() {
        String code = """
                @startuml
                interface Conducible
                class Auto implements Conducible {
                    +arrancarMotor() : void
                }
                class Moto {
                    +arrancarMotor() : void
                }
                Moto ..|> Conducible
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaClasesConHerenciaMultiple() {
        // Solo herencia simple: una clase con más de una superclase no se analiza
        String code = """
                @startuml
                class Avion
                class Barco
                class Hidroavion extends Avion, Barco {
                    +despegar() : void
                }
                class Jet extends Avion {
                    +despegar() : void
                }
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testNoReportaDiagramaSinClases() {
        String code = """
                @startuml
                @enduml
                """;
        assertFalse(finder.sniff(code).stinks());
    }
}
