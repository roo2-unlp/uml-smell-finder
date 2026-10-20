# UML Smell Finder :detective::deciduous_tree:

Este es un proyecto de ejemplo que utiliza [ANTLR4](https://www.antlr.org/) con Maven y una gramática de [PlantUML](https://plantuml.com/es/class-diagram) para diagramas de clases. Se implementa un **code sniffer** que no detecta olores ([`AnosmicCodeSniffer`](./src/main/java/oo2/redictado/AnosmicCodeSniffer/AnosmicCodeSniffer.java)) y uno que busca todos los métodos `print` declarados en el diagrama ([`PrintSniffer`](./src/main/java/oo2/redictado/PrintSniffer/PrintSniffer.java)). Además, se incluyen [tests de unidad](./src/test/java/oo2/redictado/) para validar su funcionamiento.

> [!TIP]
> Si es la primera vez que usás ANTLR, empezá por la [guía de ANTLR4 para este proyecto](./documentation/ANTLR4/ANTLR4.md).

## :wrench: Requerimientos

Asegurate de tener instalados y configurados los **paths** de:

- **Java 21** (o superior)

- **Maven 3.9.9**

> [!NOTE]
> En el repositorio está incluido el wrapper de Maven, que descargará la versión 3.9.9 si no la tenés instalada. Si preferís optar por esta opción, podés no instalar Maven por tu cuenta y reemplazar `mvn` por `./mvnw` o `./mvnw.cmd` (según tu OS) en los comandos que se detallan a continuación.

ANTLR4 no se instala aparte: Maven lo descarga automáticamente (ver [Instalación](./documentation/ANTLR4/ANTLR4.md#instalación)).

## :building_construction: Estructura del proyecto

El árbol de archivos se ve de la siguiente manera:

```tree
.
├── documentation
│   ├── Anosmic Code Sniffer
│   │   └── Anosmic Code Sniffer.md
│   ├── ANTLR4
│   │   └── ANTLR4.md
│   ├── PlantUML
│   │   ├── PlantUML.md
│   │   └── Examples
│   │       ├── banco.puml
│   │       ├── biblioteca.puml
│   │       ├── figuras.puml
│   │       └── reporte.puml
│   └── Print Sniffer
│       └── Print Sniffer.md
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── antlr4
    │   │   ├── PlantUmlLexer.g4
    │   │   └── PlantUmlParser.g4
    │   └── java
    │       └── oo2
    │           └── redictado
    │               ├── AnosmicCodeSniffer
    │               │   ├── AnosmicCodeSniffer.java
    │               │   └── AnosmicVisitor.java
    │               ├── antlr4
    │               │   ├── PlantUmlLexer.interp
    │               │   ├── PlantUmlLexer.java
    │               │   ├── PlantUmlLexer.tokens
    │               │   ├── PlantUmlParserBaseListener.java
    │               │   ├── PlantUmlParserBaseVisitor.java
    │               │   ├── PlantUmlParser.interp
    │               │   ├── PlantUmlParser.java
    │               │   ├── PlantUmlParserListener.java
    │               │   ├── PlantUmlParser.tokens
    │               │   └── PlantUmlParserVisitor.java
    │               ├── Aroma.java
    │               ├── AromaReport.java
    │               ├── CodeSniffer.java
    │               ├── PrintSniffer
    │               │   ├── PrintSniffer.java
    │               │   └── PrintSnifferVisitor.java
    │               └── SerialSniffer.java
    └── test
        └── java
            └── oo2
                └── redictado
                    ├── AnosmicCodeSnifferTest.java
                    └── PrintSnifferTest.java
```

- Las clases deben ubicarse en la carpeta [`src/main/java/oo2/redictado`](./src/main/java/oo2/redictado/).

  - La interfaz [`CodeSniffer`](./src/main/java/oo2/redictado/CodeSniffer.java) define el protocolo que debe seguir un **code sniffer**.

  - También hay carpetas/paquetes de ejemplo para el [`AnosmicCodeSniffer`](./src/main/java/oo2/redictado/AnosmicCodeSniffer/) y [`PrintSniffer`](./src/main/java/oo2/redictado/PrintSniffer/). Cada nuevo **code sniffer** que se implemente debe estar en su propia carpeta.

  - La clase [`SerialSniffer`](./src/main/java/oo2/redictado/SerialSniffer.java) es la encargada de ejecutar los **code sniffers** en un orden específico y configurable.

  - La clase [`Aroma`](./src/main/java/oo2/redictado/Aroma.java) representa un mensaje de olor, que contiene información sobre el olor detectado (si lo hubiera).

  - La clase [`AromaReport`](./src/main/java/oo2/redictado/AromaReport.java) es un reporte que contiene todos los `Aroma` generados por los **code sniffers** de un `SerialSniffer`.

- La carpeta [`src/main/java/oo2/redictado/antlr4`](./src/main/java/oo2/redictado/antlr4/) contiene las clases **generadas** por ANTLR4. No las modifiques a mano.

- Las clases de prueba deben ir en [`src/test/java/oo2/redictado`](./src/test/java/oo2/redictado/).

  - El proyecto incluye clases de test para probar el `AnosmicCodeSniffer` y el `PrintSniffer`.

- Las gramáticas para ANTLR4 deben colocarse en la carpeta [`src/main/antlr4`](./src/main/antlr4/).

- La carpeta [`documentation`](./documentation/) está destinada a almacenar la documentación de los **code sniffers** implementados.
  - En el ejemplo, se incluyen carpetas para el "Anosmic Code Sniffer" y "Print Sniffer". También hay una carpeta que documenta la gramática de [PlantUML](./documentation/PlantUML/PlantUML.md), explicando qué reconoce y mostrando ejemplos, y una [guía de ANTLR4](./documentation/ANTLR4/ANTLR4.md).

## :old_key: Cómo utilizar ANTLR4

Para generar las clases de Java correspondientes al **Lexer**, **Parser**, **Listener** y **Visitor** de una gramática específica, seguí estos pasos:

1. Colocá la gramática en la carpeta [`src/main/antlr4`](./src/main/antlr4/).
2. Ejecutá el comando:

   ```bash
   mvn antlr4:antlr4
   ```

> [!IMPORTANT]
> Tenés que volver a ejecutar este comando **cada vez que modifiques un `.g4`**. `mvn test` no regenera las clases.

Para más detalle (cómo leer la gramática, cómo escribir un visitor, cómo ver el árbol de un diagrama y errores frecuentes) mirá la [guía de ANTLR4](./documentation/ANTLR4/ANTLR4.md).

## :construction_worker_woman: Cómo compilar y ejecutar los tests

Para compilar las clases y ejecutar las pruebas con JUnit, usá el siguiente comando:

```bash
mvn test
```
