# Print Sniffer

**Print Sniffer** es una herramienta de análisis de diagramas que detecta los métodos llamados `print` declarados en las clases del diagrama.

Que una clase de dominio (por ejemplo `Alumno` o `Factura`) sepa imprimirse suele indicar que está mezclando dos responsabilidades: la lógica del dominio y la forma de mostrarla.

- Si se encuentran métodos `print`, el sniffer informa que el diagrama presenta un "smell" (indicación de un posible problema en el diseño) y agrega un `Aroma` por cada método encontrado. Se detectan tanto los declarados dentro de la clase (`+print() : void`) como fuera de ella (`Alumno : +print()`).

- En caso de que no se detecten métodos `print`, el sniffer indica que el diagrama no presenta ningún "smell".

- La palabra `print` dentro de una nota, de un atributo (`-printer : Printer`) o de un nombre de clase no cuenta.

Podés ver un diagrama con este olor en [`reporte.puml`](../PlantUML/Examples/reporte.puml).
