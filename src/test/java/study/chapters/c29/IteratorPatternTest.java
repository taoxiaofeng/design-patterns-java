package study.chapters.c29;

import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c29.IteratorPattern.*;

class IteratorPatternTest {
    @Test void traversesInOrderAndRejectsExhaustion() {
        Iterator<String> i = new Playlist(List.of("a", "b")).iterator(); assertTrue(i.hasNext()); assertEquals("a", i.next());
        assertEquals("b", i.next()); assertFalse(i.hasNext()); assertThrows(NoSuchElementException.class, i::next);
    }
    @Test void cursorsAreIndependent() {
        Playlist p = new Playlist(List.of("a", "b")); Iterator<String> a = p.iterator(); Iterator<String> b = p.iterator();
        a.next(); a.next(); assertEquals("a", b.next());
    }
    @Test void emptyIteratorHasNoElement() {
        Iterator<String> i = new Playlist(List.of()).iterator(); assertFalse(i.hasNext()); assertThrows(NoSuchElementException.class, i::next);
    }
}
