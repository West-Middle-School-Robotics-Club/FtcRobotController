# Hardware Configuration

The names in the **Driver Station robot configuration** must match the names in our code **exactly**, including capital letters.
If a name doesn't match, the OpMode crashes on INIT with an error like `Unable to find a hardware device with name "frontLeftDrive"`.

**Changing a name?** Update all three places: the Driver Station config, the constant in the subsystem class, and this table.

## Naming rules (decision 009)

| Kind of hardware | Rule | Examples |
|---|---|---|
| **One-of-a-kind devices** | Use the standard name that the SDK samples, Road Runner and Pedro Pathing all use | `imu`, `pinpoint`, `webcam` |
| **Drive motors** | `<position>Drive`: named by their job, since other mechanisms use motors too | `frontLeftDrive`, `frontRightDrive`, `backLeftDrive`, `backRightDrive` |
| **Mechanisms** | camelCase, mechanism first, then the part | `intakeMotor`, `launcherMotor`, `launcherFeedServo`, `intakeColorSensor` |

- Name hardware by its **job**, not its port (`intakeMotor`, not `motor2`).
- The **variable in code uses the same name** as the config name: `DcMotor frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");`
- No spaces. Names are **case-sensitive**.
- Label the physical wires with the same name.

## Drivetrain (`subsystems/Drivetrain.java`)

| Config name | Device type | Hub / Port | Direction | Notes |
|---|---|---|---|---|
| `frontLeftDrive`  | Motor | Control Hub / motor __ | REVERSE | Left side reversed (tested 9/23) |
| `backLeftDrive`   | Motor | Control Hub / motor __ | REVERSE | |
| `frontRightDrive` | Motor | Control Hub / motor __ | FORWARD | |
| `backRightDrive`  | Motor | Control Hub / motor __ | FORWARD | |

## IMU

| Config name | Device type | Hub / Port | Notes |
|---|---|---|---|
| `imu` | Control Hub built-in IMU | Control Hub / I2C bus 0 | Default name. Heading for driving comes from the Pinpoint (decision 005) |

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
| `webcam` | Webcam | Control Hub / USB | Record mounting position here. Rename from the default `Webcam 1` |
