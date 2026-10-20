// Generated from PlantUmlParser.g4 by ANTLR 4.13.2
package oo2.redictado.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PlantUmlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PlantUmlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#diagram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagram(PlantUmlParser.DiagramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#diagramName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiagramName(PlantUmlParser.DiagramNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(PlantUmlParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#titleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitleStatement(PlantUmlParser.TitleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#directionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectionStatement(PlantUmlParser.DirectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#packageDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageDeclaration(PlantUmlParser.PackageDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#namespaceDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceDeclaration(PlantUmlParser.NamespaceDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(PlantUmlParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#classDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#classTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassTail(PlantUmlParser.ClassTailContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#classModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(PlantUmlParser.ClassModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#classKind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassKind(PlantUmlParser.ClassKindContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(PlantUmlParser.ClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#aliasClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAliasClause(PlantUmlParser.AliasClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(PlantUmlParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#extendsClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsClause(PlantUmlParser.ExtendsClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#implementsClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplementsClause(PlantUmlParser.ImplementsClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#genericParams}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGenericParams(PlantUmlParser.GenericParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(PlantUmlParser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PlantUmlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#stereotype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStereotype(PlantUmlParser.StereotypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(PlantUmlParser.ColorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(PlantUmlParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#bodyItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBodyItem(PlantUmlParser.BodyItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#member}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMember(PlantUmlParser.MemberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#memberModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberModifier(PlantUmlParser.MemberModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#visibility}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVisibility(PlantUmlParser.VisibilityContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#memberSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberSignature(PlantUmlParser.MemberSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#methodSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(PlantUmlParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(PlantUmlParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#fieldSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldSignature(PlantUmlParser.FieldSignatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#memberExternal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberExternal(PlantUmlParser.MemberExternalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#relationship}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationship(PlantUmlParser.RelationshipContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#relEndpoint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelEndpoint(PlantUmlParser.RelEndpointContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#cardinality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCardinality(PlantUmlParser.CardinalityContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(PlantUmlParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#note}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNote(PlantUmlParser.NoteContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#notePosition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotePosition(PlantUmlParser.NotePositionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#noteTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteTarget(PlantUmlParser.NoteTargetContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#noteBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteBody(PlantUmlParser.NoteBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#noteLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteLine(PlantUmlParser.NoteLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#hideShowDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHideShowDirective(PlantUmlParser.HideShowDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#skinparamDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkinparamDirective(PlantUmlParser.SkinparamDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(PlantUmlParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlantUmlParser#restOfLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestOfLine(PlantUmlParser.RestOfLineContext ctx);
}