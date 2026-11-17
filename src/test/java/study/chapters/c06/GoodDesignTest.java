package study.chapters.c06;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c06.GoodDesign.*;

class GoodDesignTest {
    @Test void reusesCalculationAcrossFormats() {
        Report r = new Report(); assertEquals("30", r.render(List.of(10, 20), n -> Integer.toString(n)));
        assertEquals("<b>30</b>", r.render(List.of(10, 20), n -> "<b>" + n + "</b>"));
    }
    @Test void emptyInputHasZeroTotal() { assertEquals(0, new Report().total(List.of())); }
    @Test void sumsActualValues() { assertEquals(5, new Report().total(List.of(-5, 10))); }
}
