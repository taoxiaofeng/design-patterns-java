package study.chapters.c02;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static study.chapters.c02.OopPillars.*;

class OopPillarsTest {
    @Test void encapsulatesBalance() {
        Account account = new Savings(); account.deposit(10); account.deposit(20);
        assertEquals(30, account.balance());
    }
    @Test void rejectsInvalidDepositWithoutMutation() {
        Account account = new Savings(); account.deposit(5);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0)); assertEquals(5, account.balance());
    }
    @Test void acceptsAnotherSubtype() {
        Account account = new Account() { public String label() { return "current"; } };
        account.deposit(8); assertEquals("current:8", describe(account));
    }
}
