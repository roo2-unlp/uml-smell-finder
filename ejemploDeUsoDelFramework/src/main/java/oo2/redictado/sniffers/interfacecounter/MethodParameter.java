package oo2.redictado.sniffers.interfacecounter;

import java.util.List;

/**
 * Representa un parámetro formal dentro de la firma de un método.
 */
public class MethodParameter {
    private final List<String> tokens;

    public MethodParameter(List<String> tokens) {
        this.tokens = List.copyOf(tokens);
    }

    public String format() {
        return String.join(" ", this.tokens);
    }
}

