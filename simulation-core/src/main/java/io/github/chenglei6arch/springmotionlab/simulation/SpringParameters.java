/*
 * 负责人为朱禹行。
 * TODO 1 在构造器中检查三个数值是否有限，并且都在各自的闭区间内。
 * TODO 2 若参数非法，抛出 IllegalArgumentException，消息包含参数名称和允许范围。
 * 验收时检查两个端点可以使用，非数、无穷大和越界值会报错。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

/**
 * 弹簧参数记录。
 * 保存每轮仿真开始前设置的三个数值。质量固定为一千克，重物从所设位置以零速度释放。
 * 构造器承担参数校验，创建后的参数通过与字段同名的访问方法读取。
 *
 * @param stiffnessNewtonsPerMeter 弹力系数，单位为牛顿每米，范围为 4.0 至 100.0
 * @param initialDisplacementMeters 相对平衡点的初始位移，单位为米，范围为 -0.30 至 0.30
 * @param dampingNewtonSecondsPerMeter 摩擦系数，按线性阻尼计算，单位为牛顿秒每米，范围为 0.5 至 12.0
 */
public record SpringParameters(
        double stiffnessNewtonsPerMeter,
        double initialDisplacementMeters,
        double dampingNewtonSecondsPerMeter
) {
    /** 重物的固定质量，单位为千克。 */
    public static final double MASS_KILOGRAMS = 1.0;
    /** 弹力系数的下限，单位为牛顿每米。 */
    public static final double MIN_STIFFNESS = 4.0;
    /** 弹力系数的上限，单位为牛顿每米。 */
    public static final double MAX_STIFFNESS = 100.0;
    /** 初始位移的下限，单位为米。 */
    public static final double MIN_INITIAL_DISPLACEMENT = -0.30;
    /** 初始位移的上限，单位为米。 */
    public static final double MAX_INITIAL_DISPLACEMENT = 0.30;
    /** 摩擦系数的下限，单位为牛顿秒每米。 */
    public static final double MIN_DAMPING = 0.5;
    /** 摩擦系数的上限，单位为牛顿秒每米。 */
    public static final double MAX_DAMPING = 12.0;

    /**
     * 创建用于界面初次打开和默认演示的参数。
     *
     * @return 弹力系数为 16.0、初始位移为 0.20、摩擦系数为 2.0 的参数记录
     */
    public static SpringParameters defaults() {
        return new SpringParameters(16.0, 0.20, 2.0);
    }

    /**
     * 用三个输入值创建一轮仿真所用的参数。每个取值范围都包含两个端点。
     *
     * @param stiffnessNewtonsPerMeter 用户设置的弹力系数
     * @param initialDisplacementMeters 用户设置的相对平衡位置的初始位移
     * @param dampingNewtonSecondsPerMeter 用户设置的摩擦系数
     * @throws IllegalArgumentException 任意输入为非数、无穷大或超出允许范围
     */
    public SpringParameters {
        // TODO 1–2 按已有上下限常量校验每个参数。
    }
}
