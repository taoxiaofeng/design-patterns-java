package study.chapters.c14;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c14.DependencyInversion.*;

class DependencyInversionTest {
    @Test void highLevelWorksThroughAbstraction() {
        MemoryRepository r = new MemoryRepository(); new OrderService(r).place("A"); assertTrue(r.exists("A"));
    }
    @Test void alternativeImplementationReceivesCalls() {
        List<String> seen = new ArrayList<>(); OrderRepository r = new OrderRepository() {
            public void save(String id) { seen.add(id); } public boolean exists(String id) { return seen.contains(id); }
        };
        new OrderService(r).place("B"); assertEquals(List.of("B"), seen);
    }
    @Test void validatesBeforeSaving() {
        MemoryRepository r = new MemoryRepository(); assertThrows(IllegalArgumentException.class, () -> new OrderService(r).place(""));
        assertFalse(r.exists(""));
    }
}
