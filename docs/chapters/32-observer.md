# 32 · 观察者 Observer

来源状态：**仅目录，章节正文缺失**（`TOC_ONLY`）  
原书目录起始页：**336**；定位证据：仅见 PDF 5-6 的目录条目；正文未包含。

PDF 仅在目录中列出这一节，未提供对应正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

发布者维护订阅者集合，事件发生时通知订阅者，运行时允许订阅与取消。

## 示例角色与阅读路线

News 是发布者；Listener 是观察者契约；subscribe 和 unsubscribe 控制关系。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `ObserverPatternTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c32`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run observer
java -jar target/design-patterns-lab.jar --notes 32
java -cp target/design-patterns-lab.jar study.chapters.c32.ObserverPattern
mvn -Dtest=ObserverPatternTest test
```

也可在 IDE 中直接运行 `ObserverPattern.main()` 或右键运行 `ObserverPatternTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 增加单次订阅，测试它在回调期间取消订阅时的语义。
2. 设计并实现异常隔离策略，验证一个订阅者失败不影响其他订阅者。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

示例同步通知、去重订阅，并采用订阅快照迭代；回调异常直接传播并停止当次后续通知，不是可靠消息系统。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
