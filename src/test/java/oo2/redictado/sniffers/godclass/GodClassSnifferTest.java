package oo2.redictado.sniffers.godclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import oo2.redictado.core.Aroma;
import oo2.redictado.core.AromaReport;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.core.SnifferParams;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class GodClassSnifferTest {

    private static final Path RESOURCES_DIR = Path.of("src/test/resources/godclass");

    private SmellFinder<DiagramContext> finder;

    @BeforeEach
    public void setUp() {
        finder = new SmellFinder<>(new PlantUml(), new GodClassSniffer());
    }

    private String readDiagram(String fileName) throws IOException {
        return Files.readString(RESOURCES_DIR.resolve(fileName));
    }

    @Test
    public void sniff_CincoClasesConUnicaGodClass_ReportaAromaGodClass() throws IOException {
        // TC-12: n = 5; Escuela con NOM = 12, NOA = 10; U = (7, 5)
        String code = readDiagram("tc12_n_5_unica_god_class.puml");

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().getFirst();
        assertEquals("GodClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("God Class: Escuela", aroma.message());
    }

    @Test
    public void sniff_VariasClasesGrandesPeroSoloUnaSuperaCercaDeTukey_ReportaSoloLaClaseSuperior() throws IOException {
        // TC-13: n = 9; Escuela = (16, 13), Profesor = (9, 7), Curso = (8, 6); U = (15.5, 12)
        String code = readDiagram("tc13_varias_grandes_una_superior.puml");

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().getFirst();
        assertEquals("GodClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("God Class: Escuela", aroma.message());
    }

    @Test
    public void sniff_DosGodClassesAnomalas_ReportaAmbasClasesEnOrdenDeDeclaracion() throws IOException {
        // TC-14: n = 8; Escuela = (9, 7), Secretaria = (10, 8), 6 clases (2, 2); U = (7, 5.125)
        String code = readDiagram("tc14_dos_god_classes.puml");

        AromaReport report = finder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(2, report.getAromas().size());

        Aroma firstAroma = report.getAromas().getFirst();
        assertEquals("GodClassSniffer", firstAroma.name());
        assertEquals(2, firstAroma.line());
        assertEquals("God Class: Escuela", firstAroma.message());

        Aroma secondAroma = report.getAromas().get(1);
        assertEquals("GodClassSniffer", secondAroma.name());
        assertEquals(20, secondAroma.line());
        assertEquals("God Class: Secretaria", secondAroma.message());
    }


    @Test
    public void sniff_UmbralesPersonalizadosMasBajosConSnifferParams_ReportaClaseQueSuperaUmbralesPersonalizados() throws IOException {
        // En tc10 Escuela tiene NOM = 7, NOA = 5 y con piso default (7, 5) no se reporta.
        // Con minMethods = 6 y minAttributes = 4 debe reportarse.
        SnifferParams params = new SnifferParams(Map.of(
                "minClasses", "5",
                "minMethods", "6",
                "minAttributes", "4",
                "iqrFactor", "1.5"
        ));
        SmellFinder<DiagramContext> customFinder = new SmellFinder<>(new PlantUml(), new GodClassSniffer(params));
        String code = readDiagram("tc10_piso_estricto.puml");

        AromaReport report = customFinder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        assertEquals("God Class: Escuela", report.getAromas().getFirst().message());
    }

    @Test
    public void sniff_ClaseEnCercaDeTukeyExactaConFactorIqrMenorAUnoPuntoCinco_ReportaAromaGodClass() throws IOException {
        // En tc11 Escuela tiene NOM = 8 (> piso 7) y NOA = 6 (> piso 5), pero con k = 1.5
        // la cerca de Tukey da U = (8, 6) y no se reporta.
        // Al reducir únicamente k (iqrFactor) a 1.0, la cerca baja a (7, 5) y Escuela sí se reporta.
        SnifferParams params = new SnifferParams(Map.of("iqrFactor", "1.0"));
        SmellFinder<DiagramContext> customFinder = new SmellFinder<>(new PlantUml(), new GodClassSniffer(params));
        String code = readDiagram("tc11_cerca_tukey_exacta.puml");

        AromaReport report = customFinder.sniff(code);

        assertTrue(report.stinks());
        assertEquals(1, report.getAromas().size());
        Aroma aroma = report.getAromas().getFirst();
        assertEquals("GodClassSniffer", aroma.name());
        assertEquals(2, aroma.line());
        assertEquals("God Class: Escuela", aroma.message());
    }

    @Test
    public void sniff_DiagramaVacio_RetornaSinAromas() throws IOException {
        // TC-01: Diagrama vacío (n = 0)
        String code = readDiagram("tc01_vacio.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_DiagramaConSintaxisCorrupta_LanzaIllegalArgumentException() throws IOException {
        // TC-02: Sintaxis corrupta
        String code = readDiagram("tc02_sintaxis_corrupta.puml");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> finder.sniff(code)
        );
        assertEquals("Syntax error", exception.getMessage());
    }

    @Test
    public void sniff_UnicaClaseVacia_RetornaSinAromas() throws IOException {
        // TC-03: Clase vacía (n = 1, NOM = 0, NOA = 0)
        String code = readDiagram("tc03_clase_vacia.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_MenosDeCincoClasesConClaseGrande_RetornaSinAromas() throws IOException {
        // TC-04: n = 4 < 5; Escuela con NOM = 12, NOA = 10
        String code = readDiagram("tc04_n_menor_5_clase_grande.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_MenosDeCincoClasesAnalizablesPorTiposExcluidos_RetornaSinAromas() throws IOException {
        // TC-05: 7 elementos pero 3 excluidos (interface, enum, <<interface>>), n = 4 < 5
        String code = readDiagram("tc05_n_menor_5_tipos_excluidos.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_IqrCeroConClaseApenasMayorDebajoDelPiso_RetornaSinAromas() throws IOException {
        // TC-06: n = 5; NOM = [2,2,2,2,3], NOA = [2,2,2,2,3]; IQR = 0 pero actúa el piso (7, 5)
        String code = readDiagram("tc06_iqr_cero_piso.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_DataClassSoloConConstructorGettersYSetters_RetornaSinAromas() throws IOException {
        // TC-07: n = 5; Alumno tiene NOA = 8 y 17 métodos accesorios/constructor -> NOM = 0
        String code = readDiagram("tc07_data_class.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_ClaseQueSuperaSoloUmbralNom_RetornaSinAromas() throws IOException {
        // TC-08: n = 5; Escuela tiene NOM = 12 (> 7) pero NOA = 3 (<= 5)
        String code = readDiagram("tc08_solo_nom.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_ClaseQueSuperaSoloUmbralNoa_RetornaSinAromas() throws IOException {
        // TC-09: n = 5; Escuela tiene NOM = 3 (<= 7) pero NOA = 10 (> 5)
        String code = readDiagram("tc09_solo_noa.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_ClaseConValoresIgualesAlPisoAbsoluto_RetornaSinAromas() throws IOException {
        // TC-10: n = 5; Escuela tiene NOM = 7, NOA = 5; U = (7, 5) y la desigualdad es estricta (>)
        String code = readDiagram("tc10_piso_estricto.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_ClaseConValoresIgualesALaCercaDeTukeyExacta_RetornaSinAromas() throws IOException {
        // TC-11: n = 5; Escuela tiene NOM = 8, NOA = 6; cerca de Tukey U = (8, 6), comparación estricta (>)
        String code = readDiagram("tc11_cerca_tukey_exacta.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }

    @Test
    public void sniff_ClaseConRelacionesSalientesEtiquetadasEnElPisoDeNoa_NoCuentaRelacionesComoNoa() throws IOException {
        // TC-15: n = 5; Escuela tiene NOM = 8 (> 7), NOA = 5 (= piso) y 4 relaciones salientes etiquetadas
        String code = readDiagram("tc15_relaciones_no_cuentan_noa.puml");

        AromaReport report = finder.sniff(code);

        assertFalse(report.stinks());
        assertTrue(report.getAromas().isEmpty());
    }
}
