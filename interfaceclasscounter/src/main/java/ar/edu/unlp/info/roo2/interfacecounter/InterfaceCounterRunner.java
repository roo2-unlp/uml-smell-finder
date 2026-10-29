package ar.edu.unlp.info.roo2.interfacecounter;

import java.io.IOException;
import java.nio.file.Path;

public class InterfaceCounterRunner {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Uso: InterfaceCounterRunner <archivo.puml>");
            System.exit(1);
        }

        Path path = Path.of(args[0]);
        InterfaceCounter.Result result = new InterfaceCounter().count(path);

        System.out.printf("Interfaces encontradas: %d%n", result.interfaceCount());
        result.interfaces().forEach((name, methods) -> {
            System.out.println(" - " + name);
            if (methods.isEmpty()) {
                System.out.println("     (sin métodos)");
            } else {
                methods.forEach(m -> System.out.println("     * " + m));
            }
        });

        System.out.printf("%nClases encontradas: %d%n", result.classCount());
        result.classes().forEach((name, methods) -> {
            System.out.println(" - " + name);
            if (methods.isEmpty()) {
                System.out.println("     (sin métodos)");
            } else {
                methods.forEach(m -> System.out.println("     * " + m));
            }
        });
    }
}
