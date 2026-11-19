package io.github.chenglei6arch.springmotionlab.simulation.control;

import io.github.chenglei6arch.springmotionlab.simulation.model.MotionSample;

/**
 * 仿真快照记录。
 * 把同一个时刻的运行状态和运动采样一起交给界面，供按钮、动画和数据读数使用。
 *
 * @param status 当前运行状态，通过 {@code status()} 读取
 * @param sample 当前运动采样，通过 {@code sample()} 读取
 */
public record SimulationSnapshot(SimulationStatus status, MotionSample sample) {}
