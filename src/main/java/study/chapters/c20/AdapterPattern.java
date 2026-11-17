package study.chapters.c20;

import java.util.Objects;

/** 适配器 Adapter：原创教学实现，来源范围见本章中文笔记。 */
public final class AdapterPattern {
    public interface Temperature { double celsius(); }
    public record LegacySensor(double fahrenheit) {}
    public record SensorAdapter(LegacySensor sensor) implements Temperature {
        public SensorAdapter { Objects.requireNonNull(sensor); }
        public double celsius() { return (sensor.fahrenheit() - 32) * 5 / 9; }
    }
    public static boolean freezing(Temperature temperature) { return temperature.celsius() <= 0; }
    public static String demo() { Temperature t = new SensorAdapter(new LegacySensor(32)); return "celsius=" + t.celsius() + ", freezing=" + freezing(t); }

    public static void main(String[] args) { System.out.println(demo()); }
}
