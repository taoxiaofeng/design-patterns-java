package study.chapters.c14;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** SOLID：依赖倒置原则：原创教学实现，来源范围见本章中文笔记。 */
public final class DependencyInversion {
    public interface OrderRepository { void save(String id); boolean exists(String id); }
    public static final class MemoryRepository implements OrderRepository {
        private final Set<String> ids = new HashSet<>();
        public void save(String id) { ids.add(id); }
        public boolean exists(String id) { return ids.contains(id); }
    }
    public static final class OrderService {
        private final OrderRepository repository;
        public OrderService(OrderRepository repository) { this.repository = Objects.requireNonNull(repository); }
        public void place(String id) {
            if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
            repository.save(id);
        }
    }
    public static String demo() {
        MemoryRepository r = new MemoryRepository(); new OrderService(r).place("A-1"); return "saved=" + r.exists("A-1");
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
