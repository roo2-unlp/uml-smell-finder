package oo2.redictado.core;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Base class for sniffers that look for bad smells with a visitor.
 *
 * It runs the visitor returned by {@link #createVisitor()} over the tree,
 * which adds the bad smells it finds to the report. Subclasses only need to
 * provide the visitor (Template Method).
 *
 * @param <T> the type of the root of the parse tree
 */
public abstract class VisitorSniffer<T extends ParserRuleContext> implements CodeSniffer<T> {

    @Override
    public final void sniff(T tree, AromaReport report) {
        this.createVisitor().sniff(tree, new SmellCollector(this.name(), report));
    }

    /**
     * @return a new visitor that looks for this sniffer's bad smell
     */
    protected abstract SmellVisitor<T> createVisitor();

    /**
     * @return the name used in the aromas this sniffer adds to the report
     */
    protected String name() {
        String name = this.getClass().getSimpleName();
        // Anonymous classes have no simple name
        return name.isEmpty() ? this.getClass().getName() : name;
    }
}
