### Detectar variables sin visibilidad explícita:

Una variable sin visibilidad es un elemento de una clase concreta, abstracta o de una interfaz cuya visibilidad no es representada con ningún símbolo anterior al nombre de la variable y no posee paréntesis: ‘variable’ es una variable sin visibilidad explícita siempre que no posea símbolos inmediatamente anterior a la definición y se encuentre definida dentro de una clase: class prueba{ variable} o por fuera haciendo referencia a la misma: ‘prueba : variable’.  
Formas posibles de definir variables sin visibilidad explícita: 
@startuml
class Prueba {
  String data
}

Prueba : data2 : String 
@enduml
Con las distintas variantes para classDeclaration: class | abstract | interface

# Tests que deberían devolver que el diagrama contiene variables sin visibilidad explícita: 
  1. class| interface| abstract Prueba{nombre : String}
  2. class| interface| abstract Prueba{String data} 
  3. class| interface| abstract Prueba{saldo} 
  4. class| interface| abstract Prueba{ {static} contador : int } 
  5. class| interface| abstract Prueba{lista : List<String>} 
  6. class| interface| abstract Prueba{ids : int[]} 
  7. Cuenta : alias : String 
  8. Cuenta : alias 
  9. Cuenta : String alias 
# Tests que no deberían devolver nada: 
  1. class| interface| abstract Prueba{+ x : int} 
  2. class| interface| abstract Prueba{- x : int} 
  3. class| interface| abstract Prueba{# x : int}  
  4. class| interface| abstract Prueba{+ {static} x : int} 
  5. class| interface| abstract Prueba{calcular()} 
  6. class| interface| abstract Prueba{calcular() : double} 
  7. class| interface| abstract Prueba{int calcular()} 
  8. class| interface| abstract Prueba{+ getX() : int} 
  9. Cuenta : + alias : String 
  10. Cuenta : - alias 
  11. Cuenta : calcular() 
  12. enum| entity| annotation| exception| metaclass| stereotype| protocol| struct Prueba{nombre : String} 
