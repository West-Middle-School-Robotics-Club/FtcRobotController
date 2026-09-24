# Hardware Configuration

The names in the **Driver Station robot configuration** must match the names in our code **exactly**, including capital letters.
If a name doesn't match, the OpMode crashes on INIT with an error like `Unable to find a hardware device with name "frontLeftMotor"`.

**Changing a name?** Update all three places: the Driver Station config, the constant in the subsystem class, and this table.

## Drivetrain (`subsystems/Drivetrain.java`)

| Config name | Device type | Hub / Port | Direction | Notes |
|---|---|---|---|---|
| `frontLeftMotor`  | Motor | Control Hub / motor __ | REVERSE | Left side reversed (tested 9/23) |
| `backLeftMotor`   | Motor | Control Hub / motor __ | REVERSE | |
| `frontRightMotor` | Motor | Control Hub / motor __ | FORWARD | |
| `backRightMotor`  | Motor | Control Hub / motor __ | FORWARD | |

## Odometry (issue #6)

| Config name | Device type | Hub / Port | Notes |
|---|---|---|---|
| `pinpoint` | goBILDA Pinpoint Odometry Computer | Control Hub / I2C bus __ | Not in code yet |

## Intake (`subsystems/Intake.java`, issue #4)

| Config name | Device type | Hub / Port | Notes |
|---|---|---|---|
| ___ | ___ | ___ | Waiting on build team |

## Launcher (`subsystems/Launcher.java`, issue #5)

| Config name | Device type | Hub / Port | Notes |
|---|---|---|---|
| ___ | ___ | ___ | Waiting on build team |

## Camera (issue #7)

| Config name | Device type | Hub / Port | Notes |
|---|---|---|---|
| `Webcam 1` | Webcam | Control Hub / USB | Record mounting position here |
