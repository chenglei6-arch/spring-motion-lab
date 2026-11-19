# 朱禹行的开发任务

你负责 Java 参数和按钮背后的状态。前端传进三个数值后由你检查，开始、暂停、继续、复位由控制器统一处理。可以用测试积分器先跑通自己的部分。

## 开工

用 IntelliJ IDEA 打开 `spring-motion-lab`，也就是包含 `settings.gradle.kts` 和 `gradlew.bat` 的目录。将 Gradle JVM 设为 JDK 21，然后在项目终端切到自己的分支。

```powershell
git fetch origin
git switch "朱禹行"
git branch --show-current
```

最后一行显示 `朱禹行` 就可以开始。首次切换时，Git 会根据同名远程分支建立本地分支。

## 要改的文件在哪里

下表从仓库根目录开始。可以在 IDE 左侧的 **Project** 文件树逐级展开，也可以复制完整路径定位。点表里的路径可以直接在 GitHub 查看文件开头的 TODO。

| 顺序 | 完整路径 | 修改位置 |
|---|---|---|
| 1 | [`simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SpringParameters.java`](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SpringParameters.java) | record 的紧凑构造器 `public SpringParameters { ... }` |
| 2 | [`simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationController.java`](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationController.java) | `start()`、`pause()`、`reset()`、`tick()` |

## 1. 校验三个参数

使用 `Double.isFinite()` 排除 `NaN` 和正负无穷，再用文件里已有的最小值、最大值常量判断范围。两端都允许。发现非法值时抛出 `IllegalArgumentException`，消息包含对应参数名和允许范围，界面会用这个消息提示用户。

| 参数 | 允许范围 | 自测输入 |
|---|---|---|
| 弹力系数 `k` | `4.0` 到 `100.0 N/m` | `4.0` 合法，`3.9` 应报错 |
| 初始位移 `x0` | `-0.30` 到 `0.30 m` | `-0.30` 合法，`0.31` 应报错 |
| 摩擦系数 `c` | `0.5` 到 `12.0 N·s/m` | `12.0` 合法，`0.49` 应报错 |

## 2. 完成控制器

| 方法 | 文件里要实现的行为 |
|---|---|
| `start()` | `READY` 或 `PAUSED` 时改为 `RUNNING`。继续时原来的位移和时间都保留 |
| `pause()` | `RUNNING` 时改为 `PAUSED` |
| `reset(parameters)` | 用新参数和现有 `integrator` 创建 `SpringSimulator` 并替换旧实例，再回到 `READY`。新快照的时间和速度为 0，位移为新参数中的初始位移 |
| `tick()` | `RUNNING` 时调用一次 `simulator.advance()`，随后检查 `simulator.isSettled()`，达标就切到 `SETTLED`。所有状态都返回当前快照，只有 `RUNNING` 推进时间 |

其他状态下调用 `start()` 或 `pause()` 时保持当前状态。状态常量在 [`simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationStatus.java`](../../../simulation-core/src/main/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationStatus.java)，各方法的约定见 [Java 接口](../../api.md)。

## 3. 自己跑一次状态切换

用下面的积分器创建控制器。它每次只增加时间并返回固定速度，方便你检查控制器的行为。

```java
MotionIntegrator testIntegrator = (parameters, current, dt) ->
        new MotionSample(current.timeSeconds() + dt, current.displacementMeters(), 0.1);
var controller = new SimulationController(SpringParameters.defaults(), testIntegrator);
```

按 `start → tick → pause → tick → start → tick → reset` 顺序尝试。第一个 `tick()` 让时间增加，暂停后的 `tick()` 保持快照一致，继续后的 `tick()` 再增加一步，最后用 `new SpringParameters(16.0, -0.10, 2.0)` 复位，结果回到 `READY`、时间 0、位移 -0.10 m、速度 0。

```powershell
.\gradlew.bat :simulation-core:test
```

在 `朱禹行` 分支提交两个 Java 文件。钟江泽的实现合入后，再一起确认控制器会在静止时进入 `SETTLED`。
