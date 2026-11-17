package study.chapters.c02;



/** OOP 四大支柱：原创教学实现，来源范围见本章中文笔记。 */
public final class OopPillars {
    public abstract static class Account {
        private int balance;
        public final void deposit(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("amount");
            balance = Math.addExact(balance, amount);
        }
        public final int balance() { return balance; }
        public abstract String label();
    }
    public static final class Savings extends Account {
        @Override public String label() { return "savings"; }
    }
    public static String describe(Account account) { return account.label() + ":" + account.balance(); }
    public static String demo() {
        Account account = new Savings(); account.deposit(100); return describe(account);
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
