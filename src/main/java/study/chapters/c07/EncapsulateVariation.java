package study.chapters.c07;

import java.util.List;

/** 封装变化：原创教学实现，来源范围见本章中文笔记。 */
public final class EncapsulateVariation {
    public record Line(int price, int quantity) {
        public Line { if (price < 0 || quantity <= 0) throw new IllegalArgumentException("line"); }
        public int subtotal() { return Math.multiplyExact(price, quantity); }
    }
    public interface FeePolicy { int fee(int subtotal); }
    public record Order(List<Line> lines) {
        public Order { lines = List.copyOf(lines); }
        public int total(FeePolicy policy) {
            int subtotal = lines.stream().mapToInt(Line::subtotal).reduce(0, Math::addExact);
            int fee = policy.fee(subtotal);
            if (fee < 0) throw new IllegalArgumentException("fee");
            return Math.addExact(subtotal, fee);
        }
    }
    public static String demo() {
        Order order = new Order(List.of(new Line(500, 2)));
        return "fixed=" + order.total(n -> 100) + ", free=" + order.total(n -> 0);
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
