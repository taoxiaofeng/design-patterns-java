package study;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/** 无参数进入互动菜单；带参数适合脚本、IDE 与单章学习。 */
public final class Launcher {
    private Launcher() {}
    public static void main(String[] args) {
        int status = run(args, new InputStreamReader(System.in, StandardCharsets.UTF_8), System.out, System.err);
        if (status != 0) System.exit(status);
    }
    public static int run(String[] args, Reader input, PrintStream out, PrintStream err) {
        try {
            if (args.length > 0) return command(args, out);
            out.println("Java 17 设计模式学习项目 | 36 章 | 输入 help 查看命令");
            list(null, out);
            BufferedReader reader = input instanceof BufferedReader b ? b : new BufferedReader(input);
            while (true) {
                out.print("选择章节> "); out.flush();
                String line = reader.readLine();
                if (line == null) { out.println(); return 0; }
                line = line.strip();
                if (line.isEmpty()) continue;
                if (Set.of("q", "quit", "exit", "0").contains(line.toLowerCase(Locale.ROOT))) return 0;
                try { command(line.split("\\s+"), out); }
                catch (IllegalArgumentException ex) { err.println("输入错误：" + ex.getMessage()); }
            }
        } catch (IllegalArgumentException ex) {
            err.println("输入错误：" + ex.getMessage()); return 2;
        } catch (IOException | ReflectiveOperationException ex) {
            Throwable cause = ex instanceof InvocationTargetException i ? i.getTargetException() : ex;
            err.println("运行失败：" + cause.getClass().getSimpleName() + ": " + cause.getMessage()); return 1;
        }
    }
    private static int command(String[] words, PrintStream out) throws IOException, ReflectiveOperationException {
        String action = words[0].toLowerCase(Locale.ROOT);
        switch (action) {
            case "--help", "help" -> { require(words.length == 1, "help 不接受其他参数"); help(out); }
            case "--list", "list" -> {
                require(words.length <= 2, "list [分组]");
                list(words.length == 2 ? words[1].toLowerCase(Locale.ROOT) : null, out);
            }
            case "--all", "all" -> {
                require(words.length == 1, "all 不接受其他参数");
                for (Catalog.Chapter chapter : Catalog.CHAPTERS) execute(chapter, out);
            }
            case "--notes", "notes" -> {
                require(words.length == 2, "notes <编号或英文标识>"); notes(select(words[1]), out);
            }
            case "--run", "run" -> {
                require(words.length == 2, "run <编号或英文标识>"); execute(select(words[1]), out);
            }
            default -> { require(words.length == 1, "请使用编号、英文标识或 help"); execute(select(words[0]), out); }
        }
        return 0;
    }
    private static void require(boolean condition, String message) {
        if (!condition) throw new IllegalArgumentException(message);
    }
    private static Catalog.Chapter select(String selector) {
        return Catalog.find(selector).orElseThrow(() -> new IllegalArgumentException("未知章节：" + selector + "，请输入 1-36 或 --list"));
    }
    private static void list(String group, PrintStream out) {
        require(group == null || Catalog.GROUPS.contains(group), "未知分组；可用：oop intro principles solid creational structural behavioral");
        for (Catalog.Chapter c : Catalog.CHAPTERS) if (group == null || c.group().equals(group))
            out.printf("%02d | %s | %s | %s | %s%n", c.number(), c.slug(), c.title(), c.group(), c.source());
    }
    private static void execute(Catalog.Chapter chapter, PrintStream out) throws ReflectiveOperationException {
        out.printf("[%02d] %s (%s)%n", chapter.number(), chapter.title(), chapter.source());
        // 调用 demo 以便输出可被菜单测试捕获；每章另有独立 main。
        Object result = Class.forName(chapter.className()).getMethod("demo").invoke(null);
        out.println(result);
    }
    private static void notes(Catalog.Chapter chapter, PrintStream out) throws IOException {
        try (InputStream stream = Launcher.class.getResourceAsStream(chapter.resource())) {
            if (stream == null) throw new IOException("缺少笔记：" + chapter.resource());
            out.print(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
        }
    }
    private static void help(PrintStream out) {
        out.println("编号 / 英文标识 / run <章节>：运行；notes <章节>：中文笔记与任务");
        out.println("list [分组]：目录；all：运行全部；q / quit / exit / 0：退出互动菜单");
        out.println("命令行：--list [分组]、--run <章节>、--notes <章节>、--all、--help；无参数进入互动菜单");
        out.println("分组：oop intro principles solid creational structural behavioral");
    }
}
