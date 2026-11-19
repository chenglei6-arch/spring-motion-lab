# 朱禹行的开发任务

在 `朱禹行` 分支开发。只需要编辑下列两个 Java 文件。允许的参数范围与状态切换规则见[Java 接口](../../api.md)。

1. [SpringParameters.java](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SpringParameters.java) 在 record 构造器里校验参数。用 `Double.isFinite()` 和已有边界常量检查数值，错误消息写清参数和范围。
2. [SimulationController.java](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationController.java) 按接口文档补完开始、暂停、复位和单步推进。复位换一个新模拟器，使模拟时间、速度和连续静止计数归零。

计算模块开发期间，可以把下面的测试替身传给控制器。它每次让时间增加一个步长，让你检查暂停前后和复位后的快照。

```java
MotionIntegrator testIntegrator = (parameters, current, dt) ->
        new MotionSample(current.timeSeconds() + dt, current.displacementMeters(), 0.1);
var controller = new SimulationController(SpringParameters.defaults(), testIntegrator);
```

```powershell
.\gradlew.bat :simulation-core:test
```

验收时检查非法参数被拒绝，暂停期间 `tick()` 保持快照，继续后时间前进，复位可切换到新初始位移。静止状态随计算模块合入后联调。
