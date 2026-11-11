package oo2.redictado.sniffers.unimplementedAbstractMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

public class UnimplementedAbtractMethodVisitor extends PlantUmlVisitor {
    // clases abstractas que evaden obligaciones
    private final Set<String> clasesAbstractas = new HashSet<>();
    
    // Clases con firmas de metodos abstractos
    private final Map<String, List<String>> obligacionesAbstractasPorClase = new HashMap<>();
    
    // Clases con metodos implementados
    private final Map<String, List<String>> metodosConcretosPorClase = new HashMap<>();
    
    // herencias clases junto con sus padres
    private final Map<String, List<String>> herencias = new HashMap<>();

    // RECOLECTAR CLASES, HERENCIAS CON EXTENDS, MÉTODOS Y FIRMAS EN LAS COLECCIONES

    @Override 
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx){
        return null;
    }

    // RECOLECTAR HERENCIAS CON FLECHAS

    @Override 
    public Void visitRelationship(PlantUmlParser.RelationshipContext ctx) {
        return null;
    }

    // 4. ANALIZAR LAS COLECCIONES EN BUSCA DEL SMELL
    
    @Override 
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        return null;
    }

    // Metodos Auxiliares

    // Genera Firmas con el formato visibilidad + nombre + ( parametros ) + tipo retorno

    private String generarFirma(PlantUmlParser.VisibilityContext visCtx, PlantUmlParser.MethodSignatureContext sigCtx) {
        // obtener visibilidad si no tiene, queda vacío.
        String vis = (visCtx != null) ? visCtx.getText().trim() : "";

        // obtener nombre del metodo
        String nombre = sigCtx.IDENTIFIER().getText();

        // tipos de parametros
        List<String> tiposParams = new ArrayList<>();
        if (sigCtx.paramList() != null) {
            //todos los parametros son agregados a la lista tiposParams
            for (PlantUmlParser.ParamContext p : sigCtx.paramList().param()) {
                if (p.type() != null) {
                    // normaliza los espacios para que un List<Int> sea igual a List< int >
                    tiposParams.add(p.type().getText().replaceAll("\\s+", "")); 
                } else {
                    // Si hay un parametro sin tipo se guarda un espacio vacio
                    tiposParams.add("");
                }
            }
        }
        //Transforma la lista en String y separa los elementos por comas
        String paramsStr = String.join(",", tiposParams);

        // tipo de retorno (igualdad textual, sacando espacios)
        String retorno = "";
        if (sigCtx.COLON() != null && sigCtx.type() != null && !sigCtx.type().isEmpty()) {
            // Estilo UML: nombre() : Tipo
            retorno = sigCtx.type(sigCtx.type().size() - 1).getText();
        } else if (sigCtx.type() != null && !sigCtx.type().isEmpty()) {
            // Estilo Java: Tipo nombre()
            retorno = sigCtx.type(0).getText();
        }
        retorno = retorno.replaceAll("\\s+", "");

        // retorna un string con todos los datos
        return vis + nombre + "(" + paramsStr + "):" + retorno;
    }




}
