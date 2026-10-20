// Generated from PlantUmlParser.g4 by ANTLR 4.13.2
package oo2.redictado.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PlantUmlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STARTUML=1, ENDUML=2, CLASS=3, INTERFACE=4, ABSTRACT=5, ENUM=6, ENTITY=7, 
		ANNOTATION=8, EXCEPTION=9, METACLASS=10, STEREOTYPE_KW=11, PROTOCOL=12, 
		STRUCT=13, EXTENDS=14, IMPLEMENTS=15, PACKAGE=16, NAMESPACE=17, NOTE=18, 
		END=19, AS=20, OF=21, LEFT=22, RIGHT=23, TOP=24, BOTTOM=25, TO=26, DIRECTION=27, 
		HIDE=28, SHOW=29, SKINPARAM=30, TITLE=31, STATIC=32, STATIC_MOD=33, ABSTRACT_MOD=34, 
		LBRACE=35, RBRACE=36, LPAREN=37, RPAREN=38, LBRACK=39, RBRACK=40, DOUBLECOLON=41, 
		COLON=42, COMMA=43, SEMI=44, ASSIGN=45, DOUBLE_LT=46, DOUBLE_GT=47, LT=48, 
		GT=49, PLUS=50, MINUS=51, HASH=52, TILDE=53, ARROW=54, HEXCOLOR=55, COLORNAME=56, 
		STRING=57, IDENTIFIER=58, LINE_COMMENT=59, BLOCK_COMMENT=60, NEWLINE=61, 
		WS=62, OTHER=63;
	public static final int
		RULE_diagram = 0, RULE_diagramName = 1, RULE_element = 2, RULE_titleStatement = 3, 
		RULE_directionStatement = 4, RULE_packageDeclaration = 5, RULE_namespaceDeclaration = 6, 
		RULE_packageName = 7, RULE_classDeclaration = 8, RULE_classTail = 9, RULE_classModifier = 10, 
		RULE_classKind = 11, RULE_className = 12, RULE_aliasClause = 13, RULE_alias = 14, 
		RULE_extendsClause = 15, RULE_implementsClause = 16, RULE_genericParams = 17, 
		RULE_typeList = 18, RULE_type = 19, RULE_stereotype = 20, RULE_color = 21, 
		RULE_classBody = 22, RULE_bodyItem = 23, RULE_member = 24, RULE_memberModifier = 25, 
		RULE_visibility = 26, RULE_memberSignature = 27, RULE_methodSignature = 28, 
		RULE_paramList = 29, RULE_param = 30, RULE_fieldSignature = 31, RULE_memberExternal = 32, 
		RULE_relationship = 33, RULE_relEndpoint = 34, RULE_cardinality = 35, 
		RULE_label = 36, RULE_note = 37, RULE_notePosition = 38, RULE_noteTarget = 39, 
		RULE_noteBody = 40, RULE_noteLine = 41, RULE_hideShowDirective = 42, RULE_skinparamDirective = 43, 
		RULE_comment = 44, RULE_restOfLine = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"diagram", "diagramName", "element", "titleStatement", "directionStatement", 
			"packageDeclaration", "namespaceDeclaration", "packageName", "classDeclaration", 
			"classTail", "classModifier", "classKind", "className", "aliasClause", 
			"alias", "extendsClause", "implementsClause", "genericParams", "typeList", 
			"type", "stereotype", "color", "classBody", "bodyItem", "member", "memberModifier", 
			"visibility", "memberSignature", "methodSignature", "paramList", "param", 
			"fieldSignature", "memberExternal", "relationship", "relEndpoint", "cardinality", 
			"label", "note", "notePosition", "noteTarget", "noteBody", "noteLine", 
			"hideShowDirective", "skinparamDirective", "comment", "restOfLine"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@startuml'", "'@enduml'", "'class'", "'interface'", "'abstract'", 
			"'enum'", "'entity'", "'annotation'", "'exception'", "'metaclass'", "'stereotype'", 
			"'protocol'", "'struct'", "'extends'", "'implements'", "'package'", "'namespace'", 
			"'note'", "'end'", "'as'", "'of'", "'left'", "'right'", "'top'", "'bottom'", 
			"'to'", "'direction'", "'hide'", "'show'", "'skinparam'", "'title'", 
			"'static'", "'{static}'", "'{abstract}'", "'{'", "'}'", "'('", "')'", 
			"'['", "']'", "'::'", "':'", "','", "';'", "'='", "'<<'", "'>>'", "'<'", 
			"'>'", "'+'", "'-'", "'#'", "'~'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STARTUML", "ENDUML", "CLASS", "INTERFACE", "ABSTRACT", "ENUM", 
			"ENTITY", "ANNOTATION", "EXCEPTION", "METACLASS", "STEREOTYPE_KW", "PROTOCOL", 
			"STRUCT", "EXTENDS", "IMPLEMENTS", "PACKAGE", "NAMESPACE", "NOTE", "END", 
			"AS", "OF", "LEFT", "RIGHT", "TOP", "BOTTOM", "TO", "DIRECTION", "HIDE", 
			"SHOW", "SKINPARAM", "TITLE", "STATIC", "STATIC_MOD", "ABSTRACT_MOD", 
			"LBRACE", "RBRACE", "LPAREN", "RPAREN", "LBRACK", "RBRACK", "DOUBLECOLON", 
			"COLON", "COMMA", "SEMI", "ASSIGN", "DOUBLE_LT", "DOUBLE_GT", "LT", "GT", 
			"PLUS", "MINUS", "HASH", "TILDE", "ARROW", "HEXCOLOR", "COLORNAME", "STRING", 
			"IDENTIFIER", "LINE_COMMENT", "BLOCK_COMMENT", "NEWLINE", "WS", "OTHER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PlantUmlParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PlantUmlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DiagramContext extends ParserRuleContext {
		public TerminalNode STARTUML() { return getToken(PlantUmlParser.STARTUML, 0); }
		public TerminalNode ENDUML() { return getToken(PlantUmlParser.ENDUML, 0); }
		public TerminalNode EOF() { return getToken(PlantUmlParser.EOF, 0); }
		public DiagramNameContext diagramName() {
			return getRuleContext(DiagramNameContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public DiagramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_diagram; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterDiagram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitDiagram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitDiagram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiagramContext diagram() throws RecognitionException {
		DiagramContext _localctx = new DiagramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_diagram);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(STARTUML);
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(93);
				diagramName();
				}
				break;
			}
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(96);
					match(NEWLINE);
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4467570838694477816L) != 0)) {
				{
				{
				setState(102);
				element();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(ENDUML);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(109);
				match(NEWLINE);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DiagramNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public DiagramNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_diagramName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterDiagramName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitDiagramName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitDiagramName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DiagramNameContext diagramName() throws RecognitionException {
		DiagramNameContext _localctx = new DiagramNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_diagramName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElementContext extends ParserRuleContext {
		public TitleStatementContext titleStatement() {
			return getRuleContext(TitleStatementContext.class,0);
		}
		public DirectionStatementContext directionStatement() {
			return getRuleContext(DirectionStatementContext.class,0);
		}
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public NamespaceDeclarationContext namespaceDeclaration() {
			return getRuleContext(NamespaceDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public RelationshipContext relationship() {
			return getRuleContext(RelationshipContext.class,0);
		}
		public MemberExternalContext memberExternal() {
			return getRuleContext(MemberExternalContext.class,0);
		}
		public NoteContext note() {
			return getRuleContext(NoteContext.class,0);
		}
		public HideShowDirectiveContext hideShowDirective() {
			return getRuleContext(HideShowDirectiveContext.class,0);
		}
		public SkinparamDirectiveContext skinparamDirective() {
			return getRuleContext(SkinparamDirectiveContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_element);
		try {
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				titleStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				directionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(121);
				packageDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(122);
				namespaceDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(123);
				classDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				relationship();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(125);
				memberExternal();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(126);
				note();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(127);
				hideShowDirective();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(128);
				skinparamDirective();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(129);
				comment();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(130);
				match(NEWLINE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TitleStatementContext extends ParserRuleContext {
		public TerminalNode TITLE() { return getToken(PlantUmlParser.TITLE, 0); }
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public TitleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_titleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterTitleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitTitleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitTitleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleStatementContext titleStatement() throws RecognitionException {
		TitleStatementContext _localctx = new TitleStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_titleStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(TITLE);
			setState(134);
			restOfLine();
			setState(135);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DirectionStatementContext extends ParserRuleContext {
		public TerminalNode LEFT() { return getToken(PlantUmlParser.LEFT, 0); }
		public TerminalNode TO() { return getToken(PlantUmlParser.TO, 0); }
		public TerminalNode RIGHT() { return getToken(PlantUmlParser.RIGHT, 0); }
		public TerminalNode DIRECTION() { return getToken(PlantUmlParser.DIRECTION, 0); }
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public TerminalNode TOP() { return getToken(PlantUmlParser.TOP, 0); }
		public TerminalNode BOTTOM() { return getToken(PlantUmlParser.BOTTOM, 0); }
		public DirectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterDirectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitDirectionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitDirectionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectionStatementContext directionStatement() throws RecognitionException {
		DirectionStatementContext _localctx = new DirectionStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_directionStatement);
		try {
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEFT:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(LEFT);
				setState(138);
				match(TO);
				setState(139);
				match(RIGHT);
				setState(140);
				match(DIRECTION);
				setState(141);
				match(NEWLINE);
				}
				break;
			case TOP:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				match(TOP);
				setState(143);
				match(TO);
				setState(144);
				match(BOTTOM);
				setState(145);
				match(DIRECTION);
				setState(146);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageDeclarationContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(PlantUmlParser.PACKAGE, 0); }
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(PlantUmlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(PlantUmlParser.RBRACE, 0); }
		public StereotypeContext stereotype() {
			return getRuleContext(StereotypeContext.class,0);
		}
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitPackageDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitPackageDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_packageDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(PACKAGE);
			setState(150);
			packageName();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLE_LT) {
				{
				setState(151);
				stereotype();
				}
			}

			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HEXCOLOR || _la==COLORNAME) {
				{
				setState(154);
				color();
				}
			}

			setState(157);
			match(LBRACE);
			setState(161);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(158);
					match(NEWLINE);
					}
					} 
				}
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4467570838694477816L) != 0)) {
				{
				{
				setState(164);
				element();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			match(RBRACE);
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(171);
					match(NEWLINE);
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamespaceDeclarationContext extends ParserRuleContext {
		public TerminalNode NAMESPACE() { return getToken(PlantUmlParser.NAMESPACE, 0); }
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(PlantUmlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(PlantUmlParser.RBRACE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public NamespaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNamespaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNamespaceDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNamespaceDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceDeclarationContext namespaceDeclaration() throws RecognitionException {
		NamespaceDeclarationContext _localctx = new NamespaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_namespaceDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(NAMESPACE);
			setState(178);
			packageName();
			setState(179);
			match(LBRACE);
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(180);
					match(NEWLINE);
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4467570838694477816L) != 0)) {
				{
				{
				setState(186);
				element();
				}
				}
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(192);
			match(RBRACE);
			setState(196);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(193);
					match(NEWLINE);
					}
					} 
				}
				setState(198);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitPackageName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitPackageName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_packageName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public ClassTailContext classTail() {
			return getRuleContext(ClassTailContext.class,0);
		}
		public ClassKindContext classKind() {
			return getRuleContext(ClassKindContext.class,0);
		}
		public TerminalNode ABSTRACT() { return getToken(PlantUmlParser.ABSTRACT, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public List<ClassModifierContext> classModifier() {
			return getRuleContexts(ClassModifierContext.class);
		}
		public ClassModifierContext classModifier(int i) {
			return getRuleContext(ClassModifierContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ABSTRACT || _la==STATIC) {
					{
					{
					setState(201);
					classModifier();
					}
					}
					setState(206);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(207);
				classKind();
				}
				break;
			case 2:
				{
				setState(208);
				match(ABSTRACT);
				}
				break;
			}
			setState(211);
			className();
			setState(212);
			classTail();
			setState(216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(213);
					match(NEWLINE);
					}
					} 
				}
				setState(218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassTailContext extends ParserRuleContext {
		public GenericParamsContext genericParams() {
			return getRuleContext(GenericParamsContext.class,0);
		}
		public StereotypeContext stereotype() {
			return getRuleContext(StereotypeContext.class,0);
		}
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public AliasClauseContext aliasClause() {
			return getRuleContext(AliasClauseContext.class,0);
		}
		public ExtendsClauseContext extendsClause() {
			return getRuleContext(ExtendsClauseContext.class,0);
		}
		public ImplementsClauseContext implementsClause() {
			return getRuleContext(ImplementsClauseContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ClassTailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classTail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassTail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassTail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassTail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTailContext classTail() throws RecognitionException {
		ClassTailContext _localctx = new ClassTailContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classTail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(219);
				genericParams();
				}
			}

			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLE_LT) {
				{
				setState(222);
				stereotype();
				}
			}

			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HEXCOLOR || _la==COLORNAME) {
				{
				setState(225);
				color();
				}
			}

			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(228);
				aliasClause();
				}
			}

			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(231);
				extendsClause();
				}
			}

			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(234);
				implementsClause();
				}
			}

			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(237);
				classBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassModifierContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(PlantUmlParser.ABSTRACT, 0); }
		public TerminalNode STATIC() { return getToken(PlantUmlParser.STATIC, 0); }
		public ClassModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassModifierContext classModifier() throws RecognitionException {
		ClassModifierContext _localctx = new ClassModifierContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_la = _input.LA(1);
			if ( !(_la==ABSTRACT || _la==STATIC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassKindContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(PlantUmlParser.CLASS, 0); }
		public TerminalNode INTERFACE() { return getToken(PlantUmlParser.INTERFACE, 0); }
		public TerminalNode ENUM() { return getToken(PlantUmlParser.ENUM, 0); }
		public TerminalNode ENTITY() { return getToken(PlantUmlParser.ENTITY, 0); }
		public TerminalNode ANNOTATION() { return getToken(PlantUmlParser.ANNOTATION, 0); }
		public TerminalNode EXCEPTION() { return getToken(PlantUmlParser.EXCEPTION, 0); }
		public TerminalNode METACLASS() { return getToken(PlantUmlParser.METACLASS, 0); }
		public TerminalNode STEREOTYPE_KW() { return getToken(PlantUmlParser.STEREOTYPE_KW, 0); }
		public TerminalNode PROTOCOL() { return getToken(PlantUmlParser.PROTOCOL, 0); }
		public TerminalNode STRUCT() { return getToken(PlantUmlParser.STRUCT, 0); }
		public ClassKindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classKind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassKindContext classKind() throws RecognitionException {
		ClassKindContext _localctx = new ClassKindContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_classKind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16344L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_className);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasClauseContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(PlantUmlParser.AS, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public AliasClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aliasClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterAliasClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitAliasClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitAliasClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasClauseContext aliasClause() throws RecognitionException {
		AliasClauseContext _localctx = new AliasClauseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_aliasClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(AS);
			setState(247);
			alias();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtendsClauseContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(PlantUmlParser.EXTENDS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public ExtendsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterExtendsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitExtendsClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitExtendsClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendsClauseContext extendsClause() throws RecognitionException {
		ExtendsClauseContext _localctx = new ExtendsClauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_extendsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(EXTENDS);
			setState(252);
			typeList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImplementsClauseContext extends ParserRuleContext {
		public TerminalNode IMPLEMENTS() { return getToken(PlantUmlParser.IMPLEMENTS, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public ImplementsClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implementsClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterImplementsClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitImplementsClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitImplementsClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImplementsClauseContext implementsClause() throws RecognitionException {
		ImplementsClauseContext _localctx = new ImplementsClauseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_implementsClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(IMPLEMENTS);
			setState(255);
			typeList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericParamsContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(PlantUmlParser.LT, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(PlantUmlParser.GT, 0); }
		public GenericParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericParams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterGenericParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitGenericParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitGenericParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericParamsContext genericParams() throws RecognitionException {
		GenericParamsContext _localctx = new GenericParamsContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_genericParams);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(LT);
			setState(258);
			typeList();
			setState(259);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PlantUmlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PlantUmlParser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			type();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(262);
				match(COMMA);
				setState(263);
				type();
				}
				}
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode LT() { return getToken(PlantUmlParser.LT, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode GT() { return getToken(PlantUmlParser.GT, 0); }
		public TerminalNode LBRACK() { return getToken(PlantUmlParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PlantUmlParser.RBRACK, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(IDENTIFIER);
			setState(274);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(270);
				match(LT);
				setState(271);
				typeList();
				setState(272);
				match(GT);
				}
			}

			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(276);
				match(LBRACK);
				setState(277);
				match(RBRACK);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StereotypeContext extends ParserRuleContext {
		public TerminalNode DOUBLE_LT() { return getToken(PlantUmlParser.DOUBLE_LT, 0); }
		public List<TerminalNode> DOUBLE_GT() { return getTokens(PlantUmlParser.DOUBLE_GT); }
		public TerminalNode DOUBLE_GT(int i) {
			return getToken(PlantUmlParser.DOUBLE_GT, i);
		}
		public StereotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereotype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterStereotype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitStereotype(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitStereotype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StereotypeContext stereotype() throws RecognitionException {
		StereotypeContext _localctx = new StereotypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_stereotype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(DOUBLE_LT);
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -140737488355330L) != 0)) {
				{
				{
				setState(281);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==DOUBLE_GT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(287);
			match(DOUBLE_GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColorContext extends ParserRuleContext {
		public TerminalNode HEXCOLOR() { return getToken(PlantUmlParser.HEXCOLOR, 0); }
		public TerminalNode COLORNAME() { return getToken(PlantUmlParser.COLORNAME, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterColor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitColor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_color);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			_la = _input.LA(1);
			if ( !(_la==HEXCOLOR || _la==COLORNAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassBodyContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(PlantUmlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(PlantUmlParser.RBRACE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public List<BodyItemContext> bodyItem() {
			return getRuleContexts(BodyItemContext.class);
		}
		public BodyItemContext bodyItem(int i) {
			return getRuleContext(BodyItemContext.class,i);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitClassBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitClassBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_classBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(LBRACE);
			setState(295);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(292);
					match(NEWLINE);
					}
					} 
				}
				setState(297);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2628976308247330816L) != 0)) {
				{
				{
				setState(298);
				bodyItem();
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(304);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyItemContext extends ParserRuleContext {
		public MemberContext member() {
			return getRuleContext(MemberContext.class,0);
		}
		public TerminalNode ARROW() { return getToken(PlantUmlParser.ARROW, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public BodyItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bodyItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterBodyItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitBodyItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitBodyItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyItemContext bodyItem() throws RecognitionException {
		BodyItemContext _localctx = new BodyItemContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_bodyItem);
		try {
			int _alt;
			setState(315);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STATIC_MOD:
			case ABSTRACT_MOD:
			case PLUS:
			case MINUS:
			case HASH:
			case TILDE:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(306);
				member();
				}
				break;
			case ARROW:
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				match(ARROW);
				setState(311);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(308);
						match(NEWLINE);
						}
						} 
					}
					setState(313);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
				match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberContext extends ParserRuleContext {
		public MemberSignatureContext memberSignature() {
			return getRuleContext(MemberSignatureContext.class,0);
		}
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public List<MemberModifierContext> memberModifier() {
			return getRuleContexts(MemberModifierContext.class);
		}
		public MemberModifierContext memberModifier(int i) {
			return getRuleContext(MemberModifierContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public TerminalNode SEMI() { return getToken(PlantUmlParser.SEMI, 0); }
		public MemberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterMember(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitMember(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitMember(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberContext member() throws RecognitionException {
		MemberContext _localctx = new MemberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_member);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16888498602639360L) != 0)) {
				{
				setState(317);
				visibility();
				}
			}

			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STATIC_MOD || _la==ABSTRACT_MOD) {
				{
				{
				setState(320);
				memberModifier();
				}
				}
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(326);
			memberSignature();
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(327);
				_la = _input.LA(1);
				if ( !(_la==SEMI || _la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberModifierContext extends ParserRuleContext {
		public TerminalNode STATIC_MOD() { return getToken(PlantUmlParser.STATIC_MOD, 0); }
		public TerminalNode ABSTRACT_MOD() { return getToken(PlantUmlParser.ABSTRACT_MOD, 0); }
		public MemberModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterMemberModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitMemberModifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitMemberModifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberModifierContext memberModifier() throws RecognitionException {
		MemberModifierContext _localctx = new MemberModifierContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_memberModifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_la = _input.LA(1);
			if ( !(_la==STATIC_MOD || _la==ABSTRACT_MOD) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VisibilityContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(PlantUmlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PlantUmlParser.MINUS, 0); }
		public TerminalNode HASH() { return getToken(PlantUmlParser.HASH, 0); }
		public TerminalNode TILDE() { return getToken(PlantUmlParser.TILDE, 0); }
		public VisibilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_visibility; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterVisibility(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitVisibility(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitVisibility(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VisibilityContext visibility() throws RecognitionException {
		VisibilityContext _localctx = new VisibilityContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_visibility);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(332);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16888498602639360L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberSignatureContext extends ParserRuleContext {
		public MethodSignatureContext methodSignature() {
			return getRuleContext(MethodSignatureContext.class,0);
		}
		public FieldSignatureContext fieldSignature() {
			return getRuleContext(FieldSignatureContext.class,0);
		}
		public MemberSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterMemberSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitMemberSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitMemberSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberSignatureContext memberSignature() throws RecognitionException {
		MemberSignatureContext _localctx = new MemberSignatureContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_memberSignature);
		try {
			setState(336);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(334);
				methodSignature();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				fieldSignature();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MethodSignatureContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(PlantUmlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PlantUmlParser.RPAREN, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public MethodSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterMethodSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitMethodSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitMethodSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodSignatureContext methodSignature() throws RecognitionException {
		MethodSignatureContext _localctx = new MethodSignatureContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(338);
				type();
				}
				break;
			}
			setState(341);
			match(IDENTIFIER);
			setState(342);
			match(LPAREN);
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(343);
				paramList();
				}
			}

			setState(346);
			match(RPAREN);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(347);
				match(COLON);
				setState(348);
				type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PlantUmlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PlantUmlParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			param();
			setState(356);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(352);
				match(COMMA);
				setState(353);
				param();
				}
				}
				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_param);
		int _la;
		try {
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				match(IDENTIFIER);
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(360);
					match(COLON);
					setState(361);
					type();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(364);
				type();
				setState(365);
				match(IDENTIFIER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldSignatureContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public TerminalNode ASSIGN() { return getToken(PlantUmlParser.ASSIGN, 0); }
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public FieldSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterFieldSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitFieldSignature(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitFieldSignature(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldSignatureContext fieldSignature() throws RecognitionException {
		FieldSignatureContext _localctx = new FieldSignatureContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_fieldSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(369);
				type();
				}
				break;
			}
			setState(372);
			match(IDENTIFIER);
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(373);
				match(COLON);
				setState(374);
				type();
				}
			}

			setState(379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(377);
				match(ASSIGN);
				setState(378);
				restOfLine();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberExternalContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public MemberSignatureContext memberSignature() {
			return getRuleContext(MemberSignatureContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public VisibilityContext visibility() {
			return getRuleContext(VisibilityContext.class,0);
		}
		public List<MemberModifierContext> memberModifier() {
			return getRuleContexts(MemberModifierContext.class);
		}
		public MemberModifierContext memberModifier(int i) {
			return getRuleContext(MemberModifierContext.class,i);
		}
		public MemberExternalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberExternal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterMemberExternal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitMemberExternal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitMemberExternal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberExternalContext memberExternal() throws RecognitionException {
		MemberExternalContext _localctx = new MemberExternalContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_memberExternal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			className();
			setState(382);
			match(COLON);
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16888498602639360L) != 0)) {
				{
				setState(383);
				visibility();
				}
			}

			setState(389);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STATIC_MOD || _la==ABSTRACT_MOD) {
				{
				{
				setState(386);
				memberModifier();
				}
				}
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(392);
			memberSignature();
			setState(393);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipContext extends ParserRuleContext {
		public List<RelEndpointContext> relEndpoint() {
			return getRuleContexts(RelEndpointContext.class);
		}
		public RelEndpointContext relEndpoint(int i) {
			return getRuleContext(RelEndpointContext.class,i);
		}
		public TerminalNode ARROW() { return getToken(PlantUmlParser.ARROW, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public RelationshipContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationship; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterRelationship(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitRelationship(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitRelationship(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipContext relationship() throws RecognitionException {
		RelationshipContext _localctx = new RelationshipContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_relationship);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			relEndpoint();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==IDENTIFIER) {
				{
				setState(396);
				label();
				}
			}

			setState(399);
			match(ARROW);
			setState(401);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(400);
				label();
				}
				break;
			}
			setState(403);
			relEndpoint();
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(404);
				match(COLON);
				setState(405);
				restOfLine();
				}
			}

			setState(411);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(408);
					match(NEWLINE);
					}
					} 
				}
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelEndpointContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public CardinalityContext cardinality() {
			return getRuleContext(CardinalityContext.class,0);
		}
		public RelEndpointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relEndpoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterRelEndpoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitRelEndpoint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitRelEndpoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelEndpointContext relEndpoint() throws RecognitionException {
		RelEndpointContext _localctx = new RelEndpointContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_relEndpoint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			className();
			setState(416);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(415);
				cardinality();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CardinalityContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public CardinalityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cardinality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterCardinality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitCardinality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitCardinality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CardinalityContext cardinality() throws RecognitionException {
		CardinalityContext _localctx = new CardinalityContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_cardinality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_label);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NoteContext extends ParserRuleContext {
		public List<TerminalNode> NOTE() { return getTokens(PlantUmlParser.NOTE); }
		public TerminalNode NOTE(int i) {
			return getToken(PlantUmlParser.NOTE, i);
		}
		public NotePositionContext notePosition() {
			return getRuleContext(NotePositionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(PlantUmlParser.COLON, 0); }
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public TerminalNode OF() { return getToken(PlantUmlParser.OF, 0); }
		public NoteTargetContext noteTarget() {
			return getRuleContext(NoteTargetContext.class,0);
		}
		public NoteBodyContext noteBody() {
			return getRuleContext(NoteBodyContext.class,0);
		}
		public TerminalNode END() { return getToken(PlantUmlParser.END, 0); }
		public TerminalNode AS() { return getToken(PlantUmlParser.AS, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public TerminalNode STRING() { return getToken(PlantUmlParser.STRING, 0); }
		public NoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNote(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNote(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNote(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteContext note() throws RecognitionException {
		NoteContext _localctx = new NoteContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_note);
		int _la;
		try {
			setState(471);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(422);
				match(NOTE);
				setState(423);
				notePosition();
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OF) {
					{
					setState(424);
					match(OF);
					setState(425);
					noteTarget();
					}
				}

				setState(428);
				match(COLON);
				setState(429);
				restOfLine();
				setState(430);
				match(NEWLINE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(432);
				match(NOTE);
				setState(433);
				notePosition();
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OF) {
					{
					setState(434);
					match(OF);
					setState(435);
					noteTarget();
					}
				}

				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(438);
					match(AS);
					setState(439);
					alias();
					}
				}

				setState(442);
				match(NEWLINE);
				setState(443);
				noteBody();
				setState(444);
				match(END);
				setState(445);
				match(NOTE);
				setState(446);
				match(NEWLINE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(448);
				match(NOTE);
				setState(449);
				match(AS);
				setState(450);
				alias();
				setState(451);
				match(NEWLINE);
				setState(452);
				noteBody();
				setState(453);
				match(END);
				setState(454);
				match(NOTE);
				setState(455);
				match(NEWLINE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(457);
				match(NOTE);
				setState(458);
				match(NEWLINE);
				setState(459);
				noteBody();
				setState(460);
				match(END);
				setState(461);
				match(NOTE);
				setState(462);
				match(NEWLINE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(464);
				match(NOTE);
				setState(465);
				match(STRING);
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(466);
					match(AS);
					setState(467);
					alias();
					}
				}

				setState(470);
				match(NEWLINE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NotePositionContext extends ParserRuleContext {
		public TerminalNode LEFT() { return getToken(PlantUmlParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(PlantUmlParser.RIGHT, 0); }
		public TerminalNode TOP() { return getToken(PlantUmlParser.TOP, 0); }
		public TerminalNode BOTTOM() { return getToken(PlantUmlParser.BOTTOM, 0); }
		public NotePositionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notePosition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNotePosition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNotePosition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNotePosition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotePositionContext notePosition() throws RecognitionException {
		NotePositionContext _localctx = new NotePositionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_notePosition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 62914560L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NoteTargetContext extends ParserRuleContext {
		public ClassNameContext className() {
			return getRuleContext(ClassNameContext.class,0);
		}
		public TerminalNode DOUBLECOLON() { return getToken(PlantUmlParser.DOUBLECOLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PlantUmlParser.IDENTIFIER, 0); }
		public NoteTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteTarget; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNoteTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNoteTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNoteTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteTargetContext noteTarget() throws RecognitionException {
		NoteTargetContext _localctx = new NoteTargetContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_noteTarget);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			className();
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLECOLON) {
				{
				setState(476);
				match(DOUBLECOLON);
				setState(477);
				match(IDENTIFIER);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NoteBodyContext extends ParserRuleContext {
		public List<NoteLineContext> noteLine() {
			return getRuleContexts(NoteLineContext.class);
		}
		public NoteLineContext noteLine(int i) {
			return getRuleContext(NoteLineContext.class,i);
		}
		public NoteBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNoteBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNoteBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNoteBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteBodyContext noteBody() throws RecognitionException {
		NoteBodyContext _localctx = new NoteBodyContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_noteBody);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(480);
					noteLine();
					}
					} 
				}
				setState(485);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NoteLineContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public NoteLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterNoteLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitNoteLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitNoteLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteLineContext noteLine() throws RecognitionException {
		NoteLineContext _localctx = new NoteLineContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_noteLine);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -2305843009213693954L) != 0)) {
				{
				{
				setState(486);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==NEWLINE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(491);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(492);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HideShowDirectiveContext extends ParserRuleContext {
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public TerminalNode HIDE() { return getToken(PlantUmlParser.HIDE, 0); }
		public TerminalNode SHOW() { return getToken(PlantUmlParser.SHOW, 0); }
		public HideShowDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hideShowDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterHideShowDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitHideShowDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitHideShowDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HideShowDirectiveContext hideShowDirective() throws RecognitionException {
		HideShowDirectiveContext _localctx = new HideShowDirectiveContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_hideShowDirective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			_la = _input.LA(1);
			if ( !(_la==HIDE || _la==SHOW) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(495);
			restOfLine();
			setState(496);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SkinparamDirectiveContext extends ParserRuleContext {
		public TerminalNode SKINPARAM() { return getToken(PlantUmlParser.SKINPARAM, 0); }
		public RestOfLineContext restOfLine() {
			return getRuleContext(RestOfLineContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(PlantUmlParser.NEWLINE, 0); }
		public SkinparamDirectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skinparamDirective; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterSkinparamDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitSkinparamDirective(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitSkinparamDirective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkinparamDirectiveContext skinparamDirective() throws RecognitionException {
		SkinparamDirectiveContext _localctx = new SkinparamDirectiveContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_skinparamDirective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			match(SKINPARAM);
			setState(499);
			restOfLine();
			setState(500);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommentContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(PlantUmlParser.LINE_COMMENT, 0); }
		public TerminalNode BLOCK_COMMENT() { return getToken(PlantUmlParser.BLOCK_COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(502);
			_la = _input.LA(1);
			if ( !(_la==LINE_COMMENT || _la==BLOCK_COMMENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RestOfLineContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(PlantUmlParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(PlantUmlParser.NEWLINE, i);
		}
		public RestOfLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_restOfLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).enterRestOfLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PlantUmlParserListener ) ((PlantUmlParserListener)listener).exitRestOfLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PlantUmlParserVisitor ) return ((PlantUmlParserVisitor<? extends T>)visitor).visitRestOfLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RestOfLineContext restOfLine() throws RecognitionException {
		RestOfLineContext _localctx = new RestOfLineContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_restOfLine);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(504);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(509);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001?\u01ff\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0001\u0000\u0001\u0000\u0003\u0000_\b\u0000\u0001\u0000\u0005"+
		"\u0000b\b\u0000\n\u0000\f\u0000e\t\u0000\u0001\u0000\u0005\u0000h\b\u0000"+
		"\n\u0000\f\u0000k\t\u0000\u0001\u0000\u0001\u0000\u0005\u0000o\b\u0000"+
		"\n\u0000\f\u0000r\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002\u0084\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0094\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0099\b\u0005\u0001\u0005"+
		"\u0003\u0005\u009c\b\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00a0\b"+
		"\u0005\n\u0005\f\u0005\u00a3\t\u0005\u0001\u0005\u0005\u0005\u00a6\b\u0005"+
		"\n\u0005\f\u0005\u00a9\t\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00ad"+
		"\b\u0005\n\u0005\f\u0005\u00b0\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u00b6\b\u0006\n\u0006\f\u0006\u00b9\t\u0006\u0001"+
		"\u0006\u0005\u0006\u00bc\b\u0006\n\u0006\f\u0006\u00bf\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u00c3\b\u0006\n\u0006\f\u0006\u00c6\t\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0005\b\u00cb\b\b\n\b\f\b\u00ce\t\b\u0001\b"+
		"\u0001\b\u0003\b\u00d2\b\b\u0001\b\u0001\b\u0001\b\u0005\b\u00d7\b\b\n"+
		"\b\f\b\u00da\t\b\u0001\t\u0003\t\u00dd\b\t\u0001\t\u0003\t\u00e0\b\t\u0001"+
		"\t\u0003\t\u00e3\b\t\u0001\t\u0003\t\u00e6\b\t\u0001\t\u0003\t\u00e9\b"+
		"\t\u0001\t\u0003\t\u00ec\b\t\u0001\t\u0003\t\u00ef\b\t\u0001\n\u0001\n"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0109\b\u0012\n\u0012\f\u0012"+
		"\u010c\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u0113\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0117\b"+
		"\u0013\u0001\u0014\u0001\u0014\u0005\u0014\u011b\b\u0014\n\u0014\f\u0014"+
		"\u011e\t\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u0126\b\u0016\n\u0016\f\u0016\u0129\t\u0016\u0001"+
		"\u0016\u0005\u0016\u012c\b\u0016\n\u0016\f\u0016\u012f\t\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0136\b\u0017"+
		"\n\u0017\f\u0017\u0139\t\u0017\u0001\u0017\u0003\u0017\u013c\b\u0017\u0001"+
		"\u0018\u0003\u0018\u013f\b\u0018\u0001\u0018\u0005\u0018\u0142\b\u0018"+
		"\n\u0018\f\u0018\u0145\t\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0149"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u0151\b\u001b\u0001\u001c\u0003\u001c\u0154\b\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u0159\b\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0003\u001c\u015e\b\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u0163\b\u001d\n\u001d\f\u001d\u0166\t\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u016b\b\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0003\u001e\u0170\b\u001e\u0001\u001f\u0003\u001f\u0173"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0178\b\u001f"+
		"\u0001\u001f\u0001\u001f\u0003\u001f\u017c\b\u001f\u0001 \u0001 \u0001"+
		" \u0003 \u0181\b \u0001 \u0005 \u0184\b \n \f \u0187\t \u0001 \u0001 "+
		"\u0001 \u0001!\u0001!\u0003!\u018e\b!\u0001!\u0001!\u0003!\u0192\b!\u0001"+
		"!\u0001!\u0001!\u0003!\u0197\b!\u0001!\u0005!\u019a\b!\n!\f!\u019d\t!"+
		"\u0001\"\u0001\"\u0003\"\u01a1\b\"\u0001#\u0001#\u0001$\u0001$\u0001%"+
		"\u0001%\u0001%\u0001%\u0003%\u01ab\b%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0003%\u01b5\b%\u0001%\u0001%\u0003%\u01b9\b%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u01d5\b%\u0001%\u0003%\u01d8"+
		"\b%\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0003\'\u01df\b\'\u0001(\u0005"+
		"(\u01e2\b(\n(\f(\u01e5\t(\u0001)\u0005)\u01e8\b)\n)\f)\u01eb\t)\u0001"+
		")\u0001)\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001+\u0001+\u0001"+
		",\u0001,\u0001-\u0005-\u01fa\b-\n-\f-\u01fd\t-\u0001-\u0001\u01e3\u0000"+
		".\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\u0000\f\u0001\u00009:\u0002"+
		"\u0000\u0005\u0005  \u0002\u0000\u0003\u0004\u0006\r\u0001\u0000//\u0001"+
		"\u000078\u0002\u0000,,==\u0001\u0000!\"\u0001\u000025\u0001\u0000\u0016"+
		"\u0019\u0001\u0000==\u0001\u0000\u001c\u001d\u0001\u0000;<\u021b\u0000"+
		"\\\u0001\u0000\u0000\u0000\u0002u\u0001\u0000\u0000\u0000\u0004\u0083"+
		"\u0001\u0000\u0000\u0000\u0006\u0085\u0001\u0000\u0000\u0000\b\u0093\u0001"+
		"\u0000\u0000\u0000\n\u0095\u0001\u0000\u0000\u0000\f\u00b1\u0001\u0000"+
		"\u0000\u0000\u000e\u00c7\u0001\u0000\u0000\u0000\u0010\u00d1\u0001\u0000"+
		"\u0000\u0000\u0012\u00dc\u0001\u0000\u0000\u0000\u0014\u00f0\u0001\u0000"+
		"\u0000\u0000\u0016\u00f2\u0001\u0000\u0000\u0000\u0018\u00f4\u0001\u0000"+
		"\u0000\u0000\u001a\u00f6\u0001\u0000\u0000\u0000\u001c\u00f9\u0001\u0000"+
		"\u0000\u0000\u001e\u00fb\u0001\u0000\u0000\u0000 \u00fe\u0001\u0000\u0000"+
		"\u0000\"\u0101\u0001\u0000\u0000\u0000$\u0105\u0001\u0000\u0000\u0000"+
		"&\u010d\u0001\u0000\u0000\u0000(\u0118\u0001\u0000\u0000\u0000*\u0121"+
		"\u0001\u0000\u0000\u0000,\u0123\u0001\u0000\u0000\u0000.\u013b\u0001\u0000"+
		"\u0000\u00000\u013e\u0001\u0000\u0000\u00002\u014a\u0001\u0000\u0000\u0000"+
		"4\u014c\u0001\u0000\u0000\u00006\u0150\u0001\u0000\u0000\u00008\u0153"+
		"\u0001\u0000\u0000\u0000:\u015f\u0001\u0000\u0000\u0000<\u016f\u0001\u0000"+
		"\u0000\u0000>\u0172\u0001\u0000\u0000\u0000@\u017d\u0001\u0000\u0000\u0000"+
		"B\u018b\u0001\u0000\u0000\u0000D\u019e\u0001\u0000\u0000\u0000F\u01a2"+
		"\u0001\u0000\u0000\u0000H\u01a4\u0001\u0000\u0000\u0000J\u01d7\u0001\u0000"+
		"\u0000\u0000L\u01d9\u0001\u0000\u0000\u0000N\u01db\u0001\u0000\u0000\u0000"+
		"P\u01e3\u0001\u0000\u0000\u0000R\u01e9\u0001\u0000\u0000\u0000T\u01ee"+
		"\u0001\u0000\u0000\u0000V\u01f2\u0001\u0000\u0000\u0000X\u01f6\u0001\u0000"+
		"\u0000\u0000Z\u01fb\u0001\u0000\u0000\u0000\\^\u0005\u0001\u0000\u0000"+
		"]_\u0003\u0002\u0001\u0000^]\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_c\u0001\u0000\u0000\u0000`b\u0005=\u0000\u0000a`\u0001\u0000\u0000"+
		"\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000"+
		"\u0000\u0000di\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000fh\u0003"+
		"\u0004\u0002\u0000gf\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000"+
		"ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jl\u0001\u0000\u0000"+
		"\u0000ki\u0001\u0000\u0000\u0000lp\u0005\u0002\u0000\u0000mo\u0005=\u0000"+
		"\u0000nm\u0001\u0000\u0000\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000"+
		"\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rp\u0001"+
		"\u0000\u0000\u0000st\u0005\u0000\u0000\u0001t\u0001\u0001\u0000\u0000"+
		"\u0000uv\u0007\u0000\u0000\u0000v\u0003\u0001\u0000\u0000\u0000w\u0084"+
		"\u0003\u0006\u0003\u0000x\u0084\u0003\b\u0004\u0000y\u0084\u0003\n\u0005"+
		"\u0000z\u0084\u0003\f\u0006\u0000{\u0084\u0003\u0010\b\u0000|\u0084\u0003"+
		"B!\u0000}\u0084\u0003@ \u0000~\u0084\u0003J%\u0000\u007f\u0084\u0003T"+
		"*\u0000\u0080\u0084\u0003V+\u0000\u0081\u0084\u0003X,\u0000\u0082\u0084"+
		"\u0005=\u0000\u0000\u0083w\u0001\u0000\u0000\u0000\u0083x\u0001\u0000"+
		"\u0000\u0000\u0083y\u0001\u0000\u0000\u0000\u0083z\u0001\u0000\u0000\u0000"+
		"\u0083{\u0001\u0000\u0000\u0000\u0083|\u0001\u0000\u0000\u0000\u0083}"+
		"\u0001\u0000\u0000\u0000\u0083~\u0001\u0000\u0000\u0000\u0083\u007f\u0001"+
		"\u0000\u0000\u0000\u0083\u0080\u0001\u0000\u0000\u0000\u0083\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u0005\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0005\u001f\u0000\u0000\u0086\u0087\u0003"+
		"Z-\u0000\u0087\u0088\u0005=\u0000\u0000\u0088\u0007\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0005\u0016\u0000\u0000\u008a\u008b\u0005\u001a\u0000"+
		"\u0000\u008b\u008c\u0005\u0017\u0000\u0000\u008c\u008d\u0005\u001b\u0000"+
		"\u0000\u008d\u0094\u0005=\u0000\u0000\u008e\u008f\u0005\u0018\u0000\u0000"+
		"\u008f\u0090\u0005\u001a\u0000\u0000\u0090\u0091\u0005\u0019\u0000\u0000"+
		"\u0091\u0092\u0005\u001b\u0000\u0000\u0092\u0094\u0005=\u0000\u0000\u0093"+
		"\u0089\u0001\u0000\u0000\u0000\u0093\u008e\u0001\u0000\u0000\u0000\u0094"+
		"\t\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0010\u0000\u0000\u0096\u0098"+
		"\u0003\u000e\u0007\u0000\u0097\u0099\u0003(\u0014\u0000\u0098\u0097\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009b\u0001"+
		"\u0000\u0000\u0000\u009a\u009c\u0003*\u0015\u0000\u009b\u009a\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000"+
		"\u0000\u0000\u009d\u00a1\u0005#\u0000\u0000\u009e\u00a0\u0005=\u0000\u0000"+
		"\u009f\u009e\u0001\u0000\u0000\u0000\u00a0\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a2\u00a7\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a6\u0003\u0004\u0002\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a7\u0001\u0000\u0000\u0000\u00aa\u00ae\u0005$\u0000\u0000\u00ab"+
		"\u00ad\u0005=\u0000\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00b0"+
		"\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u000b\u0001\u0000\u0000\u0000\u00b0\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u0011\u0000\u0000\u00b2\u00b3"+
		"\u0003\u000e\u0007\u0000\u00b3\u00b7\u0005#\u0000\u0000\u00b4\u00b6\u0005"+
		"=\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00bd\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000"+
		"\u0000\u0000\u00ba\u00bc\u0003\u0004\u0002\u0000\u00bb\u00ba\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000"+
		"\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00c0\u0001\u0000"+
		"\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c4\u0005$\u0000"+
		"\u0000\u00c1\u00c3\u0005=\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c6\u0001\u0000\u0000\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\r\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c8\u0007\u0000\u0000\u0000\u00c8"+
		"\u000f\u0001\u0000\u0000\u0000\u00c9\u00cb\u0003\u0014\n\u0000\u00ca\u00c9"+
		"\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000\u0000\u0000\u00cc\u00ca"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000\u0000\u00cd\u00cf"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d2"+
		"\u0003\u0016\u000b\u0000\u00d0\u00d2\u0005\u0005\u0000\u0000\u00d1\u00cc"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d3\u00d4\u0003\u0018\f\u0000\u00d4\u00d8\u0003"+
		"\u0012\t\u0000\u00d5\u00d7\u0005=\u0000\u0000\u00d6\u00d5\u0001\u0000"+
		"\u0000\u0000\u00d7\u00da\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u0011\u0001\u0000"+
		"\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00db\u00dd\u0003\"\u0011"+
		"\u0000\u00dc\u00db\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000"+
		"\u0000\u00dd\u00df\u0001\u0000\u0000\u0000\u00de\u00e0\u0003(\u0014\u0000"+
		"\u00df\u00de\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00e3\u0003*\u0015\u0000\u00e2"+
		"\u00e1\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e5\u0001\u0000\u0000\u0000\u00e4\u00e6\u0003\u001a\r\u0000\u00e5\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e8"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e9\u0003\u001e\u000f\u0000\u00e8\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ea\u00ec\u0003 \u0010\u0000\u00eb\u00ea\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ee\u0001"+
		"\u0000\u0000\u0000\u00ed\u00ef\u0003,\u0016\u0000\u00ee\u00ed\u0001\u0000"+
		"\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u0013\u0001\u0000"+
		"\u0000\u0000\u00f0\u00f1\u0007\u0001\u0000\u0000\u00f1\u0015\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0007\u0002\u0000\u0000\u00f3\u0017\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f5\u0007\u0000\u0000\u0000\u00f5\u0019\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f7\u0005\u0014\u0000\u0000\u00f7\u00f8\u0003\u001c"+
		"\u000e\u0000\u00f8\u001b\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005:\u0000"+
		"\u0000\u00fa\u001d\u0001\u0000\u0000\u0000\u00fb\u00fc\u0005\u000e\u0000"+
		"\u0000\u00fc\u00fd\u0003$\u0012\u0000\u00fd\u001f\u0001\u0000\u0000\u0000"+
		"\u00fe\u00ff\u0005\u000f\u0000\u0000\u00ff\u0100\u0003$\u0012\u0000\u0100"+
		"!\u0001\u0000\u0000\u0000\u0101\u0102\u00050\u0000\u0000\u0102\u0103\u0003"+
		"$\u0012\u0000\u0103\u0104\u00051\u0000\u0000\u0104#\u0001\u0000\u0000"+
		"\u0000\u0105\u010a\u0003&\u0013\u0000\u0106\u0107\u0005+\u0000\u0000\u0107"+
		"\u0109\u0003&\u0013\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0109\u010c"+
		"\u0001\u0000\u0000\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\u0001\u0000\u0000\u0000\u010b%\u0001\u0000\u0000\u0000\u010c\u010a\u0001"+
		"\u0000\u0000\u0000\u010d\u0112\u0005:\u0000\u0000\u010e\u010f\u00050\u0000"+
		"\u0000\u010f\u0110\u0003$\u0012\u0000\u0110\u0111\u00051\u0000\u0000\u0111"+
		"\u0113\u0001\u0000\u0000\u0000\u0112\u010e\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0001\u0000\u0000\u0000\u0113\u0116\u0001\u0000\u0000\u0000\u0114"+
		"\u0115\u0005\'\u0000\u0000\u0115\u0117\u0005(\u0000\u0000\u0116\u0114"+
		"\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\'\u0001"+
		"\u0000\u0000\u0000\u0118\u011c\u0005.\u0000\u0000\u0119\u011b\b\u0003"+
		"\u0000\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011b\u011e\u0001\u0000"+
		"\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011c\u011d\u0001\u0000"+
		"\u0000\u0000\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u011c\u0001\u0000"+
		"\u0000\u0000\u011f\u0120\u0005/\u0000\u0000\u0120)\u0001\u0000\u0000\u0000"+
		"\u0121\u0122\u0007\u0004\u0000\u0000\u0122+\u0001\u0000\u0000\u0000\u0123"+
		"\u0127\u0005#\u0000\u0000\u0124\u0126\u0005=\u0000\u0000\u0125\u0124\u0001"+
		"\u0000\u0000\u0000\u0126\u0129\u0001\u0000\u0000\u0000\u0127\u0125\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u012d\u0001"+
		"\u0000\u0000\u0000\u0129\u0127\u0001\u0000\u0000\u0000\u012a\u012c\u0003"+
		".\u0017\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000"+
		"\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000"+
		"\u0000\u0000\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000"+
		"\u0000\u0000\u0130\u0131\u0005$\u0000\u0000\u0131-\u0001\u0000\u0000\u0000"+
		"\u0132\u013c\u00030\u0018\u0000\u0133\u0137\u00056\u0000\u0000\u0134\u0136"+
		"\u0005=\u0000\u0000\u0135\u0134\u0001\u0000\u0000\u0000\u0136\u0139\u0001"+
		"\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001"+
		"\u0000\u0000\u0000\u0138\u013c\u0001\u0000\u0000\u0000\u0139\u0137\u0001"+
		"\u0000\u0000\u0000\u013a\u013c\u0005=\u0000\u0000\u013b\u0132\u0001\u0000"+
		"\u0000\u0000\u013b\u0133\u0001\u0000\u0000\u0000\u013b\u013a\u0001\u0000"+
		"\u0000\u0000\u013c/\u0001\u0000\u0000\u0000\u013d\u013f\u00034\u001a\u0000"+
		"\u013e\u013d\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000"+
		"\u013f\u0143\u0001\u0000\u0000\u0000\u0140\u0142\u00032\u0019\u0000\u0141"+
		"\u0140\u0001\u0000\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143"+
		"\u0141\u0001\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144"+
		"\u0146\u0001\u0000\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146"+
		"\u0148\u00036\u001b\u0000\u0147\u0149\u0007\u0005\u0000\u0000\u0148\u0147"+
		"\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u01491\u0001"+
		"\u0000\u0000\u0000\u014a\u014b\u0007\u0006\u0000\u0000\u014b3\u0001\u0000"+
		"\u0000\u0000\u014c\u014d\u0007\u0007\u0000\u0000\u014d5\u0001\u0000\u0000"+
		"\u0000\u014e\u0151\u00038\u001c\u0000\u014f\u0151\u0003>\u001f\u0000\u0150"+
		"\u014e\u0001\u0000\u0000\u0000\u0150\u014f\u0001\u0000\u0000\u0000\u0151"+
		"7\u0001\u0000\u0000\u0000\u0152\u0154\u0003&\u0013\u0000\u0153\u0152\u0001"+
		"\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154\u0155\u0001"+
		"\u0000\u0000\u0000\u0155\u0156\u0005:\u0000\u0000\u0156\u0158\u0005%\u0000"+
		"\u0000\u0157\u0159\u0003:\u001d\u0000\u0158\u0157\u0001\u0000\u0000\u0000"+
		"\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000"+
		"\u015a\u015d\u0005&\u0000\u0000\u015b\u015c\u0005*\u0000\u0000\u015c\u015e"+
		"\u0003&\u0013\u0000\u015d\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001"+
		"\u0000\u0000\u0000\u015e9\u0001\u0000\u0000\u0000\u015f\u0164\u0003<\u001e"+
		"\u0000\u0160\u0161\u0005+\u0000\u0000\u0161\u0163\u0003<\u001e\u0000\u0162"+
		"\u0160\u0001\u0000\u0000\u0000\u0163\u0166\u0001\u0000\u0000\u0000\u0164"+
		"\u0162\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165"+
		";\u0001\u0000\u0000\u0000\u0166\u0164\u0001\u0000\u0000\u0000\u0167\u016a"+
		"\u0005:\u0000\u0000\u0168\u0169\u0005*\u0000\u0000\u0169\u016b\u0003&"+
		"\u0013\u0000\u016a\u0168\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000"+
		"\u0000\u0000\u016b\u0170\u0001\u0000\u0000\u0000\u016c\u016d\u0003&\u0013"+
		"\u0000\u016d\u016e\u0005:\u0000\u0000\u016e\u0170\u0001\u0000\u0000\u0000"+
		"\u016f\u0167\u0001\u0000\u0000\u0000\u016f\u016c\u0001\u0000\u0000\u0000"+
		"\u0170=\u0001\u0000\u0000\u0000\u0171\u0173\u0003&\u0013\u0000\u0172\u0171"+
		"\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173\u0174"+
		"\u0001\u0000\u0000\u0000\u0174\u0177\u0005:\u0000\u0000\u0175\u0176\u0005"+
		"*\u0000\u0000\u0176\u0178\u0003&\u0013\u0000\u0177\u0175\u0001\u0000\u0000"+
		"\u0000\u0177\u0178\u0001\u0000\u0000\u0000\u0178\u017b\u0001\u0000\u0000"+
		"\u0000\u0179\u017a\u0005-\u0000\u0000\u017a\u017c\u0003Z-\u0000\u017b"+
		"\u0179\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c"+
		"?\u0001\u0000\u0000\u0000\u017d\u017e\u0003\u0018\f\u0000\u017e\u0180"+
		"\u0005*\u0000\u0000\u017f\u0181\u00034\u001a\u0000\u0180\u017f\u0001\u0000"+
		"\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0185\u0001\u0000"+
		"\u0000\u0000\u0182\u0184\u00032\u0019\u0000\u0183\u0182\u0001\u0000\u0000"+
		"\u0000\u0184\u0187\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000"+
		"\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0188\u0001\u0000\u0000"+
		"\u0000\u0187\u0185\u0001\u0000\u0000\u0000\u0188\u0189\u00036\u001b\u0000"+
		"\u0189\u018a\u0005=\u0000\u0000\u018aA\u0001\u0000\u0000\u0000\u018b\u018d"+
		"\u0003D\"\u0000\u018c\u018e\u0003H$\u0000\u018d\u018c\u0001\u0000\u0000"+
		"\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u018f\u0001\u0000\u0000"+
		"\u0000\u018f\u0191\u00056\u0000\u0000\u0190\u0192\u0003H$\u0000\u0191"+
		"\u0190\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192"+
		"\u0193\u0001\u0000\u0000\u0000\u0193\u0196\u0003D\"\u0000\u0194\u0195"+
		"\u0005*\u0000\u0000\u0195\u0197\u0003Z-\u0000\u0196\u0194\u0001\u0000"+
		"\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u019b\u0001\u0000"+
		"\u0000\u0000\u0198\u019a\u0005=\u0000\u0000\u0199\u0198\u0001\u0000\u0000"+
		"\u0000\u019a\u019d\u0001\u0000\u0000\u0000\u019b\u0199\u0001\u0000\u0000"+
		"\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019cC\u0001\u0000\u0000\u0000"+
		"\u019d\u019b\u0001\u0000\u0000\u0000\u019e\u01a0\u0003\u0018\f\u0000\u019f"+
		"\u01a1\u0003F#\u0000\u01a0\u019f\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a1E\u0001\u0000\u0000\u0000\u01a2\u01a3\u0007\u0000"+
		"\u0000\u0000\u01a3G\u0001\u0000\u0000\u0000\u01a4\u01a5\u0007\u0000\u0000"+
		"\u0000\u01a5I\u0001\u0000\u0000\u0000\u01a6\u01a7\u0005\u0012\u0000\u0000"+
		"\u01a7\u01aa\u0003L&\u0000\u01a8\u01a9\u0005\u0015\u0000\u0000\u01a9\u01ab"+
		"\u0003N\'\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001"+
		"\u0000\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac\u01ad\u0005"+
		"*\u0000\u0000\u01ad\u01ae\u0003Z-\u0000\u01ae\u01af\u0005=\u0000\u0000"+
		"\u01af\u01d8\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005\u0012\u0000\u0000"+
		"\u01b1\u01b4\u0003L&\u0000\u01b2\u01b3\u0005\u0015\u0000\u0000\u01b3\u01b5"+
		"\u0003N\'\u0000\u01b4\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b8\u0001\u0000\u0000\u0000\u01b6\u01b7\u0005"+
		"\u0014\u0000\u0000\u01b7\u01b9\u0003\u001c\u000e\u0000\u01b8\u01b6\u0001"+
		"\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001"+
		"\u0000\u0000\u0000\u01ba\u01bb\u0005=\u0000\u0000\u01bb\u01bc\u0003P("+
		"\u0000\u01bc\u01bd\u0005\u0013\u0000\u0000\u01bd\u01be\u0005\u0012\u0000"+
		"\u0000\u01be\u01bf\u0005=\u0000\u0000\u01bf\u01d8\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c1\u0005\u0012\u0000\u0000\u01c1\u01c2\u0005\u0014\u0000\u0000"+
		"\u01c2\u01c3\u0003\u001c\u000e\u0000\u01c3\u01c4\u0005=\u0000\u0000\u01c4"+
		"\u01c5\u0003P(\u0000\u01c5\u01c6\u0005\u0013\u0000\u0000\u01c6\u01c7\u0005"+
		"\u0012\u0000\u0000\u01c7\u01c8\u0005=\u0000\u0000\u01c8\u01d8\u0001\u0000"+
		"\u0000\u0000\u01c9\u01ca\u0005\u0012\u0000\u0000\u01ca\u01cb\u0005=\u0000"+
		"\u0000\u01cb\u01cc\u0003P(\u0000\u01cc\u01cd\u0005\u0013\u0000\u0000\u01cd"+
		"\u01ce\u0005\u0012\u0000\u0000\u01ce\u01cf\u0005=\u0000\u0000\u01cf\u01d8"+
		"\u0001\u0000\u0000\u0000\u01d0\u01d1\u0005\u0012\u0000\u0000\u01d1\u01d4"+
		"\u00059\u0000\u0000\u01d2\u01d3\u0005\u0014\u0000\u0000\u01d3\u01d5\u0003"+
		"\u001c\u000e\u0000\u01d4\u01d2\u0001\u0000\u0000\u0000\u01d4\u01d5\u0001"+
		"\u0000\u0000\u0000\u01d5\u01d6\u0001\u0000\u0000\u0000\u01d6\u01d8\u0005"+
		"=\u0000\u0000\u01d7\u01a6\u0001\u0000\u0000\u0000\u01d7\u01b0\u0001\u0000"+
		"\u0000\u0000\u01d7\u01c0\u0001\u0000\u0000\u0000\u01d7\u01c9\u0001\u0000"+
		"\u0000\u0000\u01d7\u01d0\u0001\u0000\u0000\u0000\u01d8K\u0001\u0000\u0000"+
		"\u0000\u01d9\u01da\u0007\b\u0000\u0000\u01daM\u0001\u0000\u0000\u0000"+
		"\u01db\u01de\u0003\u0018\f\u0000\u01dc\u01dd\u0005)\u0000\u0000\u01dd"+
		"\u01df\u0005:\u0000\u0000\u01de\u01dc\u0001\u0000\u0000\u0000\u01de\u01df"+
		"\u0001\u0000\u0000\u0000\u01dfO\u0001\u0000\u0000\u0000\u01e0\u01e2\u0003"+
		"R)\u0000\u01e1\u01e0\u0001\u0000\u0000\u0000\u01e2\u01e5\u0001\u0000\u0000"+
		"\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e3\u01e1\u0001\u0000\u0000"+
		"\u0000\u01e4Q\u0001\u0000\u0000\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e8\b\t\u0000\u0000\u01e7\u01e6\u0001\u0000\u0000\u0000\u01e8"+
		"\u01eb\u0001\u0000\u0000\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000\u01e9"+
		"\u01ea\u0001\u0000\u0000\u0000\u01ea\u01ec\u0001\u0000\u0000\u0000\u01eb"+
		"\u01e9\u0001\u0000\u0000\u0000\u01ec\u01ed\u0005=\u0000\u0000\u01edS\u0001"+
		"\u0000\u0000\u0000\u01ee\u01ef\u0007\n\u0000\u0000\u01ef\u01f0\u0003Z"+
		"-\u0000\u01f0\u01f1\u0005=\u0000\u0000\u01f1U\u0001\u0000\u0000\u0000"+
		"\u01f2\u01f3\u0005\u001e\u0000\u0000\u01f3\u01f4\u0003Z-\u0000\u01f4\u01f5"+
		"\u0005=\u0000\u0000\u01f5W\u0001\u0000\u0000\u0000\u01f6\u01f7\u0007\u000b"+
		"\u0000\u0000\u01f7Y\u0001\u0000\u0000\u0000\u01f8\u01fa\b\t\u0000\u0000"+
		"\u01f9\u01f8\u0001\u0000\u0000\u0000\u01fa\u01fd\u0001\u0000\u0000\u0000"+
		"\u01fb\u01f9\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000"+
		"\u01fc[\u0001\u0000\u0000\u0000\u01fd\u01fb\u0001\u0000\u0000\u0000=^"+
		"cip\u0083\u0093\u0098\u009b\u00a1\u00a7\u00ae\u00b7\u00bd\u00c4\u00cc"+
		"\u00d1\u00d8\u00dc\u00df\u00e2\u00e5\u00e8\u00eb\u00ee\u010a\u0112\u0116"+
		"\u011c\u0127\u012d\u0137\u013b\u013e\u0143\u0148\u0150\u0153\u0158\u015d"+
		"\u0164\u016a\u016f\u0172\u0177\u017b\u0180\u0185\u018d\u0191\u0196\u019b"+
		"\u01a0\u01aa\u01b4\u01b8\u01d4\u01d7\u01de\u01e3\u01e9\u01fb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}