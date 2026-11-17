package study.chapters.c30;

import java.util.ArrayList;
import java.util.List;

/** 中介者 Mediator：原创教学实现，来源范围见本章中文笔记。 */
public final class MediatorPattern {
    public static final class ChatRoom {
        private final List<User> users = new ArrayList<>();
        public User join(String name) { User user = new User(name, this); users.add(user); return user; }
        private void send(User sender, String text) {
            for (User user : users) if (user != sender) user.inbox.add(sender.name + ":" + text);
        }
    }
    public static final class User {
        private final String name; private final ChatRoom room;
        private final List<String> inbox = new ArrayList<>();
        private User(String name, ChatRoom room) { this.name = name; this.room = room; }
        public void send(String text) { room.send(this, text); }
        public List<String> inbox() { return List.copyOf(inbox); }
    }
    public static String demo() {
        ChatRoom r = new ChatRoom(); User a = r.join("Lin"); User b = r.join("Qin"); a.send("hello"); return b.inbox().toString();
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
