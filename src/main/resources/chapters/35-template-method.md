# 35 · 模板方法 Template Method

来源状态：**仅目录，章节正文缺失**（`TOC_ONLY`）  
原书目录起始页：**381**；定位证据：仅见 PDF 5-6 的目录条目；正文未包含。

PDF 仅在目录中列出这一节，未提供对应正文。以下讲解、场景、练习、实现与测试全部是扩展原创，不能视为原书该章内容。

## 学习目标与关键概念

父类固定算法骨架，将变化步骤交给子类实现或覆盖。

## 示例角色与阅读路线

Importer.run 是 final 模板方法；parse 是抽象步骤；after 是可选钩子；CsvImporter 是具体类。

先看 `demo()` 的客户调用，再沿接口进入具体实现，最后打开 `TemplateMethodTest`，将每个断言与对象协作对应起来。
本章源码包为 `study.chapters.c35`，与其他章节没有相互依赖。

## 运行与测试

在项目根目录完成 `mvn clean verify` 后执行：

```sh
java -jar target/design-patterns-lab.jar --run template-method
java -jar target/design-patterns-lab.jar --notes 35
java -cp target/design-patterns-lab.jar study.chapters.c35.TemplateMethod
mvn -Dtest=TemplateMethodTest test
```

也可在 IDE 中直接运行 `TemplateMethod.main()` 或右键运行 `TemplateMethodTest`。完整参考实现已经提供；以下任务是留给你的扩展练习，不是构建所缺的功能。

## 练习任务

1. 新增分号分隔导入器，复用 run 的顺序。
2. 让 after 做额外记录，测试它始终在 parse 后执行。
3. 为扩展补充至少一个正常情形和一个边界/失败情形的 JUnit 5 测试，再运行本章测试及全量 `mvn verify`。

## 验收与复盘

保留已有测试通过，为任务中的新增行为写明确断言。观察修改发生在哪个角色，是否迫使原本稳定的客户代码一起改变。
练习时可以暂时注释参考实现，先依据测试重写；遇到困难再回看本章源码。仅让输出看起来相同，不代表契约与状态处理正确。

## 适用边界与代价

继承会把子类绑定到父类骨架；若需要运行时替换整个算法，可考虑策略。示例 CSV 只按逗号拆分，不支持引号转义。

这些小例子用于观察设计结构；没有网络、数据库或文件写入副作用。返回代码和异常约定以源码及测试为准。
