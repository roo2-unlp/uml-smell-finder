package oo2.redictado.sniffers.classinterfacemismatch;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;

// Una operacion ya sacada del arbol (nombre, parametros, retorno, etc).
// La visibilidad, static y abstract los guardamos para las otras heuristicas
public record Operacion(String nombre, List<Parametro> parametros, String retorno, String visibilidad,
        boolean esEstatica, boolean esAbstracta, ParserRuleContext dondeSeDeclara) {

    public boolean tieneElMismoNombreQue(Operacion otra) {
        return nombre.equals(otra.nombre());
    }

    // misma firma = mismo nombre y mismos tipos de parametros en el mismo orden (los nombres no importan)
    public boolean tieneLaMismaFirmaQue(Operacion otra) {
        return tieneElMismoNombreQue(otra) && tiposDeParametros().equals(otra.tiposDeParametros());
    }

    public List<String> tiposDeParametros() {
        return parametros.stream().map(Parametro::tipo).toList();
    }

    @Override
    public String toString() {
        String lista = parametros.stream().map(Parametro::toString).collect(Collectors.joining(", "));
        String firma = nombre + "(" + lista + ")";
        return retorno == null ? firma : firma + " : " + retorno;
    }
}
