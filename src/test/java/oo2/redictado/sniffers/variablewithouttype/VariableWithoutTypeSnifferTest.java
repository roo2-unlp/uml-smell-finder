package oo2.redictado.sniffers.variablewithouttype;

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

public class VariableWithoutTypeSnifferTest {
    static final List<String> KINDS = List.of("class", "interface", "abstract");
    static final List<String> OTHER_KINDS = List.of("enum", "entity", "annotation", "exception",
            "metaclass", "stereotype", "protocol", "struct");

    static final List<String> WITHOUT_TYPE_BODY = List.of(
            "+ data", "- variable", "# variable", "~variable", "variable",
            "+{static} contador", "+ pin = 1234");
    static final List<String> WITHOUT_TYPE_EXTERNAL = List.of(
            "+ data2", "data3", "- pin = 1234");
    static final List<String> WITH_TYPE_OR_METHOD_BODY = List.of(
            "+ int variable", "- int variable", "- variable : int",
            "+ lista : List<String>", "+ List<String> lista",
            "+ ids : int[]", "+ int[] ids", "- pin : int = 1234",
            "+ getX() : int", "+ method()", "+ setX(x)");
    static final List<String> WITH_TYPE_EXTERNAL = List.of(
            "+ data2 : String", "+ String data2");

    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new VariableWithoutTypeSniffer());
    }

    static Stream<Arguments> combine(List<String> kinds, List<String> members) {
        return kinds.stream().flatMap(k -> members.stream().map(m -> Arguments.of(k, m)));
    }

    static Stream<Arguments> withoutTypeBody()     { return combine(KINDS, WITHOUT_TYPE_BODY); }
    static Stream<Arguments> withoutTypeExternal() { return combine(KINDS, WITHOUT_TYPE_EXTERNAL); }
    static Stream<Arguments> withTypeBody()        { return combine(KINDS, WITH_TYPE_OR_METHOD_BODY); }
    static Stream<Arguments> withTypeExternal()    { return combine(KINDS, WITH_TYPE_EXTERNAL); }
    static Stream<Arguments> otherKindsBody()      { return combine(OTHER_KINDS, List.of("+data")); }

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
    @MethodSource("withoutTypeBody")
    public void testVariableWithoutTypeInBody(String kind, String member) {
        AromaReport report = finder.sniff(inBody(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("VariableWithoutTypeSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("withoutTypeExternal")
    public void testVariableWithoutTypeExternal(String kind, String member) {
        AromaReport report = finder.sniff(external(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("VariableWithoutTypeSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' en el cuerpo")
    @MethodSource("withTypeBody")
    public void testNoVariableWithoutTypeInBody(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("withTypeExternal")
    public void testNoVariableWithoutTypeExternal(String kind, String member) {
        assertFalse(finder.sniff(external(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' no cuenta")
    @MethodSource("otherKindsBody")
    public void testOtherKindsAreIgnored(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }
}
