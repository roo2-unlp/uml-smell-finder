package oo2.redictado.sniffers.dataclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oo2.redictado.plantuml.PlantUmlVisitor;
import oo2.redictado.plantuml.antlr4.PlantUmlParser;


public class DataClassVisitor extends PlantUmlVisitor {

    private final List<PlantUmlParser.ClassDeclarationContext> clases = new ArrayList<>();
    private final Map<String, List<Miembro>> miembrosExternos = new HashMap<>();

    private record Miembro(
            PlantUmlParser.MemberSignatureContext firma,
            PlantUmlParser.VisibilityContext visibilidad,
            List<PlantUmlParser.MemberModifierContext> modificadores
    ) {}

    @Override
    public Void visitDiagram(PlantUmlParser.DiagramContext ctx) {
        visitChildren(ctx);
        for (PlantUmlParser.ClassDeclarationContext clase : clases) {
            if (esDataClass(clase)) {
                smell(clase.className().getText()
                        + " es una Data Class: expone sus datos y no tiene comportamiento.", clase);
            }
        }
        return null;
    }

    @Override
    public Void visitClassDeclaration(PlantUmlParser.ClassDeclarationContext ctx) {
        clases.add(ctx);
        return visitChildren(ctx);
    }

    @Override
    public Void visitMemberExternal(PlantUmlParser.MemberExternalContext ctx) {
        String nombreClase = ctx.className().getText();
        Miembro miembro = new Miembro(
                ctx.memberSignature(),
                ctx.visibility(),
                ctx.memberModifier()
        );
        miembrosExternos
            .computeIfAbsent(nombreClase, clave -> new ArrayList<>())
            .add(miembro);
        return visitChildren(ctx);
    }

    private boolean esDataClass(PlantUmlParser.ClassDeclarationContext ctx) {
        return esClase(ctx)
                && tieneAtributosDeInstancia(ctx)
                && exponeSusDatos(ctx)
                && noTieneComportamiento(ctx);
    }

    private boolean esClase(PlantUmlParser.ClassDeclarationContext ctx) {
        boolean abstractaSinClass = ctx.ABSTRACT() != null;
        boolean esClass = ctx.classKind() != null && ctx.classKind().CLASS() != null;
        return abstractaSinClass || esClass;
    }

    private boolean tieneAtributosDeInstancia(PlantUmlParser.ClassDeclarationContext ctx) {
        for (Miembro miembro : miembrosDeInstancia(ctx)) {
            if (miembro.firma.fieldSignature() != null) {
                return true;
            }
        }
        return false;
    }

    private boolean exponeSusDatos(PlantUmlParser.ClassDeclarationContext ctx) {
        for (Miembro miembro : miembrosDeInstancia(ctx)) {
            boolean atributoPublico = miembro.firma.fieldSignature() != null
                    && esPublico(miembro.visibilidad);
            boolean accessorNoPrivado = miembro.firma.methodSignature() != null
                    && esAccessor(miembro.firma.methodSignature())
                    && !esPrivado(miembro.visibilidad);
            if (atributoPublico || accessorNoPrivado) {
                return true;
            }
        }
        return false;
    }

    private boolean noTieneComportamiento(PlantUmlParser.ClassDeclarationContext ctx) {
        String nombreClase = ctx.className().getText();
        for (Miembro miembro : miembrosDeInstancia(ctx)) {
            PlantUmlParser.MemberSignatureContext firma = miembro.firma;
            if (firma.methodSignature() == null || esStatic(miembro.modificadores)) {
                continue;
            }

            PlantUmlParser.MethodSignatureContext metodo = firma.methodSignature();
            if (esConstructor(metodo, nombreClase) || esAccessor(metodo)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean esConstructor(PlantUmlParser.MethodSignatureContext metodo, String nombreClase) {
        return metodo.IDENTIFIER().getText().equals(nombreClase);
    }

    private List<Miembro> miembrosDeInstancia(PlantUmlParser.ClassDeclarationContext ctx) {
        List<Miembro> miembros = new ArrayList<>();
        String nombreClase = ctx.className().getText();
        PlantUmlParser.ClassBodyContext cuerpo = ctx.classTail().classBody();
        if (cuerpo != null) {
            for (PlantUmlParser.BodyItemContext item : cuerpo.bodyItem()) {
                PlantUmlParser.MemberContext miembro = item.member();
                if (miembro != null && !esStatic(miembro.memberModifier())) {
                    miembros.add(new Miembro(
                            miembro.memberSignature(),
                            miembro.visibility(),
                            miembro.memberModifier()
                    ));
                }
            }
        }
        for (Miembro miembro : miembrosExternos.getOrDefault(nombreClase, List.of())) {
            if (!esStatic(miembro.modificadores)) {
                miembros.add(miembro);
            }
        }
        return miembros;
    }

    private boolean esAccessor(PlantUmlParser.MethodSignatureContext metodo) {
        String nombre = metodo.IDENTIFIER().getText();
        int parametros = metodo.paramList() == null ? 0 : metodo.paramList().param().size();
        boolean getter = (tienePrefijo(nombre, "get") || tienePrefijo(nombre, "is")) && parametros == 0;
        boolean setter = tienePrefijo(nombre, "set") && parametros == 1;
        return getter || setter;
    }

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
