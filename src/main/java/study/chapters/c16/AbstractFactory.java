package study.chapters.c16;



/** 抽象工厂 Abstract Factory：原创教学实现，来源范围见本章中文笔记。 */
public final class AbstractFactory {
    public interface Button { String draw(); }
    public interface Checkbox { String draw(); }
    public interface UiFactory { Button button(); Checkbox checkbox(); }
    public static final class LightFactory implements UiFactory {
        public Button button() { return () -> "light-button"; }
        public Checkbox checkbox() { return () -> "light-checkbox"; }
    }
    public static final class DarkFactory implements UiFactory {
        public Button button() { return () -> "dark-button"; }
        public Checkbox checkbox() { return () -> "dark-checkbox"; }
    }
    public static String screen(UiFactory factory) { return factory.button().draw() + "," + factory.checkbox().draw(); }
    public static String demo() { return screen(new LightFactory()) + " / " + screen(new DarkFactory()); }

    public static void main(String[] args) { System.out.println(demo()); }
}
