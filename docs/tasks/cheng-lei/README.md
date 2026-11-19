# 程磊的开发任务

在 `程磊` 分支开发。先读[架构](../../architecture.md)与[Java 接口](../../api.md)，依次完成下列文件开头的 TODO。

1. [SpringCanvas.kt](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringCanvas.kt) 画固定端、弹簧、重物和平衡位置参考线。先传入正、零、负三种位移的采样检查绘制。
2. [SpringLabScreen.kt](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/SpringLabScreen.kt) 增加参数输入、控制、状态显示与帧循环。先用测试采样验证界面，Java 实现合入后连接控制器完成联调。
3. [DisplacementChart.kt](../../../desktopApp/src/main/kotlin/io/github/chenglei6arch/springmotionlab/desktop/DisplacementChart.kt) 在单弹簧振动跑通后加位移–时间曲线。两组阻尼的截图使用同一坐标尺度。
4. 联调后与阿卜杜热合曼一起演示调参、运行、暂停、继续、复位、自然停止，再核对 JAR 的启动方式。

```powershell
.\gradlew.bat :desktopApp:run
.\gradlew.bat :desktopApp:packageUberJarForCurrentOS
```

交付物为能显示完整振动过程的桌面 UI、可复现的曲线对照，以及与 Java 模块联调后的运行结果。
