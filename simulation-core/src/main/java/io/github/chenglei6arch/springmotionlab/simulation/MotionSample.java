package io.github.chenglei6arch.springmotionlab.simulation;

/**
 * 运动采样记录。
 * 保存某个模拟时刻的位移和速度，供积分器、静止检测器、弹簧画布与曲线使用。
 * 创建后各项数值保持不变。通过与字段同名的访问方法读取对应值。
 *
 * @param timeSeconds 从本轮仿真开始经过的模拟时间，单位为秒
 * @param displacementMeters 相对于平衡位置的位移，单位为米，向下为正
 * @param velocityMetersPerSecond 当前速度，单位为米每秒，向下为正
 */
public record MotionSample(
        double timeSeconds,
        double displacementMeters,
        double velocityMetersPerSecond
) {}
