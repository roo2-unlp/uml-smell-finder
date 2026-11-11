Detecta Clases Concretas que no implementan metodos abstractos que su superclase declaro.

No declarar el metodo abstracto que una clase concreta hereda indica inconsistencias en el modelo o falta de consolidación en el diseño.

El algoritmo recolecta datos de todas las clases, las clasifica si son abstractas dentro de un set, lleva un map de metodos abstractos de cada clase, otro de cada clase y sus metodos, y otro cada clase con sus herencias.

Tras obtener todos los datos, corrobora que todos las clases que hereden de otra contengan los metodos abstractos de la/las clases que heredan.


