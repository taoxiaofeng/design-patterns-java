# 16 · 抽象工厂 Abstract Factory

来源状态：**仅简短概述，章节正文缺失**（`OVERVIEW_ONLY`）  
原书目录起始页：**90**；定位证据：概述 PDF 27；书内 72。

PDF 只提供一段创建型模式概述，未提供本模式的章节正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

通过一个工厂创建同一产品族中的多个相关产品，让客户依赖抽象产品。

## 示例角色与阅读路线

UiFactory 生产 Button 与 Checkbox；LightFactory 和 DarkFactory 提供一致风格的产品族；Screen 是客户。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `AbstractFactoryTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c16`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run abstract-factory
java -jar target/design-patterns-lab.jar --notes 16
java -cp target/design-patterns-lab.jar study.chapters.c16.AbstractFactory
mvn -Dtest=AbstractFactoryTest test
```

也可在 IDE 中直接运行 `AbstractFactory.main()` 或右键运行 `AbstractFactoryTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 新增 ContrastFactory，验证两个产品均采用 contrast 风格。
2. 增加 Slider 产品，记录必须修改的接口和实现。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

新增产品族方便，新增一种产品需要修改所有工厂。示例用文本模拟 UI，不启动图形窗口。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
