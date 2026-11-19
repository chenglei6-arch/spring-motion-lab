/*
 * 负责人：朱禹行
 * TODO 1：在紧凑构造器内校验三个参数均为有限数，且在对应的闭区间内。
 * TODO 2：非法值抛出 IllegalArgumentException，消息写明参数和允许的范围。
 * 验收：边界值可用，NaN、无穷大和越界值均被拒绝。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

/** Spring settings for one run. Mass is fixed and initial velocity is zero. */
public record SpringParameters(
        double stiffnessNewtonsPerMeter,
        double initialDisplacementMeters,
        double dampingNewtonSecondsPerMeter
) {
    public static final double MASS_KILOGRAMS = 1.0;
    public static final double MIN_STIFFNESS = 4.0;
    public static final double MAX_STIFFNESS = 100.0;
    public static final double MIN_INITIAL_DISPLACEMENT = -0.30;
    public static final double MAX_INITIAL_DISPLACEMENT = 0.30;
    public static final double MIN_DAMPING = 0.5;
    public static final double MAX_DAMPING = 12.0;

    public static SpringParameters defaults() {
        return new SpringParameters(16.0, 0.20, 2.0);
    }

    public SpringParameters {
        // TODO 1–2：实现参数校验。
    }
}
