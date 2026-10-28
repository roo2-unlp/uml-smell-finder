# UML Smell Finder :detective::deciduous_tree:

Busca malos olores de diseño en diagramas de clases de [PlantUML](https://plantuml.com/es/class-diagram). Los diagramas se parsean con una gramática de [ANTLR4](https://www.antlr.org/) y cada olor lo detecta un *sniffer* que recorre el árbol resultante.

Hay dos sniffers de ejemplo: [`PrintSniffer`](./src/main/java/oo2/redictado/sniffers/print/PrintSniffer.java), que detecta métodos `print`, y [`AnosmicCodeSniffer`](./src/main/java/oo2/redictado/sniffers/anosmic/AnosmicCodeSniffer.java), que no detecta nada.

Si nunca usaste ANTLR, empezá por la [guía de ANTLR4](./documentation/ANTLR4/ANTLR4.md).

## :wrench: Requisitos

- Java 21 o superior.
- Maven 3.9.9. Es opcional: el repositorio trae el wrapper, así que podés usar `./mvnw` (o `mvnw.cmd` en Windows) en lugar de `mvn` y se descarga solo.

ANTLR no se instala aparte, lo baja Maven.

## :rocket: Uso

Para compilar y correr los tests:

```bash
mvn test
```

Para analizar un diagrama:

```bash
mvn -q compile exec:java -Dexec.args="documentation/PlantUML/Examples/reporte.puml"
```

```text
[PrintSniffer] line 8: The diagram has print methods.
[PrintSniffer] line 12: The diagram has print methods.
```

Si no encuentra olores, imprime `The diagram smells good.`. El `-q` oculta los mensajes de Maven; sin él, el reporte aparece mezclado con el log del build.

Por defecto corren todos los sniffers registrados en `catalog()` de [`UmlSmellFinder`](./src/main/java/oo2/redictado/UmlSmellFinder.java), con sus valores por defecto. Para correr solo algunos, pasá sus nombres de clase separados por comas:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers PrintSniffer documentation/PlantUML/Examples/reporte.puml"
```

Si un sniffer acepta parámetros, van después del nombre, separados por `:`:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers PrintSniffer,MiSniffer:umbral=20:estricto=true diagrama.puml"
```

Un parámetro que el sniffer no usa, o un valor del tipo equivocado, es un error.

## :building_construction: Estructura

| Paquete                          | Contenido                                                    | Quién lo modifica            |
|----------------------------------|--------------------------------------------------------------|------------------------------|
| `oo2.redictado.sniffers.<tuyo>`  | El sniffer de cada grupo.                                    | Cada grupo, el suyo.         |
| `oo2.redictado`                  | `UmlSmellFinder`: el `main` y la lista de sniffers.          | Una línea por sniffer.       |
| `oo2.redictado.plantuml`         | Parser de PlantUML y clases base para sniffers y visitors.   | Nadie, salvo en un PR aparte. |
| `oo2.redictado.plantuml.antlr4`  | Clases generadas por ANTLR a partir de la gramática.         | Nadie, se regeneran al compilar. |
| `oo2.redictado.core`             | El framework, que no depende de ninguna gramática.           | Nadie, salvo en un PR aparte. |

```tree
.
├── documentation
│   ├── ANTLR4/               guía de ANTLR4
│   ├── PlantUML/             qué acepta la gramática, con ejemplos
│   └── <Nombre del Sniffer>/ documentación de cada sniffer
├── pom.xml
└── src
    ├── main
    │   ├── antlr4/oo2/redictado/plantuml/antlr4
    │   │   ├── PlantUmlLexer.g4
    │   │   └── PlantUmlParser.g4
    │   └── java/oo2/redictado
    │       ├── UmlSmellFinder.java
    │       ├── core/
    │       ├── plantuml/
    │       └── sniffers
    │           ├── anosmic/
    │           ├── print/
    │           └── <tu sniffer>/
    └── test/java/oo2/redictado
        ├── UmlSmellFinderTest.java
        ├── core/
        └── sniffers
            ├── anosmic/
            ├── print/
            └── <tu sniffer>/
```

### Sniffers

Cada sniffer va en su propio paquete dentro de [`sniffers`](./src/main/java/oo2/redictado/sniffers/), con los tests en el mismo paquete bajo `src/test` y la documentación en una carpeta propia de [`documentation`](./documentation/). Lo único que comparten los grupos es una línea en `catalog()` de [`UmlSmellFinder`](./src/main/java/oo2/redictado/UmlSmellFinder.java), así que los merges no deberían tener conflictos.

Los pasos para crear uno están en [Crear un nuevo sniffer](./documentation/ANTLR4/ANTLR4.md#crear-un-nuevo-sniffer).

Si necesitás cambiar `core`, `plantuml` o la gramática, no lo hagas en la rama de tu sniffer: mandá un PR aparte y chico, así el resto de los grupos lo puede integrar.

### Soporte para PlantUML

- [`PlantUml`](./src/main/java/oo2/redictado/plantuml/PlantUml.java) convierte el texto en un árbol. Si hay errores de sintaxis, lanza `IllegalArgumentException`.
- [`PlantUmlSniffer`](./src/main/java/oo2/redictado/plantuml/PlantUmlSniffer.java) es la superclase de los sniffers. Solo hay que implementar `createVisitor()`.
- [`PlantUmlVisitor`](./src/main/java/oo2/redictado/plantuml/PlantUmlVisitor.java) es la superclase de los visitors. Se redefinen los `visitXX` necesarios y se llama a `smell(mensaje, ctx)` por cada olor.

### Framework

- [`CodeSniffer`](./src/main/java/oo2/redictado/core/CodeSniffer.java): recibe el árbol del código y agrega al reporte los olores que encuentra.
- [`VisitorSniffer`](./src/main/java/oo2/redictado/core/VisitorSniffer.java): un `CodeSniffer` que recorre el árbol con un visitor. Las subclases solo dicen qué visitor usar (*Template Method*).
- [`SmellVisitor`](./src/main/java/oo2/redictado/core/SmellVisitor.java) y [`SmellCollector`](./src/main/java/oo2/redictado/core/SmellCollector.java): la interfaz de esos visitors y el objeto por el que informan los olores.
- [`SerialSniffer`](./src/main/java/oo2/redictado/core/SerialSniffer.java): corre varios sniffers en orden. Le pasa a cada uno una copia del árbol hecha con [`TreeCopier`](./src/main/java/oo2/redictado/core/TreeCopier.java), para que si uno la modifica no afecte a los demás.
- [`SnifferCatalog`](./src/main/java/oo2/redictado/core/SnifferCatalog.java): los sniffers que se pueden ejecutar, cada uno con un nombre. Los crea a partir de sus parámetros.
- [`SnifferParams`](./src/main/java/oo2/redictado/core/SnifferParams.java): los parámetros de un sniffer, que se leen por nombre y con un valor por defecto.
- [`Language`](./src/main/java/oo2/redictado/core/Language.java): un lenguaje que se puede parsear.
- [`SmellFinder`](./src/main/java/oo2/redictado/core/SmellFinder.java): parsea el código con un `Language` y le pasa el árbol a un sniffer.
- [`Aroma`](./src/main/java/oo2/redictado/core/Aroma.java) y [`AromaReport`](./src/main/java/oo2/redictado/core/AromaReport.java): un olor (sniffer, mensaje y línea) y el reporte que los junta.
- [`ReportFormatter`](./src/main/java/oo2/redictado/core/ReportFormatter.java): convierte un reporte en texto (*Strategy*). Por ahora solo existe [`PlainTextFormatter`](./src/main/java/oo2/redictado/core/PlainTextFormatter.java).
