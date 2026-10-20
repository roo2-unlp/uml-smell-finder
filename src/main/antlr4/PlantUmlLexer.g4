lexer grammar PlantUmlLexer;

// =====================================================================
// LEXER RULES
// =====================================================================

STARTUML : '@startuml' ;
ENDUML   : '@enduml' ;

CLASS         : 'class' ;
INTERFACE     : 'interface' ;
ABSTRACT      : 'abstract' ;
ENUM          : 'enum' ;
ENTITY        : 'entity' ;
ANNOTATION    : 'annotation' ;
EXCEPTION     : 'exception' ;
METACLASS     : 'metaclass' ;
STEREOTYPE_KW : 'stereotype' ;
PROTOCOL      : 'protocol' ;
STRUCT        : 'struct' ;

EXTENDS    : 'extends' ;
IMPLEMENTS : 'implements' ;
PACKAGE    : 'package' ;
NAMESPACE  : 'namespace' ;
NOTE       : 'note' ;
END        : 'end' ;
AS         : 'as' ;
OF         : 'of' ;
LEFT       : 'left' ;
RIGHT      : 'right' ;
TOP        : 'top' ;
BOTTOM     : 'bottom' ;
TO         : 'to' ;
DIRECTION  : 'direction' ;
HIDE       : 'hide' ;
SHOW       : 'show' ;
SKINPARAM  : 'skinparam' ;
TITLE      : 'title' ;
STATIC     : 'static' ;

STATIC_MOD   : '{static}' ;
ABSTRACT_MOD : '{abstract}' ;

LBRACE : '{' ;
RBRACE : '}' ;
LPAREN : '(' ;
RPAREN : ')' ;
LBRACK : '[' ;
RBRACK : ']' ;
DOUBLECOLON : '::' ;
COLON  : ':' ;
COMMA  : ',' ;
SEMI   : ';' ;
ASSIGN : '=' ;

DOUBLE_LT : '<<' ;
DOUBLE_GT : '>>' ;
LT        : '<' ;
GT        : '>' ;

PLUS  : '+' ;
MINUS : '-' ;
HASH  : '#' ;
TILDE : '~' ;

// Arrows: optional left decoration, a dash/dot run (optionally split by a
// direction hint like -up-> / -left->), optional right decoration.
// Covers:  --  ..  -->  <--  ..>  <..  --|>  <|--  ..|>  <|..  *--  --*
//          o--  --o  -up->  -left->  etc.
fragment DIRWORD   : ('up' | 'down' | 'left' | 'right' | 'u' | 'd' | 'l' | 'r') ;
fragment DASHES    : '-'+ ;
fragment DOTS      : '.'+ ;
fragment LEFTDECOR  : '<|' | '<' | '*' | 'o' | 'x' | '+' | '}' ;
fragment RIGHTDECOR : '|>' | '>' | '*' | 'o' | 'x' | '+' | '{' ;

ARROW : LEFTDECOR? (DASHES | DOTS) (DIRWORD (DASHES | DOTS))? RIGHTDECOR? ;

HEXCOLOR  : '#' [0-9a-fA-F]+ ;
COLORNAME : '#' [a-zA-Z]+ ;

STRING : '"' (~["\r\n])* '"' ;

IDENTIFIER : [a-zA-Z_][a-zA-Z0-9_.]* ;

LINE_COMMENT  : '\'' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/\'' .*? '\'/' -> skip ;

NEWLINE : '\r'? '\n' ;
WS      : [ \t]+ -> skip ;

// Catch-all: this grammar only defines lexer rules for punctuation that
// has meaning somewhere in the language (arrows, braces, colons, etc).
// Free-form text -- note bodies above all -- can legally contain any other
// punctuation a person types: '!', '$', '%', '&', a backtick, and so on.
// Without a rule to match it, such a character matches nothing at all and
// the lexer throws a "token recognition error" and silently drops it.
// This rule is deliberately last (lowest priority): every more specific
// rule above still wins whenever it applies, and only a character that
// truly matches nothing else falls through to become a plain OTHER token,
// which noteLine (and restOfLine) already accept as ordinary content.
OTHER : . ;