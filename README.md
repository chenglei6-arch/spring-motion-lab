# Spring Motion Lab

《面向对象程序设计》期末课程设计题目 13。项目用于调整弹簧参数，观察重物从起始位置运动到静止的过程。

## 从个人任务开始

| 开发分支 | 任务 README | 负责范围 |
|---|---|---|
| `程磊` | [程磊](https://github.com/chenglei6-arch/spring-motion-lab/blob/%E7%A8%8B%E7%A3%8A/docs/tasks/cheng-lei/README.md) | Compose 界面、动画、曲线和联调 |
| `钟江泽` | [钟江泽](https://github.com/chenglei6-arch/spring-motion-lab/blob/%E9%92%9F%E6%B1%9F%E6%B3%BD/docs/tasks/zhong-jiangze/README.md) | Java 运动计算与静止判定 |
| `朱禹行` | [朱禹行](https://github.com/chenglei6-arch/spring-motion-lab/blob/%E6%9C%B1%E7%A6%B9%E8%A1%8C/docs/tasks/zhu-yuxing/README.md) | Java 参数校验与仿真控制 |
| `阿卜杜热合曼` | [阿卜杜热合曼](https://github.com/chenglei6-arch/spring-motion-lab/blob/%E9%98%BF%E5%8D%9C%E6%9D%9C%E7%83%AD%E5%90%88%E6%9B%BC/docs/tasks/abudureheman/README.md) | Java 测试、对照实验与交付验收 |

每人切到同名分支，按个人 README 打开指定文件，从文件开头的 TODO 开始。当前版本提供可运行的工程骨架，运动计算、操作控件和验收用例由各负责人完成。

## 开发入口

安装 JDK 21，通过 IntelliJ IDEA 打开这个目录并把 Gradle JVM 设为 JDK 21。PowerShell 中让 `JAVA_HOME` 指向自己的 JDK 21，然后运行以下命令。

```powershell
java -version
.\gradlew.bat :simulation-core:test
.\gradlew.bat :desktopApp:run
```

macOS 或 Linux 将 `.\gradlew.bat` 换成 `./gradlew`。

- [架构和类关系](docs/architecture.md)
- [Java 接口说明与控制流程](docs/api.md)
- [开发规范和协作流程](docs/development.md)
- [验收和打包检查](docs/acceptance.md)
