package oo2.redictado.sniffers.unimplementedAbstractMethod;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.UmlSmellFinder;
import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class UnimplementedAbstractMethodSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new UnimplementedAbstractMethodSniffer());
    }

    /**
     * Verifica que haya un solo aroma, que nombre a la clase culpable y que no
     * nombre a las inocentes.
     */
    private Aroma assertReportaSolo(String code, String culpable, String... inocentes) {
        AromaReport report = finder.sniff(code);
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("UnimplementedAbstractMethodSniffer", aroma.name());
        assertTrue(aroma.message().contains(culpable), aroma.message());
        for (String inocente : inocentes) {
            assertFalse(aroma.message().contains(inocente), aroma.message());
        }
        return aroma;
    }

    /**
     * Verifica que haya un aroma por cada clase culpable y que ningún aroma
     * nombre a las inocentes.
     */
    private void assertReporta(String code, List<String> culpables, String... inocentes) {
        AromaReport report = finder.sniff(code);
        assertEquals(culpables.size(), report.getAromas().size(), () -> report.getAromas().toString());
        for (String culpable : culpables) {
            assertEquals(1, report.getAromas().stream().filter(aroma -> aroma.message().contains(culpable)).count(),
                    () -> culpable + " en " + report.getAromas());
        }
        for (Aroma aroma : report.getAromas()) {
            assertEquals("UnimplementedAbstractMethodSniffer", aroma.name());
            for (String inocente : inocentes) {
                assertFalse(aroma.message().contains(inocente), aroma.message());
            }
        }
    }

    private void assertNoReporta(String code) {
        AromaReport report = finder.sniff(code);
        assertFalse(report.stinks(), () -> report.getAromas().toString());
    }

    // A. Implementaciones correctas

    @Test
    public void testCaso1_AmbasHijasImplementan() {
        assertNoReporta("""
                @startuml
                abstract class Cliente {
                    +{abstract} calcularPrecioCuota(): Real
                }
                class ClienteNormal {
                    +calcularPrecioCuota(): Real
                }
                class ClientePremium {
                    +calcularPrecioCuota(): Real
                }
                Cliente <|-- ClienteNormal
                Cliente <|-- ClientePremium
                @enduml
                """);
    }

    @Test
    public void testCaso2_NombresDeParametrosDistintos() {
        assertNoReporta("""
                @startuml
                abstract class Cliente {
                    +{abstract} calcularPrecioCuota(a: int): Real
                }
                class ClienteNormal {
                    +calcularPrecioCuota(adelanto: int): Real
                }
                class ClientePremium {
                    +calcularPrecioCuota(ade:int): Real
                }
                Cliente <|-- ClienteNormal
                Cliente <|-- ClientePremium
                @enduml
                """);
    }

    @Test
    public void testCaso3_HijaConAtributosQueImplementa() {
        assertNoReporta("""
                @startuml
                abstract class Figura {
                    +{abstract} calcularArea(): Real
                }
                class Circulo {
                    -radio : Real
                    +calcularArea(): Real
                }
                class Cuadrado {
                    -lado : Real
                    +calcularArea(): Real
                }
                Figura <|-- Circulo
                Figura <|-- Cuadrado
                @enduml
                """);
    }

    // B. No implementa

    @Test
    public void testCaso4_NoImplementaElMetodo() {
        Aroma aroma = assertReportaSolo("""
                @startuml
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                class Empleado {
                    +getSueldo(): Real
                }
                class Profesor {
                    +getNombre(): String
                    +getBono(): Real
                }
                Persona <|-- Empleado
                Persona <|-- Profesor
                @enduml
                """, "Empleado", "Profesor");
        assertTrue(aroma.message().contains("getNombre"));
    }

    @Test
    public void testCaso5_ImplementaSoloUnoDeDos() {
        Aroma aroma = assertReportaSolo("""
                @startuml
                abstract class Vehiculo {
                    +{abstract} getKilometros(): Real
                    +{abstract} calcularConsumoxKM(): Real
                }
                class Auto {
                    +getKilometros(): Real
                    +calcularConsumoxKM(): Real
                }
                class Camion {
                    +getKilometros(): Real
                    +getPeso(): Real
                }
                Vehiculo <|-- Auto
                Vehiculo <|-- Camion
                @enduml
                """, "Camion", "Auto");
        assertTrue(aroma.message().contains("calcularConsumoxKM"));
        assertFalse(aroma.message().contains("getKilometros"));
    }

    @Test
    public void testCaso6_SuperclaseConcretaConMetodoAbstracto() {
        assertReportaSolo("""
                @startuml
                class Descuento {
                    +{abstract} calcular(monto: Real): Real
                    +describir(): String
                }
                class DescuentoNavidad {
                    +describir(): String
                }
                class DescuentoBlackFriday {
                    +calcular(monto: Real): Real
                }
                Descuento <|-- DescuentoNavidad
                Descuento <|-- DescuentoBlackFriday
                @enduml
                """, "DescuentoNavidad", "DescuentoBlackFriday");
    }

    // C. Firma distinta

    @Test
    public void testCaso7_TipoDeParametroDistinto() {
        assertReportaSolo("""
                @startuml
                abstract class Vehiculo {
                    +{abstract} calcularPrecioEnvio(destino: String): Real
                }
                class Auto {
                    +calcularPrecioEnvio(destino: Real): Real
                }
                class Camion {
                    +calcularPrecioEnvio(destino: String): Real
                }
                Vehiculo <|-- Auto
                Vehiculo <|-- Camion
                @enduml
                """, "Auto", "Camion");
    }

    @Test
    public void testCaso8_OrdenDeParametrosDistinto() {
        assertReportaSolo("""
                @startuml
                abstract class Cliente {
                    +{abstract} calcularPrecioCuota(a: int, d: Real): Real
                }
                class ClienteNormal {
                    +calcularPrecioCuota(deuda: Real, adelanto:int): Real
                }
                class ClientePremium {
                    +calcularPrecioCuota(ade:int, deuda: Real): Real
                }
                Cliente <|-- ClienteNormal
                Cliente <|-- ClientePremium
                @enduml
                """, "ClienteNormal", "ClientePremium");
    }

    @Test
    public void testCaso9_CantidadDeParametrosDistinta() {
        assertReportaSolo("""
                @startuml
                abstract class Figura {
                    +{abstract} crearFigura(largo: Real, ancho: Real): void
                }
                class Circulo {
                    +crearFigura(radio: Real): void
                }
                class Cuadrado {
                    +crearFigura(largo: Real, ancho: Real): void
                }
                Figura <|-- Circulo
                Figura <|-- Cuadrado
                @enduml
                """, "Circulo", "Cuadrado");
    }

    @Test
    public void testCaso10_TipoDeRetornoDistinto() {
        assertReportaSolo("""
                @startuml
                abstract class Cliente {
                    +{abstract} calcularPrecioCuota(): Real
                }
                class ClienteNormal {
                    +calcularPrecioCuota(): String
                }
                class ClientePremium {
                    +calcularPrecioCuota(): Real
                }
                Cliente <|-- ClienteNormal
                Cliente <|-- ClientePremium
                @enduml
                """, "ClienteNormal", "ClientePremium");
    }

    // Un retorno no declarado solo coincide con otro no declarado: m() no es m(): void
    @Test
    public void testCaso11_RetornoOmitido() {
        assertReporta("""
                @startuml
                abstract class Alarma {
                    +{abstract} activar()
                }
                class AlarmaSonora {
                    +activar()
                }
                class AlarmaVisual {
                    +activar(): String
                }
                class AlarmaTactil {
                    +activar(): void
                }
                Alarma <|-- AlarmaSonora
                Alarma <|-- AlarmaVisual
                Alarma <|-- AlarmaTactil
                @enduml
                """, List.of("AlarmaVisual", "AlarmaTactil"), "AlarmaSonora");
    }

    // D. Visibilidad y nombre

    @Test
    public void testCaso12_VisibilidadDistinta() {
        assertReportaSolo("""
                @startuml
                abstract class Vehiculo {
                    # {abstract} getCantRuedas(): int
                }
                class Camion {
                    # getCantRuedas(): int
                }
                class Auto {
                    +getCantRuedas(): int
                }
                Vehiculo <|-- Camion
                Vehiculo <|-- Auto
                @enduml
                """, "Auto", "Camion");
    }

    // Una visibilidad no declarada solo coincide con otra no declarada: m() no es +m()
    @Test
    public void testCaso13_VisibilidadOmitida() {
        assertReporta("""
                @startuml
                abstract class Vehiculo {
                    {abstract} getCantRuedas(): int
                }
                class Moto {
                    +getCantRuedas(): int
                }
                class Triciclo {
                    # getCantRuedas(): int
                }
                class Bicicleta {
                    getCantRuedas(): int
                }
                Vehiculo <|-- Moto
                Vehiculo <|-- Triciclo
                Vehiculo <|-- Bicicleta
                @enduml
                """, List.of("Moto", "Triciclo"), "Bicicleta");
    }

    @Test
    public void testCaso14_NombreDistintoPorMayusculas() {
        assertReportaSolo("""
                @startuml
                abstract class Cuenta {
                    +{abstract} getInteres(): Real
                }
                class CuentaBancaria {
                    +GetInteres(): Real
                }
                class CuentaCorriente {
                    +getInteres(): Real
                }
                Cuenta <|-- CuentaBancaria
                Cuenta <|-- CuentaCorriente
                @enduml
                """, "CuentaBancaria", "CuentaCorriente");
    }

    // E. Clases abstractas

    @Test
    public void testCaso15_AbstractaQueNoImplementaNoSeReporta() {
        assertNoReporta("""
                @startuml
                abstract class Persona {
                    +{abstract} calcularPrecioCuota(a: int, d: Real): Real
                }
                abstract class Cliente {
                    +{abstract} calcularPrecioBono(): Real
                }
                Persona <|-- Cliente
                @enduml
                """);
    }

    @Test
    public void testCaso16_AbstractaIntermediaImplementa() {
        assertNoReporta("""
                @startuml
                abstract class Figura {
                    +{abstract} calcularArea(): Real
                }
                abstract class FiguraPlana {
                    +calcularArea(): Real
                }
                class Circulo
                class Cuadrado
                Figura <|-- FiguraPlana
                FiguraPlana <|-- Circulo
                FiguraPlana <|-- Cuadrado
                @enduml
                """);
    }

    @Test
    public void testCaso17_SuperclaseAbstractaVacia() {
        assertNoReporta("""
                @startuml
                abstract class Animal
                class Perro
                class Gato
                Animal <|-- Perro
                Animal <|-- Gato
                @enduml
                """);
    }

    // F. Limitación conocida: solo se mira la superclase directa

    @Test
    public void testCaso18_NoMiraMasAllaDelPadreDirecto() {
        assertNoReporta("""
                @startuml
                abstract class Cuenta {
                    +{abstract} getInteres(): Real
                }
                abstract class CuentaPremium
                class CuentaOro
                Cuenta <|-- CuentaPremium
                CuentaPremium <|-- CuentaOro
                @enduml
                """);
    }

    // G. Fuera del alcance: herencia múltiple (solo se analizan clases con un único padre)

    @Test
    public void testCaso19_HerenciaMultipleNoSeAnaliza() {
        assertNoReporta("""
                @startuml
                abstract class Maquina {
                    +{abstract} mover(): void
                }
                abstract class Bateria {
                    +{abstract} cargar(): void
                }
                class Robot extends Maquina, Bateria {
                    +apagar(): void
                }
                @enduml
                """);
    }

    // Formas de declarar la herencia y los métodos

    @Test
    public void testHerenciaConExtends() {
        assertReportaSolo("""
                @startuml
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                class Empleado extends Persona {
                    +getSueldo(): Real
                }
                class Profesor extends Persona {
                    +getNombre(): String
                }
                @enduml
                """, "Empleado", "Profesor");
    }

    @Test
    public void testHerenciaConFlechasEnAmbosSentidos() {
        assertReportaSolo("""
                @startuml
                Persona <|-- Empleado
                Profesor --|> Persona
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                class Empleado {
                    +getSueldo(): Real
                }
                class Profesor {
                    +getNombre(): String
                }
                @enduml
                """, "Empleado", "Profesor");
    }

    @Test
    public void testMetodosDeclaradosFueraDeLaClase() {
        assertReportaSolo("""
                @startuml
                abstract class Persona
                class Empleado
                class Profesor
                Persona : +{abstract} getNombre(): String
                Profesor : +getNombre(): String
                Persona <|-- Empleado
                Persona <|-- Profesor
                @enduml
                """, "Empleado", "Profesor");
    }

    @Test
    public void testMetodoEstaticoNoImplementa() {
        assertReportaSolo("""
                @startuml
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                class Empleado {
                    +{static} getNombre(): String
                }
                class Profesor {
                    +getNombre(): String
                }
                Persona <|-- Empleado
                Persona <|-- Profesor
                @enduml
                """, "Empleado", "Profesor");
    }

    @Test
    public void testMetodoEstaticoAbstractoNoEsObligacion() {
        assertNoReporta("""
                @startuml
                abstract class Persona {
                    +{static} {abstract} crear(): Persona
                }
                class Empleado
                Persona <|-- Empleado
                @enduml
                """);
    }

    @Test
    public void testUnAromaPorClaseConTodasLasFirmasFaltantes() {
        Aroma aroma = assertReportaSolo("""
                @startuml
                abstract class Vehiculo {
                    +{abstract} getKilometros(): Real
                    +{abstract} calcularConsumoxKM(): Real
                }
                class Camion
                Vehiculo <|-- Camion
                @enduml
                """, "Camion");
        assertTrue(aroma.message().contains("getKilometros"));
        assertTrue(aroma.message().contains("calcularConsumoxKM"));
    }

    @Test
    public void testElAromaMarcaLaLineaDeLaClaseCulpable() {
        Aroma aroma = assertReportaSolo("""
                @startuml
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                class Profesor extends Persona {
                    +getNombre(): String
                }
                class Empleado extends Persona {
                    +getSueldo(): Real
                }
                @enduml
                """, "Empleado", "Profesor");
        assertEquals(8, aroma.line());
    }

    // Fuera del alcance

    @Test
    public void testIgnoraRealizacionDeInterfaces() {
        assertNoReporta("""
                @startuml
                interface Imprimible {
                    +{abstract} imprimir(): void
                }
                class Factura
                Factura ..|> Imprimible
                Imprimible <|.. Factura
                @enduml
                """);
    }

    @Test
    public void testIgnoraInterfacesAunqueUsenFlechaDeHerencia() {
        assertNoReporta("""
                @startuml
                interface Imprimible {
                    +{abstract} imprimir(): void
                }
                class Factura
                Imprimible <|-- Factura
                @enduml
                """);
    }

    @Test
    public void testIgnoraClasesConMasDeUnPadre() {
        assertNoReporta("""
                @startuml
                abstract class Persona {
                    +{abstract} getNombre(): String
                }
                abstract class Trabajador {
                    +{abstract} getSueldo(): Real
                }
                class Empleado
                Persona <|-- Empleado
                Trabajador <|-- Empleado
                @enduml
                """);
    }

    @Test
    public void testSuperclaseFueraDelDiagrama() {
        assertNoReporta("""
                @startuml
                class Empleado extends Persona {
                    +getSueldo(): Real
                }
                @enduml
                """);
    }

    @Test
    public void testSyntaxError() {
        String code = "abstract class Persona { +{abstract} getNombre(): String }";
        assertThrows(IllegalArgumentException.class, () -> finder.sniff(code));
    }

    @Test
    public void testEstaRegistradoEnElCatalogo() {
        assertTrue(UmlSmellFinder.catalog().names().contains("UnimplementedAbstractMethodSniffer"));
    }
}
