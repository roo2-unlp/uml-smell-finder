# God Class Sniffer

Detecta **clases que concentran una cantidad anormal de comportamiento y estado propio** en diagramas de clases PlantUML, comparando cada clase contra el resto de las clases del mismo diagrama.

---

## Índice

1. [Marco conceptual: God Class vs. Large Class](#1-marco-conceptual-god-class-vs-large-class)
2. [Alcance al trabajar sobre diagramas sin relaciones](#2-alcance-al-trabajar-sobre-diagramas-sin-relaciones)
3. [Hipótesis de detección](#3-hipótesis-de-detección)
4. [Definición formal de las métricas](#4-definición-formal-de-las-métricas)
   - 4.1 [Población analizada](#41-población-analizada)
   - 4.2 [NOM: Número de Métodos de Comportamiento](#42-nom-número-de-métodos-de-comportamiento)
   - 4.3 [NOA: Número de Atributos Propios](#43-noa-número-de-atributos-propios)
5. [Umbrales dinámicos](#5-umbrales-dinámicos)
   - 5.1 [Fundamento del uso del Rango Intercuartílico (IQR)](#51-fundamento-del-uso-del-rango-intercuartílico-iqr)
   - 5.3 [El umbral híbrido: cerca de Tukey con piso absoluto](#53-el-umbral-híbrido-cerca-de-tukey-con-piso-absoluto)
   - 5.4 [Tamaño mínimo por defecto del diagrama](#54-tamaño-mínimo-por-defecto-del-diagrama)
6. [Regla de decisión](#6-regla-de-decisión)
7. [Referencias](#13-referencias)

---

## 1. Marco conceptual: God Class vs. Large Class

En el diseño orientado a objetos, una **God Class** es una clase que centraliza la inteligencia del sistema: concentra demasiada lógica, manipula datos de otras clases en lugar de delegarles responsabilidades y deja a su alrededor clases "anémicas".

Lanza y Marinescu (2006) formalizan su detección en código fuente mediante tres síntomas simultáneos:

| Síntoma | Significado | Métrica en código |
|---|---|---|
| **Alta complejidad** | La clase acumula mucha lógica | WMC (*Weighted Methods per Class*) |
| **Acceso a datos ajenos** | Lee o modifica directamente atributos de otras clases | ATFD (*Access To Foreign Data*) |
| **Baja cohesión** | Sus métodos operan sobre subconjuntos disjuntos de estado | TCC (*Tight Class Cohesion*) |

Por su parte, Fowler (1999) describe el olor **Large Class**: una clase que intenta hacer demasiado y se manifiesta principalmente por tener **demasiados atributos** y **demasiados métodos**.

### ¿Por qué es un problema de diseño?

Ambos olores violan el **Principio de Responsabilidad Única (SRP)**: la clase tiene múltiples razones para cambiar. Como consecuencia:

- Se vuelve difícil de comprender, mantener y testear.
- Concentra modificaciones frecuentes (alto riesgo de errores y conflictos).
- Impide la reutilización modular del diseño.

---

## 2. Alcance al trabajar sobre diagramas sin relaciones

El framework parsea **diagramas de clases PlantUML**, no código fuente. Por lo tanto, disponemos de:

| Información en PlantUML | ¿Se usa en esta versión? | Consecuencia |
|---|---|---|
| Nombre y tipo (`class`, `abstract class`, `interface`, `enum`, ...) | **Sí** | Permite filtrar qué elementos son clases analizables |
| Firmas de métodos (nombre y parámetros) | **Sí** | Permite contar métodos de comportamiento (**NOM**) |
| Atributos declarados | **Sí** | Permite contar estado propio (**NOA**) |
| Relaciones (`-->`, `o--`, `*--`, `..>`) | **No** *(fuera de alcance)* | Simplifica el analizador, pero impide medir acoplamiento |
| Cuerpo de los métodos | **No disponible** | Imposible calcular WMC, ATFD o TCC reales |


Sin cuerpos de método ni relaciones no es posible verificar si una clase accede a datos ajenos (ATFD) ni si sus métodos son cohesivos (TCC). Por lo tanto:

> **Lo que detecta exactamente este sniffer es hipertrofia estructural** (un **Large Class** en el sentido de Fowler: exceso simultáneo de estado propio y comportamiento), utilizado como **indicador estructural de una posible God Class** dentro de las limitaciones de un diagrama estático.

Aclarar este alcance es fundamental: no se reporta una confirmación definitiva, sino una **alerta fundada** para revisar el diseño de esa clase.

---

## 3. Hipótesis de detección

> **Hipótesis.** Dentro de un diagrama con suficiente población de clases, una clase es sospechosa de concentrar responsabilidades en exceso (candidata a God Class / Large Class) si posee simultáneamente una cantidad **anormalmente alta** de **métodos de comportamiento (NOM)** y de **atributos propios (NOA)**.

La noción de "anormalmente alta" combina dos criterios complementarios:

1. **Criterio relativo al diagrama:** los umbrales se calculan con estadística no paramétrica sobre las clases del mismo diagrama (cuartiles y Rango Intercuartílico, **IQR**), adaptándose a sistemas chicos o grandes.
2. **Criterio absoluto mínimo (piso):** una clase no se considera anómala si no supera además un umbral mínimo con respaldo cognitivo y bibliográfico, evitando marcar clases pequeñas en diagramas muy homogéneos.

---

## 4. Definición formal de las métricas

### 4.1 Población analizada

Se considera **clase analizable** a todo elemento declarado con palabra clave `class` o `abstract` (incluyendo `abstract class`).

**Se excluyen:** `interface` (por palabra clave o por estereotipo `<<interface>>`), `enum`, `annotation`, `exception`, `entity`, `metaclass`, `stereotype`, `protocol` y `struct` (todas las variantes previstas en [`PlantUmlParser.g4`](../../src/main/antlr4/oo2/redictado/plantuml/antlr4/PlantUmlParser.g4)).

| Elemento excluido | Justificación |
|---|---|
| Interfaces | No poseen estado ni implementación; sesgarían la distribución de atributos hacia cero |
| Enums | La gramática parsea sus constantes como campos (`fieldSignature`), inflando artificialmente su cantidad de atributos |
| Otros tipos auxiliares | No representan clases de dominio con comportamiento y estado propios |

**Reglas de consolidación:**

- Si una misma clase aparece **declarada más de una vez**, sus miembros se **fusionan** en un único registro y se conserva la línea de la primera declaración para el reporte.
- Los miembros declarados **fuera del cuerpo** (`memberExternal`, por ejemplo `Cuenta : +transferir(...)`) se suman a la clase correspondiente, sin importar si aparecen antes o después de la declaración de la clase.
- Los miembros externos pertenecientes a tipos excluidos o no declarados como clase analizable se descartan al evaluar.

Se nota $n$ a la cantidad total de clases analizables del diagrama.

### 4.2 NOM: Número de Métodos de Comportamiento

$\mathbf{NOM}(c)$ es la cantidad de métodos declarados en la clase $c$ (dentro o fuera del cuerpo) **excluyendo**:

| Método excluido | Regla de reconocimiento | Ejemplo en `Cuenta` |
|---|---|---|
| Constructores | Nombre idéntico al nombre de la clase | `Cuenta(saldo : Real)` |
| Getters | Comienza con `get` + letra mayúscula y tiene `0` parámetros | `getSaldo()` |
| Getters booleanos | Comienza con `is` + letra mayúscula y tiene `0` parámetros | `isActiva()` |
| Setters | Comienza con `set` + letra mayúscula y tiene `1` parámetro | `setSaldo(s : Real)` |

**Justificación:** los constructores y accesores (*getters/setters*) no encapsulan lógica de negocio. Si se contaran, una **Data Class / DTO** (por ejemplo, 12 atributos con 24 getters y setters y sin lógica) dispararía falsos positivos, cuando en realidad representa el olor opuesto a una God Class (clase anémica sin inteligencia).

### 4.3 NOA: Número de Atributos Propios

$\mathbf{NOA}(c)$ es la cantidad de campos (`fieldSignature`) declarados explícitamente en la clase $c$.

**¿Qué mide y qué no mide NOA?**

- **Mide exclusivamente el estado propio declarado** dentro de la clase.
- **No mide acceso a datos de otras clases** (ATFD no es observable en un diagrama UML sin cuerpos de método).
- **No contabiliza asociaciones** (`Banco o-- Cuenta`).

---

## 5. Umbrales dinámicos

### 5.1 Fundamento del uso del Rango Intercuartílico (IQR)

Para determinar si una clase concentra una cantidad desproporcionada de responsabilidades respecto de su entorno, se emplea estadística de orden mediante cuartiles y el **Rango Intercuartílico ($IQR = Q_3 - Q_1$)**.

Este enfoque permite:
- **Adaptarse a la escala de cada diagrama:** mide la dispersión real del 50 % central de las clases ($Q_1$ a $Q_3$), reflejando el tamaño típico de las abstracciones de ese modelo sin asumir una distribución predefinida.
- **Aislar valores atípicos con estabilidad:** al descartar el 25 % inferior y el 25 % superior de la muestra durante el cálculo de dispersión, la presencia de una o más clases gigantes no altera el umbral de referencia.
- **Identificar desvíos estructurales:** mediante la regla de la cerca superior de Tukey ($Q_3 + k \cdot IQR$), se establece una frontera dinámica a partir de la cual el tamaño de una clase deja de ser parte de la variación normal del diseño y pasa a ser una anomalía estructural.


### 5.3 El umbral híbrido: cerca de Tukey con piso absoluto

Para cada métrica $M \in \{\text{NOM}, \text{NOA}\}$, el umbral de anomalía es:

$$U_M = \max\left(Q_3^M + k \cdot IQR^M, \, \text{piso}_M\right)$$

con $k = 1.5$ (cerca superior clásica de Tukey, 1977) por defecto.

#### ¿Qué significa matemáticamente el piso?

La condición $x > \max(\text{cerca}, \text{piso})$ equivale a exigir **dos cosas simultáneamente**:

$$x > U_M \iff \left(x > Q_3^M + k \cdot IQR^M\right) \land \left(x > \text{piso}_M\right)$$

Donde:
- **Significancia estadística ($x > Q_3^M + k \cdot IQR^M$):** la **cerca de Tukey** responde *"¿esta clase es estadísticamente atípica respecto del resto del diagrama?"*
- **Relevancia práctica ($x > \text{piso}_M$):** el **piso** responde *"¿esa diferencia tiene un tamaño preocupante en términos de diseño?"*

#### ¿Por qué es indispensable el piso?

Sin piso, el modelo estadístico puro falla en dos escenarios reales:

1. **Colapso por $IQR = 0$:** si al menos el 75 % de las clases de un diagrama sano tienen el mismo tamaño (algo muy frecuente en diagramas pequeños), resulta $Q_1 = Q_3$, luego $IQR = 0$ y la cerca queda en $U = Q_3$. Cualquier clase con apenas **1 método o 1 atributo más** que la norma sería marcada erróneamente como God Class.
2. **Inestabilidad en diagramas pequeños:** cuando $n$ está entre 5 y 8, los cuartiles son muy sensibles a pequeñas variaciones; el piso actúa como red de seguridad.

Cuando el diagrama es grande y sus clases tienen tamaño elevado, la cerca de Tukey supera al piso y este **no interviene**.

#### Justificación de los valores por defecto

| Parámetro | Piso | Significa reportar desde | Respaldo teórico |
|---|---|---|---|
| `minMethods` | **7** | **8 o más** métodos de comportamiento | **Ley de Miller (1956)** sobre capacidad de memoria de corto plazo ($7 \pm 2$ conceptos simultáneos) y umbral semántico de memoria corta en **Lanza y Marinescu (2006)** (*Cap. 2*): superar 7 responsabilidades distintas excede lo que un desarrollador comprende de una vez |
| `minAttributes` | **5** | **6 o más** atributos propios | Umbral semántico **`FEW`** (*"unos pocos"*, límite superior = 5) utilizado en las estrategias de detección de **Lanza y Marinescu (2006)** |


### 5.4 Tamaño mínimo por defecto del diagrama

Si $n < 5$ (parámetro `minClasses`), el sniffer **no emite ningún aroma**. Con menos de 5 clases, calcular cuartiles carece de sustento estadístico y no existe suficiente distribución de responsabilidades como para detectar concentraciones atípicas.

---

## 6. Regla de decisión

Una clase analizable $c$ se reporta como sospechosa si y solo si supera **estrictamente** ambos umbrales:

$$\text{NOM}(c) > U_{\text{NOM}} \quad \land \quad \text{NOA}(c) > U_{\text{NOA}}$$


----


# 7. Referencias

- Lanza, M. & Marinescu, R. (2006). *Object-Oriented Metrics in Practice*. Springer. (Estrategia de detección de God Class; umbrales estadísticos).
- Fowler, M. (1999). *Refactoring: Improving the Design of Existing Code*. Addison-Wesley. (*Large Class*).
- Tukey, J. W. (1977). *Exploratory Data Analysis*. Addison-Wesley. (Cercas de Tukey).