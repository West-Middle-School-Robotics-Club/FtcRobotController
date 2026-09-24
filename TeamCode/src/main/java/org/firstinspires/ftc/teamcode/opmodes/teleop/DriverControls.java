package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * The ONE place that turns gamepad sticks into drive commands.
 *
 * Every TeleOp drives the robot with:
 *     DriverControls.drive(robot.drivetrain, gamepad1);
 * so all TeleOps drive the same way, and a wrong direction only needs fixing here.
 *
 * Autonomous does NOT use this. It calls robot.drivetrain.drive(...) directly,
 * because its numbers (e.g. from the Pinpoint) already follow our convention.
 */
public class DriverControls {

    // Multiply sideways stick input a little, because mecanum wheels strafe
    // slower than they drive forward.
    public static final double STRAFE_CORRECTION = 1.1;

    /** Read the driver's sticks and drive the robot. Call this once per loop. */
    public static void drive(Drivetrain drivetrain, Gamepad gamepad) {
        // The gamepad sticks don't match our direction convention (decision 007),
        // so we flip them HERE, and only here:
        //   stick pushed forward = negative Y   -> flip to get +forward
        //   stick pushed right   = positive X   -> flip to get +left
        //   right stick right    = positive X   -> flip to get +turn (counter-clockwise)
        double forward = -gamepad.left_stick_y;
        double left    = -gamepad.left_stick_x * STRAFE_CORRECTION;
        double turn    = -gamepad.right_stick_x;

        // TODO(#3): add slow mode here, so every TeleOp gets it.

        drivetrain.drive(forward, left, turn);
    }
}
