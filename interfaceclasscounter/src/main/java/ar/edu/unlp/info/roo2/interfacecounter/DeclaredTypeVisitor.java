package ar.edu.unlp.info.roo2.interfacecounter;

/**
 * Contrato Visitor para operar polimórficamente mediante Double Dispatch
 * sobre la jerarquía {@link DeclaredType}.
 */
public interface DeclaredTypeVisitor {
    void visitInterface(InterfaceType interfaceType);

    void visitClass(ClassType classType);

    void visitUnclassified(UnclassifiedType unclassifiedType);
}

