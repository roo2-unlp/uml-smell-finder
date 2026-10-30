package oo2.redictado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import oo2.redictado.core.CodeSniffer;
import oo2.redictado.core.PlainTextFormatter;
import oo2.redictado.core.ReportFormatter;
import oo2.redictado.core.SmellFinder;
import oo2.redictado.core.SnifferCatalog;
import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;
import oo2.redictado.sniffers.interfacecounter.InterfaceCounterSniffer;
import oo2.redictado.sniffers.print.PrintSniffer;

/**
 * Runs the sniffers over a PlantUML file and prints the report.
 */
public class UmlSmellFinder {

    public static void main(String[] args) throws IOException {
        CodeSniffer<DiagramContext> sniffer;
        String file;
        if (args.length == 1) {
            sniffer = catalog().all();
            file = args[0];
        } else if (args.length == 3 && args[0].equals("--sniffers")) {
            try {
                sniffer = catalog().sniffers(args[1]);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                return;
            }
            file = args[2];
        } else {
            System.err.println("Usage: UmlSmellFinder [--sniffers <name>[:<param>=<value>...],...] <diagram.puml>");
            return;
        }
        String code = Files.readString(Path.of(file));
        SmellFinder<DiagramContext> finder = new SmellFinder<>(new PlantUml(), sniffer);
        ReportFormatter formatter = new PlainTextFormatter();
        System.out.print(formatter.format(finder.sniff(code)));
    }

    /**
     * @return every sniffer of the project
     */
    public static SnifferCatalog<DiagramContext> catalog() {
        SnifferCatalog<DiagramContext> catalog = new SnifferCatalog<>();
        // Add one line per sniffer
        catalog.add("PrintSniffer", params -> new PrintSniffer());
        catalog.add("InterfaceCounterSniffer", params -> new InterfaceCounterSniffer());
        return catalog;
    }
}
