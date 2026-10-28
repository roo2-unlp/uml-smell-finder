package oo2.redictado.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * Parameters given to a sniffer from outside, for example from the command
 * line. Each value is read by name, with a default for when it is missing.
 *
 * A sniffer that accepts parameters has a public constructor that receives a
 * SnifferParams.
 */
public class SnifferParams {
    private final Map<String, String> values;
    private final Set<String> read = new HashSet<>();

    public SnifferParams(Map<String, String> values) {
        this.values = Map.copyOf(values);
    }

    public static SnifferParams none() {
        return new SnifferParams(Map.of());
    }

    public String getString(String name, String defaultValue) {
        this.read.add(name);
        return this.values.getOrDefault(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        return this.get(name, defaultValue, Integer::parseInt, "an integer");
    }

    public double getDouble(String name, double defaultValue) {
        return this.get(name, defaultValue, Double::parseDouble, "a number");
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        return this.get(name, defaultValue, value -> switch (value) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new IllegalArgumentException();
        }, "true or false");
    }

    /**
     * @return the names of the parameters that were given but never read,
     *         which usually means they are misspelled
     */
    public Set<String> unused() {
        Set<String> unused = new TreeSet<>(this.values.keySet());
        unused.removeAll(this.read);
        return unused;
    }

    private <V> V get(String name, V defaultValue, Function<String, V> parser, String expected) {
        String value = this.getString(name, null);
        if (value == null) {
            return defaultValue;
        }
        try {
            return parser.apply(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Parameter " + name + " must be " + expected + ", not '" + value + "'");
        }
    }
}
