# 06 · 良好设计：复用与可扩展性

来源状态：**试读正文**（`BODY`）  
原书目录起始页：**33**；定位证据：PDF 16-19；书内 33-36。

PDF 正文提供了这一节的概念基础。下文是独立撰写的中文学习笔记，Java 场景和代码均为原创，非逐字翻译或原书代码。

## 学习目标与关键概念

复用与扩展是目标，但模式增加的复杂度也是成本；把可变的输出方式从稳定业务中分离。

## 示例角色与阅读路线

Report 只计算合计；Formatter 接口复用同一份计算结果，生成不同表现。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `GoodDesignTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c06`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run good-design
java -jar target/design-patterns-lab.jar --notes 6
java -cp target/design-patterns-lab.jar study.chapters.c06.GoodDesign
mvn -Dtest=GoodDesignTest test
```

也可在 IDE 中直接运行 `GoodDesign.main()` 或右键运行 `GoodDesignTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 新增 JSON 格式，不改 Report。
2. 用包含负数和空列表的数据验证业务合计，说明应否允许负数。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

额外接口有维护成本。示例用整数和短文本聚焦依赖关系，不提供完整报表或货币系统。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
