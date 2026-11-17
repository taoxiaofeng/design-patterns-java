package study.chapters.c16;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c16.AbstractFactory.*;

class AbstractFactoryTest {
    @Test void createsConsistentLightFamily() { assertEquals("light-button,light-checkbox", screen(new LightFactory())); }
    @Test void createsConsistentDarkFamily() { assertEquals("dark-button,dark-checkbox", screen(new DarkFactory())); }
    @Test void clientAcceptsNewFamily() {
        UiFactory f = new UiFactory() {
            public Button button() { return () -> "contrast-button"; }
            public Checkbox checkbox() { return () -> "contrast-checkbox"; }
        };
        assertEquals("contrast-button,contrast-checkbox", screen(f));
    }
}
