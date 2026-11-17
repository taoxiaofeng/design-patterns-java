package study.chapters.c24;

import java.util.ArrayList;
import java.util.List;

/** 外观 Facade：原创教学实现，来源范围见本章中文笔记。 */
public final class FacadePattern {
    public interface Inventory { void reserve(String item); }
    public interface Payment { void charge(int amount); }
    public interface Shipping { String ship(String item); }
    public record CheckoutFacade(Inventory inventory, Payment payment, Shipping shipping) {
        public String checkout(String item, int amount) {
            if (amount < 0) throw new IllegalArgumentException("amount");
            inventory.reserve(item); payment.charge(amount); return shipping.ship(item);
        }
    }
    public static String demo() {
        List<String> log = new ArrayList<>();
        CheckoutFacade f = new CheckoutFacade(i -> log.add("reserve:" + i), a -> log.add("pay:" + a), i -> "shipped:" + i);
        return f.checkout("book", 50) + ", steps=" + log;
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
