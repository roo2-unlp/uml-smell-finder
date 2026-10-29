package oo2.redictado.sniffers.interfacecounter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;

/**
 * Visitor del framework {@code uml-smell-finder} que recorre el árbol sintáctico de un
 * diagrama PlantUML, construye objetos de dominio polimórficos para las interfaces,
 * clases y sus métodos, y reporta los aromas recolectados.
 */
public class InterfaceCounterVisitor extends PlantUmlVisitor {
    private static final Map<String, BiFunction<String, ParserRuleContext, DeclaredType>> KIND_FACTORIES = Map.of(
            "interface", InterfaceType::new,
            "class", ClassType::new
    );

    private static final Map<String, BiFunction<String, ParserRuleContext, DeclaredType>> STEREOTYPE_FACTORIES = Map.of(
            "interface", InterfaceType::new
    );

    private final Map<String, DeclaredType> typesByName = new LinkedHashMap<>();
    private DeclaredType currentType = new UnclassifiedType("", null);
    private Visibility currentVisibility = new NoVisibility();
    private List<MethodParameter> currentParams = new ArrayList<>();

    /**
     * Instancia y retorna un {@link TypeCollector} recorriendo la colección de tipos
     * registrados hasta el momento para clasificarlos y contarlos mediante Double Dispatch.
     *
     * @return recolector con las interfaces y clases ya clasificadas
     */
    public TypeCollector collectTypes() {
        return TypeCollector.collectFrom(this.typesByName.values());
    }

