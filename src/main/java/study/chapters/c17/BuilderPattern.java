package study.chapters.c17;

import java.util.ArrayList;
import java.util.List;

/** 生成器 Builder：原创教学实现，来源范围见本章中文笔记。 */
public final class BuilderPattern {
    public interface BuildSteps { void reset(); void main(String value); void drink(String value); }
    public record Meal(String main, String drink) {}
    public static final class MealBuilder implements BuildSteps {
        private String main; private String drink;
        public void reset() { main = null; drink = null; }
        public void main(String value) { main = value; }
        public void drink(String value) { drink = value; }
        public Meal result() {
            if (main == null || drink == null) throw new IllegalStateException("incomplete meal");
            return new Meal(main, drink);
        }
    }
    public static final class MenuBuilder implements BuildSteps {
        private final List<String> lines = new ArrayList<>();
        public void reset() { lines.clear(); }
        public void main(String value) { lines.add("main=" + value); }
        public void drink(String value) { lines.add("drink=" + value); }
        public String result() { return String.join(",", lines); }
    }
    public static final class Director {
        public void lunch(BuildSteps builder) { builder.reset(); builder.main("rice"); builder.drink("tea"); }
    }
    public static String demo() {
        Director d = new Director(); MealBuilder meal = new MealBuilder(); MenuBuilder menu = new MenuBuilder();
        d.lunch(meal); d.lunch(menu); return meal.result() + " / " + menu.result();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
