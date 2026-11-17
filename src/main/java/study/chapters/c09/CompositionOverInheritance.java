package study.chapters.c09;

import java.util.Objects;

/** 组合优于继承：原创教学实现，来源范围见本章中文笔记。 */
public final class CompositionOverInheritance {
    public interface Movement { String move(); }
    public interface Tool { String work(); }
    public record Robot(Movement movement, Tool tool) {
        public Robot { Objects.requireNonNull(movement); Objects.requireNonNull(tool); }
        public String execute() { return movement.move() + ":" + tool.work(); }
    }
    public static String demo() {
        Movement wheels = () -> "roll";
        return new Robot(wheels, () -> "clean").execute() + " / " + new Robot(wheels, () -> "inspect").execute();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
