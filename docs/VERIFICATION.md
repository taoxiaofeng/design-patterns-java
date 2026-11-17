# 构建与运行验证

验证日期：2026-11-17（Asia/Shanghai）。该记录描述交付时实际执行的验证，不是未经运行的建议。

## 环境与结果

- Windows 11 amd64，Eclipse Temurin JDK 17.0.21，Apache Maven 3.9.9。
- 执行 Maven `clean verify`，启用离线模式并指定工作目录内的依赖缓存，结果 **BUILD SUCCESS**。
- 首次受限执行环境无法让 Java 读取 JAR 的真实路径，正式验证改在允许读取依赖的本机进程运行；未改变源码以绕过测试。
- 38 个主源码文件，37 个测试源码文件，JUnit 5.11.4：**120 项测试，0 失败，0 错误，0 跳过**。
- 章节基线测试 108 项（36 × 3），菜单/目录/资源/来源完整性测试 12 项。
- 单独启动了 **36 个章节 main**，均以 0 退出；`--all` 也成功运行全部 36 章。
- 每章 `--notes` 均可读取内嵌中文笔记，且与 `docs/chapters` 的内容一致。
- 验证互动输入：错误命令后继续运行、选择 15、阅读 34 章笔记、按 SOLID 分组、退出、EOF。
- 验证非法章节及缺失参数：命令行退出码为 2；互动模式错误输入后可继续。
- Windows `run.cmd --list solid` 实际通过；Linux/macOS 脚本仅静态检查，未声称在其他操作系统测试。
- JAR 主类为 `study.Launcher`，class 文件主版本 **61**（Java 17）；36 份笔记全部嵌入，未捆绑测试依赖。

构建命令的等价常规形式为 `mvn clean verify`。本机实际验证使用 `-o -B` 与 `-Dmaven.repo.local=<验证缓存>`，
没有验证首次联网下载；读者需要 Maven、可用网络或匹配版本的本地缓存。项目运行本身不联网。

## 可复查证据

- `verification/maven-build.log`：最后一次成功构建日志，项目绝对路径已替换为占位符。
- `verification/junit-summary.json`：从本次 Surefire XML 汇总的 37 个测试类结果。
- `verification/all-chapters-output.txt`：菜单运行全部章节的输出。
- `verification/independent-main-results.json`：36 次独立进程的退出码与输出。
- `verification/interactive-output.txt`：互动菜单输出，含预期的错误输入提示。
- `verification/windows-script-output.txt`：Windows 便捷脚本结果。
- `verification/files.sha256`：ZIP 中交付文件的 SHA-256（不含该清单自身）。

JAR SHA-256：`88ab1f535e8a38679f362679685b208155e9cf0e1b725fe2da2ba665e639eef0`。

## 交付边界

ZIP 包含源码、JUnit 测试、中文文档、章节元数据、脚本、上述验证证据以及可直接运行的 JAR。
未附 Maven 依赖缓存、编译临时文件、XML 中的本机系统属性或原 PDF。
示例是内存教学程序；并发、事务、完整格式解析等简化均在相应章节说明。
基线实现已完成；章节中的扩展任务是学习作业，不是构建缺口。
