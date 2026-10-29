package ar.edu.unlp.info.roo2.interfacecounter;

import ar.edu.unlp.info.roo2.grammar.PlantUMLClassDiagramBaseVisitor;
import ar.edu.unlp.info.roo2.grammar.PlantUMLClassDiagramParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InterfaceCounterVisitor extends PlantUMLClassDiagramBaseVisitor<Void> {
    private static final Map<String, Function<String, DeclaredType>> KIND_FACTORIES = Map.of(
            "interface", InterfaceType::new,
            "class", ClassType::new
    );

    private static final Map<String, Function<String, DeclaredType>> STEREOTYPE_FACTORIES = Map.of(
            "interface", InterfaceType::new
    );

    private final Map<String, DeclaredType> typesByName = new LinkedHashMap<>();
    private DeclaredType currentType = new UnclassifiedType("");
    private String currentVisibility = "";
    private List<String> currentParams = new ArrayList<>();

    public Set<String> getInterfaces() {
        return getInterfacesWithMethods().keySet();
    }

    public int getInterfaceCount() {
        return getInterfacesWithMethods().size();
    }

    public Map<String, List<String>> getInterfacesWithMethods() {
        return collectTypes().getInterfaces();
    }

    public Set<String> getClasses() {
        return getClassesWithMethods().keySet();
    }

    public int getClassCount() {
        return getClassesWithMethods().size();
    }

    public Map<String, List<String>> getClassesWithMethods() {
        return collectTypes().getClasses();
    }

    public TypeCollector collectTypes() {
        return TypeCollector.collectFrom(typesByName.values());
    }

    @Override
    public Void visitClassDeclaration(PlantUMLClassDiagramParser.ClassDeclarationContext ctx) {
        String name = extractName(ctx.className());
        currentType = new ClassType(name);
        visitChildren(ctx);
        registerType(currentType);
        currentType = new UnclassifiedType("");
        return null;
    }

    @Override
    public Void visitClassKind(PlantUMLClassDiagramParser.ClassKindContext ctx) {
        String kind = ctx.getText().toLowerCase();
        currentType = KIND_FACTORIES
                .getOrDefault(kind, UnclassifiedType::new)
                .apply(currentType.getName());
        return null;
    }

    @Override
    public Void visitStereotype(PlantUMLClassDiagramParser.StereotypeContext ctx) {
        String stereotype = ctx.getText().replace("<", "").replace(">", "").trim().toLowerCase();
        DeclaredType stereotypeType = STEREOTYPE_FACTORIES
                .getOrDefault(stereotype, UnclassifiedType::new)
                .apply(currentType.getName());
        currentType = currentType.mergeWith(stereotypeType);
        return null;
    }

    @Override
    public Void visitMember(PlantUMLClassDiagramParser.MemberContext ctx) {
        currentVisibility = "";
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUMLClassDiagramParser.MemberExternalContext ctx) {
        String name = extractName(ctx.className());
        currentType = new UnclassifiedType(name);
        currentVisibility = "";
        visitChildren(ctx);
        registerType(currentType);
        currentType = new UnclassifiedType("");
        return null;
    }

    @Override
    public Void visitVisibility(PlantUMLClassDiagramParser.VisibilityContext ctx) {
        currentVisibility = ctx.getText() + " ";
        return null;
    }

    @Override
    public Void visitMethodSignature(PlantUMLClassDiagramParser.MethodSignatureContext sig) {
        currentParams = new ArrayList<>();
        visitChildren(sig);
        String name = sig.IDENTIFIER().getText();
        String params = String.join(", ", currentParams);
        String returnType = extractReturnType(sig);
        currentType.addMethod(currentVisibility + name + "(" + params + ")" + returnType);
        return null;
    }

    @Override
    public Void visitParam(PlantUMLClassDiagramParser.ParamContext ctx) {
        String param = ctx.children.stream()
                .map(ParseTree::getText)
                .collect(Collectors.joining(" "));
        currentParams.add(param);
        return null;
    }

    private void registerType(DeclaredType type) {
        DeclaredType existing = typesByName.getOrDefault(type.getName(), new UnclassifiedType(type.getName()));
        typesByName.put(type.getName(), existing.mergeWith(type));
    }

    private String extractReturnType(PlantUMLClassDiagramParser.MethodSignatureContext sig) {
        return sig.type().stream()
                .reduce((first, second) -> second)
                .map(t -> " : " + t.getText())
                .orElse("");
    }

    private String extractName(PlantUMLClassDiagramParser.ClassNameContext ctx) {
        return ctx.getText().replace("\"", "");
    }
}
