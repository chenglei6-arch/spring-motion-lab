package io.github.chenglei6arch.springmotionlab.simulation.engine;

import io.github.chenglei6arch.springmotionlab.simulation.model.MotionSample;
import io.github.chenglei6arch.springmotionlab.simulation.model.SpringParameters;

import java.util.Objects;

/**
 * 弹簧仿真器。
 * 保存一轮运动的参数和最新采样。每次推进时交给积分器算一步，
 * 再交给静止检测器检查。控制器在复位时创建新的仿真器。
 */
public final class SpringSimulator {
    /** 每模拟一秒执行的计算次数。 */
    static final int STEPS_PER_SECOND = 120;

    /** 每次调用积分器时推进的固定模拟时长，单位为秒。 */
    public static final double STEP_SECONDS = 1.0 / STEPS_PER_SECOND;

    private final SpringParameters parameters;
    private final MotionIntegrator integrator;
    private final RestDetector restDetector = new RestDetector();
    private MotionSample current;

    /**
     * 初始化一轮弹簧仿真。初始时间和速度为零，位移取参数指定的起始值。
     *
     * @param parameters 本轮使用的弹簧参数
     * @param integrator 负责算出下一步采样的积分器
     */
    public SpringSimulator(SpringParameters parameters, MotionIntegrator integrator) {
        this.parameters = Objects.requireNonNull(parameters);
        this.integrator = Objects.requireNonNull(integrator);
        current = new MotionSample(0.0, parameters.initialDisplacementMeters(), 0.0);
    }

    /**
     * 读取已经保存的运动采样，不推进时间。
     *
     * @return 当前采样，刚创建时返回初始采样
     */
    public MotionSample current() {
        return current;
    }

    /**
     * 在未静止时推进一固定步长，保存计算结果并更新静止判断。
     *
     * @return 本步得到的运动采样，已经静止时返回最后一次保存的采样
     */
    public MotionSample advance() {
        if (!isSettled()) {
            current = integrator.step(parameters, current, STEP_SECONDS);
            restDetector.observe(current);
        }
        return current;
    }

    /**
     * 查询重物是否已经达到静止条件。
     *
     * @return 达到静止条件时返回 {@code true}，否则返回 {@code false}
     */
    public boolean isSettled() {
        return restDetector.isSettled();
    }
}
