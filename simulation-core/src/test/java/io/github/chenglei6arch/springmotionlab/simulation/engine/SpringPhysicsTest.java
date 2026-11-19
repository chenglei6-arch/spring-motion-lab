/*
 * 负责人为阿卜杜热合曼。
 * TODO 1 使用默认参数，检查积分器前两步的时间、位移和速度。
 * TODO 2 分别验证经过平衡点、到达转折点，以及连续达标后进入静止。
 * 最后一个样本应达到 RestDetector.REQUIRED_STEPS，前一个样本应仍未静止。
 */
package io.github.chenglei6arch.springmotionlab.simulation.engine;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 弹簧仿真引擎测试类。
 * 用独立样本测试运动积分和静止判定，无需启动界面。
 */
public final class SpringPhysicsTest {
    /**
     * 验证换算得到的步数既覆盖规定时长，又是满足时长的最小整数步数。
     */
    @Test
    public void restWindowMatchesFixedStep() {
        double step = SpringSimulator.STEP_SECONDS;
        double duration = RestDetector.REST_DURATION_MILLIS / 1_000.0;
        int steps = RestDetector.REQUIRED_STEPS;
        double roundingTolerance = 1e-12;

        assertTrue(steps * step + roundingTolerance >= duration);
        assertTrue((steps - 1) * step < duration - roundingTolerance);
    }
}
