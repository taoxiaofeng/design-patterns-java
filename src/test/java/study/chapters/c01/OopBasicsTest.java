package study.chapters.c01;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c01.OopBasics.*;

class OopBasicsTest {
    @Test void instancesHaveIndependentState() {
        Cat first = new Cat("A"); Cat second = new Cat("B"); first.feed(3);
        assertEquals(3, first.energy()); assertEquals(0, second.energy());
    }
    @Test void dispatchesThroughBaseType() {
        List<Animal> animals = List.of(new Cat("A"), new Dog("B"));
        assertEquals(List.of("A: meow", "B: woof"), animals.stream().map(Animal::speak).toList());
    }
    @Test void protectsState() {
        Cat cat = new Cat("A"); assertThrows(IllegalArgumentException.class, () -> cat.feed(-1));
        assertEquals(0, cat.energy()); assertThrows(IllegalArgumentException.class, () -> new Cat(" "));
    }
}
