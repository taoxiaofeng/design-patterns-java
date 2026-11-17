package study.chapters.c28;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c28.CommandPattern.*;

class CommandPatternTest {
    @Test void executesAndUndoesInReverseOrder() {
        Editor e = new Editor(); Invoker i = new Invoker(); i.execute(new Append(e, "a")); i.execute(new Append(e, "b"));
        assertEquals("ab", e.text()); assertTrue(i.undo()); assertEquals("a", e.text()); assertTrue(i.undo()); assertEquals("", e.text()); assertFalse(i.undo());
    }
    @Test void failedCommandDoesNotEnterHistory() {
        Invoker i = new Invoker(); Command bad = new Command() { public void execute() { throw new IllegalStateException(); } public void undo() { fail(); } };
        assertThrows(IllegalStateException.class, () -> i.execute(bad)); assertFalse(i.undo());
    }
    @Test void cannotUndoBeforeExecution() { assertThrows(IllegalStateException.class, () -> new Append(new Editor(), "x").undo()); }
}
