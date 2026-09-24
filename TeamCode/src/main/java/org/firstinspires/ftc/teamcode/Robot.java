package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Launcher;

/**
 * The whole robot: one object that holds every subsystem.
 *
 * Every OpMode starts the same way:
 *     Robot robot = new Robot(hardwareMap);
 * and then uses robot.drive, robot.intake, robot.launcher.
 *
 * Adding a new mechanism? Create its class in the subsystems folder,
 * then add it here in three places: the field, the constructor, and stop()/addTelemetry().
 */
public class Robot {

    public final Drive drive;
    public final Intake intake;
    public final Launcher launcher;

    public Robot(HardwareMap hardwareMap) {
        drive = new Drive(hardwareMap);
        intake = new Intake(hardwareMap);
        launcher = new Launcher(hardwareMap);
    }

    /** Stop every mechanism. */
    public void stop() {
        drive.stop();
        intake.stop();
        launcher.stop();
    }

    /** Show every subsystem's status on the Driver Station. */
    public void addTelemetry(Telemetry telemetry) {
        drive.addTelemetry(telemetry);
        intake.addTelemetry(telemetry);
        launcher.addTelemetry(telemetry);
    }
}
