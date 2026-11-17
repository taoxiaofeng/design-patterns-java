package study.chapters.c35;

import java.util.Arrays;
import java.util.List;

/** 模板方法 Template Method：原创教学实现，来源范围见本章中文笔记。 */
public final class TemplateMethod {
    public abstract static class Importer {
        public final List<String> run(String source) {
            String normalized = source.strip();
            List<String> result = List.copyOf(parse(normalized));
            after(result); return result;
        }
        protected abstract List<String> parse(String source);
        protected void after(List<String> values) {}
    }
    public static final class CsvImporter extends Importer {
        protected List<String> parse(String source) {
            if (source.isEmpty()) return List.of();
            return Arrays.stream(source.split(",", -1)).map(String::strip).toList();
        }
    }
    public static String demo() { return new CsvImporter().run(" apple, pear ").toString(); }

    public static void main(String[] args) { System.out.println(demo()); }
}
