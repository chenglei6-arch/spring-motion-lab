# 阿卜杜热合曼的开发任务

在 `阿卜杜热合曼` 分支开发。工程已配置 JUnit 和桌面打包。

1. [SimulationAcceptanceTest.java](../../../simulation-core/src/test/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationAcceptanceTest.java) 按文件开头的 TODO 添加数值首步、静止检测、参数校验和状态切换用例。用可预测的输入写断言；对应实现合入后执行全部用例。
2. [验收与交付](../../acceptance.md) 按相同的质量、弹力系数和初始位移运行两组阻尼，记录进入静止的时间及图像。补上基本功能验收结果。
3. 在 Windows 答辩环境的同类平台生成 JAR，按验收文档在另一台装有 JDK 21 的机器启动。记录执行命令、系统架构、JAR 名称和结果。

```powershell
.\gradlew.bat :simulation-core:test
.\gradlew.bat :desktopApp:packageUberJarForCurrentOS
```

交付物为自动化用例、实验记录和可复查的运行证据。报告里可以用“经过平衡点时仍有速度”这一用例解释为什么要采用双阈值。
