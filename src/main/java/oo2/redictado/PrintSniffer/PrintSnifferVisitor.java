package oo2.redictado.PrintSniffer;

import oo2.redictado.Aroma;
import oo2.redictado.AromaReport;
import oo2.redictado.antlr4.PlantUmlParser;
import oo2.redictado.antlr4.PlantUmlParserBaseVisitor;

public class PrintSnifferVisitor extends PlantUmlParserBaseVisitor<Void> {
    private AromaReport report;
    private String callerName;

    public PrintSnifferVisitor(AromaReport report, String callerName) {
        super();
        this.report = report;
        this.callerName = callerName;
    }

    @Override
    public Void visitMethodSignature(PlantUmlParser.MethodSignatureContext ctx) {
        if (ctx.IDENTIFIER().getText().equals("print")) {
            report.addAroma(new Aroma(this.callerName, "The diagram has print methods.", true));
        }
        return visitChildren(ctx);
    }
}
