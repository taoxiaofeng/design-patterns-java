package study.chapters.c27;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c27.ChainOfResponsibility.*;

class ChainOfResponsibilityTest {
    @Test void passesValidRequestToTerminalHandler() { assertEquals("approved", chain().handle(new Request(true, 100))); }
    @Test void stopsAtFirstFailure() {
        Handler forbidden = new Handler(null) { public String handle(Request r) { fail("must short circuit"); return ""; } };
        assertEquals("denied:auth", new Authenticated(forbidden).handle(new Request(false, 10)));
        assertEquals("denied:amount", chain().handle(new Request(true, 101)));
        assertEquals("denied:amount", chain().handle(new Request(true, -1)));
    }
    @Test void missingTerminalIsExplicit() { assertEquals("unhandled", new Authenticated(null).handle(new Request(true, 10))); }
}
