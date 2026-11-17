package study.chapters.c21;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c21.BridgePattern.*;

class BridgePatternTest {
    @Test void sameAbstractionWorksWithBothImplementations() {
        for (Device d : List.of(new Radio(), new Television())) { Remote r = new Remote(d); r.toggle(); assertTrue(d.enabled()); r.toggle(); assertFalse(d.enabled()); }
    }
    @Test void refinedAbstractionReusesImplementation() {
        Device d = new Radio(); AdvancedRemote r = new AdvancedRemote(d); r.toggle(); r.off(); assertFalse(d.enabled());
    }
    @Test void devicesKeepIndependentState() {
        Device a = new Radio(); Device b = new Television(); new Remote(a).toggle(); assertFalse(b.enabled());
    }
}
