package study.chapters.c32;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c32.ObserverPattern.*;

class ObserverPatternTest {
    @Test void supportsSubscribeUnsubscribeAndDeduplication() {
        News n = new News(); List<String> seen = new ArrayList<>(); Listener l = seen::add;
        n.subscribe(l); n.subscribe(l); n.publish("A"); n.unsubscribe(l); n.publish("B"); assertEquals(List.of("A"), seen);
    }
    @Test void callbackMayUnsubscribeDuringPublish() {
        News n = new News(); List<String> seen = new ArrayList<>(); Listener[] self = new Listener[1];
        self[0] = e -> { seen.add(e); n.unsubscribe(self[0]); }; n.subscribe(self[0]); n.publish("A"); n.publish("B"); assertEquals(List.of("A"), seen);
    }
    @Test void newSubscriberReceivesOnlyLaterEvents() {
        News n = new News(); List<String> seen = new ArrayList<>(); Listener later = seen::add;
        n.subscribe(e -> n.subscribe(later)); n.publish("A"); assertTrue(seen.isEmpty()); n.publish("B"); assertEquals(List.of("B"), seen);
    }
}
