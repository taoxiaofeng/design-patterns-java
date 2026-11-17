# 15 · 工厂方法 Factory Method

来源状态：**试读正文**（`BODY`）  
原书目录起始页：**74**；定位证据：PDF 29-44；书内 74-89。

PDF 正文提供了这一节的概念基础。下文是独立撰写的中文学习笔记，Java 场景和代码均为原创，非逐字翻译或原书代码。

## 学习目标与关键概念

让创建者的业务流程调用可覆盖的工厂方法，子类决定返回哪种产品。

## 示例角色与阅读路线

Exporter 是 Creator；createWriter 是工厂方法；Writer 是 Product；TextExporter 与 HtmlExporter 是具体创建者。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `FactoryMethodTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c15`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run factory-method
java -jar target/design-patterns-lab.jar --notes 15
java -cp target/design-patterns-lab.jar study.chapters.c15.FactoryMethod
mvn -Dtest=FactoryMethodTest test
```

也可在 IDE 中直接运行 `FactoryMethod.main()` 或右键运行 `FactoryMethodTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 新增 MarkdownExporter 与相应 Writer，保持 Exporter.export 不变。
2. 加入一个复用同一 Writer 的创建者，用测试说明工厂方法不必每次 new。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

会增加创建者子类。与简单工厂的集中分支不同，本例通过覆盖方法变化产品；与抽象工厂不同，本例只创建一种产品。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
