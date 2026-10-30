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

}
