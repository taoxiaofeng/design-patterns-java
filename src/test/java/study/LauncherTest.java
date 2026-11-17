package study;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Modifier;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class LauncherTest {
    record Result(int status, String out, String err) {}
    Result invoke(String input, String... args) {
        var output = new ByteArrayOutputStream(); var error = new ByteArrayOutputStream();
        int status = Launcher.run(args, new StringReader(input), new PrintStream(output, true, StandardCharsets.UTF_8), new PrintStream(error, true, StandardCharsets.UTF_8));
        return new Result(status, output.toString(StandardCharsets.UTF_8), error.toString(StandardCharsets.UTF_8));
    }
    @Test void catalogHasUniqueIdsAndExpectedGroupSizes() {
        assertEquals(36, Catalog.CHAPTERS.size()); assertEquals(36, Catalog.CHAPTERS.stream().map(Catalog.Chapter::slug).distinct().count());
        assertEquals(5, Catalog.CHAPTERS.stream().filter(c -> c.group().equals("creational")).count());
        assertEquals(7, Catalog.CHAPTERS.stream().filter(c -> c.group().equals("structural")).count());
        assertEquals(10, Catalog.CHAPTERS.stream().filter(c -> c.group().equals("behavioral")).count());
        for (int i = 1; i <= 36; i++) assertEquals(i, Catalog.CHAPTERS.get(i - 1).number());
    }
    @Test void everyChapterHasNotesDemoAndIndependentMain() throws Exception {
        for (Catalog.Chapter c : Catalog.CHAPTERS) {
            Class<?> type = Class.forName(c.className());
            assertTrue(Modifier.isStatic(type.getMethod("main", String[].class).getModifiers()));
            assertNotNull(type.getMethod("demo").invoke(null));
            try (InputStream in = Launcher.class.getResourceAsStream(c.resource())) {
                assertNotNull(in, c.resource()); String notes = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                assertTrue(notes.contains("练习任务")); assertTrue(notes.contains(c.source()));
            }
        }
    }
    @Test void numericAndSlugSelectorsAgree() {
        assertEquals(invoke("", "15").out(), invoke("", "--run", "factory-method").out());
        assertTrue(invoke("", "01").out().contains("OOP 基础"));
    }
    @Test void notesComeFromBundledResource() {
        Result r = invoke("", "--notes", "15"); assertEquals(0, r.status()); assertTrue(r.out().contains("工厂方法"));
        assertTrue(r.out().contains("试读正文")); assertTrue(r.out().contains("练习任务"));
    }
    @Test void listCanBeFiltered() {
        Result r = invoke("", "--list", "creational"); assertEquals(0, r.status());
        assertEquals(5, r.out().lines().count()); assertFalse(r.out().contains("adapter"));
    }
    @Test void allChaptersExecute() {
        Result r = invoke("", "--all"); assertEquals(0, r.status()); assertTrue(r.err().isEmpty());
        assertEquals(36, r.out().lines().filter(s -> s.matches("\\[\\d{2}].*")).count());
    }
    @Test void invalidSelectionReturnsNonzero() {
        for (String selector : List.of("0", "37", "-1", "99999999999999999", "missing")) {
            Result r = invoke("", selector); assertEquals(2, r.status()); assertTrue(r.err().contains("未知章节"));
        }
    }
    @Test void missingAndExtraArgumentsAreRejected() {
        assertEquals(2, invoke("", "--run").status()); assertEquals(2, invoke("", "--notes", "15", "extra").status());
        assertEquals(2, invoke("", "--all", "extra").status()); assertEquals(2, invoke("", "--list", "missing").status());
    }
    @Test void interactiveMenuContinuesAfterInvalidInput() {
        Result r = invoke("wrong\n15\nnotes 34\nlist solid\nq\n"); assertEquals(0, r.status());
        assertTrue(r.err().contains("未知章节")); assertTrue(r.out().contains("TEXT:hello")); assertTrue(r.out().contains("策略 Strategy"));
    }
    @Test void eofAndBlankInputExitCleanly() { assertEquals(0, invoke("\n\n").status()); }
    @Test void helpAndCaseInsensitiveSelectorsWork() {
        assertTrue(invoke("", "--help").out().contains("分组")); assertEquals(0, invoke("", "STRATEGY").status());
    }
    @Test void bodyCoverageIsNotConfusedWithOverviewOrToc() {
        assertEquals(4, Catalog.CHAPTERS.stream().filter(c -> c.source().equals("试读正文")).count());
        assertEquals(4, Catalog.CHAPTERS.stream().filter(c -> c.source().startsWith("仅简短概述")).count());
        assertEquals(28, Catalog.CHAPTERS.stream().filter(c -> c.source().startsWith("仅目录")).count());
    }
}
