package oo2.redictado.sniffers.unimplementedAbstractMethod;


import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClaseTest {
    Clase persona;
    Metodo getNombre;


    @BeforeEach
    public void setUp() {
        persona = new Clase("Persona");
        persona.marcarAbstracta();
        getNombre = new Metodo("+", "getNombre", List.of(), "String", true, false);
        persona.agregarMetodo(getNombre);
        persona.agregarMetodo(new Metodo("+", "getEdad", List.of(), "int", false, false));
    }


    @Test
    public void testMetodosAbstractos() {
        assertEquals(List.of(getNombre), persona.metodosAbstractos());
    }


    @Test
    public void testUnMetodoEstaticoNoEsObligacionAunqueSeaAbstracto() {
        persona.agregarMetodo(new Metodo("+", "crear", List.of(), "Persona", true, true));


        assertEquals(List.of(getNombre), persona.metodosAbstractos());
    }


    @Test
    public void testClaseConcretaQueNoImplementa() {
        Clase empleado = new Clase("Empleado");
        empleado.heredaDe(persona);


        assertEquals(List.of(getNombre), empleado.metodosSinImplementar());
    }


    @Test
    public void testClaseConcretaQueImplementa() {
        Clase profesor = new Clase("Profesor");
        profesor.heredaDe(persona);
        profesor.agregarMetodo(new Metodo("+", "getNombre", List.of(), "String", false, false));


        assertTrue(profesor.metodosSinImplementar().isEmpty());
    }


    @Test
    public void testClaseAbstractaNoTieneObligaciones() {
        Clase cliente = new Clase("Cliente");
        cliente.marcarAbstracta();
        cliente.heredaDe(persona);


        assertTrue(cliente.metodosSinImplementar().isEmpty());
    }


    @Test
    public void testClaseSinSuperclaseNoTieneObligaciones() {
        assertTrue(new Clase("Suelta").metodosSinImplementar().isEmpty());
    }
}
