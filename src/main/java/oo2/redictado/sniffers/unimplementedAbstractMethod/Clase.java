package oo2.redictado.sniffers.unimplementedAbstractMethod;


import java.util.ArrayList;
import java.util.List;


/**
 * Una clase del diagrama, con sus métodos y su superclase directa.
 */
public class Clase {
    private final String nombre;
    private boolean abstracta;
    private final List<Metodo> metodos = new ArrayList<>();
    private Clase superclase;


    public Clase(String nombre) {
        this.nombre = nombre;
    }


    public String getNombre() {
        return this.nombre;
    }


    public boolean esAbstracta() {
        return this.abstracta;
    }


    public void marcarAbstracta() {
        this.abstracta = true;
    }


    public void agregarMetodo(Metodo metodo) {
        this.metodos.add(metodo);
    }


    public Clase getSuperclase() {
        return this.superclase;
    }


    public void heredaDe(Clase superclase) {
        this.superclase = superclase;
    }


    /**
     * Los miembros {static} no se consideran: aunque sean {abstract} no son
     * obligaciones.
     *
     * @return los métodos abstractos que declara esta clase, que son las
     *         obligaciones de sus subclases concretas
     */
    public List<Metodo> metodosAbstractos() {
        return this.metodos.stream().filter(metodo -> metodo.esAbstracto() && !metodo.esEstatico()).toList();
    }


    /**
     * @param abstracto un método abstracto
     * @return si alguno de los métodos de esta clase lo implementa
     */
    public boolean implementa(Metodo abstracto) {
        return this.metodos.stream().anyMatch(metodo -> metodo.implementa(abstracto));
    }


    /**
     * Solo se mira la superclase directa. Las clases abstractas y las que no
     * tienen superclase no tienen obligaciones.
     *
     * @return los métodos abstractos de la superclase que esta clase no implementa
     */
    public List<Metodo> metodosSinImplementar() {
        if (this.esAbstracta() || this.superclase == null) {
            return List.of();
        }
        return this.superclase.metodosAbstractos().stream()
                .filter(abstracto -> !this.implementa(abstracto))
                .toList();
    }
}



