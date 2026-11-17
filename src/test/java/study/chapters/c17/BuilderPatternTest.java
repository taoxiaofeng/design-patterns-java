package study.chapters.c17;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c17.BuilderPattern.*;

class BuilderPatternTest {
    @Test void sameStepsProduceDifferentRepresentations() {
        Director d = new Director(); MealBuilder meal = new MealBuilder(); MenuBuilder menu = new MenuBuilder();
        d.lunch(meal); d.lunch(menu); assertEquals(new Meal("rice", "tea"), meal.result());
        assertEquals("main=rice,drink=tea", menu.result());
    }
    @Test void resetDoesNotMutateBuiltProduct() {
        MealBuilder b = new MealBuilder(); new Director().lunch(b); Meal previous = b.result();
        b.reset(); b.main("bread"); b.drink("water"); assertEquals("rice", previous.main()); assertEquals("bread", b.result().main());
    }
    @Test void incompleteProductIsRejected() { assertThrows(IllegalStateException.class, () -> new MealBuilder().result()); }
}
