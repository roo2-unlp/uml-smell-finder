package oo2.redictado.sniffers.interfacecounter;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Representa una interfaz declarada en el diagrama (por palabra clave {@code interface}
 * o por estereotipo {@code <<interface>>}).
 */
public class InterfaceType extends DeclaredType {
    public InterfaceType(String name, ParserRuleContext declarationNode) {
        super(name, declarationNode);
    }

    public String describe() {
        return "Interfaz " + this.getName() + ": " + this.describeMethods();
    }

    @Override
    public DeclaredType mergeWith(DeclaredType incoming) {
        return incoming.mergeWithInterface(this);
    }

    @Override
    public DeclaredType mergeWithInterface(InterfaceType existingInterface) {
        return existingInterface.addMethodsFrom(this);
    }

    @Override
    public DeclaredType mergeWithClass(ClassType existingClass) {
        InterfaceType promoted = new InterfaceType(this.getName(), existingClass.getDeclarationNode());
        promoted.addMethodsFrom(existingClass);
        return promoted.addMethodsFrom(this);
    }

    @Override
    public DeclaredType mergeWithUnclassified(UnclassifiedType existingUnclassified) {
        InterfaceType promoted = new InterfaceType(this.getName(), this.getDeclarationNode());
        promoted.addMethodsFrom(existingUnclassified);
        return promoted.addMethodsFrom(this);
    }

    @Override
    public void accept(DeclaredTypeVisitor visitor) {
        visitor.visitInterface(this);
    }
}

