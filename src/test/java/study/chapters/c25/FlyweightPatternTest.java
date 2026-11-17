package study.chapters.c25;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c25.FlyweightPattern.*;

class FlyweightPatternTest {
    @Test void equalIntrinsicStateSharesIdentity() {
        StyleFactory f = new StyleFactory(); assertSame(f.get("mono", "blue"), f.get("mono", "blue")); assertEquals(1, f.size());
    }
    @Test void differentIntrinsicStateDoesNotShare() {
        StyleFactory f = new StyleFactory(); assertNotSame(f.get("mono", "blue"), f.get("mono", "red")); assertEquals(2, f.size());
    }
    @Test void extrinsicStateRemainsIndependent() {
        Style s = new StyleFactory().get("mono", "blue"); Glyph a = new Glyph('A', 1, 2, s); Glyph b = new Glyph('B', 3, 4, s);
        assertSame(a.style(), b.style()); assertEquals("A@1,2:blue", a.draw()); assertEquals("B@3,4:blue", b.draw());
    }
}
