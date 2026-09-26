## Changes Made
Please update with short description of any changes you make to the code so we can keep better track of what's been added.
All code should be added in the TeamCode folder NOT the FtcRobotController.

9/23:
Added HelloWorld, manualDriver, and manualDriver_kids_copy.
These are to make sure repo is in sync with current robot status
Robot status as of 9/23: with manualDriver, can drive robot with controller.

9/24:
Added the robot code skeleton (issue #2). New: `Robot.java`, `subsystems/` (Drivetrain, Intake, Launcher), `opmodes/teleop/ExampleTeleOp.java`, `opmodes/teleop/DriverControls.java`.
Drivetrain uses the same motor directions as manualDriver.
Drive motors are renamed to frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive (decision 009). The Driver Station robot configuration uses the new names. manualDriver and manualDriver_kids_copy were updated to the new names. Intake and Launcher are empty starters (issues #4 and #5).
All code uses one direction convention: +X forward, +Y left, +turn counter-clockwise (decision 007).
See `TeamCode/docs/code-structure.md` for how the code is organized and `TeamCode/docs/hardware-config.md` for config names.

9/26:
Added `subsystems/Odometry.java` (the Pinpoint) and the "Pinpoint Test" OpMode in `opmodes/test/` (issue #6).
Students tune the pod type, offsets and directions at the top of Odometry.java by following the test procedure in issue #6.
Added `Drivetrain.setBrake()` so the robot can be pushed by hand during tests.

