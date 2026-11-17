package study.chapters.c12;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c12.LiskovSubstitution.*;

class LiskovSubstitutionTest {
    @Test void subtypesSatisfySameReadContract() {
        MemoryStore memory = new MemoryStore(); memory.put("name", "A");
        for (ReadableStore s : List.of(memory, new ReadOnlySnapshot(Map.of("name", "A")))) {
            assertEquals("A", lookup(s)); assertEquals(Optional.empty(), s.get("absent"));
        }
    }
    @Test void writableContractPreservesWrittenValue() {
        WritableStore s = new MemoryStore(); s.put("x", "1"); s.put("x", "2"); assertEquals(Optional.of("2"), s.get("x"));
    }
    @Test void snapshotDoesNotShareMutableState() {
        Map<String, String> map = new HashMap<>(Map.of("name", "A")); ReadOnlySnapshot s = new ReadOnlySnapshot(map);
        map.put("name", "B"); assertEquals("A", lookup(s));
    }
}
