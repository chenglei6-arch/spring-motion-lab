package io.github.chenglei6arch.springmotionlab.simulation;

/**
 * 仿真运行状态枚举。
 * 表示一轮仿真当前所处的阶段。控制器根据状态判断能否推进，界面据此显示操作按钮。
 */
public enum SimulationStatus {
    /** 待开始。初次创建或复位后进入此状态。 */
    READY,
    /** 运行中。每次调用控制器的推进方法都会计算一个时间步。 */
    RUNNING,
    /** 已暂停。保留当前时间、位移和速度，继续后从当前位置推进。 */
    PAUSED,
    /** 已静止。保留最后一次采样，复位后才可以开始新一轮。 */
    SETTLED
}
