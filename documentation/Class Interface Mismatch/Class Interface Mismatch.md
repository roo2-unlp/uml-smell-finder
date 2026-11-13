# Class/Interface Mismatch Sniffer

Detecta clases concretas que declaran una relación de realización con una interfaz, pero no satisfacen el contrato de esa interfaz.

La interfaz es el patrón de comparación: se revisan sus operaciones y se comprueba que la clase las ofrezca, ya sea directamente o por herencia. La clase puede tener operaciones adicionales; eso no constituye un mismatch.

Se considera que una operación no cumple el contrato cuando falta, no existe una firma compatible, su visibilidad es más restrictiva, es estática cuando la operación de la interfaz es de instancia, está declarada como abstracta en una clase concreta o tiene un tipo de retorno incompatible. Para la firma se comparan la cantidad, los tipos y el orden de los parámetros; los nombres de los parámetros no tienen que coincidir. Un retorno más específico es válido si su tipo es subtipo del declarado por la interfaz.

No se reporta mismatch para clases abstractas que difieren la implementación, interfaces sin operaciones, clases que no realizan interfaces ni relaciones `implements` con un clasificador que no sea una interfaz.

## Detección

El sniffer recorre el árbol de sintaxis generado a partir del diagrama PlantUML y reúne los clasificadores, sus operaciones y sus relaciones. Reconoce una interfaz tanto si se declara con `interface Nombre` como si se declara como `class Nombre <<interface>>`. Interpreta como realización las relaciones PlantUML `Interfaz <|.. Clase` y `Clase ..|> Interfaz`. También registra las relaciones de herencia para considerar las operaciones heredadas por la clase y las operaciones heredadas por una interfaz.

Para cada relación entre una clase concreta y una interfaz, toma las operaciones del contrato de esa interfaz y las compara con las operaciones propias y heredadas disponibles en la clase. Antes de comparar, normaliza cada operación para obtener su nombre, parámetros y tipos, retorno, visibilidad y modificadores. Los atributos no forman parte de esta comparación.

La comparación se hace por operación del contrato:

1. Busca operaciones disponibles en la clase con el mismo nombre.
2. Si no encuentra ninguna, registra que la operación está ausente. Si encuentra operaciones con ese nombre, pero ninguna tiene la misma cantidad, tipos y orden de parámetros, registra firma incompatible.
3. Si hay una operación con firma compatible, comprueba su visibilidad, si es estática o de instancia, si está declarada abstracta y si su retorno es compatible. Para el retorno, acepta el mismo tipo o un subtipo según las generalizaciones del diagrama.
4. Si alguna sobrecarga cumple el contrato, no registra mismatch para esa operación. Las operaciones adicionales no se reportan.

Cada relación clase-interfaz se analiza por separado. Por eso, si una clase realiza varias interfaces, el incumplimiento de una operación se reporta para la interfaz cuyo contrato no se cumple; no afecta a las demás. Una clase abstracta no se analiza como una implementación concreta incompleta.

## Reporte

Por cada operación del contrato que la clase no satisface, el sniffer agrega un aroma al reporte con la línea y el motivo. Cuando encuentra una operación incompatible, indica la línea de esa operación. Cuando la operación está ausente, indica la línea de la relación de realización, que es donde se declara el compromiso de implementar la interfaz. El mensaje identifica la clase, la interfaz, la operación requerida y el motivo detectado.

Al procesar [`ejemplo.puml`](./ejemplo.puml), el reporte esperado tendría un aroma por cada clase que incumple el contrato.

```text
[ClassInterfaceMismatchSniffer] line 52: RepositorioIncompleto realiza Repositorio, pero no ofrece buscar(id : String) : Entidad (operación ausente)
[ClassInterfaceMismatchSniffer] line 23: RepositorioConFirmaIncompatible realiza Repositorio, pero buscar(id : Integer) : Entidad no coincide con buscar(id : String) : Entidad (firma incompatible)
[ClassInterfaceMismatchSniffer] line 29: RepositorioConVisibilidadReducida realiza Repositorio, pero buscar(id : String) : Entidad tiene visibilidad privada y la interfaz la declara pública (visibilidad reducida)
[ClassInterfaceMismatchSniffer] line 35: RepositorioConOperacionEstatica realiza Repositorio, pero buscar(id : String) : Entidad es estática y la interfaz requiere una operación de instancia (estaticidad incompatible)
[ClassInterfaceMismatchSniffer] line 41: RepositorioConOperacionAbstracta realiza Repositorio, pero buscar(id : String) : Entidad está declarada abstracta en una clase concreta (implementación diferida)
[ClassInterfaceMismatchSniffer] line 47: RepositorioConRetornoIncompatible realiza Repositorio, pero buscar(id : String) : String no tiene un retorno compatible con Entidad (retorno incompatible)
```

`RepositorioCompleto` cumple el contrato y no genera un aroma. Si no se detecta ningún incumplimiento, el framework informa `The diagram smells good.`