package oo2.redictado.sniffers.publicvariable;

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

public class PublicVariableSnifferTest {
    static final List<String> KINDS = List.of("class", "interface", "abstract", "abstract class");
    static final List<String> OTHER_KINDS = List.of("enum", "entity", "annotation", "exception",
            "metaclass", "stereotype", "protocol", "struct");

    static final List<String> PUBLIC_BODY = List.of(
            "+ nombre : String", "+ int numero", "+ saldo",
            "+ {static} contador : int", "+ lista : List<String>", "+ ids : int[]");
    static final List<String> PUBLIC_EXTERNAL = List.of(
            "+ alias : String", "+ alias", "+ String alias");
    static final List<String> NOT_PUBLIC_BODY = List.of(
            "- valor : int", "# dato : int", "x : int", "+ getVariable() : int");
    static final List<String> NOT_PUBLIC_EXTERNAL = List.of("+ getVariable()");

    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new PublicVariableSniffer());
    }

    static Stream<Arguments> combine(List<String> kinds, List<String> members) {
        return kinds.stream().flatMap(k -> members.stream().map(m -> Arguments.of(k, m)));
    }

    static Stream<Arguments> publicBody()        { return combine(KINDS, PUBLIC_BODY); }
    static Stream<Arguments> publicExternal()    { return combine(KINDS, PUBLIC_EXTERNAL); }
    static Stream<Arguments> notPublicBody()     { return combine(KINDS, NOT_PUBLIC_BODY); }
    static Stream<Arguments> notPublicExternal() { return combine(KINDS, NOT_PUBLIC_EXTERNAL); }
    static Stream<Arguments> otherKindsBody()    { return combine(OTHER_KINDS, List.of("+ nombre : String")); }

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
    @MethodSource("publicBody")
    public void testPublicVariableInBody(String kind, String member) {
        AromaReport report = finder.sniff(inBody(kind, member));

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().get(0);
        assertEquals("PublicVariableSniffer", aroma.name());
        assertEquals(3, aroma.line());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("publicExternal")
    public void testPublicVariableExternal(String kind, String member) {
        AromaReport report = finder.sniff(external(kind, member));

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' en el cuerpo")
    @MethodSource("notPublicBody")
    public void testNoPublicVariableInBody(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''Prueba : {1}''")
    @MethodSource("notPublicExternal")
    public void testNoPublicVariableExternal(String kind, String member) {
        assertFalse(finder.sniff(external(kind, member)).stinks());
    }

    @ParameterizedTest(name = "{0} con ''{1}'' no cuenta")
    @MethodSource("otherKindsBody")
    public void testOtherKindsAreIgnored(String kind, String member) {
        assertFalse(finder.sniff(inBody(kind, member)).stinks());
    }

    @Test
    public void testOnlyThePublicVariableIsReported() {
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
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testSeveralClasses() {
        String code = """
               @startuml
               class A {
                   +x : int
               }
               class B {
                   -y : int
               }
               interface C {
                   +z : int
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
                   -saldo : double
               }
               note right of Cuenta
                   + saldo : double
               end note
               @enduml
               """;
        assertFalse(finder.sniff(code).stinks());
    }

    @Test
    public void testSyntaxError() {
        String code = "class Prueba { + saldo }";
        assertThrows(IllegalArgumentException.class, () -> finder.sniff(code));
    }
}