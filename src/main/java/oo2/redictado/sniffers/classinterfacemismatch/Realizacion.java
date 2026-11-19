package oo2.redictado.sniffers.classinterfacemismatch;

import org.antlr.v4.runtime.ParserRuleContext;

// Clase que dice implementar una interfaz. Guardamos el nodo para saber la linea del reporte
public record Realizacion(String clase, String interfaz, ParserRuleContext dondeSeDeclara) {
}
