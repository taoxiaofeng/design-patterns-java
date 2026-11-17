package study.chapters.c04;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c04.WhatIsPattern.*;

class WhatIsPatternTest {
    @Test void contextUsesCollaborator() { assertEquals(90, new Checkout(x -> x - 10).total(100)); }
    @Test void implementationCanVary() { assertEquals(200, new Checkout(x -> x * 2).total(100)); }
    @Test void contractRejectsNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> new Checkout(x -> x).total(-1));
    }
}
