package study.chapters.c36;



/** 访问者 Visitor：原创教学实现，来源范围见本章中文笔记。 */
public final class VisitorPattern {
    public interface Shape { <R> R accept(Visitor<R> visitor); }
    public record Circle(double radius) implements Shape {
        public Circle { if (radius < 0) throw new IllegalArgumentException("radius"); }
        public <R> R accept(Visitor<R> visitor) { return visitor.visit(this); }
    }
    public record Rectangle(double width, double height) implements Shape {
        public Rectangle { if (width < 0 || height < 0) throw new IllegalArgumentException("dimensions"); }
        public <R> R accept(Visitor<R> visitor) { return visitor.visit(this); }
    }
    public interface Visitor<R> { R visit(Circle circle); R visit(Rectangle rectangle); }
    public static final class AreaVisitor implements Visitor<Double> {
        public Double visit(Circle c) { return Math.PI * c.radius() * c.radius(); }
        public Double visit(Rectangle r) { return r.width() * r.height(); }
    }
    public static final class NameVisitor implements Visitor<String> {
        public String visit(Circle c) { return "circle"; }
        public String visit(Rectangle r) { return "rectangle"; }
    }
    public static String demo() {
        Shape s = new Rectangle(2, 3); return s.accept(new NameVisitor()) + " area=" + s.accept(new AreaVisitor());
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
