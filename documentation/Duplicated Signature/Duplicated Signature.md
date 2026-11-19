# Duplicated Signature Sniffer

Detecta métodos con la misma firma en subclases de una misma superclase, cuando la superclase no declara ese método.

Si varias subclases declaran el mismo método y la superclase no lo conoce, no se las puede usar de forma polimórfica a través de la superclase. Suele indicar que ese método debería estar declarado en la superclase.

Agrega un aroma por cada firma duplicada, con la línea del método en la primera subclase, que es la que aparece primero con su herencia en el diagrama. Reporta cuando se cumplen las tres condiciones:

1. Una superclase tiene dos o más subclases directas.
2. Al menos dos de esas subclases declaran un método con la misma firma.
3. La superclase no declara ese método, ni concreto ni abstracto.

Dos firmas son iguales si coinciden el nombre, los tipos de los parámetros (cantidad y orden) y el tipo de retorno. Un método sin tipo de retorno cuenta como `void`. No importan los nombres de los parámetros, la visibilidad ni `{abstract}`/`{static}`.

Solo cuenta la herencia, escrita con `extends` o con flechas como `<|--` y `--|>`. No cuentan `implements`, `..|>`, composición, agregación ni asociación. Cada subclase se compara con su superclase directa: si una superclase indirecta ya declara el método, igual se reporta. Las clases con más de una superclase no se cuentan como subclases.

[`ejemplo.puml`](./ejemplo.puml) tiene a `Auto` y `Moto` con dos métodos iguales:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers DuplicatedSignatureSniffer \"documentation/Duplicated Signature/ejemplo.puml\""
```

```text
[DuplicatedSignatureSniffer] line 9: Firma duplicada: arrancarMotor() : void en Auto y Moto, y no está en Vehiculo
[DuplicatedSignatureSniffer] line 10: Firma duplicada: cargarCombustible(Real) : Real en Auto y Moto, y no está en Vehiculo
```

Si el diagrama no tiene firmas duplicadas, el reporte indica `The diagram smells good.`.

Limitaciones:

- En `cargar(Real)` la gramática lee `Real` como el nombre del parámetro. El sniffer lo toma como el tipo, así que `cargar(monto)` y `cargar(importe)` cuentan como firmas distintas.
- `-obtener()` se lee mal, como un método llamado `btener`. Hay que escribir `- obtener()`. Lo mismo pasa con los nombres que empiezan con `x`.
- Los alias no se resuelven: `class "Cuenta Corriente" as CC` no se reconoce como la misma clase que `CC`.
- `interface I extends J` se trata como una clase común.
- Los estereotipos en métodos, como `<<abstract>>` o `<<create>>`, dan error de sintaxis. Un método abstracto se escribe `+{abstract} arrancarMotor() : void`.
