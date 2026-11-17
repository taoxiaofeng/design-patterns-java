package study.chapters.c08;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c08.ProgramToInterface.*;

class ProgramToInterfaceTest {
    @Test void serviceDependsOnContract() {
        List<String> seen = new ArrayList<>(); new MessageService(seen::add).welcome("A");
        assertEquals(List.of("welcome A"), seen);
    }
    @Test void concreteImplementationRecordsMessages() {
        MemorySender sender = new MemorySender(); MessageService s = new MessageService(sender);
        s.welcome("A"); s.welcome("B"); assertEquals(List.of("welcome A", "welcome B"), sender.messages());
    }
    @Test void requiresDependency() { assertThrows(NullPointerException.class, () -> new MessageService(null)); }
}
