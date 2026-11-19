package io.github.chenglei6arch.springmotionlab.simulation;

/**
 * 运动积分器接口。
 * 约定如何根据弹簧的参数和当前运动状态，计算一个时间步之后的运动状态。
 * 实现类负责数值计算，仿真器负责保存状态和判断静止。
 */
@FunctionalInterface
public interface MotionIntegrator {
    /**
     * 从当前状态推进一个时间步，原采样保持不变。
     *
     * @param parameters 本次仿真的弹力系数、初始位移和阻尼参数
     * @param current 当前时刻的运动采样
     * @param stepSeconds 时间步长，单位为秒，取正数
     * @return 新的运动采样，包含当前时刻加上步长后的时间、位移和速度
     */
    MotionSample step(SpringParameters parameters, MotionSample current, double stepSeconds);
}
