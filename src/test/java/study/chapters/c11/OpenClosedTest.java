package study.chapters.c11;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c11.OpenClosed.*;

class OpenClosedTest {
    @Test void supportsExistingShapes() { assertEquals(6 + Math.PI, total(List.of(new Rectangle(2, 3), new Circle(1))), 1e-9); }
    @Test void extendsWithoutChangingCalculator() { Shape triangle = () -> 6; assertEquals(6, total(List.of(triangle)), 1e-9); }
    @Test void emptyCollectionAndInvalidDimensions() {
        assertEquals(0, total(List.of()), 1e-9); assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
    }
}
