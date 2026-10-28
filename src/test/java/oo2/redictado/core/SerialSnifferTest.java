package oo2.redictado.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class SerialSnifferTest {
    DiagramContext diagram = new PlantUml().parse("""
           @startuml
           class Printer {
               +print()
           }
           @enduml
           """);

    CodeSniffer<DiagramContext> smellySniffer(String name) {
        return (tree, report) -> report.addAroma(new Aroma(name, "It stinks", 1));
    }

    @Test
    public void testRunsSniffersInOrder() {
        SerialSniffer<DiagramContext> serialSniffer = new SerialSniffer<>();
        serialSniffer.addSniffer(smellySniffer("First"));
        serialSniffer.addSniffer(smellySniffer("Second"));
        AromaReport report = new AromaReport();
        serialSniffer.sniff(diagram, report);

        assertEquals(2, report.getAromas().size());
        assertEquals("First", report.getAromas().get(0).name());
        assertEquals("Second", report.getAromas().get(1).name());
    }

    @Test
    public void testContainsOtherSerialSniffers() {
        SerialSniffer<DiagramContext> inner = new SerialSniffer<>();
        inner.addSniffer(smellySniffer("Inner"));
        inner.addSniffer(smellySniffer("Inner"));
        SerialSniffer<DiagramContext> outer = new SerialSniffer<>();
        outer.addSniffer(inner);
        outer.addSniffer(smellySniffer("Outer"));
        AromaReport report = new AromaReport();
        outer.sniff(diagram, report);

        assertEquals(3, report.getAromas().size());
    }

    @Test
    public void testSniffersDoNotSeeChangesMadeByOthers() {
        List<String> treesSeen = new ArrayList<>();
        SerialSniffer<DiagramContext> serialSniffer = new SerialSniffer<>();
        serialSniffer.addSniffer((tree, report) -> tree.children.clear());
        serialSniffer.addSniffer((tree, report) -> treesSeen.add(tree.toStringTree()));

        String originalTree = diagram.toStringTree();
        serialSniffer.sniff(diagram, new AromaReport());

        assertEquals(List.of(originalTree), treesSeen);
        assertEquals(originalTree, diagram.toStringTree());
    }
}
