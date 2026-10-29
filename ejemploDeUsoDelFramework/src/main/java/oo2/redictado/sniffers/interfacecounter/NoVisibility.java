package oo2.redictado.sniffers.interfacecounter;

/**
 * Null Object para miembros cuya visibilidad no fue especificada en el diagrama.
 */
public class NoVisibility implements Visibility {
    @Override
    public String applyTo(String signature) {
        return signature;
    }
}

