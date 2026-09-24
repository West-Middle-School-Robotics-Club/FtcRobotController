package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Intake subsystem: picks up game elements.
 *
 * STARTER CLASS: the methods exist so OpModes can already call them,
 * but they don't do anything yet. Filling this in is issue #4.
 * Use Drivetrain.java as the example to follow.
 */
public class Intake {

    // TODO(#4): add config names once the build team decides the hardware, e.g.
    // public static final String MOTOR_NAME = "intakeMotor";

    // TODO(#4): put speeds here as named constants, e.g.
    // public static final double IN_POWER = 1.0;

    public Intake(HardwareMap hardwareMap) {
        // TODO(#4): get the intake hardware from hardwareMap and set directions.
    }

    /** Run the intake to pull game elements in. */
    public void in() {
        // TODO(#4)
    }

    /** Run the intake backwards to push game elements out. */
    public void out() {
        // TODO(#4)
    }

    /** Stop the intake. */
    public void stop() {
        // TODO(#4)
    }

    /** Show this subsystem's status on the Driver Station. */
    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("Intake", "not built yet (#4)");
    }
}
