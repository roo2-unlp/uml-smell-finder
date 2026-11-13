# Heurísticas de detección del bad smell Class/Interface Mismatch

## 1. Propósito y alcance

Este documento especifica el criterio de detección del bad smell Class/interface mismatch para el analizador que trabajara sobre un árbol de sintaxis generado por ANTLR a partir de un diagrama de clases PlantUML (v1.2025.0).

Los objetivos son:

- Definir con precisión el smell, de modo que podamos identificar unívocamente si esta presente o no y en cual es la evidencia que nos basamos para detectarlo.
- Especificar la detección, en términos implementables, que información extraer del árbol de sintaxis, normalizaciones previas y heurísticas a considerar.

## 2. Definición concreta del smell

### 2.1 Definición

Hay class/interface mismatch cuando un clasificador concreto (clase) declara una relación de realización con una interfaz y no satisface el contrato de esa interfaz: le falta alguna de sus operaciones, o la operación declarada no es compatible en firma, visibilidad, tipo de retorno o naturaleza (instancia vs. estática).

Esta definición se apoya en la regla de realización de UML: la realización «conecta a un elemento del modelo, como una clase, con otro elemento del modelo, como una interfaz, que le proporciona su especificación de comportamiento, pero no su estructura o implementación. El cliente debe tener (por herencia o por declaración directa) al menos todas las operaciones que tiene el proveedor» (Libro UML, p. 55).

De la regla se derivan dos consecuencias que se deben respetar:

- La interfaz es el patrón de comparación, no la clase: se recorre el contrato y se verifica que la clase lo cubra, nunca al revés.
- Cubrir de más está permitido: una clase puede declarar operaciones adicionales a las de la interfaz. Eso no es mismatch (la regla pide «al menos todas las operaciones del proveedor»).

### 2.2 Delimitación frente a smells vecinos

| Situacion Observada | ¿Es CIM? | Motivo |
|---|---|---|
| Clase realiza interfaz y le falta una operación | Si | Viola la regla de completitud |
| Clase realiza interfaz con la operación pero firma incompatible | Si | La operación no satisface el contrato |
| Clase realiza interfaz con la operación pero visibilidad reducida | Si | No es invocable a través del contrato |
| Clase abstracta que difiere operación a sus subclases | No | La realizacion es legitimamente incompleta en un tipo abstracto |
| Interfaz sin operaciones | No | No hay contrato que cumplir |
| Clase con muchos métodos y ninguna interfaz | No | Es Large class, no CIM |
| Jerarquía de clases donde una subclase rehusa métodos heredados | No | Es refused bequest |
| Implements sobre un nombre no declarado como interface | No | Error de modelo |

## 3. Especificación de la detección

Para especificar que datos son necesarios extraer del árbol de sintaxis partimos de la definición previamente planteada.

"El cliente debe tener al menos todas las operaciones que tiene el proveedor".

- **Definición de datos necesarios:** Para poder afirmar o negar la regla anterior, necesitamos las operaciones definidas por la interfaz, las operaciones ofrecidas por la clase (incluyendo su herencia) y la correspondencia exacta de que clase implementa que interfaz.
- **Extracción de los datos:** Estos datos crudos se obtienen directamente del árbol de sintaxis generado por ANTLR. El parser procesa este arbol y produce 2 listas, cajas (Clasificaciones de UML, con cada linea dentro de las "cajas"), y relaciones (Relaciones de UML).
- **Parseo y normalización de Firmas:** El árbol de ANTLR por si solo no entrega "operaciones" lógicas, si no texto. Es necesario traducir este texto para decidir que corresponde al nombre, cuales a los parámetros, y que tipo de retorno posee y cual es su visibilidad. (Esta función quizá se encuentre implementada previamente en el framework).
- **Criterio de identidad de operaciones:** Una vez pasadas, dos operaciones de un mismo método pueden diferir sintácticamente. Ejemplo: En la interfaz: + buscar(id : String) y en la clase: + buscar(codigo : String). Por ello, el algoritmo debe definir formalmente bajo qué condiciones exactas dos operaciones se consideran "la misma".
- **Herencia:** El alcance del analisis se amplia reconociendo que los método heredados de las superclases tambien forman parte del comportamiento propio de la clase evaluada por lo que deben sumarse al conjunto de operaciones a comparar.
- **Aplicación de heurísticas:** Con ambas listas normalizas y completas (operaciones de la interfaz y operaciones de la clase), se procede a la comparacion mediante un conjunto de heurísticas que modelan las diferentes formas en que el cumplimiento del contrato puede fallar.

