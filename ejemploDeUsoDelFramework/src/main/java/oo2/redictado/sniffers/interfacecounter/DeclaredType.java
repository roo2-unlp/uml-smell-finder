package oo2.redictado.sniffers.interfacecounter;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Representa un elemento de tipo declarado en el diagrama (interfaz, clase o tipo no clasificado).
 * Utiliza Double Dispatch tanto para fusionar declaraciones repetidas o promocionadas
 * como para ser visitado por un {@link DeclaredTypeVisitor}.
 */
public abstract class DeclaredType {
    private final String name;
    private final ParserRuleContext declarationNode;
    private MethodCollection methods = new EmptyMethodCollection();

    protected DeclaredType(String name, ParserRuleContext declarationNode) {
        this.name = name;
        this.declarationNode = declarationNode;
    }

    public String getName() {
        return this.name;
    }

    public ParserRuleContext getDeclarationNode() {
        return this.declarationNode;
    }

    public MethodCollection getMethodCollection() {
        return this.methods;
    }

    public void addMethod(DeclaredMethod method) {
        this.methods = this.methods.add(method);
    }

    public DeclaredType addMethodsFrom(DeclaredType other) {
        this.methods = this.methods.addAll(other.getMethodCollection());
        return this;
    }

    public String describeMethods() {
        return this.methods.describe();
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
