package study.chapters.c30;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c30.MediatorPattern.*;

class MediatorPatternTest {
    @Test void routesToOtherColleaguesOnly() {
        ChatRoom room = new ChatRoom(); User a = room.join("A"); User b = room.join("B"); User c = room.join("C"); a.send("hi");
        assertTrue(a.inbox().isEmpty()); assertEquals(List.of("A:hi"), b.inbox()); assertEquals(b.inbox(), c.inbox());
    }
    @Test void roomsRemainIsolated() {
        User a = new ChatRoom().join("A"); User b = new ChatRoom().join("B"); a.send("hi"); assertTrue(b.inbox().isEmpty());
    }
    @Test void preservesMessageOrder() {
        ChatRoom room = new ChatRoom(); User a = room.join("A"); User b = room.join("B"); a.send("1"); a.send("2");
        assertEquals(List.of("A:1", "A:2"), b.inbox());
    }
}
