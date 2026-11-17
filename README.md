# Java 17 设计模式学习项目

基于用户提供的《Dive Into Design Patterns》英文试读 PDF 目录建立的原创 Java 学习项目。
36 章独立运行：OOP 3 章、模式导论 2 章、设计原则与 SOLID 9 章、22 个模式。
每章包含中文笔记、两项具体扩展练习、完整示例和 JUnit 5 行为测试；菜单支持反复选择、分组查看和内嵌笔记。

**来源边界：4 章有试读正文，4 章仅有简短概述，28 章仅见目录。所有 Java 代码、任务与测试均为原创。**
详见 [PDF 来源核验](docs/SOURCE_MAP.md)；未附原 PDF，也未假称读过缺失正文。

## 立即运行

解压 ZIP 后进入包含 `pom.xml` 的 `design-patterns-java17` 目录。
需要 **JDK 17**（不是只有 JRE）和 **Maven 3.9+**。若 Java 版本不对，请检查 JAVA_HOME 与 PATH。

```sh
java -version
mvn -version
mvn clean verify
java -jar target/design-patterns-lab.jar
```

ZIP 附带已经验证的 `target/design-patterns-lab.jar`。直接体验菜单只需 Java 17；修改源码后请重新构建再运行。
运行 JAR 不需要 JUnit 或任何第三方运行时依赖。首次 Maven 构建通常需要网络下载依赖；ZIP 不含 Maven 本体、wrapper 或依赖缓存。

Windows 可双击 `run.cmd` 或执行 `run.cmd 15`；macOS/Linux 使用 `sh run.sh`。
脚本会切换到项目目录，Windows 脚本会设置 UTF-8。终端手动运行时，如中文乱码请先执行 `chcp 65001`，
再使用 `java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -jar target/design-patterns-lab.jar`。

## 选择章节

```sh
java -jar target/design-patterns-lab.jar --list
java -jar target/design-patterns-lab.jar --list solid
java -jar target/design-patterns-lab.jar --run 15
java -jar target/design-patterns-lab.jar factory-method
java -jar target/design-patterns-lab.jar --notes 15
java -jar target/design-patterns-lab.jar --all
```

无参数进入互动菜单，输入 `15` 或 `factory-method` 运行工厂方法；输入 `notes 15` 查看笔记与任务，
输入 `list behavioral` 查看行为型目录，输入 `q`、`quit`、`exit` 或 `0` 退出。输入有误会提示并继续选择，文件输入结束会正常退出。
分组标识：`oop`、`intro`、`principles`、`solid`、`creational`、`structural`、`behavioral`。
命令行非法参数返回 2，执行失败返回 1，成功返回 0。

## 独立运行与单章测试

每章都是独立包，不依赖 Launcher 或其他章节。以第 15 章为例：

```sh
java -cp target/design-patterns-lab.jar study.chapters.c15.FactoryMethod
mvn -Dtest=FactoryMethodTest test
mvn test
```

IDEA / Eclipse / VS Code：将根目录导入为 Maven 项目，项目 SDK 设为 17，源码编码设为 UTF-8；
直接运行 `study.Launcher` 或任意 `study.chapters.cXX` 中的 `main`。无需额外环境变量或外部服务。

## 文件导航

- [36 章索引与笔记](docs/CHAPTERS.md)：每章具体运行命令、角色、适用边界和任务。
- [中文学习路线](docs/LEARNING_GUIDE.md)：建议顺序、相近模式比较与结课练习。
- [来源核验表](docs/SOURCE_MAP.md)：PDF 物理页码、书内页码和原创范围。
- [构建与运行验证](docs/VERIFICATION.md)：实际环境、测试数量、JAR 与打包检查。
- `src/main/java/study/chapters/c01` 至 `c36`：完整原创示例，每章一个入口。
- `src/test/java`：每章三项 JUnit 5 基线测试，以及菜单/来源/资源完整性测试。
- `src/main/resources/chapters`：JAR 中可直接读取的笔记副本，与 `docs/chapters` 保持一致。
- `chapter-manifest.json`：编号、目录页、来源标签、目标与任务的机器可读索引。

## 如何做练习

先运行示例并读测试，再做笔记中列出的两个扩展任务，为新增行为补充测试。
默认代码是可通过构建的参考答案；扩展任务是学习作业，没有未经完成的 TODO 或故意失败的测试。
修改后用本章测试验证，再执行 `mvn clean verify`；如果只执行 `mvn test`，它不会更新打包的 JAR。
示例使用整数教学数据，特定格式解析和并发能力有明确简化；请阅读各章的适用边界。

## 版本与分发

Java release 17；JUnit 5.11.4；构建插件版本固定在 pom.xml 中。未使用运行期第三方库。
本项目是独立教学材料，与原书作者无隶属或背书关系；不包含原书内容的再分发授权。
