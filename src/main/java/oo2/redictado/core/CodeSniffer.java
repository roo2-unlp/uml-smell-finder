package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Looks for a bad smell in the parse tree of some code.
 *
 * @param <T> the type of the root of the parse tree, which depends on the
 *            language being analyzed
 */
public interface CodeSniffer<T extends ParserRuleContext> {
    /**
     * Looks for bad smells in a parse tree.
     *
     * @param tree the parse tree of the code to be analyzed
     * @param report the report where the bad smells found are added
     */
    void sniff(T tree, AromaReport report);
}
