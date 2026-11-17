package study.chapters.c28;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/** 命令 Command：原创教学实现，来源范围见本章中文笔记。 */
public final class CommandPattern {
    public static final class Editor {
        private String text = "";
        public String text() { return text; }
        public void text(String value) { text = Objects.requireNonNull(value); }
    }
    public interface Command { void execute(); void undo(); }
    public static final class Append implements Command {
        private final Editor editor; private final String suffix; private String before;
        public Append(Editor editor, String suffix) { this.editor = editor; this.suffix = Objects.requireNonNull(suffix); }
        public void execute() {
            if (before != null) throw new IllegalStateException("command already used; create a new command");
            before = editor.text(); editor.text(before + suffix);
        }
        public void undo() { if (before == null) throw new IllegalStateException("not executed"); editor.text(before); }
    }
    public static final class Invoker {
        private final Deque<Command> history = new ArrayDeque<>();
        public void execute(Command command) { command.execute(); history.push(command); }
        public boolean undo() { if (history.isEmpty()) return false; history.peek().undo(); history.pop(); return true; }
    }
    public static String demo() {
        Editor e = new Editor(); Invoker i = new Invoker(); i.execute(new Append(e, "hello")); i.execute(new Append(e, " world"));
        String before = e.text(); i.undo(); return before + " -> undo -> " + e.text();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
