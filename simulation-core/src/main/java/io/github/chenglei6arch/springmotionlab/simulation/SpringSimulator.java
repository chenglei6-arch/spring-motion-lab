package io.github.chenglei6arch.springmotionlab.simulation;

import java.util.Objects;

/** Owns the motion state and the rest detector for a single run. */
public final class SpringSimulator {
    public static final double STEP_SECONDS = 1.0 / 120.0;

    private final SpringParameters parameters;
    private final MotionIntegrator integrator;
    private final RestDetector restDetector = new RestDetector();
    private MotionSample current;

    public SpringSimulator(SpringParameters parameters, MotionIntegrator integrator) {
        this.parameters = Objects.requireNonNull(parameters);
        this.integrator = Objects.requireNonNull(integrator);
        current = new MotionSample(0.0, parameters.initialDisplacementMeters(), 0.0);
    }

    public MotionSample current() {
        return current;
    }

    public MotionSample advance() {
        if (!isSettled()) {
            current = integrator.step(parameters, current, STEP_SECONDS);
            restDetector.observe(current);
        }
        return current;
    }

    public boolean isSettled() {
        return restDetector.isSettled();
    }
}
