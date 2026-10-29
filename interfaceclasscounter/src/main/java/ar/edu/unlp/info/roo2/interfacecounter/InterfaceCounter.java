package ar.edu.unlp.info.roo2.interfacecounter;

import ar.edu.unlp.info.roo2.parsing.antlr.PlantUmlParsing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterfaceCounter {
    public record Result(Map<String, List<String>> interfaces, Map<String, List<String>> classes) {
        public int count() {
            return interfaces.size();
        }

        public int interfaceCount() {
            return interfaces.size();
        }

        public int classCount() {
            return classes.size();
        }

        public Set<String> interfaceNames() {
            return interfaces.keySet();
        }

        public Set<String> classNames() {
            return classes.keySet();
        }

        public List<String> methodsOf(String typeName) {
            return interfaces.getOrDefault(typeName, classes.getOrDefault(typeName, List.of()));
        }
    }

    public Result count(String source) {
        InterfaceCounterVisitor visitor = new InterfaceCounterVisitor();
        visitor.visit(PlantUmlParsing.parse(source).tree());
        TypeCollector collector = visitor.collectTypes();
        return new Result(collector.getInterfaces(), collector.getClasses());
    }

    public Result count(Path file) throws IOException {
        return count(Files.readString(file));
    }
}
