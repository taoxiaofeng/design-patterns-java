package study.chapters.c12;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/** SOLID：里氏替换原则：原创教学实现，来源范围见本章中文笔记。 */
public final class LiskovSubstitution {
    public interface ReadableStore { Optional<String> get(String key); }
    public interface WritableStore extends ReadableStore { void put(String key, String value); }
    public static final class MemoryStore implements WritableStore {
        private final Map<String, String> data = new HashMap<>();
        public Optional<String> get(String key) { return Optional.ofNullable(data.get(key)); }
        public void put(String key, String value) { data.put(Objects.requireNonNull(key), Objects.requireNonNull(value)); }
    }
    public record ReadOnlySnapshot(Map<String, String> data) implements ReadableStore {
        public ReadOnlySnapshot { data = Map.copyOf(data); }
        public Optional<String> get(String key) { return Optional.ofNullable(data.get(key)); }
    }
    public static String lookup(ReadableStore store) { return store.get("name").orElse("unknown"); }
    public static String demo() {
        MemoryStore store = new MemoryStore(); store.put("name", "Lin");
        return lookup(store) + " / " + lookup(new ReadOnlySnapshot(Map.of("name", "Qin")));
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
