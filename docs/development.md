# 开发与命名规范

## 开工步骤

1. 安装 JDK 21，在 IntelliJ IDEA 打开根目录，等待 Gradle 同步。
2. 没有本地副本时从 GitHub 克隆。已有副本时执行 `git fetch origin`。
3. 运行 `git switch --track origin/你的中文姓名` 建立本地跟踪分支。如果本地同名分支已经存在，运行 `git switch 你的中文姓名`。
4. 读个人 README，进入列明的文件，根据文件顶部 TODO 顺序完成。多个成员共用的类型和接口从 `main` 继承。
5. 可先写小测试或用控制台检查。有上游依赖时，用假实现验证自己的部分，等上游合入 `main` 后同步再做完整联调。
6. 提交时用能说明改动的消息，例如 `feat: implement spring integration`。执行 `git push`，需要合并时在 GitHub 创建指向 `main` 的 Pull Request。接口改动在合并前和调用方一起确认。

## 命名与格式

| 对象 | 约定 | 示例 |
|---|---|---|
| Java 和 Kotlin 包 | 全小写，无下划线 | `io.github.chenglei6arch.springmotionlab.simulation.model` |
| Java 类、接口、record、枚举 | PascalCase，顶级类型与文件名一致 | `MotionIntegrator.java` |
| Kotlin 文件、类和 Compose 组件 | PascalCase 和职责匹配 | `SpringCanvas.kt`、`SpringCanvas()` |
| 方法、变量 | lowerCamelCase | `initialDisplacementMeters`、`isSettled()` |
| Java 常量、枚举项 | UPPER_SNAKE_CASE | `STEP_SECONDS`、`RUNNING` |
| 测试方法 | lowerCamelCase，描述观察到的行为 | `controllerStartsAtConfiguredDisplacement` |
| 个人分支 | 使用成员中文姓名 | `程磊` |

使用 UTF-8、4 空格缩进。物理量字段带单位后缀，公式片段可以使用 `x`、`v`、`dt`。公开接口改动同步更新[接口文档](api.md)。源码注释可以用中文，界面文案使用中文。

## 常用命令

以下命令以 PowerShell 为例。macOS 或 Linux 使用 `./gradlew`。

```powershell
.\gradlew.bat :simulation-core:test
.\gradlew.bat :desktopApp:run
.\gradlew.bat :desktopApp:packageUberJarForCurrentOS
```

测试报告在 `simulation-core/build/reports/tests/test/index.html`。工程骨架自带初始状态和静止时长换算测试。功能用例按个人 README 增加，关联 TODO 完成后再做完整验收。组内交付步骤见[验收文档](acceptance.md)。
