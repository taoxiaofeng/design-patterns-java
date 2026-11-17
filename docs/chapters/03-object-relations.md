# 03 · 对象关系：依赖、关联、聚合与组合

来源状态：**仅目录，章节正文缺失**（`TOC_ONLY`）  
原书目录起始页：**20**；定位证据：仅见 PDF 5-6 的目录条目；正文未包含。

PDF 仅在目录中列出这一节，未提供对应正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

关系强弱取决于所有权和生命周期，而不只取决于是否存在一个字段。

## 示例角色与阅读路线

Team 聚合外部 Member；Engine 由 Car 内部创建并拥有；Printer 只在 print 调用期间被依赖。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `ObjectRelationsTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c03`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run object-relations
java -jar target/design-patterns-lab.jar --notes 3
java -cp target/design-patterns-lab.jar study.chapters.c03.ObjectRelations
mvn -Dtest=ObjectRelationsTest test
```

也可在 IDE 中直接运行 `ObjectRelations.main()` 或右键运行 `ObjectRelationsTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 增加 Department 与 Team 的普通关联，说明为何不能据字段就认定组合。
2. 让两个 Team 共享一个 Member，验证从一个团队移除成员不影响另一个团队。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

Java 垃圾回收不等于 UML 组合语义。组合强调建模上的独占所有权；关联是一般长期引用，聚合是弱所有权的整体/部分关系。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
