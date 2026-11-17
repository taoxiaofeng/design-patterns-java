package study;
import java.util.*;

/** 章节元数据是选择器和文档的对应索引，不让章节彼此依赖。 */
public final class Catalog {
    private Catalog() {}
    public record Chapter(int number, String slug, String title, String group, String source, String className) {
        public String resource() { return "/chapters/%02d-%s.md".formatted(number, slug); }
    }
    public static final List<Chapter> CHAPTERS = List.of(
        new Chapter(1, "oop-basics", "OOP 基础：对象、类与继承", "oop", "试读正文", "study.chapters.c01.OopBasics"),
    new Chapter(2, "oop-pillars", "OOP 四大支柱", "oop", "仅目录，章节正文缺失", "study.chapters.c02.OopPillars"),
    new Chapter(3, "object-relations", "对象关系：依赖、关联、聚合与组合", "oop", "仅目录，章节正文缺失", "study.chapters.c03.ObjectRelations"),
    new Chapter(4, "what-is-pattern", "什么是设计模式", "intro", "仅目录，章节正文缺失", "study.chapters.c04.WhatIsPattern"),
    new Chapter(5, "why-patterns", "为什么学习设计模式", "intro", "仅目录，章节正文缺失", "study.chapters.c05.WhyPatterns"),
    new Chapter(6, "good-design", "良好设计：复用与可扩展性", "principles", "试读正文", "study.chapters.c06.GoodDesign"),
    new Chapter(7, "encapsulate-variation", "封装变化", "principles", "试读正文", "study.chapters.c07.EncapsulateVariation"),
    new Chapter(8, "program-to-interface", "面向接口编程", "principles", "仅目录，章节正文缺失", "study.chapters.c08.ProgramToInterface"),
    new Chapter(9, "composition-over-inheritance", "组合优于继承", "principles", "仅目录，章节正文缺失", "study.chapters.c09.CompositionOverInheritance"),
    new Chapter(10, "single-responsibility", "SOLID：单一职责原则", "solid", "仅目录，章节正文缺失", "study.chapters.c10.SingleResponsibility"),
    new Chapter(11, "open-closed", "SOLID：开闭原则", "solid", "仅目录，章节正文缺失", "study.chapters.c11.OpenClosed"),
    new Chapter(12, "liskov-substitution", "SOLID：里氏替换原则", "solid", "仅目录，章节正文缺失", "study.chapters.c12.LiskovSubstitution"),
    new Chapter(13, "interface-segregation", "SOLID：接口隔离原则", "solid", "仅目录，章节正文缺失", "study.chapters.c13.InterfaceSegregation"),
    new Chapter(14, "dependency-inversion", "SOLID：依赖倒置原则", "solid", "仅目录，章节正文缺失", "study.chapters.c14.DependencyInversion"),
    new Chapter(15, "factory-method", "工厂方法 Factory Method", "creational", "试读正文", "study.chapters.c15.FactoryMethod"),
    new Chapter(16, "abstract-factory", "抽象工厂 Abstract Factory", "creational", "仅简短概述，章节正文缺失", "study.chapters.c16.AbstractFactory"),
    new Chapter(17, "builder", "生成器 Builder", "creational", "仅简短概述，章节正文缺失", "study.chapters.c17.BuilderPattern"),
    new Chapter(18, "prototype", "原型 Prototype", "creational", "仅简短概述，章节正文缺失", "study.chapters.c18.PrototypePattern"),
    new Chapter(19, "singleton", "单例 Singleton", "creational", "仅简短概述，章节正文缺失", "study.chapters.c19.SingletonPattern"),
    new Chapter(20, "adapter", "适配器 Adapter", "structural", "仅目录，章节正文缺失", "study.chapters.c20.AdapterPattern"),
    new Chapter(21, "bridge", "桥接 Bridge", "structural", "仅目录，章节正文缺失", "study.chapters.c21.BridgePattern"),
    new Chapter(22, "composite", "组合 Composite", "structural", "仅目录，章节正文缺失", "study.chapters.c22.CompositePattern"),
    new Chapter(23, "decorator", "装饰 Decorator", "structural", "仅目录，章节正文缺失", "study.chapters.c23.DecoratorPattern"),
    new Chapter(24, "facade", "外观 Facade", "structural", "仅目录，章节正文缺失", "study.chapters.c24.FacadePattern"),
    new Chapter(25, "flyweight", "享元 Flyweight", "structural", "仅目录，章节正文缺失", "study.chapters.c25.FlyweightPattern"),
    new Chapter(26, "proxy", "代理 Proxy", "structural", "仅目录，章节正文缺失", "study.chapters.c26.ProxyPattern"),
    new Chapter(27, "chain-of-responsibility", "责任链 Chain of Responsibility", "behavioral", "仅目录，章节正文缺失", "study.chapters.c27.ChainOfResponsibility"),
    new Chapter(28, "command", "命令 Command", "behavioral", "仅目录，章节正文缺失", "study.chapters.c28.CommandPattern"),
    new Chapter(29, "iterator", "迭代器 Iterator", "behavioral", "仅目录，章节正文缺失", "study.chapters.c29.IteratorPattern"),
    new Chapter(30, "mediator", "中介者 Mediator", "behavioral", "仅目录，章节正文缺失", "study.chapters.c30.MediatorPattern"),
    new Chapter(31, "memento", "备忘录 Memento", "behavioral", "仅目录，章节正文缺失", "study.chapters.c31.MementoPattern"),
    new Chapter(32, "observer", "观察者 Observer", "behavioral", "仅目录，章节正文缺失", "study.chapters.c32.ObserverPattern"),
    new Chapter(33, "state", "状态 State", "behavioral", "仅目录，章节正文缺失", "study.chapters.c33.StatePattern"),
    new Chapter(34, "strategy", "策略 Strategy", "behavioral", "仅目录，章节正文缺失", "study.chapters.c34.StrategyPattern"),
    new Chapter(35, "template-method", "模板方法 Template Method", "behavioral", "仅目录，章节正文缺失", "study.chapters.c35.TemplateMethod"),
    new Chapter(36, "visitor", "访问者 Visitor", "behavioral", "仅目录，章节正文缺失", "study.chapters.c36.VisitorPattern")
);

    public static final Set<String> GROUPS = Set.of("oop", "intro", "principles", "solid", "creational", "structural", "behavioral");
    public static Optional<Chapter> find(String selector) {
        String normalized = selector.toLowerCase(Locale.ROOT).strip();
        if (normalized.matches("[0-9]{1,2}")) {
            int number = Integer.parseInt(normalized);
            return CHAPTERS.stream().filter(c -> c.number() == number).findFirst();
        }
        return CHAPTERS.stream().filter(c -> c.slug().equals(normalized)).findFirst();
    }
}
