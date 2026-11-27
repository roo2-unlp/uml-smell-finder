# Data Class Sniffer

Detecta clases que exponen sus datos y no tienen comportamiento propio en diagramas de clases PlantUML.

Fowler describe una *Data Class* como una clase con atributos, métodos para leerlos y modificarlos, y nada más. Son contenedores de datos que otras clases manipulan desde afuera, lo que dispersa la lógica que debería estar junto a esos datos. El refactor habitual es mover ese comportamiento a la clase (*Move Method*).

Este sniffer reporta una clase como *Data Class* cuando cumple todas estas condiciones:

1. **Es una clase**, concreta o abstracta. Se incluyen las abstractas porque, aunque no puedan instanciarse directamente, pueden declarar atributos y accessors que sus subclases hereden. No se analizan interfaces, enumeraciones ni otros tipos (`entity`, `struct`, `exception`, `annotation`, etc.), aunque algunos puedan representar datos: este sniffer se enfoca en el smell *Data Class* aplicado a clases del modelo orientado a objetos.
2. **Tiene atributos de instancia.** Los atributos `{static}` no cuentan porque pertenecen a la clase y son compartidos, en lugar de formar parte del estado individual de cada objeto.
3. **Expone sus datos:** tiene al menos un accessor que no es privado, o al menos un atributo público (`+`).
4. **No tiene comportamiento:** todos sus métodos de instancia, excepto los constructores, son accessors. Se excluyen:
   - Los métodos `{static}`, porque son operaciones asociadas a la clase, no a una instancia concreta. 
   - Los constructores, porque corresponden a la creación e inicialización del objeto, no a su comportamiento una vez creado. 

Un *accessor* es un método `getX()` o `isX()` sin parámetros, o `setX(valor)` con un único parámetro. Después del prefijo tiene que venir una mayúscula; por eso `get()`, `getter()`, `issue()` y `setup(valor)` no son accessors. El sniffer reconoce los accessors y constructores por su firma, sin analizar el cuerpo de los métodos. Un *constructor* es un método con el mismo nombre que la clase.

Se consideran los miembros declarados dentro del cuerpo de la clase y los declarados afuera (`Clase : +getX()`).

El sniffer agrega un aroma por cada *Data Class*, indicando la línea donde se declaró la clase. Si ninguna cumple las condiciones, el reporte indica `The diagram smells good.`.

[`ejemplo.puml`](./ejemplo.puml) tiene tres *Data Classes*: `Alumno`, que expone sus datos con accessors; `Direccion`, que tiene atributos públicos; y `Persona`, una clase abstracta con su getter declarado fuera del cuerpo. `Curso` no se reporta porque tiene comportamiento, y `Materia` tampoco, porque no expone sus datos:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers DataClassSniffer \"documentation/Data Class Sniffer/ejemplo.puml\""
```

```text
[DataClassSniffer] line 5: Alumno es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 15: Direccion es una Data Class: expone sus datos y no tiene comportamiento.
[DataClassSniffer] line 32: Persona es una Data Class: expone sus datos y no tiene comportamiento.
```
