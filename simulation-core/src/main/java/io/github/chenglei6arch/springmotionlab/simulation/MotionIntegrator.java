package io.github.chenglei6arch.springmotionlab.simulation;

/** Advances the current motion sample by one step of simulated time. */
@FunctionalInterface
public interface MotionIntegrator {
    MotionSample step(SpringParameters parameters, MotionSample current, double stepSeconds);
}
