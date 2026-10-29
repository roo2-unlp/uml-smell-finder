package oo2.redictado.sniffers.interfacecounter;

import java.util.List;

/**
 * Representa el estado sin métodos de un tipo declarado (Null Object / State inicial).
 */
public class EmptyMethodCollection implements MethodCollection {
    @Override
    public MethodCollection add(DeclaredMethod method) {
        return new NonEmptyMethodCollection(List.of(method));
    }

    @Override
    public MethodCollection addAll(MethodCollection other) {
        return other;
    }

    @Override
    public List<DeclaredMethod> getMethods() {
        return List.of();
    }

    @Override
    public String describe() {
        return "(sin métodos)";
    }
}
