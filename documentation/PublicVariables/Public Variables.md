### Detectar variables públicas:

Una variable pública es un elemento de una clase concreta, abstracta o de una interfaz cuya visibilidad se representa con el símbolo ‘+’ anterior al nombre de la variable y no posee paréntesis: ‘+ variable’ es una variable pública siempre que se encuentre definida dentro de una clase (o clase abstracta o interfaz): class prueba{ \+ variable } o por fuera haciendo referencia a la misma: ‘prueba: \+ variable’. 
Formas posibles de definir variables públicas: 
@startuml
class Prueba {
  + String data
}

Prueba : + data2 : String 
@enduml
Con las distintas variantes para classDeclaration: class | abstract | interface

# ¿Cómo funciona nuestro visitor?
Para detectar correctamente variables públicas, nuestro visitor realiza dos recorridos al árbol de un diagrama.
En el primer recorrido nuestro visitor guarda en una variable set unsupportedClasses aquellas declaraciones de clases sobre las que no vamos a evaluar variables públicas (aquellas que no sean clases concretas, abstractas o interfaces).
En el segundo recorrido se evalúan los miembros de las clases que nos interesan buscando la detección de una variable pública utilizando lo definido en la heurística.

# Tests que deberían devolver que el diagrama contiene variables públicas:
  1. class| interface| abstract Prueba{+ nombre : String}
  2. class| interface| abstract Prueba{+ int numero} 
  3. class| interface| abstract Prueba{+ saldo}
  4. class| interface| abstract Prueba{+ {static} contador : int }
  5. class| interface| abstract Prueba{+ lista : List<String>} 
  6. class| interface| abstract Prueba{+ ids : int[]} 
  7. class| interface| abstract Prueba
	Prueba : + alias : String
  8. class| interface| abstract Prueba
	Prueba : + alias
  9. class| interface| abstract Prueba
	Prueba : + String alias
  10. Cuenta : + alias : String 
  11. Prueba : + alias : String
	class| interface| abstract Prueba

# Tests que no deberían devolver nada:
  1. class| interface| abstract Prueba{- valor : int} 
  2. class| interface| abstract Prueba{# dato : int}
  3. class| interface| abstract Prueba{x : int }
  4. class| interface| abstract Prueba{+ getVariable() : int}
  5. class| interface| abstract Prueba : + getVariable()
  6. enum| entity| annotation| exception| metaclass| stereotype| protocol| struct Prueba{+ nombre : String}
  7. enum| entity| annotation| exception| metaclass| stereotype| protocol| struct Prueba
	Prueba : + alias : String
  8. Prueba : + alias : String
	enum| entity| annotation| exception| metaclass| stereotype| protocol| struct Prueba
  9. class| interface| abstract Prueba
	Prueba : + getVariable()
  10. class| interface| abstract Prueba
	Prueba : - alias : String
  11. class| interface| abstract Prueba
	Prueba : alias : String
  12. @startuml
	@enduml 
	Diagrama vacío.

# Tests que encuentren las variables públicas (aquellas con el símbolo asterisco a modo ilustrativo) e ignoren las que no.
  1. enum Estado
	class Cuenta
	Estado : + alias : String
	Cuenta : + alias : String *
  2. class Cuenta {
	+ saldo : doublé *
	- pin : int
	alias : String
	+ getSaldo() : double
  }
  3. class A {+x : int} *
	class B {-y : int}
	class C {+z : int} *
  4. class Cuenta {-saldo : double}
	note right of Cuenta
		+ saldo : double
	end note
