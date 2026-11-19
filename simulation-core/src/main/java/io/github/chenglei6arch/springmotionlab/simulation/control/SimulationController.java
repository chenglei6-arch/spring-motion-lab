/*
 * 负责人为朱禹行。
 * TODO 1 完成开始、继续和暂停时的状态切换。
 * TODO 2 复位时用参数创建新仿真器并切回待开始，清除旧时间和旧的静止计数。
 * TODO 3 运行中才推进一步，达到静止条件后切换状态，最后返回快照。
 * 验收时检查暂停和静止期间的采样保持一致，以及复位能应用新的初始位移。
 */
package io.github.chenglei6arch.springmotionlab.simulation.control;

import io.github.chenglei6arch.springmotionlab.simulation.engine.MotionIntegrator;
import io.github.chenglei6arch.springmotionlab.simulation.engine.SemiImplicitEulerIntegrator;
import io.github.chenglei6arch.springmotionlab.simulation.engine.SpringSimulator;
import io.github.chenglei6arch.springmotionlab.simulation.model.SpringParameters;

import java.util.Objects;

/**
 * 仿真控制器。
 * 接收桌面界面的开始、暂停、复位和推进操作，管理仿真状态并提供快照。
 * 调用方在同一个线程中调用这些方法。
 */
public final class SimulationController {
    private final MotionIntegrator integrator;
    private SpringSimulator simulator;
    private SimulationStatus status = SimulationStatus.READY;

    /**
     * 使用默认的半隐式欧拉积分器创建处于待开始状态的控制器。
     *
     * @param parameters 第一轮仿真使用的弹簧参数
     */
    public SimulationController(SpringParameters parameters) {
        this(parameters, new SemiImplicitEulerIntegrator());
    }

    /**
     * 用指定的积分器创建处于待开始状态的控制器。测试时可以传入返回已知结果的积分器。
     *
     * @param parameters 第一轮仿真使用的弹簧参数
     * @param integrator 计算每一步运动的积分器，后续复位时继续使用它
     */
    public SimulationController(SpringParameters parameters, MotionIntegrator integrator) {
        this.integrator = Objects.requireNonNull(integrator);
        simulator = new SpringSimulator(parameters, this.integrator);
    }

    /**
     * 查询每次推进所使用的固定时长，供界面安排推进次数。
     *
     * @return 每个模拟时间步的时长，单位为秒
     */
    public double stepSeconds() {
        return SpringSimulator.STEP_SECONDS;
    }

    /**
     * 开始或继续仿真。待开始和已暂停可以转为运行中，其他状态保持原样。
     * 继续时保留已有的时间、位移和速度，无返回值。
     */
    public void start() {
        // TODO 1 只在待开始或已暂停时切换为运行中。
    }

    /**
     * 将运行中的仿真转为已暂停，其他状态保持原样。此方法保留当前采样，无返回值。
     */
    public void pause() {
        // TODO 1 只在运行中时切换为已暂停。
    }

    /**
     * 应用参数并回到待开始。新一轮从零时刻、零速度和所选初始位移开始。
     * 此方法替换内部仿真器，无返回值。
     *
     * @param parameters 复位后要使用的弹簧参数，可以是原参数或新参数
     */
    public void reset(SpringParameters parameters) {
        // TODO 2 用原有积分器和传入参数重建仿真器，并更新状态。
    }

    /**
     * 运行中时推进一个时间步，达到静止条件后切换为已静止。
     * 待开始、已暂停或已静止时只读取现有快照。
     *
     * @return 本次调用结束后的运行状态和运动采样
     */
    public SimulationSnapshot tick() {
        // TODO 3 按当前状态决定是否推进，推进后判断是否静止。
        return snapshot();
    }

    /**
     * 读取当前的运行状态和运动采样，不推进模拟时间。
     *
     * @return 将当前状态和采样打包在一起的仿真快照
     */
    public SimulationSnapshot snapshot() {
        return new SimulationSnapshot(status, simulator.current());
    }
}
