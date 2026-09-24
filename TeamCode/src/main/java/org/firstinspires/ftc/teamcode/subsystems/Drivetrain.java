package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Drivetrain subsystem: our four-motor mecanum drivetrain (decision 004).
 *
 * Used by BOTH TeleOp and Autonomous. This class doesn't know where its
 * commands come from: TeleOp gets them from the gamepad sticks, Autonomous
 * gets them from code. Gamepad code belongs in OpModes, NOT here.
 *
 * This class is the ONLY place that talks to the drive motors.
 * OpModes just call drive(...) and stop().
 */
public class Drivetrain {

    // Config names. These must match the Driver Station robot configuration EXACTLY.
    // If you change one here, update docs/hardware-config.md too.
    public static final String FRONT_LEFT_NAME  = "frontLeftMotor";
    public static final String FRONT_RIGHT_NAME = "frontRightMotor";
    public static final String BACK_LEFT_NAME   = "backLeftMotor";
    public static final String BACK_RIGHT_NAME  = "backRightMotor";

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public Drivetrain(HardwareMap hardwareMap) {
        frontLeft  = hardwareMap.get(DcMotor.class, FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT_NAME);
        backLeft   = hardwareMap.get(DcMotor.class, BACK_LEFT_NAME);
        backRight  = hardwareMap.get(DcMotor.class, BACK_RIGHT_NAME);

        // The LEFT side is reversed on our robot (tested 9/23 with manualDriver).
        // If the robot drives backwards when you push the stick forward, swap these.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        for (DcMotor motor : new DcMotor[] {frontLeft, frontRight, backLeft, backRight}) {
            // BRAKE makes the robot stop quickly when the sticks are released.
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    /**
     * Drive the robot, relative to the way the robot is facing.
     *
     * @param forward positive = drive forward       (-1 to 1)
     * @param strafe  positive = drive right         (-1 to 1)
     * @param turn    positive = turn clockwise      (-1 to 1)
     */
    public void drive(double forward, double strafe, double turn) {
        // Mecanum math: each wheel gets a mix of forward, strafe and turn.
        double frontLeftPower  = forward + strafe + turn;
        double frontRightPower = forward - strafe - turn;
        double backLeftPower   = forward - strafe + turn;
        double backRightPower  = forward + strafe - turn;

        // If any power is above 1, scale them all down together so the
        // robot still moves in the direction the driver asked for.
        double max = Math.max(1.0, Math.max(
                Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        frontLeft.setPower(frontLeftPower / max);
        frontRight.setPower(frontRightPower / max);
        backLeft.setPower(backLeftPower / max);
        backRight.setPower(backRightPower / max);
    }

    /** Stop all drive motors. */
    public void stop() {
        drive(0, 0, 0);
    }

    /** Show this subsystem's status on the Driver Station. */
    public void addTelemetry(Telemetry telemetry) {
        telemetry.addData("Drivetrain front L/R", "%.2f  %.2f", frontLeft.getPower(), frontRight.getPower());
        telemetry.addData("Drivetrain back  L/R", "%.2f  %.2f", backLeft.getPower(), backRight.getPower());
    }
}
