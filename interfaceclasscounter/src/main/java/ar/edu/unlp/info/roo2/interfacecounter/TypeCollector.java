package ar.edu.unlp.info.roo2.interfacecounter;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Recolector que clasifica instancias de {@link DeclaredType} en interfaces y clases
 * usando Double Dispatch ({@link DeclaredType#accept(DeclaredTypeVisitor)}) sin condicionales.
 */
public class TypeCollector implements DeclaredTypeVisitor {
    private final Map<String, List<String>> interfaces = new LinkedHashMap<>();
    private final Map<String, List<String>> classes = new LinkedHashMap<>();

    public static TypeCollector collectFrom(Iterable<DeclaredType> types) {
        TypeCollector collector = new TypeCollector();
        for (DeclaredType type : types) {
            type.accept(collector);
        }
        return collector;
    }

    @Override
    public void visitInterface(InterfaceType interfaceType) {
        interfaces.put(interfaceType.getName(), interfaceType.getMethods());
    }

    @Override
    public void visitClass(ClassType classType) {
        classes.put(classType.getName(), classType.getMethods());
    }

    @Override
    public void visitUnclassified(UnclassifiedType unclassifiedType) {
        // Null Object: los tipos no clasificados (como enums) se ignoran sin condicionales.
    }

    public Map<String, List<String>> getInterfaces() {
        return Collections.unmodifiableMap(interfaces);
    }

    public Map<String, List<String>> getClasses() {
        return Collections.unmodifiableMap(classes);
    }
}

