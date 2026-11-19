/*
 * 负责人：阿卜杜热合曼
 * TODO 1：手算默认参数的前两步，用 assertEquals(expected, actual, delta) 验证。
 * TODO 2：测试经过平衡点、到达转折点，以及连续 48 步低于双阈值三种情况。
 * TODO 3：测试非法参数、暂停/继续、带新参数复位、静止后继续 tick 不推进。
 * TODO 4：记录两组阻尼的振幅衰减，演示步骤和测试证据见 docs/acceptance.md。
 * 骨架内的示例测试可直接运行，功能测试随相应实现合入后执行。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class SimulationAcceptanceTest {
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
