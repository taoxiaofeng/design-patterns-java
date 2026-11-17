package study.chapters.c31;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/** 备忘录 Memento：原创教学实现，来源范围见本章中文笔记。 */
public final class MementoPattern {
    public static final class Snapshot {
        private final Editor owner; private final String text;
        private Snapshot(Editor owner, String text) { this.owner = owner; this.text = text; }
    }
    public static final class Editor {
        private String text = "";
        public void write(String text) { this.text = Objects.requireNonNull(text); }
        public String text() { return text; }
        public Snapshot save() { return new Snapshot(this, text); }
        public void restore(Snapshot snapshot) {
            if (snapshot.owner != this) throw new IllegalArgumentException("foreign snapshot");
            text = snapshot.text;
        }
    }
    public static final class History {
        private final Editor editor; private final Deque<Snapshot> snapshots = new ArrayDeque<>();
        public History(Editor editor) { this.editor = editor; }
        public void checkpoint() { snapshots.push(editor.save()); }
        public boolean undo() { if (snapshots.isEmpty()) return false; editor.restore(snapshots.pop()); return true; }
    }
    public static String demo() {
        Editor e = new Editor(); History h = new History(e); e.write("draft"); h.checkpoint(); e.write("edited"); h.undo(); return e.text();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
