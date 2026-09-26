package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Launcher subsystem: scores game elements.
 *
 * STARTER CLASS: the methods exist so OpModes can already call them,
 * but they don't do anything yet. Filling this in is issue #5.
 * Use Drivetrain.java as the example to follow.
 */
public class Launcher {

    // TODO(#5): add config names once the build team decides the hardware, e.g.
    // public static final String FLYWHEEL_NAME = "launcherMotor";

    // TODO(#5): put speeds here as named constants, e.g.
    // public static final double TARGET_VELOCITY = 1500; // encoder ticks per second

    public Launcher(HardwareMap hardwareMap) {
        // TODO(#5): get the launcher hardware from hardwareMap and set directions.
    }

    /** Start getting the launcher up to speed. */
    public void spinUp() {
        // TODO(#5)
    }

    /** Launch one game element. Only call this when isReady() is true. */
    public void fire() {
        // TODO(#5)
    }

    /** Stop the launcher. */
    public void stop() {
        // TODO(#5)
    }

    /** True when the launcher is at speed and ready to fire. */
    public boolean isReady() {
        return false; // TODO(#5)
    }

    /** Show this subsystem's status on the Driver Station. */
    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("Launcher", "not built yet (#5)");
    }
}
