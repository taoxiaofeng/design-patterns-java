package study.chapters.c20;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c20.AdapterPattern.*;

class AdapterPatternTest {
    @Test void convertsBoundaryTemperatures() {
        assertEquals(0, new SensorAdapter(new LegacySensor(32)).celsius(), 1e-9);
        assertEquals(100, new SensorAdapter(new LegacySensor(212)).celsius(), 1e-9);
    }
    @Test void clientUsesTargetContract() {
        assertTrue(freezing(new SensorAdapter(new LegacySensor(-40)))); assertFalse(freezing(new SensorAdapter(new LegacySensor(68))));
    }
    @Test void requiresAdaptee() { assertThrows(NullPointerException.class, () -> new SensorAdapter(null)); }
}
