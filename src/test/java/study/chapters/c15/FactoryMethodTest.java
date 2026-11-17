package study.chapters.c15;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c15.FactoryMethod.*;

class FactoryMethodTest {
    @Test void creatorsVaryProductAndShareWorkflow() {
        assertEquals("TEXT:hi", new TextExporter().export(" hi "));
        assertEquals("<p>hi</p>", new HtmlExporter().export(" hi "));
    }
    @Test void newCreatorExtendsWithoutChangingWorkflow() {
        Exporter upper = new Exporter() { protected Writer createWriter() { return String::toUpperCase; } };
        assertEquals("HELLO", upper.export(" hello "));
    }
    @Test void htmlProductEscapesInput() { assertEquals("<p>&lt;&amp;&gt;</p>", new HtmlExporter().export("<&>")); }
}
