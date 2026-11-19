package io.github.chenglei6arch.springmotionlab.simulation;

/** An immutable state of the spring at one simulated time, using SI units. */
public record MotionSample(
        double timeSeconds,
        double displacementMeters,
        double velocityMetersPerSecond
) {}
