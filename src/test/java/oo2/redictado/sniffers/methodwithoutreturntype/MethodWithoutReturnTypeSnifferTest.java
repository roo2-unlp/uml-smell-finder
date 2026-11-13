package oo2.redictado.sniffers.methodwithoutreturntype;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class MethodWithoutReturnTypeSnifferTest {
    static final List<String> KINDS = List.of("class", "interface", "abstract");
    static final List<String> OTHER_KINDS = List.of("enum", "entity", "annotation", "exception",
            "metaclass", "stereotype", "protocol", "struct");

    static final List<String> WITHOUT_RETURN_TYPE_BODY = List.of(
            "+ method()", "- method()", "# method()", "method()",
            "+ calcular(int a, int b)", "+ setX(x : int)", "+ setY(y)",
            "+ {abstract} validar()", "+ {static} crear()");
    static final List<String> WITHOUT_RETURN_TYPE_EXTERNAL = List.of(
            "+ method()", "methodo()");
    static final List<String> WITH_RETURN_TYPE_OR_VARIABLE_BODY = List.of(
            "+ int method()", "+ method() : int", "+ void guardar(String ruta)",
            "+ method() : void", "+ List<String> listar()", "+ listar() : List<String>",
            "+ int[] ids()", "+ nombre : String", "+ nombre", "nombre");
    static final List<String> WITH_RETURN_TYPE_EXTERNAL = List.of(
            "+ method() : int", "+ int method()");

    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new MethodWithoutReturnTypeSniffer());
    }

    static Stream<Arguments> combine(List<String> kinds, List<String> members) {
        return kinds.stream().flatMap(k -> members.stream().map(m -> Arguments.of(k, m)));
    }

    static Stream<Arguments> withoutReturnTypeBody()     { return combine(KINDS, WITHOUT_RETURN_TYPE_BODY); }
    static Stream<Arguments> withoutReturnTypeExternal() { return combine(KINDS, WITHOUT_RETURN_TYPE_EXTERNAL); }
    static Stream<Arguments> withReturnTypeBody()        { return combine(KINDS, WITH_RETURN_TYPE_OR_VARIABLE_BODY); }
    static Stream<Arguments> withReturnTypeExternal()    { return combine(KINDS, WITH_RETURN_TYPE_EXTERNAL); }
    static Stream<Arguments> otherKindsBody()            { return combine(OTHER_KINDS, List.of("+metodo()")); }

    private String inBody(String kind, String member) {
        return """
               @startuml
               %s Prueba {
                   %s
               }
               @enduml
               """.formatted(kind, member);
    }

    private String external(String kind, String member) {
        return """
               @startuml
               %s Prueba
               Prueba : %s
               @enduml
               """.formatted(kind, member);
    }

    @ParameterizedTest(name = "{0} con ''{1}'' en el cuerpo")
    @MethodSource("withoutReturnTypeBody")
    public void testMethodWithoutReturnTypeInBody(String kind, String member) {
        AromaReport report = finder.sniff(inBody(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("MethodWithoutReturnTypeSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("withoutReturnTypeExternal")
    public void testMethodWithoutReturnTypeExternal(String kind, String member) {
        AromaReport report = finder.sniff(external(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("MethodWithoutReturnTypeSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' en el cuerpo")
    @MethodSource("withReturnTypeBody")
    public void testNoMethodWithoutReturnTypeInBody(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("withReturnTypeExternal")
    public void testNoMethodWithoutReturnTypeExternal(String kind, String member) {
        assertFalse(finder.sniff(external(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' no cuenta")
    @MethodSource("otherKindsBody")
    public void testOtherKindsAreIgnored(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }
}
