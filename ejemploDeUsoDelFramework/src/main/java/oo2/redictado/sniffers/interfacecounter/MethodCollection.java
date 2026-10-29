package oo2.redictado.sniffers.interfacecounter;

import java.util.List;

/**
 * Colección polimórfica de métodos de un tipo declarado (State / Null Object).
 * Evita condicionales al operar y formatear tipos con o sin métodos.
 */
public interface MethodCollection {
    MethodCollection add(DeclaredMethod method);

    MethodCollection addAll(MethodCollection other);

    List<DeclaredMethod> getMethods();

    String describe();
}
