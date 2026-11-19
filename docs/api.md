# Java 接口

Java 类型位于 `io.github.chenglei6arch.springmotionlab.simulation` 下的 `model`、`engine`、`control` 子包。桌面入口通过 `control.SimulationController` 与核心交互，并使用 `model` 中的参数和采样。下面是组内实现约定，骨架中的 TODO 逐项完成后实现对应行为。

## 参数和返回值

| 类型或字段 | 单位与范围 | 缺省值 |
|---|---|---|
| `SpringParameters.stiffnessNewtonsPerMeter()` | N/m，闭区间 [4.0, 100.0] | 16.0 |
| `SpringParameters.initialDisplacementMeters()` | m，闭区间 [-0.30, 0.30] | 0.20 |
| `SpringParameters.dampingNewtonSecondsPerMeter()` | N·s/m，闭区间 [0.5, 12.0] | 2.0 |
| 固定质量、初始速度 | kg、m/s | 1.0、0.0 |

三个可输入参数均为有限数。`new SpringParameters(k, x0, c)` 在参数非法时抛出 `IllegalArgumentException`，消息给出参数名和允许范围。`SpringParameters.defaults()` 产生一组可观察到明显振幅衰减的起始参数。较强的阻尼也可能让重物直接回到平衡点。

`MotionSample` 通过 `timeSeconds()`、`displacementMeters()`、`velocityMetersPerSecond()` 提供数据。`SimulationSnapshot` 通过 `status()` 与 `sample()` 返回同一时刻的状态和值。Kotlin 调用这些 Java record 访问方法时保留括号。

## 桌面调用

| 方法 | 行为 |
|---|---|
| `new SimulationController(parameters)` | 使用默认积分器，初始为 `READY`，时间 0、位移 `x0`、速度 0 |
| `new SimulationController(parameters, integrator)` | 使用传入的积分器，便于单独测试控制器 |
| `double stepSeconds()` | 返回固定的模拟步长，单位为秒，供界面驱动仿真 |
| `void start()` | `READY` 或 `PAUSED` 进入 `RUNNING`。其他状态保持原样 |
| `void pause()` | `RUNNING` 进入 `PAUSED`。其他状态保持原样 |
| `void reset(SpringParameters parameters)` | 任何状态下替换参数和模拟器，时间、速度和静止计数归零，回到 `READY` |
| `SimulationSnapshot tick()` | `RUNNING` 时推进一步，首次达到静止条件后进入 `SETTLED`。所有其他状态直接返回当前快照 |
| `SimulationSnapshot snapshot()` | 返回快照，读取不会推进时间 |

桌面在同一 UI 线程调用控制器。编辑中的文本留在 Kotlin 层。首次开始前构造参数并调用 `reset(parameters)`，再调用 `start()`。继续时直接调用 `start()`。UI 在运行和暂停期间禁用参数编辑，复位时使用当前有效参数。参数无效时显示错误并保留现有运行状态。静止后可编辑参数，通过复位开启下一轮。

用 `controller.stepSeconds()` 返回的值作时间累加器的单位。每累计一个步长调用一次 `tick()`，并按返回的快照刷新界面。每次开始、继续或复位时都以新的帧时钟为基准。暂停和静止期间不积累补步时间。

## 数值积分器接口

```java
MotionSample step(SpringParameters parameters, MotionSample current, double stepSeconds);
```

- `parameters` 与 `current` 由模拟器提供，均为非空的不可变值。模拟器以 `SpringSimulator.STEP_SECONDS` 调用该方法，步长由每秒仿真步数计算。
- 实现以给定时长计算并返回新的采样，其中时间等于当前采样时间加上 `stepSeconds`。方法本身无状态，采样与静止计数由模拟器持有。
- `SpringSimulator.current()` 读取当前采样。`advance()` 推进一步，更新静止检测，并返回该步采样。已静止时直接返回最后一条采样。`isSettled()` 暴露检测结果。
- 位于 `engine` 包的测试可用 `RestDetector.observe(sample)` 输入构造的样本，再调用 `isSettled()` 检查静止条件。达标步数读取 `RestDetector.REQUIRED_STEPS`。
