package oo2.redictado.sniffers.godclass;

import oo2.redictado.core.SnifferParams;
import oo2.redictado.plantuml.PlantUmlSniffer;
import oo2.redictado.plantuml.PlantUmlVisitor;

public class GodClassSniffer extends PlantUmlSniffer {
    public static final int DEFAULT_MIN_CLASSES = 5;
    public static final int DEFAULT_MIN_METHODS = 7;
    public static final int DEFAULT_MIN_ATTRIBUTES = 5;
    public static final double DEFAULT_IQR_FACTOR = 1.5;

    private final int minClasses;
    private final int minMethods;
    private final int minAttributes;
    private final double iqrFactor;

    public GodClassSniffer() {
        this(DEFAULT_MIN_CLASSES, DEFAULT_MIN_METHODS, DEFAULT_MIN_ATTRIBUTES, DEFAULT_IQR_FACTOR);
    }

    public GodClassSniffer(SnifferParams params) {
        this(
                params.getInt("minClasses", DEFAULT_MIN_CLASSES),
                params.getInt("minMethods", DEFAULT_MIN_METHODS),
                params.getInt("minAttributes", DEFAULT_MIN_ATTRIBUTES),
                params.getDouble("iqrFactor", DEFAULT_IQR_FACTOR)
        );
    }

    public GodClassSniffer(int minClasses, int minMethods, int minAttributes, double iqrFactor) {
        this.minClasses = minClasses;
        this.minMethods = minMethods;
        this.minAttributes = minAttributes;
        this.iqrFactor = iqrFactor;
    }

    @Override
    protected PlantUmlVisitor createVisitor() {
        return new GodClassSnifferVisitor(minClasses, minMethods, minAttributes, iqrFactor);
    }
}