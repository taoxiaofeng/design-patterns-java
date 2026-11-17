# 07 · 封装变化

来源状态：**试读正文**（`BODY`）  
原书目录起始页：**38**；定位证据：PDF 21-24；书内 38-41。

PDF 正文提供了这一节的概念基础。下文是独立撰写的中文学习笔记，Java 场景和代码均为原创，非逐字翻译或原书代码。

## 学习目标与关键概念

将变化频繁的计费规则抽出，让稳定的订单合计流程依赖规则接口。

## 示例角色与阅读路线

Order 负责商品小计，FeePolicy 隔离可变费用。这里采用原创配送费场景，不复刻 PDF 税率例子。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `EncapsulateVariationTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c07`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run encapsulate-variation
java -jar target/design-patterns-lab.jar --notes 7
java -cp target/design-patterns-lab.jar study.chapters.c07.EncapsulateVariation
mvn -Dtest=EncapsulateVariationTest test
```

也可在 IDE 中直接运行 `EncapsulateVariation.main()` 或右键运行 `EncapsulateVariationTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 新增按地区配置的费用策略，缺失地区时明确报错。
2. 以空订单、固定费用、免配送三种情形测试总额。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

抽出方法适合小变化；只有规则独立演化时再抽成对象。示例金额单位为分，不涉及真实税务规则。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
