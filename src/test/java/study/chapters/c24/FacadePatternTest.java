package study.chapters.c24;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c24.FacadePattern.*;

class FacadePatternTest {
    @Test void facadeCoordinatesSubsystemsInOrder() {
        List<String> log = new ArrayList<>(); CheckoutFacade f = new CheckoutFacade(i -> log.add("reserve"), a -> log.add("pay"), i -> { log.add("ship"); return "ok"; });
        assertEquals("ok", f.checkout("x", 5)); assertEquals(List.of("reserve", "pay", "ship"), log);
    }
    @Test void failureStopsLaterSubsystems() {
        List<String> log = new ArrayList<>(); CheckoutFacade f = new CheckoutFacade(i -> log.add("reserve"), a -> { throw new IllegalStateException("declined"); }, i -> { log.add("ship"); return "ok"; });
        assertThrows(IllegalStateException.class, () -> f.checkout("x", 5)); assertEquals(List.of("reserve"), log);
    }
    @Test void invalidInputDoesNotTouchSubsystems() {
        List<String> log = new ArrayList<>(); CheckoutFacade f = new CheckoutFacade(log::add, a -> log.add("pay"), i -> "ok");
        assertThrows(IllegalArgumentException.class, () -> f.checkout("x", -1)); assertTrue(log.isEmpty());
    }
}
