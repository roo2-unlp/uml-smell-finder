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
    public void testSinVisibilidadEsPublicoYSinRetornoEsVoid() {
        Metodo omitido = new Metodo(null, "notificar", List.of(), null, true, false);


        assertEquals("+notificar():void", omitido.toString());
        assertTrue(concreto("+", "notificar", List.of(), "void").implementa(omitido));
    }


    @Test
    public void testIgnoraLosEspaciosDeLosTipos() {
        Metodo conEspacios = concreto("+", "calcular", List.of("int", " Real "), " Real");


        assertTrue(conEspacios.implementa(abstracto));
        assertEquals("+calcular(int,Real):Real", abstracto.toString());
    }
}



