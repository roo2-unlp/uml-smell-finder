package oo2.redictado.sniffers.unimplementedAbstractMethod;


import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


import org.junit.jupiter.api.Test;


public class MetodoTest {
    Metodo abstracto = new Metodo("+", "calcular", List.of("int", "Real"), "Real", true, false);


    private Metodo concreto(String visibilidad, String nombre, List<String> tipos, String retorno) {
        return new Metodo(visibilidad, nombre, tipos, retorno, false, false);
    }


    @Test
    public void testImplementaConLaMismaFirma() {
        assertTrue(concreto("+", "calcular", List.of("int", "Real"), "Real").implementa(abstracto));
    }


    @Test
    public void testNoImplementaSiCambiaAlgunaParteDeLaFirma() {
        assertFalse(concreto("#", "calcular", List.of("int", "Real"), "Real").implementa(abstracto));
        assertFalse(concreto("+", "Calcular", List.of("int", "Real"), "Real").implementa(abstracto));
        assertFalse(concreto("+", "calcular", List.of("Real", "int"), "Real").implementa(abstracto));
        assertFalse(concreto("+", "calcular", List.of("int"), "Real").implementa(abstracto));
        assertFalse(concreto("+", "calcular", List.of("int", "Real"), "String").implementa(abstracto));
    }


    @Test
    public void testUnMetodoAbstractoOEstaticoNoImplementa() {
        assertFalse(new Metodo("+", "calcular", List.of("int", "Real"), "Real", true, false).implementa(abstracto));
        assertFalse(new Metodo("+", "calcular", List.of("int", "Real"), "Real", false, true).implementa(abstracto));
    }


    @Test
    public void testRetornoNoDeclaradoSoloCoincideConOtroNoDeclarado() {
        Metodo sinRetorno = new Metodo("+", "activar", List.of(), null, true, false);


        assertTrue(concreto("+", "activar", List.of(), null).implementa(sinRetorno));
        assertFalse(concreto("+", "activar", List.of(), "void").implementa(sinRetorno));
        assertFalse(concreto("+", "activar", List.of(), "String").implementa(sinRetorno));
        assertFalse(concreto("+", "calcular", List.of("int", "Real"), null).implementa(abstracto));
    }


    @Test
    public void testVisibilidadNoDeclaradaSoloCoincideConOtraNoDeclarada() {
        Metodo sinVisibilidad = new Metodo(null, "getCantRuedas", List.of(), "int", true, false);


        assertTrue(concreto(null, "getCantRuedas", List.of(), "int").implementa(sinVisibilidad));
        assertFalse(concreto("+", "getCantRuedas", List.of(), "int").implementa(sinVisibilidad));
        assertFalse(concreto("#", "getCantRuedas", List.of(), "int").implementa(sinVisibilidad));
        assertFalse(concreto(null, "calcular", List.of("int", "Real"), "Real").implementa(abstracto));
    }


    @Test
    public void testLaFirmaMuestraSoloLoDeclarado() {
        assertEquals("activar()", new Metodo(null, "activar", List.of(), null, true, false).toString());
        assertEquals("activar()", new Metodo("  ", "activar", List.of(), "", true, false).toString());
        assertEquals("+activar():void", new Metodo("+", "activar", List.of(), "void", true, false).toString());
    }


    @Test
    public void testIgnoraLosEspaciosDeLosTipos() {
        Metodo conEspacios = concreto("+", "calcular", List.of("int", " Real "), " Real");


        assertTrue(conEspacios.implementa(abstracto));
        assertEquals("+calcular(int,Real):Real", abstracto.toString());
    }
}



