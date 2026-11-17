# 01 · OOP 基础：对象、类与继承

来源状态：**试读正文**（`BODY`）  
原书目录起始页：**8**；定位证据：PDF 9-13；书内 8-12。

PDF 正文提供了这一节的概念基础。下文是独立撰写的中文学习笔记，Java 场景和代码均为原创，非逐字翻译或原书代码。

## 学习目标与关键概念

类定义状态与行为，对象各自保存状态；子类可覆盖父类行为。

## 示例角色与阅读路线

Animal 提供名称与 speak 契约；Cat 和 Dog 覆盖行为；Cat 的能量只能通过 feed 改变。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `OopBasicsTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c01`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run oop-basics
java -jar target/design-patterns-lab.jar --notes 1
java -cp target/design-patterns-lab.jar study.chapters.c01.OopBasics
mvn -Dtest=OopBasicsTest test
```

也可在 IDE 中直接运行 `OopBasics.main()` 或右键运行 `OopBasicsTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 为 Cat 增加 play(int cost)，拒绝负数和超过当前能量的消耗。
2. 新增 Bird，放入同一个 Animal 列表，验证动态派发和各对象状态独立。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

继承表达可替换的类型关系，不应只为了复用几行代码建立层次。这里的宠物模型仅用于教学。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
