package study.chapters.c13;



/** SOLID：接口隔离原则：原创教学实现，来源范围见本章中文笔记。 */
public final class InterfaceSegregation {
    public interface Printer { String print(String text); }
    public interface Scanner { String scan(); }
    public static final class PrintOnly implements Printer { public String print(String text) { return "print:" + text; } }
    public static final class AllInOne implements Printer, Scanner {
        public String print(String text) { return "print:" + text; }
        public String scan() { return "scan:data"; }
    }
    public static String printJob(Printer printer) { return printer.print("report"); }
    public static String demo() { return printJob(new PrintOnly()) + " / " + new AllInOne().scan(); }

    public static void main(String[] args) { System.out.println(demo()); }
}
