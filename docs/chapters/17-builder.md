# 17 · 生成器 Builder

来源状态：**仅简短概述，章节正文缺失**（`OVERVIEW_ONLY`）  
原书目录起始页：**105**；定位证据：概述 PDF 28；书内 73。

PDF 只提供一段创建型模式概述，未提供本模式的章节正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

把复杂对象构造拆成步骤，让相同的构造流程作用于不同生成器，得到不同表示。

## 示例角色与阅读路线

BuildSteps 是 Builder；MealBuilder 和 MenuBuilder 是具体生成器；Director 组织步骤；Meal 与字符串是不同产品。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `BuilderPatternTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c17`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run builder
java -jar target/design-patterns-lab.jar --notes 17
java -cp target/design-patterns-lab.jar study.chapters.c17.BuilderPattern
mvn -Dtest=BuilderPatternTest test
```

也可在 IDE 中直接运行 `BuilderPattern.main()` 或右键运行 `BuilderPatternTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 给 BuildSteps 增加 dessert，让套餐与菜单两种表示同步变化。
2. 新增儿童套餐构造流程，验证不会污染先前生成的产品。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

Director 可选，但在此用于展示流程复用。区别于只串联 setter 的写法，本例同一流程会生成两种表示。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
