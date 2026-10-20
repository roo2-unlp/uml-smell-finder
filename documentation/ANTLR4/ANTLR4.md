# ANTLR4: guía para este proyecto

## ¿Qué es ANTLR?

[**ANTLR**](https://www.antlr.org/) (ANother Tool for Language Recognition) es una herramienta que, a partir de una **gramática** (un archivo `.g4` que describe cómo se escribe un lenguaje), **genera código Java** capaz de leer textos en ese lenguaje y convertirlos en un **árbol** que podemos recorrer desde nuestro programa.

En este proyecto el lenguaje es PlantUML: le damos un diagrama como texto y obtenemos un árbol con sus clases, atributos, métodos y relaciones. Sobre ese árbol buscamos malos olores de diseño.

El proceso tiene dos etapas:

1. **Lexer** (análisis léxico): corta el texto en **tokens**, como cuando separamos una oración en palabras. Por ejemplo, `+print() : void` se convierte en:
    - '+'
    - IDENTIFIER(print)
    - '('
    - ')'
    - ':'
    - IDENTIFIER(void)

2. **Parser** (análisis sintáctico): agrupa los tokens según las reglas de la gramática y arma el **árbol de parseo**. Para el diagrama:

   ```plantuml
   @startuml
   class Printer {
       +print() : void
   }
   @enduml
   ```

   el árbol queda (simplificado):

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

**No hace falta instalar ANTLR a mano.** El proyecto lo usa a través de Maven, que lo descarga solo la primera vez que lo necesita. Está declarado en el [`pom.xml`](../../pom.xml) de dos formas:

| Qué                   | Para qué sirve                                                       |
|-----------------------|----------------------------------------------------------------------|
| `antlr4-maven-plugin` | **Genera** las clases Java a partir de los archivos `.g4`.           |
| `antlr4-runtime`      | Biblioteca que esas clases necesitan para **ejecutarse**.            |


Lo único que necesitás instalar es:

- **Java 21** (o superior). Verificalo con `java -version`.
- **Maven** (opcional). El proyecto trae el wrapper `mvnw` / `mvnw.cmd`, que descarga Maven solo. Si no querés instalarlo, reemplazá `mvn` por `./mvnw` (Linux / macOS) o `mvnw.cmd` (Windows) en todos los comandos.

## Generar las clases

Las gramáticas están en [`src/main/antlr4`](../../src/main/antlr4/):

- [`PlantUmlLexer.g4`](../../src/main/antlr4/PlantUmlLexer.g4): reglas del **lexer** (qué es un token).
- [`PlantUmlParser.g4`](../../src/main/antlr4/PlantUmlParser.g4): reglas del **parser** (cómo se combinan los tokens).

Para generar (o regenerar) las clases Java, desde la raíz del proyecto ejecutá:

```bash
mvn antlr4:antlr4
```

Esto crea los siguientes archivos en [`src/main/java/oo2/redictado/antlr4`](../../src/main/java/oo2/redictado/antlr4/):

| Archivo                          | Qué es                                                                 |
|----------------------------------|------------------------------------------------------------------------|
| `PlantUmlLexer.java`             | El lexer: convierte el texto en tokens.                                |
| `PlantUmlParser.java`            | El parser: convierte los tokens en un árbol. Tiene un método por regla (`diagram()`, `classDeclaration()`, ...) y una clase `XXContext` por regla, que representa un nodo del árbol. |
| `PlantUmlParserVisitor.java`     | Interfaz con un método `visitXX` por cada regla de la gramática.      |
| `PlantUmlParserBaseVisitor.java` | Implementación por defecto del visitor: visita todos los hijos. **De acá heredan nuestros sniffers.** |
| `*.tokens` y `*.interp`          | Archivos auxiliares que usa ANTLR. No hace falta abrirlos.             |

> [!IMPORTANT]
> - **No edites a mano** los archivos generados: se pisan cada vez que regenerás.
> - **Cada vez que modifiques un `.g4` tenés que volver a ejecutar `mvn antlr4:antlr4`.** `mvn test` no lo hace solo. Si te aparece un error como `cannot find symbol: visitMiNuevaRegla`, seguramente te olvidaste de regenerar.

## Cómo leer la gramática

Una gramática es una lista de reglas con la forma `nombre : definición ;`.

- Las reglas en **MAYÚSCULAS** son del lexer y definen tokens: `CLASS : 'class' ;`
- Las reglas en **minúsculas** son del parser y definen la estructura: `classBody : LBRACE bodyItem* RBRACE ;`

Operadores que vas a ver:

| Operador   | Significado                           | Ejemplo                                   |
|------------|---------------------------------------|-------------------------------------------|
| `a b`      | `a` seguido de `b`                    | `AS alias`                                |
| `a \| b`   | `a` o `b`                             | `PLUS \| MINUS \| HASH \| TILDE`          |
| `a?`       | `a` es opcional (0 o 1 vez)           | `visibility?`                             |
| `a*`       | `a` se repite 0 o más veces           | `element*`                                |
| `a+`       | `a` se repite 1 o más veces           | `'-'+`                                    |
| `( ... )`  | agrupa                                | `(COMMA param)*`                          |
| `~X`       | cualquier token **menos** `X`         | `(~NEWLINE)*` (todo hasta fin de línea)   |
| `[a-z]`    | rango de caracteres (solo en el lexer)| `[a-zA-Z_]`                               |
| `-> skip`  | el lexer descarta ese token           | `WS : [ \t]+ -> skip ;`                   |

Dos detalles del lexer que conviene conocer, porque explican muchos errores:

1. **Gana el token más largo.** Por eso `-->` es un `ARROW` y no tres `MINUS`.
2. **Si empatan en largo, gana la regla que aparece primero.** Por eso `class` es `CLASS` y no `IDENTIFIER`: `CLASS` está definida antes.

Como el lexer y el parser están en archivos separados, el parser indica de dónde sacar los tokens con `options { tokenVocab=PlantUmlLexer; }`.

## Usar el árbol desde Java: el Visitor

Cada sniffer hace siempre lo mismo (ver [`PrintSniffer.java`](../../src/main/java/oo2/redictado/PrintSniffer/PrintSniffer.java)):

```java
CharStream stream = CharStreams.fromString(code);           // 1. el texto
PlantUmlLexer lexer = new PlantUmlLexer(stream);            // 2. texto -> tokens
CommonTokenStream tokens = new CommonTokenStream(lexer);
PlantUmlParser parser = new PlantUmlParser(tokens);         // 3. tokens -> árbol
ParseTree tree = parser.diagram();                          //    empezando por la regla 'diagram'
```

Después recorre el árbol con un **visitor**: una clase que hereda de `PlantUmlParserBaseVisitor` y redefine solo los `visitXX` de las reglas que le interesan. Por ejemplo, [`PrintSnifferVisitor`](../../src/main/java/oo2/redictado/PrintSniffer/PrintSnifferVisitor.java) solo mira las firmas de métodos:

```java
@Override
public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
    if (ctx.IDENTIFIER().getText().equals("print")) {
        report.addAroma(new Aroma(this.callerName, "The diagram has print methods.", true));
    }
    return visitChildren(ctx);   // seguir recorriendo los hijos
}
```

- El parámetro `ctx` es el **nodo** del árbol. Tiene un método por cada elemento de la regla. Como la regla es `methodSignature : type? IDENTIFIER LPAREN paramList? RPAREN (COLON type)? ;`, podés pedirle `ctx.IDENTIFIER()`, `ctx.paramList()`, etc. Si un elemento es opcional y no está, devuelve `null`. Si un elemento puede aparecer más de una vez (como `type`), el método devuelve una lista: `ctx.type()` es una `List<TypeContext>` y `ctx.type(0)` es el primero.
- `getText()` devuelve el texto que corresponde a ese nodo.
- `visitChildren(ctx)` sigue recorriendo hacia abajo. Si no lo llamás, los hijos de ese nodo no se visitan.
- El tipo genérico (`Void`, `Boolean`, ...) es lo que devuelve cada `visit`. Usá `Void` (y `return visitChildren(ctx)`) si no necesitás devolver nada.

> [!TIP]
> Para saber qué métodos tiene un `XXContext`, buscá la clase en `PlantUmlParser.java`.

## Crear un nuevo sniffer

1. Creá una carpeta/paquete propio en [`src/main/java/oo2/redictado`](../../src/main/java/oo2/redictado/), por ejemplo `GodClassSniffer`.
2. Copiá `PrintSniffer.java` y `PrintSnifferVisitor.java` como punto de partida y renombralos.
3. Buscá en [`PlantUmlParser.g4`](../../src/main/antlr4/PlantUmlParser.g4) qué regla representa lo que querés analizar (una clase es `classDeclaration`, un atributo es `fieldSignature`, una relación es `relationship`, etc.) y redefiní su `visitXX`.
4. Escribí los tests en [`src/test/java/oo2/redictado`](../../src/test/java/oo2/redictado/) y la documentación en [`documentation`](../).
5. Ejecutá `mvn test`.

## Ver el árbol de un diagrama

Antes de escribir un visitor, conviene **mirar el árbol** que genera ANTLR para un diagrama concreto. Hay varias opciones:

### VS Code

Instalá la extensión [ANTLR4 grammar syntax support](https://marketplace.visualstudio.com/items?itemName=mike-lischke.vscode-antlr4). Además de colorear los `.g4`, permite depurar la gramática y ver el árbol gráficamente. Para eso creá un archivo `.vscode/launch.json` en la raíz del proyecto:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Ver árbol PlantUML",
      "type": "antlr-debug",
      "request": "launch",
      "input": "documentation/PlantUML/Examples/biblioteca.puml",
      "grammar": "src/main/antlr4/PlantUmlParser.g4",
      "startRule": "diagram",
      "visualParseTree": true
    }
  ]
}
```

y ejecutalo desde la pestaña *Run and Debug* (F5).

### IntelliJ IDEA

Instalá el plugin **ANTLR v4** (*Settings → Plugins*). Abrí `PlantUmlParser.g4`, hacé clic derecho sobre la regla `diagram` → *Test Rule diagram*, y pegá el diagrama en el panel *ANTLR Preview*.

### Terminal (antlr4-tools)

Con Python instalado:

```bash
pip install antlr4-tools
```

Y desde la raíz del proyecto:

```bash
# Muestra los tokens que genera el lexer
antlr4-parse -v 4.13.2 src/main/antlr4/PlantUmlLexer.g4 src/main/antlr4/PlantUmlParser.g4 diagram -tokens documentation/PlantUML/Examples/reporte.puml

# Muestra el árbol como texto
antlr4-parse -v 4.13.2 src/main/antlr4/PlantUmlLexer.g4 src/main/antlr4/PlantUmlParser.g4 diagram -tree documentation/PlantUML/Examples/reporte.puml

# Abre una ventana con el árbol dibujado
antlr4-parse -v 4.13.2 src/main/antlr4/PlantUmlLexer.g4 src/main/antlr4/PlantUmlParser.g4 diagram -gui documentation/PlantUML/Examples/reporte.puml
```

La primera vez descarga ANTLR (por eso necesita conexión a internet).

## Para saber más

- [Sitio oficial de ANTLR](https://www.antlr.org/)
- [Documentación oficial](https://github.com/antlr/antlr4/blob/master/doc/index.md)
- [Gramáticas de ejemplo para muchos lenguajes](https://github.com/antlr/grammars-v4)