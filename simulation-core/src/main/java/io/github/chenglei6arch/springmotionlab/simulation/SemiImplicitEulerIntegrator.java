/*
 * 负责人：钟江泽
 * TODO 1：根据当前位移 x 和速度 v 计算 a = (-k * x - c * v) / m。
 * TODO 2：按 vNext = v + a * dt，再按 xNext = x + vNext * dt 推进。
 * TODO 3：返回新的 MotionSample，时间为当前模拟时间加 dt。
 * 验收：取默认参数和 dt = 1/120，首步速度约为 -0.02666667 m/s，
 *       首步位移约为 0.19977778 m。可用控制台或测试观察后续轨迹。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

/** The fixed-step numerical method used for the first version of the simulator. */
public final class SemiImplicitEulerIntegrator implements MotionIntegrator {
    @Override
    public MotionSample step(SpringParameters parameters, MotionSample current, double stepSeconds) {
        throw new UnsupportedOperationException("TODO：计算下一步弹簧状态");
    }
}
