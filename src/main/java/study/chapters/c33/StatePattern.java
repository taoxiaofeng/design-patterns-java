package study.chapters.c33;



/** 状态 State：原创教学实现，来源范围见本章中文笔记。 */
public final class StatePattern {
    public interface Mode { String name(); void press(Player player); }
    public static final class Paused implements Mode {
        public String name() { return "paused"; } public void press(Player p) { p.change(new Playing()); }
    }
    public static final class Playing implements Mode {
        public String name() { return "playing"; } public void press(Player p) { p.change(new Paused()); }
    }
    public static final class Stopped implements Mode {
        public String name() { return "stopped"; } public void press(Player p) { p.change(new Playing()); }
    }
    public static final class Player {
        private Mode mode = new Stopped();
        private void change(Mode mode) { this.mode = mode; }
        public void press() { mode.press(this); }
        public void stop() { change(new Stopped()); }
        public String state() { return mode.name(); }
    }
    public static String demo() {
        Player p = new Player(); String initial = p.state(); p.press(); String active = p.state(); p.press();
        return initial + " -> " + active + " -> " + p.state();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
