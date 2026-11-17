package study.chapters.c07;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c07.EncapsulateVariation.*;

class EncapsulateVariationTest {
    @Test void isolatesPolicyFromSubtotal() {
        Order o = new Order(List.of(new Line(500, 2), new Line(100, 3)));
        assertEquals(1400, o.total(n -> 100)); assertEquals(1300, o.total(n -> 0));
    }
    @Test void emptyOrderCanBeFree() { assertEquals(0, new Order(List.of()).total(n -> 0)); }
    @Test void rejectsInvalidAmounts() {
        assertThrows(IllegalArgumentException.class, () -> new Line(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Order(List.of()).total(n -> -1));
    }
}
