package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;

/**
 * Odometry subsystem: the goBILDA Pinpoint with two dead-wheel pods (decision 005).
 * Tells us where the robot is on the field: X, Y and heading.
 *
 * Directions follow our team convention (decision 007):
 * +X = forward, +Y = LEFT, +heading = COUNTER-CLOCKWISE.
 *
 * The settings below are tuned with the "Pinpoint Test" OpMode (issue #6).
 * Autonomous will use these same settings, so tune them carefully!
 */
public class Odometry {

    // Config name (decision 009). Must match the Driver Station robot configuration.
    public static final String PINPOINT_NAME = "pinpoint";

    // TODO(#6) Step 0: pick the pod type we have: goBILDA_SWINGARM_POD or goBILDA_4_BAR_POD.
    // If this is wrong, the 48 in test will read way off (about 32 in or 72 in).
    public static final GoBildaPinpointDriver.GoBildaOdometryPods POD_TYPE =
            GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD;

    // TODO(#6) Step 1: measure from the robot's center, in millimeters.
    // X pod offset: how far SIDEWAYS the X (forward) pod is.   Left of center = +, right = -
    // Y pod offset: how far FORWARD the Y (strafe) pod is.     In front of center = +, behind = -
    public static final double X_POD_OFFSET_MM = 0.0;
    public static final double Y_POD_OFFSET_MM = 0.0;

    // TODO(#6) Step 2: push the robot forward, X must go UP. Push it left, Y must go UP.
    // If one goes down instead, change that pod to REVERSED.
    public static final GoBildaPinpointDriver.EncoderDirection X_POD_DIRECTION =
            GoBildaPinpointDriver.EncoderDirection.FORWARD;
    public static final GoBildaPinpointDriver.EncoderDirection Y_POD_DIRECTION =
            GoBildaPinpointDriver.EncoderDirection.FORWARD;

    private final GoBildaPinpointDriver pinpoint;

    public Odometry(HardwareMap hardwareMap) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, PINPOINT_NAME);

        pinpoint.setOffsets(X_POD_OFFSET_MM, Y_POD_OFFSET_MM, DistanceUnit.MM);
        pinpoint.setEncoderResolution(POD_TYPE);
        pinpoint.setEncoderDirections(X_POD_DIRECTION, Y_POD_DIRECTION);

        // Sets the position to 0,0,0 and calibrates the IMU.
        // The robot must be STILL until getStatus() says READY.
        pinpoint.resetPosAndIMU();
    }

    /** Read new data from the Pinpoint. Call this ONCE at the start of every loop. */
    public void update() {
        pinpoint.update();
    }

    /** Where the robot is: X, Y and heading. */
    public Pose2D getPose() {
        return pinpoint.getPosition();
    }

    /** Set the position back to 0,0,0 without recalibrating the IMU. Safe while moving. */
    public void resetPosition() {
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
    }

    /** Set the position to 0,0,0 AND recalibrate the IMU. The robot must be STILL. */
    public void resetPositionAndCalibrate() {
        pinpoint.resetPosAndIMU();
    }

    /** READY when it's working. CALIBRATING right after a reset. FAULT_... means a problem. */
    public GoBildaPinpointDriver.DeviceStatus getStatus() {
        return pinpoint.getDeviceStatus();
    }

    /** Show this subsystem's status on the Driver Station. */
    public void addTelemetry(Telemetry telemetry) {
        Pose2D pose = getPose();
        telemetry.addData("Odometry status", getStatus());
        telemetry.addData("X (in)       +forward", "%.2f", pose.getX(DistanceUnit.INCH));
        telemetry.addData("Y (in)       +left", "%.2f", pose.getY(DistanceUnit.INCH));
        telemetry.addData("Heading (°)  +counter-clockwise", "%.1f", pose.getHeading(AngleUnit.DEGREES));
    }

    /** Extra details for testing and tuning (issue #6). */
    public void addDebugTelemetry(Telemetry telemetry) {
        telemetry.addData("Total turning (°)", "%.1f",
                pinpoint.getHeading(UnnormalizedAngleUnit.DEGREES));
        telemetry.addData("Raw encoder X / Y", "%d  /  %d",
                pinpoint.getEncoderX(), pinpoint.getEncoderY());
        telemetry.addData("Pinpoint update rate (Hz)", "%.0f", pinpoint.getFrequency());
    }
}
