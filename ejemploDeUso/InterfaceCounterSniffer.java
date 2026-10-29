package ar.edu.unlp.info.roo2.interfacecounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

/**
 * Sniffer del framework {@code uml-smell-finder} que analiza diagramas de clases PlantUML,
 * cuenta interfaces, detecta si existen interfaces duplicadas y las reporta como un mal olor.
 */
public class InterfaceCounterSniffer extends PlantUmlSniffer {

    @Override
    protected PlantUmlVisitor createVisitor() {
        return new InterfaceCounterVisitor();
    }

    /**
     * Analiza el diagrama en texto plano y retorna el visitor con el conteo de interfaces
     * y la detección de duplicados.
     *
     * @param source código fuente del diagrama en PlantUML
     * @return instancia de {@link InterfaceCounterVisitor} con los resultados
     */
    public InterfaceCounterVisitor count(String source) {
        InterfaceCounterVisitor visitor = new InterfaceCounterVisitor();
        PlantUml plantUml = new PlantUml();
        visitor.visit(plantUml.parse(source));
        return visitor;
    }

    /**
     * Analiza el diagrama desde un archivo en disco.
     *
     * @param file ruta al archivo .puml
     * @return instancia de {@link InterfaceCounterVisitor} con los resultados
     * @throws IOException si ocurre un error de lectura
     */
    public InterfaceCounterVisitor count(Path file) throws IOException {
        return count(Files.readString(file));
    }
}

