package oo2.redictado.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlainTextFormatterTest {
    PlainTextFormatter formatter = new PlainTextFormatter();

    @Test
    public void testEmptyReport() {
        assertEquals("The diagram smells good.\n", formatter.format(new AromaReport()));
    }

    @Test
    public void testOneLinePerAroma() {
        AromaReport report = new AromaReport();
        report.addAroma(new Aroma("PrintSniffer", "The diagram has print methods.", 3));
        report.addAroma(new Aroma("OtherSniffer", "Another smell.", 7));

        String expected = """
                [PrintSniffer] line 3: The diagram has print methods.
                [OtherSniffer] line 7: Another smell.
                """;
        assertEquals(expected, formatter.format(report));
    }
}
