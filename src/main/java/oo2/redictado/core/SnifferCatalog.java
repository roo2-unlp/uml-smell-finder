package oo2.redictado.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * The sniffers that can be run, each one known by a name and created from
 * its parameters.
 *
 * @param <T> the type of the root of the parse tree
 */
public class SnifferCatalog<T extends ParserRuleContext> {
    private final Map<String, Function<SnifferParams, CodeSniffer<T>>> sniffers = new LinkedHashMap<>();

    /**
     * @param name the name used to choose the sniffer
     * @param factory creates the sniffer from its parameters
     */
    public void add(String name, Function<SnifferParams, CodeSniffer<T>> factory) {
        if (this.sniffers.putIfAbsent(name, factory) != null) {
            throw new IllegalArgumentException("There is already a sniffer named " + name);
        }
    }

    /**
     * @return the names of the sniffers, in the order they were added
     */
    public Set<String> names() {
        return this.sniffers.keySet();
    }

    /**
     * @return a sniffer that runs every sniffer, without parameters
     */
    public SerialSniffer<T> all() {
        SerialSniffer<T> all = new SerialSniffer<>();
        this.names().forEach(name -> all.addSniffer(this.create(name, SnifferParams.none())));
        return all;
    }

    /**
     * @param spec the sniffers to run, separated by commas, each one with its
     *             parameters separated by colons. For example
     *             "PrintSniffer,MySniffer:threshold=20:strict=true"
     * @return a sniffer that runs the given sniffers, in that order
     * @throws IllegalArgumentException if a sniffer does not exist or a
     *                                  parameter is wrong
     */
    public SerialSniffer<T> sniffers(String spec) {
        SerialSniffer<T> sniffers = new SerialSniffer<>();
        for (String part : spec.split(",")) {
            String[] pieces = part.strip().split(":");
            Map<String, String> values = new HashMap<>();
            for (int i = 1; i < pieces.length; i++) {
                String[] pair = pieces[i].split("=", 2);
                if (pair.length != 2) {
                    throw new IllegalArgumentException(
                            "Expected name=value but got '" + pieces[i] + "' in " + part.strip());
                }
                values.put(pair[0].strip(), pair[1].strip());
            }
            sniffers.addSniffer(this.create(pieces[0].strip(), new SnifferParams(values)));
        }
        return sniffers;
    }

    /**
     * @throws IllegalArgumentException if the sniffer does not exist or a
     *                                  parameter is wrong
     */
    public CodeSniffer<T> create(String name, SnifferParams params) {
        Function<SnifferParams, CodeSniffer<T>> factory = this.sniffers.get(name);
        if (factory == null) {
            throw new IllegalArgumentException(
                    "Unknown sniffer: " + name + ". Available: " + String.join(", ", this.names()));
        }
        CodeSniffer<T> sniffer;
        try {
            sniffer = factory.apply(params);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(name + ": " + e.getMessage(), e);
        }
        if (!params.unused().isEmpty()) {
            throw new IllegalArgumentException(
                    "Unknown parameters for " + name + ": " + String.join(", ", params.unused()));
        }
        return sniffer;
    }
}
