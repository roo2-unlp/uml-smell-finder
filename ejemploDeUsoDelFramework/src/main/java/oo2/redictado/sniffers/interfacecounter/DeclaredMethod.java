package oo2.redictado.sniffers.interfacecounter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa un método declarado en una interfaz o clase del diagrama.
 */
public class DeclaredMethod {
    private final String name;
    private final Visibility visibility;
    private final List<MethodParameter> parameters;
    private final ReturnType returnType;

    public DeclaredMethod(
            String name,
            Visibility visibility,
            List<MethodParameter> parameters,
            ReturnType returnType) {
        this.name = name;
        this.visibility = visibility;
        this.parameters = List.copyOf(parameters);
        this.returnType = returnType;
    }

    public String format() {
        String formattedParams = this.parameters.stream()
                .map(MethodParameter::format)
                .collect(Collectors.joining(", "));
        String baseSignature = this.name + "(" + formattedParams + ")";
        return this.visibility.applyTo(this.returnType.appendTo(baseSignature));
    }
}
