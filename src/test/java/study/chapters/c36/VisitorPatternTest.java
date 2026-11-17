package study.chapters.c36;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c36.VisitorPattern.*;

class VisitorPatternTest {
    @Test void doubleDispatchSelectsCorrectOverload() {
        List<Shape> shapes = List.of(new Circle(2), new Rectangle(2, 3)); AreaVisitor area = new AreaVisitor();
        assertEquals(4 * Math.PI, shapes.get(0).accept(area), 1e-9); assertEquals(6, shapes.get(1).accept(area), 1e-9);
    }
    @Test void newOperationDoesNotRequireElementChanges() {
        Visitor<Double> perimeter = new Visitor<>() {
            public Double visit(Circle c) { return 2 * Math.PI * c.radius(); }
            public Double visit(Rectangle r) { return 2 * (r.width() + r.height()); }
        };
        assertEquals(10, new Rectangle(2, 3).accept(perimeter), 1e-9);
    }
    @Test void secondVisitorSeesConcreteTypes() {
        assertEquals("circle", new Circle(1).accept(new NameVisitor())); assertEquals("rectangle", new Rectangle(1, 2).accept(new NameVisitor()));
    }
}
