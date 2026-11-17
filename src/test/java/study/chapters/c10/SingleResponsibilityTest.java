package study.chapters.c10;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c10.SingleResponsibility.*;

class SingleResponsibilityTest {
    @Test void calculationHasNoStorageDependency() { assertEquals(30, new Invoice(List.of(10, 20)).total()); }
    @Test void formattingDoesNotMutateInvoice() {
        Invoice i = new Invoice(List.of(5)); assertEquals("TOTAL=5", new Renderer().render(i)); assertEquals(5, i.total());
    }
    @Test void archiveStoresRenderedResult() {
        MemoryArchive a = new MemoryArchive(); a.save(new Renderer().render(new Invoice(List.of())));
        assertEquals(List.of("TOTAL=0"), a.records());
    }
}
