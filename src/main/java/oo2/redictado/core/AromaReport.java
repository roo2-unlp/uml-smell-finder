package oo2.redictado.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Report of the bad smells found in a diagram.
 */
public class AromaReport {
    private final List<Aroma> aromas;

    public AromaReport() {
        this.aromas = new ArrayList<>();
    }

    public List<Aroma> getAromas() {
        return List.copyOf(this.aromas);
    }

    /**
     * Adds a bad smell to the report.
     *
     * @param aroma The bad smell to add.
     */
    public void addAroma(Aroma aroma) {
        this.aromas.add(aroma);
    }

    /**
     * Returns whether any bad smell was found.
     *
     * @return True if the report has any bad smell, false otherwise.
     */
    public boolean stinks() {
        return !this.aromas.isEmpty();
    }
}
