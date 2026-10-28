package oo2.redictado.core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import oo2.redictado.plantuml.PlantUml;
import oo2.redictado.plantuml.antlr4.PlantUmlParser.DiagramContext;

public class TreeCopierTest {

    static Stream<Path> examples() throws IOException {
        return Files.list(Path.of("documentation/PlantUML/Examples"));
    }

    @ParameterizedTest
    @MethodSource("examples")
    public void testCopyIsEqual(Path example) throws IOException {
        DiagramContext original = new PlantUml().parse(Files.readString(example));
        DiagramContext copy = TreeCopier.copy(original);

        assertEquals(original.toStringTree(), copy.toStringTree());
        List<ParseTree> originalNodes = nodes(original);
        List<ParseTree> copyNodes = nodes(copy);
        for (int i = 0; i < originalNodes.size(); i++) {
            ParseTree originalNode = originalNodes.get(i);
            ParseTree copyNode = copyNodes.get(i);
            assertEquals(originalNode.getClass(), copyNode.getClass());
            assertEquals(originalNode.getText(), copyNode.getText());
            if (originalNode instanceof ParserRuleContext context) {
                ParserRuleContext copyContext = (ParserRuleContext) copyNode;
                assertEquals(context.getStart().getLine(), copyContext.getStart().getLine());
                assertEquals(context.getStop().getLine(), copyContext.getStop().getLine());
            }
        }
    }

    @ParameterizedTest
    @MethodSource("examples")
    public void testCopySharesNothing(Path example) throws IOException {
        DiagramContext original = new PlantUml().parse(Files.readString(example));
        DiagramContext copy = TreeCopier.copy(original);

        Set<Object> originalObjects = Collections.newSetFromMap(new IdentityHashMap<>());
        for (ParseTree node : nodes(original)) {
            originalObjects.add(node);
            if (node instanceof TerminalNode terminal) {
                originalObjects.add(terminal.getSymbol());
            }
        }
        for (ParseTree node : nodes(copy)) {
            assertFalse(originalObjects.contains(node));
            assertFalse(originalObjects.contains(node.getParent()));
            if (node instanceof TerminalNode terminal) {
                assertFalse(originalObjects.contains(terminal.getSymbol()));
            }
            if (node instanceof ParserRuleContext context) {
                assertFalse(originalObjects.contains(context.getStart()));
                assertFalse(originalObjects.contains(context.getStop()));
            }
        }
    }

    private static List<ParseTree> nodes(ParseTree root) {
        List<ParseTree> nodes = new ArrayList<>();
        nodes.add(root);
        for (int i = 0; i < root.getChildCount(); i++) {
            nodes.addAll(nodes(root.getChild(i)));
        }
        return nodes;
    }
}
