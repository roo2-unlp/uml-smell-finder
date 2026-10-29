package oo2.redictado.sniffers.interfacecounter;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Representa una clase (concreta o abstracta) declarada en el diagrama.
 */
public class ClassType extends DeclaredType {
    /**
     * Construye un tipo de clase con su nombre y el nodo de parseo donde fue declarada.
     *
     * @param name nombre de la clase
     * @param declarationNode nodo del AST correspondiente a la declaración
     */
    public ClassType(String name, ParserRuleContext declarationNode) {
        super(name, declarationNode);
    }

    /**
     * Retorna la descripción formateada de la clase junto con sus métodos (o '(sin métodos)').
     *
     * @return texto descriptivo con formato "Clase Nombre: métodos"
     */
    public String describe() {
        return "Clase " + this.getName() + ": " + this.describeMethods();
    }

    /**
     * Primer paso de Double Dispatch: delega en el tipo entrante para fusionarse.
     *
     * @param incoming nuevo tipo entrante con el mismo nombre
     * @return tipo resultante tras la fusión
     */
    @Override
    public DeclaredType mergeWith(DeclaredType incoming) {
        return incoming.mergeWithClass(this);
    }

    /**
     * Segundo paso de Double Dispatch: si la clase previa se fusiona con una interfaz,
     * la interfaz tiene precedencia y absorbe los métodos de esta clase.
     *
     * @param existingInterface interfaz previa existente en el mapa
     * @return la interfaz con los métodos de la clase agregados
     */
    @Override
    public DeclaredType mergeWithInterface(InterfaceType existingInterface) {
        return existingInterface.addMethodsFrom(this);
    }

    /**
     * Segundo paso de Double Dispatch: fusiona dos declaraciones de clase acumulando sus métodos.
     *
     * @param existingClass clase previa registrada
     * @return la clase original con los métodos nuevos agregados
     */
    @Override
    public DeclaredType mergeWithClass(ClassType existingClass) {
        return existingClass.addMethodsFrom(this);
    }

    /**
     * Segundo paso de Double Dispatch: promueve un tipo no clasificado previo (ej: miembros externos)
     * a {@link ClassType}, transfiriendo los métodos acumulados.
     *
     * @param existingUnclassified tipo previo no clasificado
     * @return nueva instancia de {@link ClassType} con todos los métodos consolidados
     */
    @Override
    public DeclaredType mergeWithUnclassified(UnclassifiedType existingUnclassified) {
        ClassType promoted = new ClassType(this.getName(), this.getDeclarationNode());
        promoted.addMethodsFrom(existingUnclassified);
        return promoted.addMethodsFrom(this);
    }

    /**
     * Despacho del patrón Visitor: invoca {@link DeclaredTypeVisitor#visitClass(ClassType)}
     * pasando esta instancia para que el recolector/contador la contabilice.
     *
     * @param visitor visitante de tipos
     */
    @Override
    public void accept(DeclaredTypeVisitor visitor) {
        visitor.visitClass(this);
    }
}

