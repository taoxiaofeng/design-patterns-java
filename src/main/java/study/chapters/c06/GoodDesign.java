package study.chapters.c06;

import java.util.List;

/** 良好设计：复用与可扩展性：原创教学实现，来源范围见本章中文笔记。 */
public final class GoodDesign {
    public interface Formatter { String format(int total); }
    public static final class Report {
        public int total(List<Integer> values) { return values.stream().mapToInt(Integer::intValue).sum(); }
        public String render(List<Integer> values, Formatter formatter) { return formatter.format(total(values)); }
    }
    public static String demo() {
        Report report = new Report(); List<Integer> values = List.of(10, 20);
        return report.render(values, n -> "total=" + n) + " / " + report.render(values, n -> "<b>" + n + "</b>");
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
