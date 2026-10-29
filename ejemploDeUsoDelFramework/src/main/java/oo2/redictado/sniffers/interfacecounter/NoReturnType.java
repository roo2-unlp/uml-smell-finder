package oo2.redictado.sniffers.interfacecounter;

/**
 * Null Object para métodos sin tipo de retorno declarado.
 */
public class NoReturnType implements ReturnType {
    @Override
    public String appendTo(String methodCall) {
        return methodCall;
    }
}

