package oo2.redictado.sniffers.interfacecounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ParserRuleContext;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

/**
 * Visitor que recorre el árbol sintáctico de un diagrama PlantUML, cuenta las interfaces,
 * detecta si existen interfaces duplicadas y reporta el mal olor correspondiente.
 */
public class InterfaceCounterVisitor extends PlantUmlVisitor {
    private final List<String> allInterfaceNames = new ArrayList<>();
    private final Set<String> distinctInterfaceNames = new LinkedHashSet<>();
    private final Set<String> duplicateInterfaces = new LinkedHashSet<>();
    private final Map<String, Integer> interfaceCounts = new LinkedHashMap<>();

    private final Set<String> classNames = new LinkedHashSet<>();
    private final Map<String, List<String>> methodsByType = new LinkedHashMap<>();
    private final List<String> detectedSmells = new ArrayList<>();

    public int getInterfaceCount() {
        return distinctInterfaceNames.size();
    }

    public int getTotalInterfaceCount() {
        return allInterfaceNames.size();
    }

    public Set<String> getInterfaces() {
        return Collections.unmodifiableSet(distinctInterfaceNames);
    }

    public Set<String> getInterfaceNames() {
        return Collections.unmodifiableSet(distinctInterfaceNames);
    }

    public List<String> getAllInterfaces() {
        return Collections.unmodifiableList(allInterfaceNames);
    }

    public Map<String, Integer> getInterfaceCounts() {
        return Collections.unmodifiableMap(interfaceCounts);
    }

    public boolean hasDuplicateInterfaces() {
        return !duplicateInterfaces.isEmpty();
    }

    public boolean hasDuplicates() {
        return !duplicateInterfaces.isEmpty();
    }

    public List<String> getDuplicateInterfaces() {
        return new ArrayList<>(duplicateInterfaces);
    }

    public Set<String> getDuplicateInterfaceNames() {
        return Collections.unmodifiableSet(duplicateInterfaces);
    }

    public int getDuplicateCount() {
        return duplicateInterfaces.size();
    }

    public List<String> getDetectedSmells() {
        return Collections.unmodifiableList(detectedSmells);
    }

    /**
     * @return string con el reporte de las interfaces duplicadas finalizando con la cantidad de interfaces detectadas
     */
    public String getDuplicatesString() {
        if (!hasDuplicateInterfaces()) {
            return "";
        }
        return String.join("\n", detectedSmells);
    }

    public String getDuplicateInterfacesString() {
        return getDuplicatesString();
    }

    public Set<String> getClasses() {
        return Collections.unmodifiableSet(classNames);
    }

    public int getClassCount() {
        return classNames.size();
    }

    public Map<String, List<String>> getInterfacesWithMethods() {
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (String name : distinctInterfaceNames) {
            result.put(name, List.copyOf(methodsByType.getOrDefault(name, List.of())));
        }
        return Collections.unmodifiableMap(result);
    }

    public Map<String, List<String>> getClassesWithMethods() {
        Map<String, List<String>> result = new LinkedHashMap<>();
        for (String name : classNames) {
            result.put(name, List.copyOf(methodsByType.getOrDefault(name, List.of())));
        }
        return Collections.unmodifiableMap(result);
    }

    public List<String> methodsOf(String typeName) {
        return List.copyOf(methodsByType.getOrDefault(typeName, List.of()));
    }

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        Void result = visitChildren(ctx);
        if (hasDuplicateInterfaces()) {
            String countMsg = "Cantidad de interfaces: " + getInterfaceCount();
            detectedSmells.add(countMsg);
            smell(countMsg, ctx);
        }
        return result;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        String name = extractName(ctx.className());
        if (isInterface(ctx)) {
            allInterfaceNames.add(name);
            int count = interfaceCounts.getOrDefault(name, 0) + 1;
            interfaceCounts.put(name, count);

            if (count > 1) {
                duplicateInterfaces.add(name);
                String msg = "Interface duplicada: " + name;
                detectedSmells.add("line " + ctx.getStart().getLine() + ": " + msg);
                smell(msg, ctx);
            } else {
                distinctInterfaceNames.add(name);
                classNames.remove(name);
            }
        } else if (isClass(ctx) && !distinctInterfaceNames.contains(name)) {
            classNames.add(name);
        }

