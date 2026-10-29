package oo2.redictado.sniffers.interfacecounter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Recolector que clasifica instancias de {@link DeclaredType} en interfaces y clases
 * usando Double Dispatch ({@link DeclaredType#accept(DeclaredTypeVisitor)}) sin condicionales,
 * y reporta cada elemento y el conteo total al recolector de aromas del framework.
 */
public class TypeCollector implements DeclaredTypeVisitor {
    private final List<InterfaceType> interfaces = new ArrayList<>();
    private final List<ClassType> classes = new ArrayList<>();

    /**
     * Método fábrica que recibe una colección iterable de tipos declarados, instancia un
     * {@link TypeCollector} y ejecuta el patrón Visitor despachando cada tipo mediante
     * {@link DeclaredType#accept(DeclaredTypeVisitor)} (Double Dispatch) para clasificarlos.
     *
     * @param types conjunto de tipos declarados extraídos del diagrama
     * @return recolector con los tipos clasificados en listas separadas de interfaces y clases
     */
    public static TypeCollector collectFrom(Iterable<DeclaredType> types) {
        TypeCollector collector = new TypeCollector();
        for (DeclaredType type : types) {
            type.accept(collector);
        }
        return collector;
    }

    /**
     * Método del Visitor invocado polimórficamente por Double Dispatch cuando el elemento
     * visitado es una {@link InterfaceType}.
     * Almacena la interfaz en la lista interna de interfaces para su conteo y reporte.
     *
     * @param interfaceType interfaz declarada en el diagrama
     */
    @Override
    public void visitInterface(InterfaceType interfaceType) {
        this.interfaces.add(interfaceType);
    }

    /**
     * Método del Visitor invocado polimórficamente por Double Dispatch cuando el elemento
     * visitado es una {@link ClassType}.
     * Almacena la clase en la lista interna de clases para su conteo y reporte (actúa como
     * el contador/recolector de clases).
     *
     * @param classType clase declarada en el diagrama
     */
    @Override
    public void visitClass(ClassType classType) {
        this.classes.add(classType);
    }

    /**
     * Método del Visitor invocado cuando el elemento es un {@link UnclassifiedType}.
     * Implementa el patrón Null Object: descarta o ignora los tipos que no califican ni
     * como clase ni como interfaz (ej: enums o tipos incompletos) sin requerir sentencias condicionales.
     *
     * @param unclassifiedType tipo no clasificado a ignorar
     */
    @Override
    public void visitUnclassified(UnclassifiedType unclassifiedType) {
        // Null Object: los tipos no clasificados (como enums) se ignoran sin condicionales.
    }

    /**
     * Vuelca todos los hallazgos hacia el recolector de aromas del framework:
     * primero emite un aroma individual por cada interfaz describiendo sus métodos,
     * luego un aroma por cada clase describiendo sus métodos, y finalmente un aroma
     * con el resumen total de interfaces y clases contabilizadas.
     *
     * @param diagramNode nodo raíz del diagrama para asociar la línea del resumen
     * @param smellReporter función de reporte (normalmente referencia al método {@code smell} del Visitor)
     */
    public void reportSmells(
            ParserRuleContext diagramNode,
            BiConsumer<String, ParserRuleContext> smellReporter) {
        this.interfaces.forEach(
                i -> smellReporter.accept(i.describe(), i.getDeclarationNode()));
        this.classes.forEach(
                c -> smellReporter.accept(c.describe(), c.getDeclarationNode()));
        smellReporter.accept(this.formatSummary(), diagramNode);
    }

    /**
     * Genera la cadena de texto con el resumen formateado del conteo total de elementos.
     *
     * @return mensaje con el formato "Interfaces encontradas: X, Clases encontradas: Y"
     */
    public String formatSummary() {
        return "Interfaces encontradas: %d, Clases encontradas: %d"
                .formatted(this.interfaceCount(), this.classCount());
    }

    /**
     * Devuelve la cantidad total de interfaces encontradas y almacenadas en este recolector.
     *
     * @return número de interfaces detectadas
     */
    public int interfaceCount() {
        return this.interfaces.size();
    }

    /**
     * Devuelve la cantidad total de clases encontradas y almacenadas en este recolector.
     *
     * @return número de clases detectadas (contador de clases)
     */
    public int classCount() {
        return this.classes.size();
    }
}
