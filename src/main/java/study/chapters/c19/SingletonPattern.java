package study.chapters.c19;



/** 单例 Singleton：原创教学实现，来源范围见本章中文笔记。 */
public final class SingletonPattern {
    public enum Configuration {
        INSTANCE;
        private final String environment = "learning";
        public String environment() { return environment; }
    }
    public static String demo() {
        return "same=" + (Configuration.INSTANCE == Configuration.INSTANCE) + ", env=" + Configuration.INSTANCE.environment();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
