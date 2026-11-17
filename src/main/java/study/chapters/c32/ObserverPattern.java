package study.chapters.c32;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/** 观察者 Observer：原创教学实现，来源范围见本章中文笔记。 */
public final class ObserverPattern {
    public interface Listener { void update(String event); }
    public static final class News {
        private final Set<Listener> listeners = new LinkedHashSet<>();
        public void subscribe(Listener listener) { listeners.add(Objects.requireNonNull(listener)); }
        public void unsubscribe(Listener listener) { listeners.remove(listener); }
        public void publish(String event) { for (Listener listener : List.copyOf(listeners)) listener.update(event); }
    }
    public static String demo() {
        News news = new News(); List<String> inbox = new ArrayList<>(); Listener listener = inbox::add;
        news.subscribe(listener); news.publish("release"); news.unsubscribe(listener); news.publish("ignored"); return inbox.toString();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
