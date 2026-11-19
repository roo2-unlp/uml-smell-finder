package oo2.redictado.sniffers.hubclass;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

public class HubClassSnifferVisitor extends PlantUmlVisitor {
    private final Map<String, PlantUmlParser.ClassDeclarationContext> classes = new LinkedHashMap<>();
    private final Map<String, String> aliases = new LinkedHashMap<>();
    private final Map<String, Set<String>> knownBy = new LinkedHashMap<>();
    private final List<Reference> references = new ArrayList<>();

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        Void result = visitChildren(ctx);

        for (Reference reference : references) {
            String source = resolve(reference.source());
            String target = resolve(reference.target());
            if (source != null && target != null && !source.equals(target)) {
                knownBy.get(target).add(source);
            }
        }

        // calculo del umbral de relaciones
        int minimumKnownBy = Math.max(3, classes.size() / 2);
        for (Map.Entry<String, PlantUmlParser.ClassDeclarationContext> entry : classes.entrySet()) {
            int count = knownBy.get(entry.getKey()).size();
            if (count >= minimumKnownBy) {
                smell(entry.getKey() + " es una Hub Class: la conocen " + count + " clases.", entry.getValue());
            }
        }
        return result;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        String name = className(ctx.className());
        classes.putIfAbsent(name, ctx);
        knownBy.computeIfAbsent(name, ignored -> new LinkedHashSet<>());

        PlantUmlParser.ClassTailContext tail = ctx.classTail();
        if (tail != null && tail.aliasClause() != null) {
            aliases.put(tail.aliasClause().alias().getText(), name);
        }

        if (tail != null && tail.classBody() != null) {
            for (PlantUmlParser.BodyItemContext item : tail.classBody().bodyItem()) {
                if (item.member() != null) {
                    addMemberReferences(name, item.member().memberSignature());
                }
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        addMemberReferences(className(ctx.className()), ctx.memberSignature());
        return visitChildren(ctx);
    }

    @Override
    public Void visitRelationship(PlantUmlParser.RelationshipContext ctx) {
        String arrow = ctx.ARROW().getText();

        // PlantUML marca las relaciones de herencia e interfaces con flechas con (|).
        // Son obviadas intencionalmente.
        if (!arrow.contains("|")) {
            String left = className(ctx.relEndpoint(0).className());
            String right = className(ctx.relEndpoint(1).className());

            // La punta (< o >) apunta a la clase conocida y el rombo (o o *) queda del lado
            // de la clase que conoce. Si la flecha no tiene ninguno, se lee de izquierda a derecha.
            boolean leftKnowsRight = arrow.endsWith(">") || arrow.startsWith("o") || arrow.startsWith("*");
            boolean rightKnowsLeft = arrow.startsWith("<") || arrow.endsWith("o") || arrow.endsWith("*");
            if (!leftKnowsRight && !rightKnowsLeft) {
                leftKnowsRight = true;
            }

            if (leftKnowsRight) {
                references.add(new Reference(left, right));
            }
            if (rightKnowsLeft) {
                references.add(new Reference(right, left));
            }
        }
        return visitChildren(ctx);
    }

    private void addMemberReferences(String owner, PlantUmlParser.MemberSignatureContext signature) {
        if (signature == null) {
            return;
        }

        if (signature.fieldSignature() != null) {
            for (PlantUmlParser.TypeContext type : signature.fieldSignature().type()) {
                addTypeReferences(owner, type);
            }
            return;
        }

        if (signature.methodSignature() != null) {
            PlantUmlParser.MethodSignatureContext method = signature.methodSignature();
            for (PlantUmlParser.TypeContext type : method.type()) {
                addTypeReferences(owner, type);
            }
            if (method.paramList() != null) {
                for (PlantUmlParser.ParamContext param : method.paramList().param()) {
                    if (param.type() != null) {
                        addTypeReferences(owner, param.type());
                    }
                }
            }
        }
    }

    private void addTypeReferences(String owner, PlantUmlParser.TypeContext type) {
        references.add(new Reference(owner, type.IDENTIFIER().getText()));
        if (type.typeList() != null) {
            for (PlantUmlParser.TypeContext nested : type.typeList().type()) {
                addTypeReferences(owner, nested);
            }
        }
    }

    private String resolve(String name) {
        String resolved = aliases.getOrDefault(name, name);
        return classes.containsKey(resolved) ? resolved : null;
    }

    private String className(PlantUmlParser.ClassNameContext ctx) {
        String text = ctx.getText();
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }

    private record Reference(String source, String target) {
    }
}
