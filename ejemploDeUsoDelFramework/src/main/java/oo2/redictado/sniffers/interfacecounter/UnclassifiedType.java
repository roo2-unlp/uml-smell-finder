package oo2.redictado.sniffers.interfacecounter;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Null Object que representa un tipo aún no clasificado (ej. miembro externo previo a la
 * declaración del tipo) o un elemento que no es ni clase ni interfaz (ej. {@code enum}).
 */
public class UnclassifiedType extends DeclaredType {
    public UnclassifiedType(String name, ParserRuleContext declarationNode) {
        super(name, declarationNode);
    }

    @Override
    public DeclaredType mergeWith(DeclaredType incoming) {
        return incoming.mergeWithUnclassified(this);
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
        return existingUnclassified.addMethodsFrom(this);
    }

    @Override
    public void accept(DeclaredTypeVisitor visitor) {
        visitor.visitUnclassified(this);
    }
}

