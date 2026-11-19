package oo2.redictado.sniffers.publicvariable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class PublicVariableSnifferTest {
    SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new PublicVariableSniffer());
    }

    // ---------- Variables dentro de una clase: formas de definirlas ----------

    @Test
    public void testPublicVariableWithTypeAfterColon() {
        String code = """
               @startuml
               class Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        assertEquals("PublicVariableSniffer", report.getAromas().get(0).name());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableWithTypeBeforeName() {
        String code = """
               @startuml
               class Prueba {
                   + int numero
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableWithoutType() {
        String code = """
               @startuml
               class Prueba {
                   + saldo
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicStaticVariable() {
        String code = """
               @startuml
               class Prueba {
                   + {static} contador : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableWithGenericType() {
        String code = """
               @startuml
               class Prueba {
                   + lista : List<String>
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableWithArrayType() {
        String code = """
               @startuml
               class Prueba {
                   + ids : int[]
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    // ---------- Variables dentro de otros tipos de declaración soportados ----------

    @Test
    public void testPublicVariableInInterface() {
        String code = """
               @startuml
               interface Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableInAbstractWithoutClassKeyword() {
        String code = """
               @startuml
               abstract Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testPublicVariableInAbstractClass() {
        String code = """
               @startuml
               abstract class Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    // ---------- Variables definidas fuera de la clase (la clase se declara antes) ----------

    @Test
    public void testExternalPublicVariableWithTypeAfterColon() {
        String code = """
               @startuml
               class Prueba
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableWithoutType() {
        String code = """
               @startuml
               class Prueba
               Prueba : + alias
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableWithTypeBeforeName() {
        String code = """
               @startuml
               class Prueba
               Prueba : + String alias
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableInInterface() {
        String code = """
               @startuml
               interface Prueba
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableInAbstractWithoutClassKeyword() {
        String code = """
               @startuml
               abstract Prueba
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableInAbstractClass() {
        String code = """
               @startuml
               abstract class Prueba
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
    }

    // ---------- Variables definidas fuera de la clase: orden de declaración ----------

    @Test
    public void testExternalPublicVariableOfUndeclaredClassIsReportedAsClass() {
        String code = """
               @startuml
               Cuenta : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(2, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableWithClassDeclaredAfter() {
        String code = """
               @startuml
               Prueba : + alias : String
               class Prueba
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(2, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableWithInterfaceDeclaredAfter() {
        String code = """
               @startuml
               Prueba : + alias : String
               interface Prueba
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(2, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableWithAbstractClassDeclaredAfter() {
        String code = """
               @startuml
               Prueba : + alias : String
               abstract class Prueba
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(2, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableOfClassWithBody() {
        String code = """
               @startuml
               class Prueba {
                   - saldo : double
               }
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(5, report.getAromas().get(0).line());
    }

    @Test
    public void testExternalPublicVariableOfEnumDeclaredBeforeIsIgnored() {
        String code = """
               @startuml
               enum Prueba
               Prueba : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testExternalPublicVariableOfEnumDeclaredAfterIsIgnored() {
        String code = """
               @startuml
               Prueba : + alias : String
               enum Prueba
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testExternalPublicVariableOnlyForSupportedKinds() {
        String code = """
               @startuml
               enum Estado
               class Cuenta
               Estado : + alias : String
               Cuenta : + alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(1, report.getAromas().size());
        assertEquals(5, report.getAromas().get(0).line());
    }

    // ---------- Casos que no deben reportarse dentro de una clase ----------

    @Test
    public void testPrivateVariableIsNotReported() {
        String code = """
               @startuml
               class Prueba {
                   - valor : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testProtectedVariableIsNotReported() {
        String code = """
               @startuml
               class Prueba {
                   # dato : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testVariableWithoutVisibilityIsNotReported() {
        String code = """
               @startuml
               class Prueba {
                   x : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicMethodWithReturnTypeIsNotReported() {
        String code = """
               @startuml
               class Prueba {
                   + getVariable() : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    // ---------- Casos que no deben reportarse fuera de la clase ----------

    @Test
    public void testExternalPublicMethodIsNotReported() {
        String code = """
               @startuml
               class Prueba
               Prueba : + getVariable()
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testExternalPrivateVariableIsNotReported() {
        String code = """
               @startuml
               class Prueba
               Prueba : - alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testExternalVariableWithoutVisibilityIsNotReported() {
        String code = """
               @startuml
               class Prueba
               Prueba : alias : String
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    // ---------- Tipos de declaración que la heurística no contempla ----------

    @Test
    public void testPublicVariableInEnumIsIgnored() {
        String code = """
               @startuml
               enum Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInEntityIsIgnored() {
        String code = """
               @startuml
               entity Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInAnnotationIsIgnored() {
        String code = """
               @startuml
               annotation Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInExceptionIsIgnored() {
        String code = """
               @startuml
               exception Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInMetaclassIsIgnored() {
        String code = """
               @startuml
               metaclass Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInStereotypeIsIgnored() {
        String code = """
               @startuml
               stereotype Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInProtocolIsIgnored() {
        String code = """
               @startuml
               protocol Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testPublicVariableInStructIsIgnored() {
        String code = """
               @startuml
               struct Prueba {
                   + nombre : String
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    // ---------- Diagramas con varios elementos ----------

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
    public void testSeveralPublicVariablesInOneClass() {
        String code = """
               @startuml
               class Cuenta {
                   +saldo : double
                   +alias : String
                   +limite : int
               }
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertEquals(3, report.getAromas().size());
        assertEquals(3, report.getAromas().get(0).line());
        assertEquals(4, report.getAromas().get(1).line());
        assertEquals(5, report.getAromas().get(2).line());
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
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testEmptyDiagramHasNoSmells() {
        String code = """
               @startuml
               @enduml
               """;
        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
    }

    @Test
    public void testSyntaxError() {
        String code = "class Prueba { + saldo }";
        assertThrows(IllegalArgumentException.class, () -> finder.sniff(code));
    }
}