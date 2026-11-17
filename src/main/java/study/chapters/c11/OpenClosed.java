package study.chapters.c11;

import java.util.List;

/** SOLID：开闭原则：原创教学实现，来源范围见本章中文笔记。 */
public final class OpenClosed {
    public interface Shape { double area(); }
    public record Rectangle(double width, double height) implements Shape {
        public Rectangle { if (width < 0 || height < 0) throw new IllegalArgumentException("dimensions"); }
        public double area() { return width * height; }
    }
    public record Circle(double radius) implements Shape {
        public Circle { if (radius < 0) throw new IllegalArgumentException("radius"); }
        public double area() { return Math.PI * radius * radius; }
    }
    public static double total(List<? extends Shape> shapes) { return shapes.stream().mapToDouble(Shape::area).sum(); }
    public static String demo() { return "area=" + total(List.of(new Rectangle(2, 3), new Circle(1))); }

    public static void main(String[] args) { System.out.println(demo()); }
}
