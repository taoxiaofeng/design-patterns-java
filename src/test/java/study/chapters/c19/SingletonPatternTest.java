package study.chapters.c19;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c19.SingletonPattern.*;

class SingletonPatternTest {
    @Test void oneEnumConstantDefinesInstance() { assertEquals(1, Configuration.values().length); assertSame(Configuration.INSTANCE, Configuration.valueOf("INSTANCE")); }
    @Test void concurrentAccessReturnsSameInstance() throws Exception {
        java.util.concurrent.ExecutorService pool = java.util.concurrent.Executors.newFixedThreadPool(4);
        try {
            List<java.util.concurrent.Callable<Configuration>> tasks = new ArrayList<>();
            for (int i = 0; i < 20; i++) tasks.add(() -> Configuration.INSTANCE);
            for (var future : pool.invokeAll(tasks)) assertSame(Configuration.INSTANCE, future.get());
        } finally { pool.shutdownNow(); }
    }
    @Test void serializationKeepsEnumIdentity() throws Exception {
        var buffer = new java.io.ByteArrayOutputStream();
        try (var out = new java.io.ObjectOutputStream(buffer)) { out.writeObject(Configuration.INSTANCE); }
        try (var in = new java.io.ObjectInputStream(new java.io.ByteArrayInputStream(buffer.toByteArray()))) {
            assertSame(Configuration.INSTANCE, in.readObject());
        }
    }
}
