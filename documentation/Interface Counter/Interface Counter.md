# Interface Counter Sniffer

Detecta interfaces declaradas más de una vez con el mismo nombre en diagramas de clases PlantUML y lleva el conteo de interfaces encontradas.

Declarar la misma interfaz en múltiples lugares del diagrama suele indicar inconsistencias en el modelo, duplicación accidental de definiciones o falta de consolidación en el diseño.

Agrega un aroma por cada declaración duplicada de una interfaz, con la línea donde está declarada. Reconoce tanto las declaraciones con la palabra clave `interface` como clases con el estereotipo `<<interface>>`. Si existen interfaces duplicadas, agrega al final un aroma con la cantidad total de interfaces detectadas (`Cantidad de interfaces: <N>`). No reporta olores si cada interfaz está declarada una sola vez.

[`ejemplo.puml`](./ejemplo.puml) tiene dos interfaces duplicadas (`Repositorio` y `Notificador`):

```bash
mvn -q compile exec:java -Dexec.args="--sniffers InterfaceCounterSniffer \"documentation/Interface Counter/ejemplo.puml\""
```

```text
[InterfaceCounterSniffer] line 20: Interface duplicada: Repositorio
[InterfaceCounterSniffer] line 25: Interface duplicada: Notificador
[InterfaceCounterSniffer] line 1: Cantidad de interfaces: 3
```

Si el diagrama no tiene interfaces duplicadas, el reporte indica `The diagram smells good.`.
