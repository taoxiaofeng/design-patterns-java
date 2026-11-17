# 19 · 单例 Singleton

来源状态：**仅简短概述，章节正文缺失**（`OVERVIEW_ONLY`）  
原书目录起始页：**138**；定位证据：概述 PDF 28；书内 73。

PDF 只提供一段创建型模式概述，未提供本模式的章节正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

限制某个类的实例数量，并提供统一访问入口。

## 示例角色与阅读路线

Configuration 使用枚举常量 INSTANCE 作为唯一入口，只保存不可变的学习配置。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `SingletonPatternTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c19`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run singleton
java -jar target/design-patterns-lab.jar --notes 19
java -cp target/design-patterns-lab.jar study.chapters.c19.SingletonPattern
mvn -Dtest=SingletonPatternTest test
```

也可在 IDE 中直接运行 `SingletonPattern.main()` 或右键运行 `SingletonPatternTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 比较枚举单例与静态内部类持有者写法。
2. 把一个依赖单例的客户改为构造器注入，解释测试上的差异。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

唯一性受类加载器边界限制；单例不自动保证内部可变状态安全，也会带来全局依赖和测试隔离问题。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