## 4. Heurísticas

### 4.1 Operación de la interfaz ausente en la clase

**Qué detecta:** una operación que la interfaz exige y que la clase no declara ni hereda.

**Condición:** la operación pertenece al contrato y no existe ningún miembro con ese nombre en las operaciones disponibles de la clase.

**Ejemplo:** Repositorio declara buscar(id : String); RepositorioMemoria sólo declara guardar(e : Entidad).

**Por qué es mismatch:** es el caso directo de la regla de la p. 55, el cliente no tiene todas las operaciones del proveedor.

### 4.2 Firma incompatible

**Qué detecta:** la operación existe con ese nombre, pero ninguna de sus versiones coincide en firma con la de la interfaz.

**Condición:** hay candidatos por nombre, pero ninguno con la misma firma canónica (misma cantidad de parámetros y mismos tipos, en el mismo orden).

**Ejemplo:** la interfaz declara buscar(id : String) y la clase declara buscar(id : Integer).

**Por qué es mismatch:** hay un método con el nombre correcto, pero no puede sustituir al que promete el contrato.

**Aclaración:** si existe al menos una sobrecarga compatible, no se reporta. Cubrir de más sigue estando permitido.

### 4.3 Visibilidad reducida

**Qué detecta:** la operación existe y la firma coincide, pero la clase la declara con una visibilidad más restrictiva que la interfaz.

**Condición:** firma igual y la visibilidad de la clase es menor que la de la interfaz, con la escala pública (+) > package (~) > protegida (#) > privada (-).

**Ejemplo:** la interfaz declara +calcular() : Double y la clase la declara -calcular() : Double.

**Por qué es mismatch:** una operación del contrato declarada privada no es invocable a través de la interfaz. El libro es explícito en que el contenido de una interfaz es público (p. 395).

**Aclaración:** si la visibilidad no está declarada en alguno de los dos lados, no se reporta.

### 4.4 Estaticidad incompatible

**Qué detecta:** la operación de la interfaz es de instancia y la de la clase es estática.

**Condición:** firma igual y la clase marca la operación como estática mientras la interfaz no lo hace.

**Ejemplo:** la interfaz declara +crear() : Entidad y la clase declara {static} +crear() : Entidad.

**Por qué es mismatch:** un método estático no participa del despacho polimórfico, por lo que no puede ocupar el lugar de la operación de instancia que promete la interfaz.

### 4.5 Implementación diferida en tipo concreto

**Qué detecta:** el hueco de 4.1 o 4.2 existe en una clase concreta, pero la operación figura como abstracta.

**Condición:** no hay implementación, la clase no es abstracta y la operación aparece declarada como {abstract}.

**Ejemplo:** class RepositorioMemoria implements Repositorio que declara {abstract} +buscar(id : String) : Entidad.

**Por qué es mismatch:** es el mismo incumplimiento que 4.1 o 4.2, pero con la causa a la vista: la operación está declarada y sin implementación en un tipo que debería poder instanciarse

### 4.6 Retorno incompatible

**Qué detecta:** coinciden nombre y firma, pero el tipo de retorno de la clase no es igual ni un subtipo del que declara la interfaz.

**Condición:** ambos retornos están declarados y el retorno de la clase no es igual ni subtipo del de la interfaz. El subtipo se resuelve con las relaciones de generalización del propio diagrama.

**Ejemplo:** la interfaz declara +crear() : Animal y la clase declara +crear() : String.

**Por qué es mismatch:** UML admite que el retorno sea más específico que el declarado, pero no un tipo ajeno. El cliente que espera un Animal recibiría otra cosa.
