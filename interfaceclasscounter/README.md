# Contador de Interfaces y Clases (interfacecounter)

Módulo independiente que analiza diagramas de clases PlantUML (`.puml`), detecta interfaces y clases, y contabiliza la cantidad total existente, listando además los métodos correspondientes a cada una.

---

## Qué detecta

1. Interfaces declaradas canónicamente con la palabra clave: `interface Nombre { ... }`.
2. Interfaces declaradas mediante estereotipo: `class Nombre <<interface>> { ... }` (o `<<Interface>>`).
3. Clases concretas y abstractas (`class Nombre`, `abstract class Nombre`, `abstract Nombre`).
4. Métodos propios de cada interfaz y clase (visibilidad, nombre, parámetros y tipo de retorno), definidos tanto dentro del bloque como en miembros externos (`Tipo : +metodo()`).
5. Ignora otros tipos (como enums) y desduplica declaraciones repetidas en el mismo archivo.

---

## Requisitos previos

Tener instalado Java 17+ y Maven. Si es la primera vez que clonas el repositorio, compila e instala los módulos base en el repositorio local de Maven ejecutando desde la raíz del proyecto:

```bash
mvn install -DskipTests
```

---

## Cómo ejecutar el Runner

### Opción 1: Desde la raíz del proyecto
```bash
mvn -pl interfacecounter exec:java -Dexec.args="ruta/al/diagrama.puml"
```

Por ejemplo, con el archivo de prueba provisto:
```bash
mvn -q -pl interfacecounter exec:java -Dexec.args="prueba_interfaces.puml"
```

### Opción 2: Posicionado dentro de la carpeta `interfacecounter`
```bash
cd interfacecounter
mvn exec:java -Dexec.args="ejemplo.puml"
```

*(En Windows PowerShell, se recomienda entrecomillar el argumento completo: `'-Dexec.args=ejemplo.puml'`)*.

---

## Ejemplo de Salida

```text
Interfaces encontradas: 3
 - Repositorio
     * + guardar()
 - Autenticable
     (sin métodos)
 - Logger
     * + log(msg : String)

Clases encontradas: 2
 - Usuario
     (sin métodos)
 - EntidadBase
     (sin métodos)
```

---

## Cómo correr los Tests

Para ejecutar la suite de pruebas unitarias (JUnit 5):

```bash
# Desde la raíz del repositorio:
mvn test -pl interfacecounter

# O dentro de la carpeta interfacecounter:
mvn test
```

---

## Estructura del Módulo y Diseño (Double Dispatch)

- **`DeclaredType`** (clase abstracta) y sus subclases polimórficas **`InterfaceType`**, **`ClassType`** y **`UnclassifiedType`** (Null Object):
  - Implementan **Double Dispatch** para la fusión/promoción de tipos (`mergeWith` → `mergeWithInterface`, `mergeWithClass`, `mergeWithUnclassified`), eliminando condicionales (`if/else`) al combinar declaraciones repetidas, estereotipos `<<interface>>` o miembros externos.
  - Implementan **Double Dispatch** (`accept(DeclaredTypeVisitor)`) para ser visitados polimórficamente.
- **`DeclaredTypeVisitor`** y **`TypeCollector`**: Separan y contabilizan interfaces y clases mediante Double Dispatch (`visitInterface`, `visitClass`, `visitUnclassified`) sin usar `if` ni `instanceof`.
- **`InterfaceCounterVisitor`**: Extiende `PlantUMLClassDiagramBaseVisitor<Void>` aprovechando el despacho polimórfico de ANTLR sobre cada nodo del árbol (`visitClassDeclaration`, `visitClassKind`, `visitStereotype`, `visitMember`, `visitMemberExternal`, `visitVisibility`, `visitMethodSignature`, `visitParam`).
- **`InterfaceCounter`**: Servicio utilitario que orquesta el parseo y devuelve un `Result` con las interfaces, clases y sus métodos.
- **`InterfaceCounterRunner`**: Clase ejecutable con método `main(String[] args)` configurada en el `pom.xml` vía `exec-maven-plugin`.
- **`InterfaceCounterTest`**: Batería de pruebas unitarias que validan casos estándar, estereotipos, promoción por Double Dispatch, clases sin interfaces y miembros externos.
