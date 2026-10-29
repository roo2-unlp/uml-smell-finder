package ar.edu.unlp.info.roo2.interfacecounter;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un elemento de tipo declarado en el diagrama (interfaz, clase o tipo no clasificado).
 * Utiliza Double Dispatch tanto para fusionar declaraciones repetidas/promocionadas
 * como para ser visitado por un {@link DeclaredTypeVisitor}.
 */
public abstract class DeclaredType {
    private final String name;
    private final List<String> methods = new ArrayList<>();

    protected DeclaredType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getMethods() {
        return List.copyOf(methods);
    }

    public void addMethod(String methodSignature) {
        this.methods.add(methodSignature);
    }

    public DeclaredType addMethodsFrom(DeclaredType other) {
        this.methods.addAll(other.methods);
        return this;
    }

    /**
     * Primer salto del Double Dispatch para combinar el tipo actual ({@code this})
     * con una nueva declaración entrante ({@code incoming}).
     */
    public abstract DeclaredType mergeWith(DeclaredType incoming);

    /**
     * Segundo salto del Double Dispatch cuando el tipo previo era una {@link InterfaceType}.
     */
    public abstract DeclaredType mergeWithInterface(InterfaceType existingInterface);

    /**
     * Segundo salto del Double Dispatch cuando el tipo previo era una {@link ClassType}.
     */
    public abstract DeclaredType mergeWithClass(ClassType existingClass);

    /**
     * Segundo salto del Double Dispatch cuando el tipo previo era un {@link UnclassifiedType}.
     */
    public abstract DeclaredType mergeWithUnclassified(UnclassifiedType existingUnclassified);

    /**
     * Primer salto del Double Dispatch para el patrón Visitor sobre los tipos declarados.
     */
    public abstract void accept(DeclaredTypeVisitor visitor);
}

