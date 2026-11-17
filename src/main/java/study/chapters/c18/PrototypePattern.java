package study.chapters.c18;

import java.util.ArrayList;
import java.util.List;

/** 原型 Prototype：原创教学实现，来源范围见本章中文笔记。 */
public final class PrototypePattern {
    public interface Prototype { Prototype copy(); }
    public static final class Document implements Prototype {
        private final String title;
        private final List<String> tags;
        public Document(String title, List<String> tags) { this.title = title; this.tags = new ArrayList<>(tags); }
        public void addTag(String tag) { tags.add(tag); }
        public List<String> tags() { return List.copyOf(tags); }
        public String title() { return title; }
        @Override public Document copy() { return new Document(title, tags); }
    }
    public static Prototype duplicate(Prototype prototype) { return prototype.copy(); }
    public static String demo() {
        Document original = new Document("lesson", List.of("java")); Document copy = original.copy(); copy.addTag("patterns");
        return "original=" + original.tags() + ", copy=" + copy.tags();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
