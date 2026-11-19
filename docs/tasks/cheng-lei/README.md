# 程磊的开发任务

你负责把 Java 仿真接到 Compose Desktop，完成参数输入、弹簧动画、控制按钮和位移–时间曲线。先做单弹簧的完整演示，再做用于答辩对照的曲线。

## 开工

用 IntelliJ IDEA 打开 `spring-motion-lab`，也就是包含 `settings.gradle.kts` 和 `gradlew.bat` 的目录。将 Gradle JVM 设为 JDK 21，然后在项目终端切到自己的分支。

```powershell
git fetch origin
git switch "程磊"
git branch --show-current
```

最后一行显示 `程磊` 就可以开始。首次切换时，Git 会根据同名远程分支建立本地分支。

## 要改的文件在哪里

下表从仓库根目录开始。可以在 IDE 左侧的 **Project** 文件树逐级展开，也可以复制完整路径定位。点表里的路径可以直接在 GitHub 查看文件开头的 TODO。

| 顺序 | 完整路径 | 修改位置 |
|---|---|---|
| 1 | [`desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringCanvas.kt`](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringCanvas.kt) | `SpringCanvas()` |
| 2 | [`desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringLabScreen.kt`](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringLabScreen.kt) | `SpringLabScreen()` |
| 3 | [`desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/DisplacementChart.kt`](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/DisplacementChart.kt) | `DisplacementChart()` |

## 1. 画弹簧和重物

在 `SpringCanvas()` 中用 Compose `Canvas` 绘制固定端、弹簧折线、重物和平衡参考线。读取 `sample.displacementMeters()`，把它按比例换成像素偏移。零位移时重物落在参考位置，正位移向下，负位移向上。让弹簧末端和重物一起移动。先用 `-0.20`、`0.0`、`0.20 m` 的三组样本检查画面，Java 算法开发期间就可以完成这一项。

## 2. 接参数和运行控制

1. 在 `SpringLabScreen()` 放弹力系数、初始位移、摩擦系数的输入框，初值为 `16.0 N/m`、`0.20 m`、`2.0 N·s/m`。读取文本后构造 `SpringParameters`，把解析或范围错误显示在界面。校验通过再调用控制器。
2. 第一次开始先调用 `reset(parameters)` 应用参数，再调用 `start()`。继续直接调用 `start()`。暂停调用 `pause()`，复位调用 `reset(parameters)`。在 `RUNNING` 和 `PAUSED` 禁用参数编辑，静止后可以编辑并复位。
3. 只在 `RUNNING` 时用 `withFrameNanos` 累加帧间隔。每积满 `SpringSimulator.STEP_SECONDS` 调用一次 `tick()`，取返回的快照刷新界面。限制单帧补步，暂停、复位或静止后结束循环，继续时重新以当前帧时间为基准。
4. 传 `snapshot.sample()` 给 `SpringCanvas()`。从同一快照取状态和模拟时间显示。确认暂停期间界面时间不变，继续从暂停点推进，复位回到所选初始位移。

窗口入口在 [`desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/Main.kt`](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/Main.kt)，Java 的方法和状态说明见 [接口文档](../../api.md)。

## 3. 加位移–时间曲线并联调

在 `SpringLabScreen()` 收集采样，复位时把记录恢复到起点。在 `DisplacementChart()` 用 `timeSeconds()` 作横轴，`displacementMeters()` 作纵轴，标明 `s` 和 `m`。空列表能显示空画布，只有一个点也能画出该点。按[验收参数](../../acceptance.md)跑两次，比较图使用相同横纵轴刻度。

## 怎么验收

```powershell
.\gradlew.bat :desktopApp:run
.\gradlew.bat :desktopApp:packageUberJarForCurrentOS
```

用默认参数从开始跑到自然静止。在另一轮中检查暂停、继续、复位和非法输入提示。两组曲线能用于答辩演示后，把改动提交到 `程磊`，并与阿卜杜热合曼核对打包后的实际运行结果。
