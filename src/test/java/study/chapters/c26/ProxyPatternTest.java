package study.chapters.c26;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c26.ProxyPattern.*;

class ProxyPatternTest {
    @Test void constructionDoesNotLoadService() {
        List<String> loads = new ArrayList<>(); new LazyImage(() -> { loads.add("load"); return new RealImage("x"); }); assertTrue(loads.isEmpty());
    }
    @Test void loadsOnceAndPreservesServiceInterface() {
        List<String> loads = new ArrayList<>(); Image i = new LazyImage(() -> { loads.add("load"); return new RealImage("x"); });
        assertEquals("image:x", i.display()); assertEquals("image:x", i.display()); assertEquals(1, loads.size());
    }
    @Test void failedLoadCanBeRetried() {
        int[] attempts = {0}; Image i = new LazyImage(() -> { if (++attempts[0] == 1) throw new IllegalStateException("failed"); return new RealImage("x"); });
        assertThrows(IllegalStateException.class, i::display); assertEquals("image:x", i.display()); assertEquals(2, attempts[0]);
    }
}
