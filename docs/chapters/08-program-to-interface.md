# 08 · 面向接口编程

来源状态：**仅目录，章节正文缺失**（`TOC_ONLY`）  
原书目录起始页：**42**；定位证据：仅见 PDF 5-6 的目录条目；正文未包含。

PDF 仅在目录中列出这一节，未提供对应正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

让上层依赖需要的行为契约，通过构造器选择实现。

## 示例角色与阅读路线

MessageService 依赖 Sender，MemorySender 是可观察的内存实现，可替换为任何满足接口的发送器。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `ProgramToInterfaceTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c08`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run program-to-interface
java -jar target/design-patterns-lab.jar --notes 8
java -cp target/design-patterns-lab.jar study.chapters.c08.ProgramToInterface
mvn -Dtest=ProgramToInterfaceTest test
```

也可在 IDE 中直接运行 `ProgramToInterface.main()` 或右键运行 `ProgramToInterfaceTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 增加带前缀的 Sender，实现同一服务的替换运行。
2. 定义失败语义，给发送失败场景增加测试。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

契约不仅是方法签名，还包含错误、顺序等语义；接口并不意味着必须为每个类创建一套抽象。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
