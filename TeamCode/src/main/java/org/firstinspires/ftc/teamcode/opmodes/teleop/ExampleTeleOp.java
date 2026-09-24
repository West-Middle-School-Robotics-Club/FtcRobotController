package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot;

/**
 * EXAMPLE: the pattern every OpMode should follow.
 *
 *   1. Create the Robot (this sets up all the hardware).
 *   2. Wait for START.
 *   3. Loop: read the gamepads, call subsystem methods, show telemetry.
 *
 * Notice there is no motor code here. All hardware details live in the subsystems.
 * Our real TeleOp (MainTeleOp) is issue #3.
 */
@TeleOp(name = "Example TeleOp", group = "Examples")
public class ExampleTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap);

        telemetry.addData("Status", "Ready! Press START");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // The gamepad's Y stick is negative when pushed forward, so flip it.
            double forward = -gamepad1.left_stick_y;
            double strafe  =  gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double turn    =  gamepad1.right_stick_x;
            robot.drivetrain.drive(forward, strafe, turn);

            robot.addTelemetry(telemetry);
            telemetry.update();
        }

        robot.stop();
    }
}
