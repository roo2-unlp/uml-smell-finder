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


# Tests que deberían devolver que el diagrama contiene variables públicas:
  1. class| interface| abstract Prueba{+ nombre : String}
  2. class| interface| abstract Prueba{+ int numero} 
  3. class| interface| abstract Prueba{+ saldo}
  4. class| interface| abstract Prueba{+ {static} contador : int }
  5. class| interface| abstract Prueba{+ lista : List<String>} 
  6. class| interface| abstract Prueba{+ ids : int[]} 
  7. Cuenta : + alias : String 
  8. Cuenta : + alias 
  9. Cuenta : + String alias 
# Tests que no deberían devolver nada:
  1. class| interface| abstract Prueba{- valor : int} 
  2. class| interface| abstract Prueba{# dato : int}
  3. class| interface| abstract Prueba{x : int }
  4. class| interface| abstract Prueba{+ getVariable() : int}
  5. class| interface| abstract Prueba : + getVariable()
  6. enum| entity| annotation| exception| metaclass| stereotype| protocol| struct Prueba{+ nombre : String}
