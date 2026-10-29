package ar.edu.unlp.info.roo2.interfacecounter;

/**
 * Representa una clase (concreta o abstracta) declarada en el diagrama.
 */
public class ClassType extends DeclaredType {
    public ClassType(String name) {
        super(name);
    }

    @Override
    public DeclaredType mergeWith(DeclaredType incoming) {
        return incoming.mergeWithClass(this);
    }

    @Override
    public DeclaredType mergeWithInterface(InterfaceType existingInterface) {
        return existingInterface.addMethodsFrom(this);
    }

    @Override
    public DeclaredType mergeWithClass(ClassType existingClass) {
        return existingClass.addMethodsFrom(this);
    }

    @Override
    public DeclaredType mergeWithUnclassified(UnclassifiedType existingUnclassified) {
        ClassType promoted = new ClassType(getName());
        promoted.addMethodsFrom(existingUnclassified);
        return promoted.addMethodsFrom(this);
    }

    @Override
    public void accept(DeclaredTypeVisitor visitor) {
        visitor.visitClass(this);
    }
}

