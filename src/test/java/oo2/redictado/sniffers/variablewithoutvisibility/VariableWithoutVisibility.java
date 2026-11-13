package oo2.redictado.sniffers.variablewithoutvisibility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class VariableWithoutVisibilitySnifferTest {
    static final List<String> KINDS = List.of("class", "interface", "abstract", "abstract class");
    static final List<String> OTHER_KINDS = List.of("enum", "entity", "annotation", "exception",
            "metaclass", "stereotype", "protocol", "struct");

    static final List<String> NO_VISIBILITY_BODY = List.of(
            "nombre : String", "String data", "saldo",
            "{static} contador : int", "lista : List<String>", "ids : int[]");
    static final List<String> NO_VISIBILITY_EXTERNAL = List.of(
            "alias : String", "alias", "String alias");
    static final List<String> WITH_VISIBILITY_OR_METHOD_BODY = List.of(
            "+ x : int", "- x : int", "# x : int", "+ {static} x : int",
            "calcular()", "calcular() : double", "int calcular()", "+ getX() : int");
    static final List<String> WITH_VISIBILITY_OR_METHOD_EXTERNAL = List.of(
            "+ alias : String", "- alias", "calcular()");

    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new VariableWithoutVisibilitySniffer());
    }

    static Stream<Arguments> combine(List<String> kinds, List<String> members) {
        return kinds.stream().flatMap(k -> members.stream().map(m -> Arguments.of(k, m)));
    }

    static Stream<Arguments> noVisibilityBody()        { return combine(KINDS, NO_VISIBILITY_BODY); }
    static Stream<Arguments> noVisibilityExternal()    { return combine(KINDS, NO_VISIBILITY_EXTERNAL); }
    static Stream<Arguments> withVisibilityBody()      { return combine(KINDS, WITH_VISIBILITY_OR_METHOD_BODY); }
    static Stream<Arguments> withVisibilityExternal()  { return combine(KINDS, WITH_VISIBILITY_OR_METHOD_EXTERNAL); }
    static Stream<Arguments> otherKindsBody()          { return combine(OTHER_KINDS, List.of("nombre : String")); }

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
    @MethodSource("noVisibilityBody")
    public void testVariableWithoutVisibilityInBody(String kind, String member) {
        AromaReport report = finder.sniff(inBody(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("VariableWithoutVisibilitySniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("noVisibilityExternal")
    public void testVariableWithoutVisibilityExternal(String kind, String member) {
        AromaReport report = finder.sniff(external(kind, member));

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' en el cuerpo")
    @MethodSource("withVisibilityBody")
    public void testNoVariableWithoutVisibilityInBody(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("withVisibilityExternal")
    public void testNoVariableWithoutVisibilityExternal(String kind, String member) {
        assertFalse(finder.sniff(external(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' no cuenta")
    @MethodSource("otherKindsBody")
    public void testOtherKindsAreIgnored(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @Test
    public void testOnlyTheVariableWithoutVisibilityIsReported() {
        String code = """
               @startuml
               class Cuenta {
                   +saldo : double
                   -pin : int
                   alias : String
                   +getSaldo() : double
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(5, report.getAromas().get(0).line());
    }

    @Test
    public void testSeveralClasses() {
        String code = """
               @startuml
               class A {
                   x : int
               }
               class B {
                   +y : int
               }
               interface C {
                   z : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(2, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
        assertEquals(9, report.getAromas().get(1).line());
    }

    @Test
    public void testNoteIsIgnored() {
        String code = """
               @startuml
               class Cuenta {
                   +saldo : double
               }
               note right of Cuenta
                   saldo : double
               end note
               @enduml
               """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testSyntaxError() {
        String code = "class Prueba { saldo }";
        assertThrows(IllegalArgumentException.class, () -> finder.sniff(code));
    }
}