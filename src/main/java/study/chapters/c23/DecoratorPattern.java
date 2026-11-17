package study.chapters.c23;



/** 装饰 Decorator：原创教学实现，来源范围见本章中文笔记。 */
public final class DecoratorPattern {
    public interface Text { String render(); }
    public record Plain(String value) implements Text { public String render() { return value; } }
    public record Bold(Text inner) implements Text { public String render() { return "<b>" + inner.render() + "</b>"; } }
    public record Brackets(Text inner) implements Text { public String render() { return "[" + inner.render() + "]"; } }
    public static String demo() { return new Brackets(new Bold(new Plain("hello"))).render(); }

    public static void main(String[] args) { System.out.println(demo()); }
}
