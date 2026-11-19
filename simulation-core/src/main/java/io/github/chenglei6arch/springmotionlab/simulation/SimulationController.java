/*
 * 负责人：朱禹行
 * TODO 1：只允许 READY 或 PAUSED 进入 RUNNING，只允许 RUNNING 进入 PAUSED。
 * TODO 2：reset() 换成新 SpringSimulator 并回到 READY，清除旧的时间和静止判定。
 * TODO 3：tick() 仅在 RUNNING 时推进一步，静止时切换到 SETTLED，并返回快照。
 * 验收：暂停和静止期间 tick() 不改变时间、位移和速度，复位可使用另一组参数。
 * 可传入简单的 MotionIntegrator 测试替身来验证状态流转。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

import java.util.Objects;

/** The Java API used by the desktop screen. Call it from a single thread. */
public final class SimulationController {
    private final MotionIntegrator integrator;
    private SpringSimulator simulator;
    private SimulationStatus status = SimulationStatus.READY;

    public SimulationController(SpringParameters parameters) {
        this(parameters, new SemiImplicitEulerIntegrator());
    }

    public SimulationController(SpringParameters parameters, MotionIntegrator integrator) {
        this.integrator = Objects.requireNonNull(integrator);
        simulator = new SpringSimulator(parameters, this.integrator);
    }

    public void start() {
        // TODO 1：开始或继续。
    }

    public void pause() {
        // TODO 1：暂停。
    }

    public void reset(SpringParameters parameters) {
        // TODO 2：重建模拟器并更新状态。
    }

    public SimulationSnapshot tick() {
        // TODO 3：推进并更新状态。
        return snapshot();
    }

    public SimulationSnapshot snapshot() {
        return new SimulationSnapshot(status, simulator.current());
    }
}
