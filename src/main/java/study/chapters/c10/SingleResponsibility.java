package study.chapters.c10;

import java.util.ArrayList;
import java.util.List;

/** SOLID：单一职责原则：原创教学实现，来源范围见本章中文笔记。 */
public final class SingleResponsibility {
    public record Invoice(List<Integer> amounts) {
        public Invoice { amounts = List.copyOf(amounts); }
        public int total() { return amounts.stream().mapToInt(Integer::intValue).sum(); }
    }
    public static final class Renderer { public String render(Invoice invoice) { return "TOTAL=" + invoice.total(); } }
    public interface Archive { void save(String text); }
    public static final class MemoryArchive implements Archive {
        private final List<String> records = new ArrayList<>();
        public void save(String text) { records.add(text); }
        public List<String> records() { return List.copyOf(records); }
    }
    public static String demo() {
        Invoice invoice = new Invoice(List.of(10, 20)); MemoryArchive archive = new MemoryArchive();
        archive.save(new Renderer().render(invoice)); return archive.records().toString();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
