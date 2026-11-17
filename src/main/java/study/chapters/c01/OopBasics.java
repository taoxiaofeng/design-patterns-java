package study.chapters.c01;



/** OOP 基础：对象、类与继承：原创教学实现，来源范围见本章中文笔记。 */
public final class OopBasics {
    public abstract static class Animal {
        private final String name;
        protected Animal(String name) {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
            this.name = name;
        }
        public String name() { return name; }
        public abstract String speak();
    }
    public static final class Cat extends Animal {
        private int energy;
        public Cat(String name) { super(name); }
        public void feed(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("amount");
            energy += amount;
        }
        public int energy() { return energy; }
        @Override public String speak() { return name() + ": meow"; }
    }
    public static final class Dog extends Animal {
        public Dog(String name) { super(name); }
        @Override public String speak() { return name() + ": woof"; }
    }
    public static String demo() {
        Cat cat = new Cat("Mimi"); cat.feed(3);
        return cat.speak() + ", energy=" + cat.energy() + "; " + new Dog("Dou").speak();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
