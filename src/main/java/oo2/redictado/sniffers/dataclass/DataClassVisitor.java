package oo2.redictado.sniffers.dataclass;

import java.util.ArrayList;
import java.util.List;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;


public class DataClassVisitor extends PlantUmlVisitor {

    private final List<PlantUmlParser.ClassDeclarationContext> clases = new ArrayList<>();

    @Override //  con V mayúscula, que es un tipo que solo puede valer null
            //  El framework eligió Void porque los visitors no devuelven nada útil; solo recorren y anotan.
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        for (PlantUmlParser.ClassDeclarationContext clase : clases) {
           smell(clase.className().getText() + " es una Data Class: expone sus datos y no tiene comportamiento.", clase);
        }
        return null;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        if (esClase(ctx) && tieneAtributosDeInstancia(ctx) && exponeSusDatos(ctx)){
            clases.add(ctx);
        }
        return visitChildren(ctx);
    }

    private boolean esClase(PlantUmlParser.ClassDeclarationContext ctx) {
        boolean abstractaSinClass = ctx.ABSTRACT() != null;
        boolean esClass = ctx.classKind() != null && ctx.classKind().CLASS() != null;
        return abstractaSinClass || esClass;
    }

    private boolean tieneAtributosDeInstancia(PlantUmlParser.ClassDeclarationContext ctx) {
        for (PlantUmlParser.MemberContext miembro : miembrosDeInstancia(ctx)) {
            if (miembro.memberSignature().fieldSignature() != null) {
                return true;
            }
        }
        return false;
    }

    // Expone si tiene un atributo público o un accessor que no es privado
    private boolean exponeSusDatos(PlantUmlParser.ClassDeclarationContext ctx) {
        for (PlantUmlParser.MemberContext miembro : miembrosDeInstancia(ctx)) {
            PlantUmlParser.MemberSignatureContext firma = miembro.memberSignature();
            boolean atributoPublico = firma.fieldSignature() != null && esPublico(miembro.visibility());
            boolean accessorNoPrivado = firma.methodSignature() != null
                    && esAccessor(firma.methodSignature())
                    && !esPrivado(miembro.visibility());
            if (atributoPublico || accessorNoPrivado) {
                return true;
            }
        }
        return false;
    }

    // Los miembros del cuerpo de la clase, sin los static
    private List<PlantUmlParser.MemberContext> miembrosDeInstancia(PlantUmlParser.ClassDeclarationContext ctx) {
        List<PlantUmlParser.MemberContext> miembros = new ArrayList<>();
        PlantUmlParser.ClassBodyContext cuerpo = ctx.classTail().classBody();
        if (cuerpo == null) {
            return miembros;
        }
        for (PlantUmlParser.BodyItemContext item : cuerpo.bodyItem()) {
            PlantUmlParser.MemberContext miembro = item.member();
            if (miembro != null && !esStatic(miembro.memberModifier())) {
                miembros.add(miembro);
            }
        }
        return miembros;
    }

    // getX() o isX() sin parámetros, o setX(valor) con un parámetro
    private boolean esAccessor(PlantUmlParser.MethodSignatureContext metodo) {
        String nombre = metodo.IDENTIFIER().getText();
        int parametros = metodo.paramList() == null ? 0 : metodo.paramList().param().size();
        boolean getter = (tienePrefijo(nombre, "get") || tienePrefijo(nombre, "is")) && parametros == 0;
        boolean setter = tienePrefijo(nombre, "set") && parametros == 1;
        return getter || setter;
    }

    // El nombre empieza con el prefijo y sigue una mayúscula: getNombre sí, getter no
    private boolean tienePrefijo(String nombre, String prefijo) {
        return nombre.length() > prefijo.length()
                && nombre.startsWith(prefijo)
                && Character.isUpperCase(nombre.charAt(prefijo.length()));
    }

    private boolean esPublico(PlantUmlParser.VisibilityContext visibilidad) {
        return visibilidad != null && visibilidad.PLUS() != null;
    }

    private boolean esPrivado(PlantUmlParser.VisibilityContext visibilidad) {
        return visibilidad != null && visibilidad.MINUS() != null;
    }

    private boolean esStatic(List<PlantUmlParser.MemberModifierContext> modificadores) {
        for (PlantUmlParser.MemberModifierContext modificador : modificadores) {
            if (modificador.STATIC_MOD() != null) {
                return true;
            }
        }
        return false;
    }
}