    /**
     * Método raíz del Visitor llamado al iniciar el análisis del diagrama PlantUML.
     * Primero visita recursivamente todos los nodos hijos del árbol sintáctico (recolectando tipos,
     * estereotipos y métodos) y, una vez finalizado el recorrido, clasifica los tipos y reporta
     * todos los aromas encontrados (detalle de cada interfaz/clase y el resumen final de conteo).
     *
     * @param ctx contexto sintáctico raíz del diagrama PlantUML
     * @return null
     */
    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        this.collectTypes().reportSmells(ctx, this::smell);
        return null;
    }

    /**
     * Se ejecuta al encontrar una declaración formal de tipo (ej: {@code class Usuario { ... }}
     * o {@code interface Repositorio { ... }}).
     * Extrae el nombre, inicializa el tipo actual provisionalmente como {@link ClassType},
     * recorre sus nodos hijos (donde se procesará el tipo real, estereotipos y miembros)
     * y finalmente registra y fusiona el tipo en el mapa general.
     *
     * @param ctx contexto sintáctico de la declaración de clase/interfaz
     * @return null
     */
    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        String name = this.extractName(ctx.className());
        this.currentType = new ClassType(name, ctx);
        visitChildren(ctx);
        this.registerType(this.currentType);
        this.currentType = new UnclassifiedType("", ctx);
        return null;
    }

    /**
     * Procesa la palabra clave que define la naturaleza del tipo (ej: {@code interface},
     * {@code class}, {@code abstract class}, etc.).
     * Determina mediante un mapa de factorías polimórficas ({@code KIND_FACTORIES}) si se debe
     * instanciar un {@link InterfaceType}, un {@link ClassType} o un {@link UnclassifiedType},
     * preservando el nombre y el nodo sintáctico de la declaración original.
     *
     * @param ctx contexto sintáctico con la palabra clave del tipo
     * @return null
     */
    @Override
    public Void visitClassKind(PlantUmlParser.ClassKindContext ctx) {
        String kind = ctx.getText().toLowerCase();
        this.currentType = KIND_FACTORIES
                .getOrDefault(kind, UnclassifiedType::new)
                .apply(this.currentType.getName(), this.currentType.getDeclarationNode());
        return null;
    }

    /**
     * Procesa los estereotipos aplicados a la declaración (ej: {@code <<interface>>}).
     * Si el estereotipo corresponde a una interfaz, instancia un {@link InterfaceType} y lo
     * fusiona con el tipo actual mediante Double Dispatch ({@code mergeWith}), promoviendo
     * por ejemplo una clase declarada con estereotipo de interfaz a {@link InterfaceType}.
     *
     * @param ctx contexto sintáctico del estereotipo
     * @return null
     */
    @Override
    public Void visitStereotype(PlantUmlParser.StereotypeContext ctx) {
        String stereotype = ctx.getText().replace("<", "").replace(">", "").trim().toLowerCase();
        DeclaredType stereotypeType = STEREOTYPE_FACTORIES
                .getOrDefault(stereotype, UnclassifiedType::new)
                .apply(this.currentType.getName(), this.currentType.getDeclarationNode());
        this.currentType = this.currentType.mergeWith(stereotypeType);
        return null;
    }

    /**
     * Se ejecuta al ingresar a la declaración de un miembro interno dentro del cuerpo de una clase o interfaz.
     * Reinicia el estado de visibilidad a {@link NoVisibility} (por defecto para miembros sin símbolo explícito)
     * y continúa la visita hacia los nodos hijos que definen el método o atributo.
     *
     * @param ctx contexto sintáctico del miembro interno
     * @return resultado de visitar a los nodos hijos
     */
    @Override
    public Void visitMember(PlantUmlParser.MemberContext ctx) {
        this.currentVisibility = new NoVisibility();
        return visitChildren(ctx);
    }

    /**
     * Procesa declaraciones externas de miembros (ej: {@code Usuario : + getNombre() : String}).
     * Extrae el nombre de la clase/interfaz receptora, inicializa un {@link UnclassifiedType} temporal,
     * reinicia la visibilidad, visita a los hijos para extraer la firma del método y finalmente
     * registra y fusiona los métodos asociados al tipo correspondiente en el mapa.
     *
     * @param ctx contexto sintáctico del miembro externo
     * @return null
     */
    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        String name = this.extractName(ctx.className());
        this.currentType = new UnclassifiedType(name, ctx);
        this.currentVisibility = new NoVisibility();
        visitChildren(ctx);
        this.registerType(this.currentType);
        this.currentType = new UnclassifiedType("", ctx);
        return null;
    }

    /**
     * Captura el símbolo de visibilidad explícito de un método ({@code +}, {@code -}, {@code #}, {@code ~}).
     * Instancia un {@link DeclaredVisibility} con dicho símbolo para aplicarlo posteriormente
     * a la firma del método que se está procesando.
     *
     * @param ctx contexto sintáctico del símbolo de visibilidad
     * @return null
     */
    @Override
    public Void visitVisibility(PlantUmlParser.VisibilityContext ctx) {
        this.currentVisibility = new DeclaredVisibility(ctx.getText());
        return null;
    }

    /**
     * Procesa la firma completa de un método (nombre, parámetros y tipo de retorno).
     * Reinicia la lista de parámetros temporales, visita los nodos hijos para poblar dichos parámetros,
     * extrae el identificador del método y su tipo de retorno (polimórfico), construye una instancia de
     * {@link DeclaredMethod} y la agrega al {@link DeclaredType} actual en procesamiento.
     *
     * @param sig contexto sintáctico de la firma del método
     * @return null
     */
    @Override
    public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext sig) {
        this.currentParams = new ArrayList<>();
        visitChildren(sig);
        String name = sig.IDENTIFIER().getText();
        ReturnType returnType = this.extractReturnType(sig);
        this.currentType.addMethod(
                new DeclaredMethod(name, this.currentVisibility, this.currentParams, returnType));
        return null;
    }

    /**
     * Procesa un parámetro formal dentro de la lista de parámetros de un método.
     * Extrae todos los tokens que componen la declaración del parámetro (admitiendo tanto
     * notación UML {@code nombre : Tipo} como notación Java {@code Tipo nombre}) y lo agrega
     * a la lista {@code currentParams}.
     *
     * @param ctx contexto sintáctico del parámetro
     * @return null
     */
    @Override
    public Void visitParam(PlantUmlParser.ParamContext ctx) {
        List<String> tokens = ctx.children.stream()
                .map(ParseTree::getText)
                .toList();
        this.currentParams.add(new MethodParameter(tokens));
        return null;
    }

    /**
     * Registra un {@link DeclaredType} en el mapa indexado por nombre ({@code typesByName}).
     * Si el tipo ya existía previamente (por ejemplo, porque se declararon métodos externos antes
     * o se declaró primero como clase y luego se le asoció un estereotipo), fusiona ambas
     * instancias utilizando Double Dispatch ({@code existing.mergeWith(type)}), acumulando
     * los métodos y preservando la categoría correcta sin necesidad de usar condicionales.
     *
     * @param type instancia del tipo a registrar o fusionar
     */
    private void registerType(DeclaredType type) {
        DeclaredType existing = this.typesByName.getOrDefault(
                type.getName(),
                new UnclassifiedType(type.getName(), type.getDeclarationNode()));
        this.typesByName.put(type.getName(), existing.mergeWith(type));
    }

    /**
     * Extrae de forma segura y polimórfica el tipo de retorno desde el contexto sintáctico de la firma.
     * En caso de existir una cláusula de tipo devuelve un {@link DeclaredReturnType}; si no se
     * especificó retorno devuelve un {@link NoReturnType} (patrón Null Object).
     *
     * @param sig contexto sintáctico de la firma del método
     * @return objeto de dominio {@link ReturnType} correspondiente
     */
    private ReturnType extractReturnType(PlantUmlParser.MethodSignatureContext sig) {
        return sig.type().stream()
                .reduce((first, second) -> second)
                .<ReturnType>map(t -> new DeclaredReturnType(t.getText()))
                .orElseGet(NoReturnType::new);
    }

    /**
     * Obtiene el nombre limpio de una clase o interfaz a partir de su nodo sintáctico,
     * removiendo comillas dobles en caso de nombres que contengan espacios o caracteres especiales.
     *
     * @param ctx contexto sintáctico del nombre de la clase
     * @return cadena con el nombre limpio del tipo
     */
    private String extractName(PlantUmlParser.ClassNameContext ctx) {
        return ctx.getText().replace("\"", "");
    }
}

