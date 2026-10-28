package oo2.redictado.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SnifferParamsTest {
    SnifferParams params = new SnifferParams(Map.of(
            "name", "print", "max", "20", "ratio", "0.5", "strict", "true", "typo", "x"));

    @Test
    public void testReadsValues() {
        assertEquals("print", params.getString("name", "default"));
        assertEquals(20, params.getInt("max", 1));
        assertEquals(0.5, params.getDouble("ratio", 1.0));
        assertTrue(params.getBoolean("strict", false));
    }

    @Test
    public void testUsesDefaultsForMissingValues() {
        SnifferParams none = SnifferParams.none();

        assertEquals("default", none.getString("name", "default"));
        assertEquals(1, none.getInt("max", 1));
        assertEquals(1.0, none.getDouble("ratio", 1.0));
        assertFalse(none.getBoolean("strict", false));
    }

    @Test
    public void testFailsWithWrongValues() {
        SnifferParams wrong = new SnifferParams(Map.of("max", "many", "strict", "yes"));

        assertThrows(IllegalArgumentException.class, () -> wrong.getInt("max", 1));
        assertThrows(IllegalArgumentException.class, () -> wrong.getDouble("max", 1.0));
        assertThrows(IllegalArgumentException.class, () -> wrong.getBoolean("strict", false));
    }

    @Test
    public void testKnowsWhichValuesWereNotRead() {
        params.getString("name", "default");
        params.getInt("max", 1);
        params.getInt("missing", 1);

        assertEquals(Set.of("ratio", "strict", "typo"), params.unused());
    }
}
