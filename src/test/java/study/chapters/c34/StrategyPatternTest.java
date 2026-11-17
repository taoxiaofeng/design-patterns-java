package study.chapters.c34;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c34.StrategyPattern.*;

class StrategyPatternTest {
    @Test void switchesAlgorithmWithoutChangingContext() {
        Checkout c = new Checkout(new Standard()); assertEquals(10, c.delivery(5)); c.strategy(new Express()); assertEquals(25, c.delivery(5));
    }
    @Test void acceptsNewStrategy() { assertEquals(0, new Checkout(w -> 0).delivery(5)); }
    @Test void validatesInputAndDependency() {
        Checkout c = new Checkout(new Standard()); assertThrows(IllegalArgumentException.class, () -> c.delivery(0));
        assertThrows(NullPointerException.class, () -> c.strategy(null)); assertEquals(2, c.delivery(1));
    }
}
