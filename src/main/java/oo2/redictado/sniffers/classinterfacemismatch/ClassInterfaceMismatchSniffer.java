package oo2.redictado.sniffers.classinterfacemismatch;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

// Sniffer del class/interface mismatch: busca clases que dicen implementar una interfaz y no la cumplen
public class ClassInterfaceMismatchSniffer extends PlantUmlSniffer {

    @Override
    protected PlantUmlVisitor createVisitor() {
        return new ClassInterfaceMismatchVisitor();
    }
}
