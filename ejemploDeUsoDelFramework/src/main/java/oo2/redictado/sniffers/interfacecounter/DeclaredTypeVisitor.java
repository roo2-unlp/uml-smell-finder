package oo2.redictado.sniffers.interfacecounter;

/**
 * Contrato Visitor para operar polimórficamente mediante Double Dispatch
 * sobre la jerarquía {@link DeclaredType}.
 */
public interface DeclaredTypeVisitor {
    /**
     * Invocado cuando el elemento visitado representa una {@link InterfaceType}.
     *
     * @param interfaceType interfaz del diagrama visitada
     */
    void visitInterface(InterfaceType interfaceType);

    /**
     * Invocado cuando el elemento visitado representa una {@link ClassType}.
     *
     * @param classType clase del diagrama visitada
     */
    void visitClass(ClassType classType);

    /**
     * Invocado cuando el elemento visitado representa un tipo no clasificado ({@link UnclassifiedType}).
     *
     * @param unclassifiedType tipo auxiliar o no clasificable visitado
     */
    void visitUnclassified(UnclassifiedType unclassifiedType);
}

