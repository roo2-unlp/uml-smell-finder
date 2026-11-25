# Data Class Sniffer

Detecta clases que exponen sus datos y no tienen comportamiento propio en diagramas de clases PlantUML.

Fowler describe una *Data Class* como una clase con atributos, métodos para leerlos y modificarlos, y nada más. Son contenedores de datos que otras clases manipulan desde afuera, lo que dispersa la lógica que debería estar junto a esos datos. El refactor habitual es mover ese comportamiento a la clase (*Move Method*).

Una clase se considera *Data Class* si expone sus datos y no tiene comportamiento al mismo tiempo, es decir, si cumple todas estas condiciones:

1. **Es una clase**, concreta o abstracta. Se incluyen las clases abstractas porque ser abstracta solo impide crear instancias directamente: igual puede definir atributos y accessors sin comportamiento, y todas sus subclases heredan ese problema. No se analizan interfaces, enumeraciones ni otros tipos (`entity`, `struct`, `exception`, `annotation`, etc.), porque representan otros conceptos y no clases que guardan estado.
2. **Tiene atributos de instancia.** Los atributos `{static}` no cuentan, porque existen una sola vez para toda la clase y no forman parte de cada objeto. Suelen usarse para constantes, como una tasa de interés fija, o para valores que comparten todas las instancias, como un contador de objetos creados. Una *Data Class* se define por los datos propios que lleva cada una de sus instancias, y un atributo `{static}` no es un dato de ninguna instancia en particular.
3. **Expone sus datos:** tiene al menos un accessor que no es privado, o al menos un atributo público (`+`).
4. **No tiene comportamiento de instancia adicional:** todos sus métodos de instancia que no sean constructores son accessors. No se tienen en cuenta:
   - los métodos `{static}`, porque son operaciones asociadas a la clase, no a una instancia concreta. Un método estático sí puede recibir un objeto y operar sobre él; se excluye porque este sniffer evalúa el comportamiento propio de las instancias. Por esa decisión, un método estático que implemente lógica de negocio no impide que la clase sea reportada.
   - los constructores, porque corresponden a la creación e inicialización del objeto, no a su comportamiento una vez creado. Se ignoran como categoría de método; esto no afirma que la inicialización nunca contenga lógica propia.

Un *accessor* es un método `getX()` o `isX()` sin parámetros, o `setX(valor)` con un único parámetro. Después del prefijo tiene que venir una mayúscula, así que `get()`, `getter()`, `issue()` y `setup(valor)` no son accessors. Un *constructor* es un método con el mismo nombre que la clase.

Los miembros se tienen en cuenta tanto si están declarados dentro del cuerpo de la clase como si están declarados afuera (`Clase : +getX()`). Una clase con atributos privados y sin métodos no se reporta, porque no expone sus datos.

Agrega un aroma por cada *Data Class*, con la línea donde está declarada la clase. No reporta olores si ninguna clase cumple todas las condiciones.

[`ejemplo.puml`](./ejemplo.puml) tiene tres *Data Classes*: `Alumno`, que expone sus datos con accessors; `Direccion`, que tiene atributos públicos; y `Persona`, una clase abstracta con su getter declarado fuera del cuerpo. `Curso` no se reporta porque tiene comportamiento, y `Materia` tampoco, porque no expone sus datos:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers DataClassSniffer \"documentation/Data Class Sniffer/ejemplo.puml\""
```

```text
[DataClassSniffer] line 5: Alumno es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 15: Direccion es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 32: Persona es una Data Class: expone sus datos y no tiene comportamiento.
```

Si el diagrama no tiene *Data Classes*, el reporte indica `The diagram smells good.`.