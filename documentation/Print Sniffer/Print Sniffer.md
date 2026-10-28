# Print Sniffer

Detecta métodos llamados `print` en las clases del diagrama.

Que una clase de dominio como `Alumno` o `Factura` sepa imprimirse suele indicar que mezcla dos responsabilidades: la lógica del dominio y la forma de mostrarla.

Agrega un aroma por cada método `print`, con la línea donde está declarado. Detecta tanto los declarados dentro de la clase (`+print() : void`) como fuera (`Alumno : +print()`). No cuenta `print` en notas, atributos (`-printer : Printer`) ni nombres de clase.

[`reporte.puml`](../PlantUML/Examples/reporte.puml) tiene dos métodos `print`:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers PrintSniffer documentation/PlantUML/Examples/reporte.puml"
```

```text
[PrintSniffer] line 8: The diagram has print methods.
[PrintSniffer] line 12: The diagram has print methods.
```
