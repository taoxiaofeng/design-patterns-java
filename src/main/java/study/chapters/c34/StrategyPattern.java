package study.chapters.c34;

import java.util.Objects;

/** 策略 Strategy：原创教学实现，来源范围见本章中文笔记。 */
public final class StrategyPattern {
    public interface Shipping { int cost(int weight); }
    public static final class Standard implements Shipping { public int cost(int weight) { return weight * 2; } }
    public static final class Express implements Shipping { public int cost(int weight) { return 10 + weight * 3; } }
    public static final class Checkout {
        private Shipping shipping;
        public Checkout(Shipping shipping) { strategy(shipping); }
        public void strategy(Shipping shipping) { this.shipping = Objects.requireNonNull(shipping); }
        public int delivery(int weight) {
            if (weight <= 0) throw new IllegalArgumentException("weight");
            return shipping.cost(weight);
        }
    }
    public static String demo() {
        Checkout c = new Checkout(new Standard()); int first = c.delivery(5); c.strategy(new Express());
        return "standard=" + first + ", express=" + c.delivery(5);
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
