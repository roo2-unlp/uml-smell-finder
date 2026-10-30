parser grammar PlantUmlParser;
options { tokenVocab=PlantUmlLexer; }

// =====================================================================
// PARSER RULES
// =====================================================================

diagram
    : STARTUML diagramName? NEWLINE*
        element*
      ENDUML NEWLINE* EOF
    ;

diagramName
    : IDENTIFIER
    | STRING
    ;

element
    : titleStatement
    | directionStatement
    | packageDeclaration
    | namespaceDeclaration
    | classDeclaration
    | relationship
    | memberExternal
    | note
    | hideShowDirective
    | skinparamDirective
    | comment
    | NEWLINE
    ;

titleStatement
    : TITLE restOfLine NEWLINE
    ;

directionStatement
    : LEFT TO RIGHT DIRECTION NEWLINE
    | TOP TO BOTTOM DIRECTION NEWLINE
    ;

// ---------------- Packages / Namespaces ----------------

packageDeclaration
    : PACKAGE packageName stereotype? color? LBRACE NEWLINE*
        element*
      RBRACE NEWLINE*
    ;

namespaceDeclaration
    : NAMESPACE packageName LBRACE NEWLINE*
        element*
      RBRACE NEWLINE*
    ;

packageName
    : IDENTIFIER
    | STRING
    ;

// ---------------- Class / Interface / Enum / etc ----------------

classDeclaration
    : ( classModifier* classKind | ABSTRACT ) className classTail NEWLINE*
    ;

classTail
    : genericParams? stereotype? color? aliasClause? extendsClause? implementsClause? classBody?
    ;

classModifier
    : ABSTRACT
    | STATIC
    ;

classKind
    : CLASS
    | INTERFACE
    | ENUM
    | ENTITY
    | ANNOTATION
    | EXCEPTION
    | METACLASS
    | STEREOTYPE_KW
    | PROTOCOL
    | STRUCT
    ;

className
    : IDENTIFIER
    | STRING
    ;

aliasClause
    : AS alias
    ;

alias
    : IDENTIFIER
    ;

extendsClause
    : EXTENDS typeList
    ;

implementsClause
    : IMPLEMENTS typeList
    ;

genericParams
    : LT typeList GT
    ;

typeList
    : type (COMMA type)*
    ;

type
    : IDENTIFIER (LT typeList GT)? (LBRACK RBRACK)?
    ;

stereotype
    : DOUBLE_LT (~DOUBLE_GT)* DOUBLE_GT
    ;

color
    : HEXCOLOR
    | COLORNAME
    ;

classBody
    : LBRACE NEWLINE*
        bodyItem*
      RBRACE
    ;

bodyItem
    : member
    | ARROW NEWLINE*   // separator line, e.g. "--" between fields and methods
    | NEWLINE
    ;

member
    : visibility? memberModifier* memberSignature (NEWLINE | SEMI)?
    ;

memberModifier
    : STATIC_MOD
    | ABSTRACT_MOD
    ;

visibility
    : PLUS
    | MINUS
    | HASH
    | TILDE
    ;

memberSignature
    : methodSignature
    | fieldSignature
    ;

// Java-style: ReturnType name(params)   OR UML-style: name(params) : ReturnType
methodSignature
    : type? IDENTIFIER LPAREN paramList? RPAREN (COLON type)?
    ;

paramList
    : param (COMMA param)*
    ;

param
    : IDENTIFIER (COLON type)?
    | type IDENTIFIER
    ;

// Java-style: Type name   OR UML-style: name : Type
fieldSignature
    : type? IDENTIFIER (COLON type)? (ASSIGN restOfLine)?
    ;

// ---------------- Members declared outside a class body ----------------
// e.g.   Class1 : +getName()
//        Class1 : -age : int

memberExternal
    : className COLON visibility? memberModifier* memberSignature NEWLINE
    ;

// ---------------- Relationships ----------------

relationship
    : relEndpoint label? ARROW label? relEndpoint (COLON restOfLine)? NEWLINE*
    ;

relEndpoint
    : className cardinality?
    ;

cardinality
    : STRING
    | IDENTIFIER
    ;

label
    : STRING
    | IDENTIFIER
    ;

// ---------------- Notes ----------------
//
// A note's body is a possibly-empty list of free-form lines, terminated by
// a line that is literally "end note". We can't tell body lines apart from
// the terminator by content alone (a body line could itself say "end note"),
// so termination is decided structurally: the noteBody loop is non-greedy,
// meaning ANTLR tries to exit (match END NOTE NEWLINE) before it tries to
// consume one more noteLine. This mirrors PlantUML's own real behavior,
// which likewise just scans for a line that reads "end note".

note
    : NOTE notePosition (OF noteTarget)? COLON restOfLine NEWLINE          // one-liner: note left of Foo : text
    | NOTE notePosition (OF noteTarget)? (AS alias)? NEWLINE
        noteBody
      END NOTE NEWLINE                                                     // block attached to a position/class/member
    | NOTE AS alias NEWLINE
        noteBody
      END NOTE NEWLINE                                                     // block, floating, with alias
    | NOTE NEWLINE
        noteBody
      END NOTE NEWLINE                                                     // block, floating, no alias/position
    | NOTE STRING (AS alias)? NEWLINE                                      // one-liner: note "text" as N1
    ;

notePosition
    : LEFT
    | RIGHT
    | TOP
    | BOTTOM
    ;

// A note can target a whole class ("note right of Flight") or one specific
// member of it ("note right of Flight::flightNumber").
noteTarget
    : className (DOUBLECOLON IDENTIFIER)?
    ;

// Zero or more lines of free-form text. Empty bodies (an "end note" on the
// very next line) are allowed, since noteLine* accepts zero repetitions.
noteBody
    : noteLine*?
    ;

// One line of arbitrary content: any run of non-NEWLINE tokens, then the
// NEWLINE that ends it. An entirely blank line is a valid (empty) noteLine.
noteLine
    : (~NEWLINE)* NEWLINE
    ;

// ---------------- Misc directives ----------------

hideShowDirective
    : (HIDE | SHOW) restOfLine NEWLINE
    ;

skinparamDirective
    : SKINPARAM restOfLine NEWLINE
    ;

comment
    : LINE_COMMENT
    | BLOCK_COMMENT
    ;

restOfLine
    : (~NEWLINE)*
    ;
