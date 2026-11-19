package oo2.redictado.sniffers.publicvariable;

import java.util.HashSet;
import java.util.Set;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

public class PublicVariableSnifferVisitor extends PlantUmlVisitor {
    private static final String MESSAGE = "The diagram has public variables.";

    private final Set<String> unsupportedClasses = new HashSet<>();
    private boolean collectingDeclarations;

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        unsupportedClasses.clear();
        collectingDeclarations = true;
        visitChildren(ctx);
        collectingDeclarations = false;
        return visitChildren(ctx);
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        if (isSupportedKind(ctx)) {
            return visitChildren(ctx);
        }
        if (collectingDeclarations) {
            unsupportedClasses.add(ctx.className().getText());
        }
        return null;
    }

    @Override
    public Void visitMember(PlantUmlParser.MemberContext ctx) {
        if (!collectingDeclarations && isPublicField(ctx.visibility(), ctx.memberSignature())) {
            this.smell(MESSAGE, ctx);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        if (!collectingDeclarations
                && !unsupportedClasses.contains(ctx.className().getText())
                && isPublicField(ctx.visibility(), ctx.memberSignature())) {
            this.smell(MESSAGE, ctx);
        }
        return visitChildren(ctx);
    }

    private boolean isSupportedKind(PlantUmlParser.ClassDeclarationContext ctx) {
        PlantUmlParser.ClassKindContext kind = ctx.classKind();
        return kind == null || kind.CLASS() != null || kind.INTERFACE() != null;
    }

    private boolean isPublicField(PlantUmlParser.VisibilityContext visibility,
                                  PlantUmlParser.MemberSignatureContext signature) {
        return visibility != null
                && visibility.PLUS() != null
                && signature.fieldSignature() != null;
    }
}