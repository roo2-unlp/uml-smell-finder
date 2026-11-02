# Data Class Sniffer

Detecta clases que exponen sus datos y no tienen comportamiento propio en diagramas de clases PlantUML.

Fowler describe una *Data Class* como una clase con atributos, métodos para leerlos y modificarlos, y nada más. Son contenedores de datos que otras clases manipulan desde afuera, lo que dispersa la lógica que debería estar junto a esos datos. El refactor habitual es mover ese comportamiento a la clase (*Move Method*).

Una clase se considera *Data Class* si expone sus datos y no tiene comportamiento al mismo tiempo, es decir, si cumple todas estas condiciones:

1. **Es una clase**, abstracta o no. No se analizan interfaces, enumeraciones ni otros tipos (`entity`, `struct`, `exception`, `annotation`, etc.).
2. **Tiene atributos de instancia.** Los atributos `{static}` no cuentan.
3. **Expone sus datos:** tiene al menos un accessor que no es privado, o al menos un atributo público (`+`).
4. **No tiene comportamiento:** todos sus métodos son accessors. Los métodos `{static}` y los constructores no se tienen en cuenta.

Un *accessor* es un método `getX()` o `isX()` sin parámetros, o `setX(valor)` con un único parámetro. Un *constructor* es un método con el mismo nombre que la clase.

Los miembros se tienen en cuenta tanto si están declarados dentro del cuerpo de la clase como si están declarados afuera (`Clase : +getX()`). Una clase con atributos privados y sin métodos no se reporta, porque no expone sus datos.

Agrega un aroma por cada *Data Class*, con la línea donde está declarada la clase. No reporta olores si ninguna clase cumple todas las condiciones.

[`ejemplo.puml`](./ejemplo.puml) tiene tres *Data Classes*: `Alumno`, que expone sus datos con accessors; `Direccion`, que tiene atributos públicos; y `Persona`, una clase abstracta con su getter declarado fuera del cuerpo. `Curso` no se reporta porque tiene comportamiento, y `Materia` tampoco, porque no expone sus datos:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers DataClassSniffer \"documentation/DataClassSniffer/ejemplo.puml\""
```

```text
[DataClassSniffer] line 5: Alumno es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 15: Direccion es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 32: Persona es una Data Class: expone sus datos y no tiene comportamiento.
```

Si el diagrama no tiene *Data Classes*, el reporte indica `The diagram smells good.`.