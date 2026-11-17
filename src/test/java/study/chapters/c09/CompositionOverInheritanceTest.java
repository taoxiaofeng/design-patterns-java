package study.chapters.c09;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c09.CompositionOverInheritance.*;

class CompositionOverInheritanceTest {
    @Test void variesToolWithoutChangingMovement() {
        Movement wheels = () -> "roll";
        assertEquals("roll:clean", new Robot(wheels, () -> "clean").execute());
        assertEquals("roll:inspect", new Robot(wheels, () -> "inspect").execute());
    }
    @Test void variesMovementIndependently() { assertEquals("fly:clean", new Robot(() -> "fly", () -> "clean").execute()); }
    @Test void requiresBothCollaborators() { assertThrows(NullPointerException.class, () -> new Robot(null, () -> "work")); }
}