        PlantUmlParser.ClassTailContext tail = ctx.classTail();
        if (tail != null && tail.classBody() != null) {
            for (PlantUmlParser.BodyItemContext item : tail.classBody().bodyItem()) {
                if (item.member() != null && item.member().memberSignature() != null
                        && item.member().memberSignature().methodSignature() != null) {
                    addMethod(name, item.member().visibility(), item.member().memberSignature().methodSignature());
                }
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        if (ctx.memberSignature() != null && ctx.memberSignature().methodSignature() != null) {
            addMethod(extractName(ctx.className()), ctx.visibility(), ctx.memberSignature().methodSignature());
        }
        return visitChildren(ctx);
    }

    @Override
    protected void smell(String message, ParserRuleContext where) {
        try {
            super.smell(message, where);
        } catch (NullPointerException ignored) {
            // Permite invocar visit() directamente sin pasar por el SmellCollector del framework
        }
    }

    private void addMethod(String typeName,
                           PlantUmlParser.VisibilityContext vis,
                           PlantUmlParser.MethodSignatureContext sig) {
        methodsByType.computeIfAbsent(typeName, k -> new ArrayList<>()).add(formatMethod(vis, sig));
    }

    private String formatMethod(PlantUmlParser.VisibilityContext vis,
                                PlantUmlParser.MethodSignatureContext sig) {
        String visibility = vis != null ? vis.getText() + " " : "";
        String name = sig.IDENTIFIER().getText();
        String params = sig.paramList() != null ? formatParams(sig.paramList()) : "";
        String returnType = extractReturnType(sig);
        return visibility + name + "(" + params + ")" + (returnType != null ? " : " + returnType : "");
    }

    private String formatParams(PlantUmlParser.ParamListContext ctx) {
        List<String> formatted = new ArrayList<>();
        for (PlantUmlParser.ParamContext p : ctx.param()) {
            if (p.COLON() != null) {
                formatted.add(p.IDENTIFIER().getText() + " : " + p.type().getText());
            } else if (p.type() != null) {
                formatted.add(p.type().getText() + " " + p.IDENTIFIER().getText());
            } else {
                formatted.add(p.IDENTIFIER().getText());
            }
        }
        return String.join(", ", formatted);
    }

    private String extractReturnType(PlantUmlParser.MethodSignatureContext sig) {
        if (sig.COLON() != null && sig.type() != null && !sig.type().isEmpty()) {
            return sig.type(sig.type().size() - 1).getText();
        }
        if (sig.type() != null && !sig.type().isEmpty()) {
            return sig.type(0).getText();
        }
        return null;
    }

    private boolean isInterface(PlantUmlParser.ClassDeclarationContext ctx) {
        String kind = ctx.classKind() != null ? ctx.classKind().getText().toLowerCase() : "";
        if ("interface".equals(kind)) {
            return true;
        }
        PlantUmlParser.ClassTailContext tail = ctx.classTail();
        if (tail != null && tail.stereotype() != null) {
            return tail.stereotype().getText().toLowerCase().contains("interface");
        }
        return false;
    }

    private boolean isClass(PlantUmlParser.ClassDeclarationContext ctx) {
        if (isInterface(ctx)) {
            return false;
        }
        if (ctx.ABSTRACT() != null) {
            return true;
        }
        String kind = ctx.classKind() != null ? ctx.classKind().getText().toLowerCase() : "";
        return "class".equals(kind);
    }

    private String extractName(PlantUmlParser.ClassNameContext ctx) {
        if (ctx == null) {
            return "";
        }
        String text = ctx.getText();
        if (text.length() >= 2 && text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }
}

