package oo2.redictado.sniffers.classinterfacemismatch;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// Puede ser una clase o una interfaz. Guarda sus operaciones y de quien hereda
public class Clasificador {
    private final String nombre;
    private final List<Operacion> operaciones = new ArrayList<>();
    private final Set<String> padres = new LinkedHashSet<>();
    private boolean esInterfaz;
    private boolean esAbstracta;

    public Clasificador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Operacion> getOperaciones() {
        return operaciones;
    }

    public Set<String> getPadres() {
        return padres;
    }

    public void agregarOperacion(Operacion operacion) {
        operaciones.add(operacion);
    }

    public void agregarPadre(String padre) {
        padres.add(padre);
    }

    public void marcarComoInterfaz() {
        esInterfaz = true;
    }

    public void marcarComoAbstracta() {
        esAbstracta = true;
    }

    public boolean esInterfaz() {
        return esInterfaz;
    }

    public boolean esClaseConcreta() {
        return !esInterfaz && !esAbstracta;
    }
}
