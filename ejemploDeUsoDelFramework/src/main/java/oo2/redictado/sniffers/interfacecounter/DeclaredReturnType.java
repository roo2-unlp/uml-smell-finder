package oo2.redictado.sniffers.interfacecounter;

/**
 * Tipo de retorno explícito de un método (ej. void, String, int).
 */
public class DeclaredReturnType implements ReturnType {
    private final String typeName;

    public DeclaredReturnType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String appendTo(String methodCall) {
        return methodCall + " : " + this.typeName;
    }
}

