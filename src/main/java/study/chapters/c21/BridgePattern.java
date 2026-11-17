package study.chapters.c21;

import java.util.Objects;

/** 桥接 Bridge：原创教学实现，来源范围见本章中文笔记。 */
public final class BridgePattern {
    public interface Device { void enabled(boolean value); boolean enabled(); String name(); }
    public static final class Radio implements Device {
        private boolean on;
        public void enabled(boolean value) { on = value; } public boolean enabled() { return on; } public String name() { return "radio"; }
    }
    public static final class Television implements Device {
        private boolean on;
        public void enabled(boolean value) { on = value; } public boolean enabled() { return on; } public String name() { return "tv"; }
    }
    public static class Remote {
        protected final Device device;
        public Remote(Device device) { this.device = Objects.requireNonNull(device); }
        public void toggle() { device.enabled(!device.enabled()); }
    }
    public static final class AdvancedRemote extends Remote {
        public AdvancedRemote(Device device) { super(device); }
        public void off() { device.enabled(false); }
    }
    public static String demo() {
        Device radio = new Radio(); new Remote(radio).toggle();
        Device tv = new Television(); AdvancedRemote remote = new AdvancedRemote(tv); remote.toggle(); remote.off();
        return radio.name() + "=" + radio.enabled() + ", " + tv.name() + "=" + tv.enabled();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
