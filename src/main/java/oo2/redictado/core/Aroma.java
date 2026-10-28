package oo2.redictado.core;

/**
 * A bad smell found in a diagram.
 *
 * @param name the name of the sniffer that found the bad smell
 * @param message the message that describes the bad smell
 * @param line the line of the diagram where the bad smell was found
 */
public record Aroma(String name, String message, int line) {
}
