package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A language whose code can be analyzed by the sniffers.
 *
 * @param <T> the type of the root of the parse tree of the language
 */
public interface Language<T extends ParserRuleContext> {
    /**
     * Parses code written in this language.
     *
     * @param code the code to parse
     * @return the root of the parse tree
     * @throws IllegalArgumentException if the code has syntax errors
     */
    T parse(String code);
}
