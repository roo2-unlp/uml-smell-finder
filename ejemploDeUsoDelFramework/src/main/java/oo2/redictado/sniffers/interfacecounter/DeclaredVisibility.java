package oo2.redictado.sniffers.interfacecounter;

/**
 * Visibilidad explícita declarada en el diagrama (+, -, #, ~).
 */
public class DeclaredVisibility implements Visibility {
    private final String symbol;

    public DeclaredVisibility(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String applyTo(String signature) {
        return this.symbol + " " + signature;
    }
}

