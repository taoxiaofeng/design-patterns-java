package study.chapters.c22;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c22.CompositePattern.*;

class CompositePatternTest {
    @Test void recursivelyTreatsLeavesAndBranchesUniformly() {
        Node tree = new Box(List.of(new Item("a", 10), new Box(List.of(new Item("b", 20), new Box(List.of(new Item("c", 30)))))));
        assertEquals(60, tree.price());
    }
    @Test void emptyCompositeHasIdentityValue() { assertEquals(0, new Box(List.of()).price()); }
    @Test void inputListCannotChangeTree() {
        List<Node> items = new ArrayList<>(List.of(new Item("a", 10))); Box box = new Box(items); items.clear(); assertEquals(10, box.price());
    }
}
