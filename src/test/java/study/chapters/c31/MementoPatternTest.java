package study.chapters.c31;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c31.MementoPattern.*;

class MementoPatternTest {
    @Test void restoresCheckpointWithoutExposingStateToHistory() {
        Editor e = new Editor(); History h = new History(e); e.write("A"); h.checkpoint(); e.write("B"); h.checkpoint(); e.write("C");
        assertTrue(h.undo()); assertEquals("B", e.text()); assertTrue(h.undo()); assertEquals("A", e.text()); assertFalse(h.undo());
    }
    @Test void savedSnapshotDoesNotChangeWithOriginator() {
        Editor e = new Editor(); e.write("A"); Snapshot s = e.save(); e.write("B"); e.restore(s); assertEquals("A", e.text());
    }
    @Test void rejectsForeignSnapshot() {
        Editor a = new Editor(); Editor b = new Editor(); b.write("B");
        assertThrows(IllegalArgumentException.class, () -> b.restore(a.save())); assertEquals("B", b.text());
    }
}
