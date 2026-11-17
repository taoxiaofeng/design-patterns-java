package study.chapters.c27;



/** 责任链 Chain of Responsibility：原创教学实现，来源范围见本章中文笔记。 */
public final class ChainOfResponsibility {
    public record Request(boolean authenticated, int amount) {}
    public abstract static class Handler {
        private final Handler next;
        protected Handler(Handler next) { this.next = next; }
        public abstract String handle(Request request);
        protected final String forward(Request request) { return next == null ? "unhandled" : next.handle(request); }
    }
    public static final class Authenticated extends Handler {
        public Authenticated(Handler next) { super(next); }
        public String handle(Request r) { return r.authenticated() ? forward(r) : "denied:auth"; }
    }
    public static final class WithinLimit extends Handler {
        public WithinLimit(Handler next) { super(next); }
        public String handle(Request r) { return r.amount() >= 0 && r.amount() <= 100 ? forward(r) : "denied:amount"; }
    }
    public static final class Approved extends Handler {
        public Approved() { super(null); }
        public String handle(Request r) { return "approved"; }
    }
    public static Handler chain() { return new Authenticated(new WithinLimit(new Approved())); }
    public static String demo() { return chain().handle(new Request(true, 50)) + " / " + chain().handle(new Request(false, 50)); }

    public static void main(String[] args) { System.out.println(demo()); }
}
