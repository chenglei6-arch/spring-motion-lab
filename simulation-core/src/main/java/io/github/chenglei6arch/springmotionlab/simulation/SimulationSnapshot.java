package io.github.chenglei6arch.springmotionlab.simulation;

/** A consistent view of the current lifecycle state and motion sample for the UI. */
public record SimulationSnapshot(SimulationStatus status, MotionSample sample) {}
