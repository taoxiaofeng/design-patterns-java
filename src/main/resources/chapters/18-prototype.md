# 18 · 原型 Prototype

来源状态：**仅简短概述，章节正文缺失**（`OVERVIEW_ONLY`）  
原书目录起始页：**124**；定位证据：概述 PDF 28；书内 73。

PDF 只提供一段创建型模式概述，未提供本模式的章节正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

通过原型契约复制对象，使客户不依赖对象的具体类，并明确深浅复制边界。

## 示例角色与阅读路线

Prototype 声明 copy；Document 复制自己的可变标签列表；客户通过 Prototype 获取副本。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `PrototypePatternTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c18`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run prototype
java -jar target/design-patterns-lab.jar --notes 18
java -cp target/design-patterns-lab.jar study.chapters.c18.PrototypePattern
mvn -Dtest=PrototypePatternTest test
```

也可在 IDE 中直接运行 `PrototypePattern.main()` 或右键运行 `PrototypePatternTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 增加一个带可变附件对象的原型，写测试区分浅复制和深复制。
2. 建立按名称选择 Prototype 的注册表，复制后互不影响。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

深复制可能昂贵；循环引用和资源句柄需要专门策略。本例不使用 Java Cloneable，避免浅复制误解。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
