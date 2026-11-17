package study.chapters.c33;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c33.StatePattern.*;

class StatePatternTest {
    @Test void transitionsFollowCurrentStateBehavior() {
        Player p = new Player(); assertEquals("stopped", p.state()); p.press(); assertEquals("playing", p.state());
        p.press(); assertEquals("paused", p.state()); p.press(); assertEquals("playing", p.state());
    }
    @Test void stopResetsAndCanRestart() {
        Player p = new Player(); p.press(); p.stop(); assertEquals("stopped", p.state()); p.press(); assertEquals("playing", p.state());
    }
    @Test void contextsHaveIndependentStates() { Player a = new Player(); Player b = new Player(); a.press(); assertEquals("stopped", b.state()); }
}
