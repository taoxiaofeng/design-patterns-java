package study.chapters.c26;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/** 代理 Proxy：原创教学实现，来源范围见本章中文笔记。 */
public final class ProxyPattern {
    public interface Image { String display(); }
    public record RealImage(String file) implements Image { public String display() { return "image:" + file; } }
    public static final class LazyImage implements Image {
        private final Supplier<Image> loader;
        private Image target;
        public LazyImage(Supplier<Image> loader) { this.loader = Objects.requireNonNull(loader); }
        public String display() {
            if (target == null) target = Objects.requireNonNull(loader.get());
            return target.display();
        }
    }
    public static String demo() {
        List<String> loads = new ArrayList<>(); Image image = new LazyImage(() -> { loads.add("load"); return new RealImage("map.png"); });
        String first = image.display(); image.display(); return first + ", loads=" + loads.size();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
