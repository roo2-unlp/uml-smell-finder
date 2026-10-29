package ar.edu.unlp.info.roo2.interfacecounter;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterfaceCounterTest {
    private final InterfaceCounter counter = new InterfaceCounter();

    @Test
    void cuentaInterfacesYClasesYExtraeSusMetodos() {
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

        InterfaceCounter.Result result = counter.count(puml);

        assertEquals(2, result.interfaceCount());
        assertEquals(Set.of("Repositorio", "Autenticable"), result.interfaceNames());

        List<String> repoMethods = result.methodsOf("Repositorio");
        assertEquals(2, repoMethods.size());
        assertEquals("+ guardar(entidad : Object) : void", repoMethods.get(0));
        assertEquals("+ buscarPorId(id : int) : Object", repoMethods.get(1));

        assertTrue(result.methodsOf("Autenticable").isEmpty());

        assertEquals(2, result.classCount());
        assertEquals(Set.of("Usuario", "BaseEntity"), result.classNames());
        assertEquals(List.of("+ getNombre() : String"), result.methodsOf("Usuario"));
        assertTrue(result.methodsOf("BaseEntity").isEmpty());
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

        InterfaceCounter.Result result = counter.count(puml);

        assertEquals(1, result.interfaceCount());
        assertEquals(
                List.of("+ inicializar() : void", "+ enviar(mensaje : String) : boolean"),
                result.methodsOf("Notificador")
        );
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

        InterfaceCounter.Result result = counter.count(puml);

        assertEquals(1, result.interfaceCount());
        assertEquals(0, result.classCount());
        assertEquals(Set.of("Servicio"), result.interfaceNames());
        assertEquals(List.of("+ ejecutar() : void", "+ detener() : void"), result.methodsOf("Servicio"));
    }

    @Test
    void retornaCeroSiNoHayInterfaces() {
        String puml = """
                @startuml
                class Cliente
                class Factura
                @enduml
                """;

        InterfaceCounter.Result result = counter.count(puml);

        assertEquals(0, result.interfaceCount());
        assertTrue(result.interfaceNames().isEmpty());
        assertEquals(2, result.classCount());
        assertEquals(Set.of("Cliente", "Factura"), result.classNames());
    }
}
