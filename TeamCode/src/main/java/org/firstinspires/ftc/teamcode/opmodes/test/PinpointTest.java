package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.opmodes.teleop.DriverControls;
import org.firstinspires.ftc.teamcode.subsystems.Odometry;

/**
 * TEST: check and tune the Pinpoint odometry (issue #6).
 *
 * Follow the step-by-step test procedure in issue #6, and record your results there.
 * The settings you tune (pod type, offsets, directions) are at the top of Odometry.java.
 *
 * Controls (gamepad 1):
 *   A          reset position to 0,0,0 (do this before each test run)
 *   B          reset AND recalibrate the IMU (robot must be STILL)
 *   sticks     drive, if you want to move the robot back to the start
 *
 * The drive motors are set to FLOAT (no brake) so the robot is easier to push by hand.
 */
@TeleOp(name = "Pinpoint Test", group = "Tests")
public class PinpointTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);
        Odometry odometry = new Odometry(hardwareMap);
        // Test-only: the same Pinpoint, read directly for extra tuning details (code-structure rule 8).
        GoBildaPinpointDriver pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, Odometry.PINPOINT_NAME);

        // Easier to push the robot by hand for the distance tests.
        TestDriveMotors.setBrake(hardwareMap, false);

        // Wait for START, showing whether the IMU calibration has finished.
        while (opModeInInit()) {
            odometry.update();
            if (odometry.getStatus() == GoBildaPinpointDriver.DeviceStatus.READY) {
                telemetry.addLine("Pinpoint READY. Press START.");
            } else {
                telemetry.addLine("Keep the robot STILL until the status says READY...");
            }
            telemetry.addData("Odometry status", odometry.getStatus());
            telemetry.update();
        }

        while (opModeIsActive()) {
            odometry.update();

            if (gamepad1.aWasPressed()) {
                odometry.resetPosition();
            }
            if (gamepad1.bWasPressed()) {
                odometry.resetPositionAndCalibrate();
            }

            DriverControls.drive(robot.drivetrain, gamepad1);

            telemetry.addLine("A = reset to 0,0,0     B = reset + recalibrate (keep STILL)");
            telemetry.addLine();
            odometry.addTelemetry(telemetry);
            telemetry.addData("Total turning (°)", "%.1f", pinpoint.getHeading(UnnormalizedAngleUnit.DEGREES));
            telemetry.addData("Raw encoder X / Y", "%d  /  %d", pinpoint.getEncoderX(), pinpoint.getEncoderY());
            telemetry.addData("Pinpoint update rate (Hz)", "%.0f", pinpoint.getFrequency());
            telemetry.addLine();
            telemetry.addLine("Push forward: X goes UP");
            telemetry.addLine("Push left: Y goes UP");
            telemetry.addLine("Turn left: heading goes UP");
            telemetry.update();
        }

        robot.stop();

        // EXPERIMENT (issue #17): this OpMode set the drive motors to FLOAT.
        // The SDK does NOT reset brake mode between OpModes, so an OpMode that never sets it
        // (like manualDriver) may keep coasting after this one. This line would switch back to BRAKE.
        // It's commented out ON PURPOSE so we can see the problem happen first. After the
        // experiment, un-comment it:
        // TestDriveMotors.setBrake(hardwareMap, true);
    }
}
