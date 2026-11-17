package study.chapters.c05;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c05.WhyPatterns.*;

class WhyPatternsTest {
    @Test void preservesExistingBehavior() {
        Notifier n = new Notifier(Map.of("mail", s -> "mail:" + s));
        assertEquals(legacy("mail", "hi"), n.send("mail", "hi"));
    }
    @Test void supportsExtension() { assertEquals("push:hi", new Notifier(Map.of("push", s -> "push:" + s)).send("push", "hi")); }
    @Test void rejectsMissingChannel() { assertThrows(IllegalArgumentException.class, () -> new Notifier(Map.of()).send("fax", "x")); }
}
