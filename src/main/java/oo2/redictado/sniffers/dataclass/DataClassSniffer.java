package oo2.redictado.sniffers.dataclass;

import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

public class DataClassSniffer extends PlantUmlSniffer {

    protected PlantUmlVisitor createVisitor(){
        return new DataClassVisitor();
    }
}