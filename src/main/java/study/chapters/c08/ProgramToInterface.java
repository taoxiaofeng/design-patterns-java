package study.chapters.c08;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** 面向接口编程：原创教学实现，来源范围见本章中文笔记。 */
public final class ProgramToInterface {
    public interface Sender { void send(String message); }
    public static final class MemorySender implements Sender {
        private final List<String> messages = new ArrayList<>();
        public void send(String message) { messages.add(message); }
        public List<String> messages() { return List.copyOf(messages); }
    }
    public static final class MessageService {
        private final Sender sender;
        public MessageService(Sender sender) { this.sender = Objects.requireNonNull(sender); }
        public void welcome(String name) { sender.send("welcome " + name); }
    }
    public static String demo() {
        MemorySender sender = new MemorySender(); new MessageService(sender).welcome("Lin");
        return sender.messages().toString();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
