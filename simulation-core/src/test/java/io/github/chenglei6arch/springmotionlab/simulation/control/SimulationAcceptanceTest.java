/*
 * 负责人为阿卜杜热合曼。
 * TODO 1 验证参数错误、暂停继续、带新参数复位，以及静止后调用推进方法的结果。
 * TODO 2 运行两组阻尼参数，对照振幅衰减，在 docs/acceptance.md 记录结果和截图。
 * 下面的初始状态测试可直接运行，其余用例在相应功能完成后验收。
 */
package io.github.chenglei6arch.springmotionlab.simulation.control;

import io.github.chenglei6arch.springmotionlab.simulation.model.SpringParameters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 仿真验收测试类。
 * 通过断言比较实际结果和预期结果，检查参数校验和控制流程。
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
