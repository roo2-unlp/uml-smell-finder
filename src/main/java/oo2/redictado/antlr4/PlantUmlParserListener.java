// Generated from PlantUmlParser.g4 by ANTLR 4.13.2
package oo2.redictado.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PlantUmlParser}.
 */
public interface PlantUmlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#diagram}.
	 * @param ctx the parse tree
	 */
	void enterDiagram(PlantUmlParser.DiagramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#diagram}.
	 * @param ctx the parse tree
	 */
	void exitDiagram(PlantUmlParser.DiagramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#diagramName}.
	 * @param ctx the parse tree
	 */
	void enterDiagramName(PlantUmlParser.DiagramNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#diagramName}.
	 * @param ctx the parse tree
	 */
	void exitDiagramName(PlantUmlParser.DiagramNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(PlantUmlParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(PlantUmlParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#titleStatement}.
	 * @param ctx the parse tree
	 */
	void enterTitleStatement(PlantUmlParser.TitleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#titleStatement}.
	 * @param ctx the parse tree
	 */
	void exitTitleStatement(PlantUmlParser.TitleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#directionStatement}.
	 * @param ctx the parse tree
	 */
	void enterDirectionStatement(PlantUmlParser.DirectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#directionStatement}.
	 * @param ctx the parse tree
	 */
	void exitDirectionStatement(PlantUmlParser.DirectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPackageDeclaration(PlantUmlParser.PackageDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#packageDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPackageDeclaration(PlantUmlParser.PackageDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDeclaration(PlantUmlParser.NamespaceDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDeclaration(PlantUmlParser.NamespaceDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(PlantUmlParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(PlantUmlParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#classDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#classTail}.
	 * @param ctx the parse tree
	 */
	void enterClassTail(PlantUmlParser.ClassTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#classTail}.
	 * @param ctx the parse tree
	 */
	void exitClassTail(PlantUmlParser.ClassTailContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(PlantUmlParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(PlantUmlParser.ClassModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#classKind}.
	 * @param ctx the parse tree
	 */
	void enterClassKind(PlantUmlParser.ClassKindContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#classKind}.
	 * @param ctx the parse tree
	 */
	void exitClassKind(PlantUmlParser.ClassKindContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(PlantUmlParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(PlantUmlParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#aliasClause}.
	 * @param ctx the parse tree
	 */
	void enterAliasClause(PlantUmlParser.AliasClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#aliasClause}.
	 * @param ctx the parse tree
	 */
	void exitAliasClause(PlantUmlParser.AliasClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(PlantUmlParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(PlantUmlParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#extendsClause}.
	 * @param ctx the parse tree
	 */
	void enterExtendsClause(PlantUmlParser.ExtendsClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#extendsClause}.
	 * @param ctx the parse tree
	 */
	void exitExtendsClause(PlantUmlParser.ExtendsClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#implementsClause}.
	 * @param ctx the parse tree
	 */
	void enterImplementsClause(PlantUmlParser.ImplementsClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#implementsClause}.
	 * @param ctx the parse tree
	 */
	void exitImplementsClause(PlantUmlParser.ImplementsClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#genericParams}.
	 * @param ctx the parse tree
	 */
	void enterGenericParams(PlantUmlParser.GenericParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#genericParams}.
	 * @param ctx the parse tree
	 */
	void exitGenericParams(PlantUmlParser.GenericParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(PlantUmlParser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(PlantUmlParser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PlantUmlParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PlantUmlParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#stereotype}.
	 * @param ctx the parse tree
	 */
	void enterStereotype(PlantUmlParser.StereotypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#stereotype}.
	 * @param ctx the parse tree
	 */
	void exitStereotype(PlantUmlParser.StereotypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#color}.
	 * @param ctx the parse tree
	 */
	void enterColor(PlantUmlParser.ColorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#color}.
	 * @param ctx the parse tree
	 */
	void exitColor(PlantUmlParser.ColorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(PlantUmlParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(PlantUmlParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#bodyItem}.
	 * @param ctx the parse tree
	 */
	void enterBodyItem(PlantUmlParser.BodyItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#bodyItem}.
	 * @param ctx the parse tree
	 */
	void exitBodyItem(PlantUmlParser.BodyItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMember(PlantUmlParser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMember(PlantUmlParser.MemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#memberModifier}.
	 * @param ctx the parse tree
	 */
	void enterMemberModifier(PlantUmlParser.MemberModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#memberModifier}.
	 * @param ctx the parse tree
	 */
	void exitMemberModifier(PlantUmlParser.MemberModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#visibility}.
	 * @param ctx the parse tree
	 */
	void enterVisibility(PlantUmlParser.VisibilityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#visibility}.
	 * @param ctx the parse tree
	 */
	void exitVisibility(PlantUmlParser.VisibilityContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#memberSignature}.
	 * @param ctx the parse tree
	 */
	void enterMemberSignature(PlantUmlParser.MemberSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#memberSignature}.
	 * @param ctx the parse tree
	 */
	void exitMemberSignature(PlantUmlParser.MemberSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void enterMethodSignature(PlantUmlParser.MethodSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void exitMethodSignature(PlantUmlParser.MethodSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(PlantUmlParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(PlantUmlParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(PlantUmlParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(PlantUmlParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#fieldSignature}.
	 * @param ctx the parse tree
	 */
	void enterFieldSignature(PlantUmlParser.FieldSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#fieldSignature}.
	 * @param ctx the parse tree
	 */
	void exitFieldSignature(PlantUmlParser.FieldSignatureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#memberExternal}.
	 * @param ctx the parse tree
	 */
	void enterMemberExternal(PlantUmlParser.MemberExternalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#memberExternal}.
	 * @param ctx the parse tree
	 */
	void exitMemberExternal(PlantUmlParser.MemberExternalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#relationship}.
	 * @param ctx the parse tree
	 */
	void enterRelationship(PlantUmlParser.RelationshipContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#relationship}.
	 * @param ctx the parse tree
	 */
	void exitRelationship(PlantUmlParser.RelationshipContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#relEndpoint}.
	 * @param ctx the parse tree
	 */
	void enterRelEndpoint(PlantUmlParser.RelEndpointContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#relEndpoint}.
	 * @param ctx the parse tree
	 */
	void exitRelEndpoint(PlantUmlParser.RelEndpointContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void enterCardinality(PlantUmlParser.CardinalityContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#cardinality}.
	 * @param ctx the parse tree
	 */
	void exitCardinality(PlantUmlParser.CardinalityContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(PlantUmlParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(PlantUmlParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#note}.
	 * @param ctx the parse tree
	 */
	void enterNote(PlantUmlParser.NoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#note}.
	 * @param ctx the parse tree
	 */
	void exitNote(PlantUmlParser.NoteContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#notePosition}.
	 * @param ctx the parse tree
	 */
	void enterNotePosition(PlantUmlParser.NotePositionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#notePosition}.
	 * @param ctx the parse tree
	 */
	void exitNotePosition(PlantUmlParser.NotePositionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#noteTarget}.
	 * @param ctx the parse tree
	 */
	void enterNoteTarget(PlantUmlParser.NoteTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#noteTarget}.
	 * @param ctx the parse tree
	 */
	void exitNoteTarget(PlantUmlParser.NoteTargetContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#noteBody}.
	 * @param ctx the parse tree
	 */
	void enterNoteBody(PlantUmlParser.NoteBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#noteBody}.
	 * @param ctx the parse tree
	 */
	void exitNoteBody(PlantUmlParser.NoteBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#noteLine}.
	 * @param ctx the parse tree
	 */
	void enterNoteLine(PlantUmlParser.NoteLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#noteLine}.
	 * @param ctx the parse tree
	 */
	void exitNoteLine(PlantUmlParser.NoteLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#hideShowDirective}.
	 * @param ctx the parse tree
	 */
	void enterHideShowDirective(PlantUmlParser.HideShowDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#hideShowDirective}.
	 * @param ctx the parse tree
	 */
	void exitHideShowDirective(PlantUmlParser.HideShowDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#skinparamDirective}.
	 * @param ctx the parse tree
	 */
	void enterSkinparamDirective(PlantUmlParser.SkinparamDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#skinparamDirective}.
	 * @param ctx the parse tree
	 */
	void exitSkinparamDirective(PlantUmlParser.SkinparamDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(PlantUmlParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(PlantUmlParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PlantUmlParser#restOfLine}.
	 * @param ctx the parse tree
	 */
	void enterRestOfLine(PlantUmlParser.RestOfLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link PlantUmlParser#restOfLine}.
	 * @param ctx the parse tree
	 */
	void exitRestOfLine(PlantUmlParser.RestOfLineContext ctx);
}