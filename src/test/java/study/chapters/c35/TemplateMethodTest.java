package study.chapters.c35;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c35.TemplateMethod.*;

class TemplateMethodTest {
    @Test void skeletonNormalizesThenParses() { assertEquals(List.of("a", "b"), new CsvImporter().run(" a, b ")); }
    @Test void supportsEmptyInputAndPreservesEmptyField() {
        assertEquals(List.of(), new CsvImporter().run(" ")); assertEquals(List.of("a", ""), new CsvImporter().run("a,"));
    }
    @Test void hookRunsAfterParsingWithImmutableResult() {
        List<String> log = new ArrayList<>(); Importer i = new Importer() {
            protected List<String> parse(String s) { log.add("parse:" + s); return List.of(s); }
            protected void after(List<String> values) { log.add("after:" + values.get(0)); assertThrows(UnsupportedOperationException.class, () -> values.add("x")); }
        };
        i.run(" x "); assertEquals(List.of("parse:x", "after:x"), log);
    }
}
