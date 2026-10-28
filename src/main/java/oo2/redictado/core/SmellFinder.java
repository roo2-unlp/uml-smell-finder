package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Parses code of a language and runs a sniffer over it.
 *
 * @param <T> the type of the root of the parse tree of the language
 */
public class SmellFinder<T extends ParserRuleContext> {
    private final Language<T> language;
    private final CodeSniffer<T> sniffer;

    public SmellFinder(Language<T> language, CodeSniffer<T> sniffer) {
        this.language = language;
        this.sniffer = sniffer;
    }

    /**
     * @param code the code to be analyzed
     * @return a report with the bad smells found
     * @throws IllegalArgumentException if the code has syntax errors
     */
    public AromaReport sniff(String code) {
        AromaReport report = new AromaReport();
        this.sniffer.sniff(this.language.parse(code), report);
        return report;
    }
}
