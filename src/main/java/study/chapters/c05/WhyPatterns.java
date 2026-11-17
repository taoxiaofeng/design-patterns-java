package study.chapters.c05;

import java.util.Map;
import java.util.function.Function;

/** 为什么学习设计模式：原创教学实现，来源范围见本章中文笔记。 */
public final class WhyPatterns {
    public static String legacy(String channel, String text) {
        return switch (channel) {
            case "mail" -> "mail:" + text;
            case "sms" -> "sms:" + text;
            default -> throw new IllegalArgumentException("unknown channel");
        };
    }
    public static final class Notifier {
        private final Map<String, Function<String, String>> channels;
        public Notifier(Map<String, Function<String, String>> channels) { this.channels = Map.copyOf(channels); }
        public String send(String channel, String text) {
            Function<String, String> sender = channels.get(channel);
            if (sender == null) throw new IllegalArgumentException("unknown channel");
            return sender.apply(text);
        }
    }
    public static String demo() { return new Notifier(Map.of("push", s -> "push:" + s)).send("push", "hello"); }

    public static void main(String[] args) { System.out.println(demo()); }
}
