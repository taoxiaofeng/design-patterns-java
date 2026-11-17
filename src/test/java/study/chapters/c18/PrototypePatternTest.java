package study.chapters.c18;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c18.PrototypePattern.*;

class PrototypePatternTest {
    @Test void copyHasSameDataButDifferentIdentity() {
        Document original = new Document("A", List.of("x")); Document copy = original.copy();
        assertNotSame(original, copy); assertEquals(original.title(), copy.title()); assertEquals(original.tags(), copy.tags());
    }
    @Test void nestedMutableCollectionIsIndependent() {
        Document original = new Document("A", List.of("x")); Document copy = original.copy(); copy.addTag("y");
        original.addTag("z"); assertEquals(List.of("x", "z"), original.tags()); assertEquals(List.of("x", "y"), copy.tags());
    }
    @Test void clientUsesPrototypeContract() {
        Prototype p = new Document("A", List.of()); assertInstanceOf(Document.class, duplicate(p)); assertNotSame(p, duplicate(p));
    }
}
