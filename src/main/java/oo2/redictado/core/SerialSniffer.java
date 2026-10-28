package oo2.redictado.core;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Runs several sniffers, in the order they were added, over the same tree.
 *
 * @param <T> the type of the root of the parse tree
 */
public class SerialSniffer<T extends ParserRuleContext> implements CodeSniffer<T> {
    private final List<CodeSniffer<T>> sniffers;

    public SerialSniffer() {
        this.sniffers = new ArrayList<>();
    }

    public void addSniffer(CodeSniffer<T> sniffer) {
        this.sniffers.add(sniffer);
    }

    /**
     * Each sniffer gets its own copy of the tree, so changes one sniffer
     * makes to the tree do not affect the others.
     */
    @Override
    public void sniff(T tree, AromaReport report) {
        this.sniffers.forEach(sniffer -> sniffer.sniff(TreeCopier.copy(tree), report));
    }
}
