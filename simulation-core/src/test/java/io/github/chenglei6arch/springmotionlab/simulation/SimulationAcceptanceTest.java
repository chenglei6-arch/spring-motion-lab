/*
 * 负责人为阿卜杜热合曼。
 * TODO 1 用手算得到的前两步时间、位移和速度验证默认参数下的运动计算。
 * TODO 2 验证经过平衡点、到达转折点和连续 48 步达标时的静止判定。
 * TODO 3 验证参数错误、暂停继续、带新参数复位，以及静止后调用推进方法的结果。
 * TODO 4 运行两组阻尼参数，对照振幅衰减，在 docs/acceptance.md 记录结果和截图。
 * 下面的初始状态测试可直接运行，其余用例在相应功能完成后验收。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 仿真验收测试类。
 * 通过断言比较计算值和预期值，检查参数校验、运动计算、静止检测和控制流程。
 * 每个测试方法独立运行，断言失败时由测试报告指出差异。
 */
public final class SimulationAcceptanceTest {
    /**
     * 验证新建控制器处于待开始，时间和速度为零，当前位移等于参数中的初始位移。
     * 测试方法无返回值，断言用于判断是否通过。
     */
    @Test
    public void controllerStartsAtConfiguredDisplacement() {
        var parameters = SpringParameters.defaults();
        var controller = new SimulationController(parameters);

        var snapshot = controller.snapshot();

        assertEquals(SimulationStatus.READY, snapshot.status());
        assertEquals(0.0, snapshot.sample().timeSeconds(), 0.0);
        assertEquals(parameters.initialDisplacementMeters(), snapshot.sample().displacementMeters(), 0.0);
        assertEquals(0.0, snapshot.sample().velocityMetersPerSecond(), 0.0);
    }
}
