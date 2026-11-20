package oo2.redictado.sniffers.unimplementedAbstractMethod;


import java.util.List;
import java.util.Optional;


/**
 * Un método declarado en una clase del diagrama.
 *
 * No se asumen valores por defecto: si el diagrama no declara la visibilidad
 * o el tipo de retorno, quedan vacíos, y un valor vacío solo coincide con
 * otro vacío. Los nombres de los parámetros no se guardan porque no importan
 * para comparar métodos.
 */
public class Metodo {
    private final Optional<String> visibilidad;
    private final String nombre;
    private final List<String> tiposParametros;
    private final Optional<String> retorno;
    private final boolean abstracto;
    private final boolean estatico;


    /**
     * @param visibilidad     +, -, # o ~; vacía o null si se omitió
     * @param nombre          el nombre del método
     * @param tiposParametros los tipos de los parámetros, en orden; vacío si un parámetro no tiene tipo
     * @param retorno         el tipo de retorno; vacío o null si se omitió
     * @param abstracto       si es {abstract}
     * @param estatico        si es {static}
     */
    public Metodo(String visibilidad, String nombre, List<String> tiposParametros, String retorno,
            boolean abstracto, boolean estatico) {
        this.visibilidad = declarado(visibilidad);
        this.nombre = nombre;
        this.tiposParametros = tiposParametros.stream().map(Metodo::sinEspacios).toList();
        this.retorno = declarado(retorno);
        this.abstracto = abstracto;
        this.estatico = estatico;
    }


    public boolean esAbstracto() {
        return this.abstracto;
    }


    public boolean esEstatico() {
        return this.estatico;
    }


    /**
     * Un método implementa a uno abstracto si es de instancia, no es abstracto
     * y tiene la misma visibilidad, nombre, tipos de parámetros (en el mismo
     * orden) y tipo de retorno. Una visibilidad o un retorno no declarados solo
     * coinciden con otros no declarados.
     *
     * @param abstracto el método abstracto que debería implementar
     * @return si este método lo implementa
     */
    public boolean implementa(Metodo abstracto) {
        return !this.esAbstracto() && !this.esEstatico()
                && this.visibilidad.equals(abstracto.visibilidad)
                && this.nombre.equals(abstracto.nombre)
                && this.tiposParametros.equals(abstracto.tiposParametros)
                && this.retorno.equals(abstracto.retorno);
    }


    /**
     * @return la firma del método tal como se declaró, por ejemplo
     *         +calcular(int,Real):Real, o calcular(int,Real) sin visibilidad ni retorno
     */
    @Override
    public String toString() {
        return this.visibilidad.orElse("") + this.nombre + "(" + String.join(",", this.tiposParametros) + ")"
                + this.retorno.map(tipo -> ":" + tipo).orElse("");
    }


    // null o en blanco significa que el diagrama no lo declara
    private static Optional<String> declarado(String valor) {
        return Optional.ofNullable(valor).map(Metodo::sinEspacios).filter(texto -> !texto.isEmpty());
    }


    // List< Int > y List<Int> son el mismo tipo
    private static String sinEspacios(String texto) {
        return texto.replaceAll("\\s+", "");
    }
}



