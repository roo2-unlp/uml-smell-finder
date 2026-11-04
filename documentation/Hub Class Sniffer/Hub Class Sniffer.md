# Hub Class Sniffer

Detecta clases conocidas por muchas otras clases en diagramas de clases PlantUML.

Una Hub Class es una clase a la que conocen y acceden muchas otras clases. Centraliza dependencias que no deberían estar todas en el mismo lugar, genera mucho acoplamiento y hace que cualquier cambio en ella pueda afectar a todas las clases que la conocen. El smell se fija solo en las dependencias entrantes, es decir, en quién conoce a la clase y no a quién conoce ella.

Una clase `A` conoce a una clase `H` si:

1. Tiene una relación hacia `H`: asociación (`A --> H`), agregación (`A o-- H`), composición (`A *-- H`) o dependencia (`A ..> H`).
2. Usa a `H` como tipo de un atributo, de un parámetro o del retorno de un método.

No se tienen en cuenta:

- La herencia (`H <|-- A` o `class A extends H`) ni la implementación de interfaces (`A ..|> H` o `class A implements H`). En patrones como Strategy, State o Composite es normal que muchas subclases hereden de la misma clase, y no queremos marcar esos casos.
- Las relaciones de una clase consigo misma.
- Las relaciones salientes: que `H` conozca a muchas clases no la convierte en Hub.

Para decidir si `H` es Hub se cuentan las clases distintas que la conocen, no cuántas veces la nombran. Ese número se compara con las otras clases del diagrama, sin contar a `H`:

- La tiene que conocer al menos la mitad de las otras clases. Si la mitad no da justa, se redondea para arriba.
- Nunca alcanza con menos de 3, aunque el diagrama sea chico.

| Clases del diagrama | Otras clases | Mitad | Tienen que conocerla |
|---------------------|--------------|-------|----------------------|
| 4                   | 3            | 1,5   | 3 (nunca menos de 3) |
| 7                   | 6            | 3     | 3                    |
| 8                   | 7            | 3,5   | 4                    |
| 21                  | 20           | 10    | 10                   |

Con `N` como la cantidad total de clases del diagrama, la cuenta es `max(3, ceil((N - 1) / 2))`.

Agrega un aroma por cada Hub Class, con la línea donde está declarada la clase. No reporta olores si ninguna clase llega a la cantidad necesaria.

[`ejemplo.puml`](./ejemplo.puml) tiene 8 clases, así que para ser Hub a una clase la tienen que conocer por lo menos 4. `Cliente` es una Hub Class porque la conocen 4 clases: `Pedido` y `Envio` por sus relaciones, `Factura` por un atributo y `Carrito` por un parámetro. `Producto` no es Hub porque la conocen solo 2 clases, `Pedido` y `Carrito`. `Libro` y `Disco` no suman porque heredan de ella:

```bash
mvn -q compile exec:java -Dexec.args="--sniffers HubClassSniffer \"documentation/Hub Class Sniffer/ejemplo.puml\""
```

```text
[HubClassSniffer] line 4: Cliente es una Hub Class: la conocen 4 clases.
```

Si el diagrama no tiene Hub Classes, el reporte indica `The diagram smells good.`.
