package study.chapters.c29;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/** 迭代器 Iterator：原创教学实现，来源范围见本章中文笔记。 */
public final class IteratorPattern {
    public static final class Playlist implements Iterable<String> {
        private final List<String> songs;
        public Playlist(List<String> songs) { this.songs = List.copyOf(songs); }
        public Iterator<String> iterator() {
            return new Iterator<>() {
                private int cursor;
                public boolean hasNext() { return cursor < songs.size(); }
                public String next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    return songs.get(cursor++);
                }
            };
        }
    }
    public static String demo() {
        List<String> visited = new ArrayList<>(); for (String song : new Playlist(List.of("intro", "finale"))) visited.add(song);
        return visited.toString();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
