# 钟江泽的开发任务

在 `钟江泽` 分支开发。只需要编辑下列两个 Java 文件。公式和运行约定见[架构](../../architecture.md)与[Java 接口](../../api.md)。

1. [SemiImplicitEulerIntegrator.java](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SemiImplicitEulerIntegrator.java) 实现 `step()`。由位移和速度算加速度，先更新速度，再更新位移，最后返回新采样。
2. [RestDetector.java](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/RestDetector.java) 添加连续步数计数。每步观察位移和速度两项，满足阈值才加一，任一项不满足就清零。计数达到 48 才判静止。

先用默认参数检查首步。初始位移为 0.20 m、初始速度为 0。一步后的位移约为 0.19977778 m，速度约为 -0.02666667 m/s。再观察长时间推进时振幅逐渐变小。可以直接在 Java 测试里调用积分器，或在 IDE 用临时控制台代码调试。

```powershell
.\gradlew.bat :simulation-core:test
```

交付物为两个实现文件。答辩时能说明计算顺序、为何同时检查位移和速度，以及为什么要连续判断。
