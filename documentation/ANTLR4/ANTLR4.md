# ANTLR4 en este proyecto

## Qué es ANTLR

[ANTLR](https://www.antlr.org/) toma una gramática (un archivo `.g4` que describe un lenguaje) y genera código Java que lee texto en ese lenguaje y lo convierte en un árbol. En este proyecto el lenguaje es PlantUML: entra un diagrama de clases y sale un árbol con sus clases, atributos, métodos y relaciones, que después recorren los sniffers.

El análisis tiene dos etapas:

1. El **lexer** corta el texto en tokens. `+print() : void` queda como `'+'`, `IDENTIFIER(print)`, `'('`, `')'`, `':'`, `IDENTIFIER(void)`.
2. El **parser** agrupa los tokens según las reglas de la gramática y arma el árbol. Para este diagrama:

   ```plantuml
   @startuml
   class Printer {
       +print() : void
   }
   @enduml
   ```

   el árbol es, simplificado:

   ```text
   diagram
   ├── @startuml
   ├── element
   │   └── classDeclaration
   │       ├── classKind: class
   │       ├── className: Printer
   │       └── classTail
   │           └── classBody
   │               └── bodyItem
   │                   └── member
   │                       ├── visibility: +
   │                       └── memberSignature
   │                           └── methodSignature
   │                               ├── print ( )
   │                               └── : type: void
   └── @enduml
   ```

## Instalación

No hay que instalar nada además de lo que pide el [README](../../README.md#wrench-requisitos). ANTLR está declarado en el [`pom.xml`](../../pom.xml) y Maven lo descarga:

- `antlr4-maven-plugin` genera las clases Java a partir de los `.g4`.
- `antlr4-runtime` es la biblioteca que esas clases usan al ejecutarse.

## Clases generadas

La gramática está dividida en dos archivos, en [`src/main/antlr4/oo2/redictado/plantuml/antlr4`](../../src/main/antlr4/oo2/redictado/plantuml/antlr4/):

- [`PlantUmlLexer.g4`](../../src/main/antlr4/oo2/redictado/plantuml/antlr4/PlantUmlLexer.g4): los tokens.
- [`PlantUmlParser.g4`](../../src/main/antlr4/oo2/redictado/plantuml/antlr4/PlantUmlParser.g4): cómo se combinan.

Cada vez que compilás (`mvn compile`, `mvn test`, etc.), Maven genera las clases en `target/generated-sources/antlr4`, en el paquete `oo2.redictado.plantuml.antlr4`, que sale de la carpeta de la gramática. Para generarlas sin compilar, usá `mvn generate-sources`.

| Archivo                          | Qué es                                                                 |
|----------------------------------|------------------------------------------------------------------------|
| `PlantUmlLexer.java`             | El lexer.                                                              |
| `PlantUmlParser.java`            | El parser. Tiene un método por regla (`diagram()`, `classDeclaration()`, ...) y una clase `XXContext` por regla, que es el tipo de los nodos del árbol. |
| `PlantUmlParserVisitor.java`     | Interfaz con un `visitXX` por regla.                                   |
| `PlantUmlParserBaseVisitor.java` | Implementación que visita todos los hijos. [`PlantUmlVisitor`](../../src/main/java/oo2/redictado/plantuml/PlantUmlVisitor.java) hereda de esta clase. |
| `*.tokens` y `*.interp`          | Archivos auxiliares de ANTLR.                                          |

Estas clases no están en git y se pisan en cada build, así que no tiene sentido editarlas. Si el IDE no encuentra el paquete `oo2.redictado.plantuml.antlr4`, corré `mvn compile` y recargá el proyecto Maven.

## Cómo leer la gramática

Cada regla tiene la forma `nombre : definición ;`. Las que están en mayúsculas son del lexer y definen tokens (`CLASS : 'class' ;`). Las que están en minúsculas son del parser y definen la estructura (`classBody : LBRACE bodyItem* RBRACE ;`).

| Operador   | Significado                            | Ejemplo                                 |
|------------|----------------------------------------|-----------------------------------------|
| `a b`      | `a` seguido de `b`                     | `AS alias`                              |
| `a \| b`   | `a` o `b`                              | `PLUS \| MINUS \| HASH \| TILDE`        |
| `a?`       | `a` opcional                           | `visibility?`                           |
| `a*`       | `a` cero o más veces                   | `element*`                              |
| `a+`       | `a` una o más veces                    | `'-'+`                                  |
| `( ... )`  | agrupa                                 | `(COMMA param)*`                        |
| `~X`       | cualquier token menos `X`              | `(~NEWLINE)*`                           |
| `[a-z]`    | rango de caracteres (solo en el lexer) | `[a-zA-Z_]`                             |
| `-> skip`  | el lexer descarta el token             | `WS : [ \t]+ -> skip ;`                 |

Dos reglas del lexer explican la mayoría de los errores raros:

1. Si varios tokens matchean, gana el más largo. Por eso `-->` es un `ARROW` y no tres `MINUS`.
2. Si empatan en largo, gana el que está definido primero. Por eso `class` es `CLASS` y no `IDENTIFIER`.

El parser toma los tokens del lexer con `options { tokenVocab=PlantUmlLexer; }`.

## Recorrer el árbol con un visitor

El parseo lo hace [`PlantUml`](../../src/main/java/oo2/redictado/plantuml/PlantUml.java), una sola vez para todos los sniffers:

```java
PlantUmlLexer lexer = new PlantUmlLexer(CharStreams.fromString(code));   // texto -> tokens
PlantUmlParser parser = new PlantUmlParser(new CommonTokenStream(lexer)); // tokens -> árbol

DiagramContext diagram = parser.diagram();   // el árbol empieza en la regla diagram
if (parser.getNumberOfSyntaxErrors() > 0) {
    throw new IllegalArgumentException("Syntax error");
}
```

Un sniffer solo dice qué visitor usar para recorrer ese árbol:

```java
public class PrintSniffer extends PlantUmlSniffer {
    @Override
    protected PlantUmlVisitor createVisitor() {
        return new PrintSnifferVisitor();
    }
}
```

El visitor extiende [`PlantUmlVisitor`](../../src/main/java/oo2/redictado/plantuml/PlantUmlVisitor.java), redefine los `visitXX` de las reglas que le interesan y llama a `smell(mensaje, nodo)` por cada olor. Del nodo se saca la línea del diagrama. [`PrintSnifferVisitor`](../../src/main/java/oo2/redictado/sniffers/print/PrintSnifferVisitor.java) solo mira las firmas de métodos:

```java
@Override
public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
    if (ctx.IDENTIFIER().getText().equals("print")) {
        this.smell("The diagram has print methods.", ctx);
    }
    return visitChildren(ctx);
}
```

Cada `smell(...)` agrega un `Aroma` al reporte, con el nombre de la clase del sniffer. Ese nombre se puede cambiar redefiniendo `name()`.

Algunas cosas a tener en cuenta:

- `ctx` es el nodo del árbol y tiene un método por cada elemento de su regla. Como `methodSignature : type? IDENTIFIER LPAREN paramList? RPAREN (COLON type)? ;`, se puede pedir `ctx.IDENTIFIER()`, `ctx.paramList()`, etc. Si un elemento opcional no está, devuelve `null`. Si puede aparecer más de una vez, como `type`, devuelve una lista (`ctx.type()`) y también se puede pedir uno por posición (`ctx.type(0)`).
- `getText()` devuelve el texto del nodo.
- `visitChildren(ctx)` sigue con los hijos. Si no lo llamás, esa parte del árbol no se recorre, así que lo normal es terminar con `return visitChildren(ctx);`.
- Cada análisis usa un visitor nuevo, así que se puede guardar estado en atributos, por ejemplo para contar algo y decidir al final redefiniendo `visitDiagram`.
- Cuando los sniffers corren juntos, cada uno recibe su propia copia del árbol, así que modificarla no afecta a los demás.

Los métodos de cada `XXContext` están en `PlantUmlParser.java`.

## Crear un nuevo sniffer

1. Creá un paquete dentro de [`oo2.redictado.sniffers`](../../src/main/java/oo2/redictado/sniffers/), en minúsculas, por ejemplo `misniffer`.
2. Creá dos clases, siguiendo el modelo de [`PrintSniffer`](../../src/main/java/oo2/redictado/sniffers/print/):
   - `MiSniffer extends PlantUmlSniffer`, que implementa `createVisitor()`.
   - `MiVisitor extends PlantUmlVisitor`, con la lógica de detección.
3. Buscá en [`PlantUmlParser.g4`](../../src/main/antlr4/oo2/redictado/plantuml/antlr4/PlantUmlParser.g4) la regla que representa lo que querés analizar y redefiní su `visitXX`. Por ejemplo, una clase es `classDeclaration`, un atributo es `fieldSignature` y una relación es `relationship`.
4. Escribí los tests en el mismo paquete bajo `src/test/java`, por ejemplo `src/test/java/oo2/redictado/sniffers/misniffer/MiSnifferTest.java`. Para analizar un diagrama usá `new SmellFinder<>(new PlantUml(), new MiSniffer()).sniff(codigo)`, como en [`PrintSnifferTest`](../../src/test/java/oo2/redictado/sniffers/print/PrintSnifferTest.java).
5. Documentalo en una carpeta propia dentro de [`documentation`](../).
6. Registralo con una línea en `catalog()` de [`UmlSmellFinder`](../../src/main/java/oo2/redictado/UmlSmellFinder.java), con el nombre que se va a usar para elegirlo desde la línea de comandos:

   ```java
   catalog.add("MiSniffer", params -> new MiSniffer());
   ```

7. Corré `mvn test`.

### Parámetros

Si el sniffer necesita valores configurables, como un umbral, agregale un constructor que reciba un [`SnifferParams`](../../src/main/java/oo2/redictado/core/SnifferParams.java) y leelos por nombre, con un valor por defecto:

```java
public class MiSniffer extends PlantUmlSniffer {
    private final int umbral;

    public MiSniffer(SnifferParams params) {
        this(params.getInt("umbral", 20));
    }

    public MiSniffer(int umbral) {
        this.umbral = umbral;
    }

    @Override
    protected PlantUmlVisitor createVisitor() {
        return new MiVisitor(this.umbral);
    }
}
```

Al registrarlo, usá ese constructor:

```java
catalog.add("MiSniffer", MiSniffer::new);
```

Desde la línea de comandos se pasan con `--sniffers MiSniffer:umbral=30`. Si no se pasan, se usa el valor por defecto. En los tests es más cómodo usar el otro constructor: `new MiSniffer(5)`.

`SnifferParams` también tiene `getString`, `getDouble` y `getBoolean`. Un sniffer sin parámetros no necesita nada de esto. Si se le pasa alguno desde la línea de comandos, es un error.

## Más información

- [Sitio de ANTLR](https://www.antlr.org/)
- [Documentación](https://github.com/antlr/antlr4/blob/master/doc/index.md)
- [Gramáticas de otros lenguajes](https://github.com/antlr/grammars-v4)
