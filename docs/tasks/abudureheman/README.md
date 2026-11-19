# 阿卜杜热合曼的开发任务

你负责把验收标准写成 Java 测试，再实际运行两组弹簧参数与打包产物。测试类和验收记录都已建好，可以先写测试，等对应功能合入后再核对结果。

## 开工

用 IntelliJ IDEA 打开 `spring-motion-lab`，也就是包含 `settings.gradle.kts` 和 `gradlew.bat` 的目录。将 Gradle JVM 设为 JDK 21，然后在项目终端切到自己的分支。

```powershell
git fetch origin
git switch "阿卜杜热合曼"
git branch --show-current
```

最后一行显示 `阿卜杜热合曼` 就可以开始。首次切换时，Git 会根据同名远程分支建立本地分支。

## 要改的文件在哪里

下表从仓库根目录开始。可以在 IDE 左侧的 **Project** 文件树逐级展开，也可以复制完整路径定位。点表里的路径可以直接在 GitHub 查看文件开头的 TODO。

| 顺序 | 完整路径 | 修改位置 |
|---|---|---|
| 1 | [`simulation-core/src/test/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationAcceptanceTest.java`](../../../simulation-core/src/test/java/io/github/chenglei6arch/springmotionlab/simulation/SimulationAcceptanceTest.java) | 在现有 JUnit 类里添加 `@Test` 方法 |
| 2 | [`docs/acceptance.md`](../../../docs/acceptance.md) | 填写功能验收、阻尼对照和打包验证结果 |

## 1. 写 Java 自动化测试

直接在 `SimulationAcceptanceTest.java` 的类中增加测试方法。现有测试可以作为写法示例。对小数用 `assertEquals(预期值, 实际值, 容差)`，例如数值计算可取 `1e-7`。测试在和仿真代码相同的包内，可以直接新建 `RestDetector` 并喂入样本。

| 要验证的情况 | 测试里怎么构造 | 期望结果 |
|---|---|---|
| 前两步运动 | 默认参数，初始位移 0.20 m、初速度 0，连续调用两次 `step()`，每步 `1/120 s` | 第一步位移约 0.19977778 m、速度约 -0.02666667 m/s。第二步位移约 0.19933728 m、速度约 -0.05285926 m/s |
| 经过平衡点、经过转折点 | 分别传入 `x = 0, v = 0.1` 与 `x = 0.1, v = 0` | 两种都应打断连续计数。可以在第 47 个达标样本后插入这些点，再检查计数重新开始 |
| 连续进入静止范围 | 连续传入 `x = 0.001, v = 0.003` | 第 47 次后为 `false`，第 48 次后为 `true` |
| 参数错误 | 分别传超范围值、`NaN` 和无穷大 | 构造参数时抛出 `IllegalArgumentException` |
| 暂停、继续、复位 | 开始后执行一步，暂停后再执行 `tick()`，继续后再执行一步，用新初始位移复位 | 暂停时快照不变，继续后时间增加，复位回到 `READY`、时间 0、速度 0 和新初始位移 |
| 静止后调用 `tick()` | 用静止样本持续推进到 `SETTLED`，再调用一次 | 状态和最后一个采样保持一致 |

```powershell
.\gradlew.bat :simulation-core:test
```

测试报告在 `simulation-core/build/reports/tests/test/index.html`。数值、静止和控制用例在各自实现合入 `main` 后用 `git fetch origin`、`git merge origin/main` 同步，再跑全套测试。

## 2. 填实验记录

在 `docs/acceptance.md` 的“阻尼对照”里填两次实测数据。两次都取质量 1 kg、`k = 16`、初始位移 0.20 m，初速度为 0，只把阻尼从 `1.0` 改到 `4.0 N·s/m`。每次从复位开始，记录进入静止的**模拟时间**，再对照曲线的衰减。将两张截图分别放在 `docs/images/damping-1.png` 和 `docs/images/damping-4.png`，目录可按需新建，然后在验收表内链接。

## 3. 生成 JAR 并登记验收

```powershell
.\gradlew.bat :desktopApp:packageUberJarForCurrentOS
Get-ChildItem .\desktopApp\build\compose\jars\*.jar
```

构建产物在 `desktopApp/build/compose/jars/`。拷到另一台对应平台、安装 JDK 21 的电脑，用 `java -jar "实际文件名.jar"` 启动。跑一遍调参、开始、暂停继续、复位和自然停止，将系统、架构、JDK 版本、JAR 文件名、实际结果写入 `docs/acceptance.md` 的“打包运行”。提交测试和验收记录到 `阿卜杜热合曼`。
