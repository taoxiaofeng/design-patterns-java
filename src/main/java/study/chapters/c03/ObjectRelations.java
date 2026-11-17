package study.chapters.c03;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 对象关系：依赖、关联、聚合与组合：原创教学实现，来源范围见本章中文笔记。 */
public final class ObjectRelations {
    public record Member(String name) {}
    public static final class Team {
        private final List<Member> members = new ArrayList<>();
        public void add(Member member) { members.add(Objects.requireNonNull(member)); }
        public void remove(Member member) { members.remove(member); }
        public List<Member> members() { return List.copyOf(members); }
    }
    public static final class Car {
        private final Engine engine = new Engine();
        public String start() { return engine.start(); }
        private static final class Engine { String start() { return "engine running"; } }
    }
    public interface Printer { String print(String text); }
    public static String printRoster(Team team, Printer printer) { return printer.print(team.members().toString()); }
    public static String demo() {
        Team team = new Team(); team.add(new Member("Lin"));
        return printRoster(team, text -> "roster=" + text) + "; " + new Car().start();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
