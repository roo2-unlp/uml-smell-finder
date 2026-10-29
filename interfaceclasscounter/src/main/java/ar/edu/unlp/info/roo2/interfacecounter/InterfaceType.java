package ar.edu.unlp.info.roo2.interfacecounter;

/**
 * Representa una interfaz declarada en el diagrama (por palabra clave {@code interface}
 * o por estereotipo {@code <<interface>>}).
 */
public class InterfaceType extends DeclaredType {
    public InterfaceType(String name) {
        super(name);
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
        InterfaceType promoted = new InterfaceType(getName());
        promoted.addMethodsFrom(existingClass);
        return promoted.addMethodsFrom(this);
    }

    @Override
    public DeclaredType mergeWithUnclassified(UnclassifiedType existingUnclassified) {
        InterfaceType promoted = new InterfaceType(getName());
        promoted.addMethodsFrom(existingUnclassified);
        return promoted.addMethodsFrom(this);
    }

    @Override
    public void accept(DeclaredTypeVisitor visitor) {
        visitor.visitInterface(this);
    }
}

