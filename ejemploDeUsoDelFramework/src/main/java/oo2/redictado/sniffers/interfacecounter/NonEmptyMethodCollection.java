package oo2.redictado.sniffers.interfacecounter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa el estado con al menos un método de un tipo declarado.
 */
public class NonEmptyMethodCollection implements MethodCollection {
    private final List<DeclaredMethod> methods;

    public NonEmptyMethodCollection(List<DeclaredMethod> methods) {
        this.methods = List.copyOf(methods);
    }

    @Override
    public MethodCollection add(DeclaredMethod method) {
        List<DeclaredMethod> updated = new ArrayList<>(this.methods);
        updated.add(method);
        return new NonEmptyMethodCollection(updated);
    }

    @Override
    public MethodCollection addAll(MethodCollection other) {
        List<DeclaredMethod> updated = new ArrayList<>(this.methods);
        updated.addAll(other.getMethods());
        return new NonEmptyMethodCollection(updated);
    }

    @Override
    public List<DeclaredMethod> getMethods() {
        return this.methods;
    }

    @Override
    public String describe() {
        return this.methods.stream()
                .map(DeclaredMethod::format)
                .collect(Collectors.joining(", "));
    }
}
