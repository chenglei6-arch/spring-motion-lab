/*
 * 负责人：钟江泽
 * TODO 1：检查一次采样的 |位移| 和 |速度| 是否同时低于各自的阈值。
 * TODO 2：两项都满足时累加连续步数，任一项超出阈值或等于阈值时清零。
 * TODO 3：连续达到 REQUIRED_STEPS 后，isSettled() 返回 true。
 * 验收：仅经过平衡点或仅经过转折点时不停止，连续满足 48 步时判定为静止。
 */
package io.github.chenglei6arch.springmotionlab.simulation;

/** Tracks consecutive samples inside the displacement and speed tolerances. */
final class RestDetector {
    static final double DISPLACEMENT_TOLERANCE_METERS = 0.002;
    static final double SPEED_TOLERANCE_METERS_PER_SECOND = 0.005;
    static final int REQUIRED_STEPS = 48;

    void observe(MotionSample sample) {
        // TODO 1–2：更新连续步数。
    }

    boolean isSettled() {
        // TODO 3：使用连续步数返回静止判定。
        return false;
    }
}
