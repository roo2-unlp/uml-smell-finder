package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A visitor that walks a parse tree looking for bad smells.
 *
 * Each language provides a base class that extends the visitor ANTLR
 * generates for its grammar and implements this interface.
 *
 * @param <T> the type of the root of the parse tree
 */
public interface SmellVisitor<T extends ParserRuleContext> {
    /**
     * Visits the tree and adds the bad smells found to the collector.
     */
    void sniff(T tree, SmellCollector smells);
}
