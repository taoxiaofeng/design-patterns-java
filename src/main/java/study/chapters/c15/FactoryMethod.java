package study.chapters.c15;



/** 工厂方法 Factory Method：原创教学实现，来源范围见本章中文笔记。 */
public final class FactoryMethod {
    public interface Writer { String write(String text); }
    public static final class TextWriter implements Writer { public String write(String text) { return "TEXT:" + text; } }
    public static final class HtmlWriter implements Writer {
        public String write(String text) {
            return "<p>" + text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;") + "</p>";
        }
    }
    public abstract static class Exporter {
        protected abstract Writer createWriter();
        public final String export(String text) { return createWriter().write(text.strip()); }
    }
    public static final class TextExporter extends Exporter {
        @Override protected Writer createWriter() { return new TextWriter(); }
    }
    public static final class HtmlExporter extends Exporter {
        @Override protected Writer createWriter() { return new HtmlWriter(); }
    }
    public static String demo() { return new TextExporter().export(" hello ") + " / " + new HtmlExporter().export(" hello "); }

    public static void main(String[] args) { System.out.println(demo()); }
}
