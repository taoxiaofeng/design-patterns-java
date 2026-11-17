package study.chapters.c23;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c23.DecoratorPattern.*;

class DecoratorPatternTest {
    @Test void decoratorsCompose() { assertEquals("[<b>x</b>]", new Brackets(new Bold(new Plain("x"))).render()); }
    @Test void orderingChangesResult() { assertEquals("<b>[x]</b>", new Bold(new Brackets(new Plain("x"))).render()); }
    @Test void leavesOriginalUnchanged() { Plain p = new Plain("x"); new Bold(p).render(); assertEquals("x", p.render()); }
}
