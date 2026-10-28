package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Adds the bad smells a sniffer finds to a report, on behalf of that sniffer.
 */
public class SmellCollector {
    private final String snifferName;
    private final AromaReport report;

    public SmellCollector(String snifferName, AromaReport report) {
        this.snifferName = snifferName;
        this.report = report;
    }

    /**
     * @param message the message that describes the bad smell
     * @param where the node of the tree where the bad smell was found
     */
    public void add(String message, ParserRuleContext where) {
        int line = where.getStart().getLine();
        this.report.addAroma(new Aroma(this.snifferName, message, line));
    }
}
