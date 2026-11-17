package study.chapters.c22;

import java.util.List;

/** 组合 Composite：原创教学实现，来源范围见本章中文笔记。 */
public final class CompositePattern {
    public interface Node { int price(); }
    public record Item(String name, int price) implements Node {
        public Item { if (price < 0) throw new IllegalArgumentException("price"); }
    }
    public record Box(List<Node> children) implements Node {
        public Box { children = List.copyOf(children); }
        public int price() { return children.stream().mapToInt(Node::price).sum(); }
    }
    public static String demo() {
        Node order = new Box(List.of(new Item("book", 50), new Box(List.of(new Item("pen", 5)))));
        return "tree total=" + order.price();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
