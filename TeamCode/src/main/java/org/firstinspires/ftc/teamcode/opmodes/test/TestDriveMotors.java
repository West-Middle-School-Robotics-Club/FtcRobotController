package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

/**
 * TEST-ONLY helper: reads and changes the drive motors' brake mode directly.
 *
 * Only test OpModes in this folder use it. Real robot code (subsystems and the
 * teleop/auto OpModes) never does, so when testing is done this file can be
 * deleted without touching Drivetrain.
 *
 * Brake mode ("ZeroPowerBehavior") decides what a motor does when its power is 0:
 *   BRAKE = the motor resists turning, so the robot stops quickly and is hard to push
 *   FLOAT = the motor spins freely, so the robot coasts and is easy to push
 */
public class TestDriveMotors {

    private static final String[] DRIVE_MOTOR_NAMES = {
            Drivetrain.FRONT_LEFT_DRIVE_NAME,
            Drivetrain.FRONT_RIGHT_DRIVE_NAME,
            Drivetrain.BACK_LEFT_DRIVE_NAME,
            Drivetrain.BACK_RIGHT_DRIVE_NAME,
    };

    /** The brake mode the drive motors have right now (reads the front-left motor). */
    public static DcMotor.ZeroPowerBehavior getBrakeMode(HardwareMap hardwareMap) {
        return hardwareMap.get(DcMotor.class, Drivetrain.FRONT_LEFT_DRIVE_NAME).getZeroPowerBehavior();
    }

    /** true = BRAKE, false = FLOAT, for all four drive motors. */
    public static void setBrake(HardwareMap hardwareMap, boolean brake) {
        DcMotor.ZeroPowerBehavior behavior =
                brake ? DcMotor.ZeroPowerBehavior.BRAKE : DcMotor.ZeroPowerBehavior.FLOAT;
        for (String name : DRIVE_MOTOR_NAMES) {
            hardwareMap.get(DcMotor.class, name).setZeroPowerBehavior(behavior);
        }
    }
}
