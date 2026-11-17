package study.chapters.c25;

import java.util.HashMap;
import java.util.Map;

/** 享元 Flyweight：原创教学实现，来源范围见本章中文笔记。 */
public final class FlyweightPattern {
    public record Style(String font, String color) {}
    public record Key(String font, String color) {}
    public static final class StyleFactory {
        private final Map<Key, Style> cache = new HashMap<>();
        public Style get(String font, String color) { return cache.computeIfAbsent(new Key(font, color), k -> new Style(k.font(), k.color())); }
        public int size() { return cache.size(); }
    }
    public record Glyph(char value, int x, int y, Style style) {
        public String draw() { return value + "@" + x + "," + y + ":" + style.color(); }
    }
    public static String demo() {
        StyleFactory f = new StyleFactory(); Glyph a = new Glyph('A', 0, 0, f.get("mono", "blue"));
        Glyph b = new Glyph('B', 1, 0, f.get("mono", "blue")); return a.draw() + " / " + b.draw() + ", styles=" + f.size();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
