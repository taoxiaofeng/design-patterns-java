package study.chapters.c04;

import java.util.Objects;

/** 什么是设计模式：原创教学实现，来源范围见本章中文笔记。 */
public final class WhatIsPattern {
    public interface Rule { int apply(int amount); }
    public static final class Checkout {
        private final Rule rule;
        public Checkout(Rule rule) { this.rule = Objects.requireNonNull(rule); }
        public int total(int amount) {
            if (amount < 0) throw new IllegalArgumentException("amount");
            return rule.apply(amount);
        }
    }
    public static String demo() {
        return "same structure, different rule: " + new Checkout(x -> x).total(100)
            + " / " + new Checkout(x -> x - 10).total(100);
    }

    public static void main(String[] args) { System.out.println(demo()); }
}
