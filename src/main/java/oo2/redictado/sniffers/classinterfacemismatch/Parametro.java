package oo2.redictado.sniffers.classinterfacemismatch;

// Parametro de una operacion. Si en el diagrama no tiene tipo, queda en null
public record Parametro(String nombre, String tipo) {

    @Override
    public String toString() {
        return tipo == null ? nombre : nombre + " : " + tipo;
    }
}
