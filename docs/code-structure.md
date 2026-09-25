# How Our Code Is Organized

All of our code lives in `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/` (decision 001).
We use **structured subsystems** (decision 006).

```
teamcode/
├── Robot.java              ← holds every subsystem
├── subsystems/             ← one class per mechanism; the ONLY code that touches hardware
│   ├── Drivetrain.java
│   ├── Intake.java
│   └── Launcher.java
└── opmodes/                ← what shows up on the Driver Station
    ├── teleop/
    │   ├── DriverControls.java ← the ONLY code that turns gamepad sticks into drive commands
    │   └── ExampleTeleOp.java
    └── auto/
```

## The rules

1. **Only subsystems touch hardware.** Motors, servos and sensors are `private` inside their subsystem.
   OpModes call methods like `robot.drivetrain.drive(...)` or `robot.intake.in()`, never `motor.setPower(...)`.
2. **Every OpMode starts with `Robot robot = new Robot(hardwareMap);`.** See `ExampleTeleOp.java`.
3. **No magic numbers.** Config names, speeds and positions are named constants at the top of the subsystem,
   e.g. `FRONT_LEFT_NAME = "frontLeftDrive"`.
4. **Every subsystem has `stop()` and `addTelemetry(telemetry)`**, so `Robot` can stop everything
   and show everything with one call.
5. **Config names match `docs/hardware-config.md`.**
6. **Subsystems never read the gamepad.** The same subsystem is used by TeleOp *and* Autonomous;
   only the OpMode knows where commands come from (sticks in TeleOp, code in Autonomous).
7. **Every TeleOp drives with `DriverControls.drive(robot.drivetrain, gamepad1);`.** Don't read the drive
   sticks anywhere else. Autonomous skips `DriverControls` and calls `robot.drivetrain.drive(...)` directly.

## Directions (decision 007)

All of our code uses the same directions as the Pinpoint, Road Runner, Pedro Pathing and the FTC field:

| | Positive (+) means | Pinpoint value |
|---|---|---|
| **X / forward** | forward | `getPosX()` |
| **Y / left** | **left** | `getPosY()` |
| **Heading / turn** | **counter-clockwise** (turning left) | `getHeading()` |

The gamepad sticks are different (stick right = +X, stick forward = −Y).
**Only `DriverControls` flips the stick values.** Everything else, including subsystems and autonomous, uses the table above.

```
TeleOp:  gamepad → DriverControls → drivetrain.drive(forward, left, turn) → motors
Auto:    Pinpoint / path code ───→ drivetrain.drive(forward, left, turn) → motors
```

## Adding a new mechanism

1. Create `subsystems/YourThing.java`. Copy the shape of `Intake.java`.
2. Add its config names to `docs/hardware-config.md`.
3. Add it to `Robot.java` in the field, the constructor, `stop()` and `addTelemetry()`.
4. Call its methods from an OpMode.

## Adding a new OpMode

1. Copy `opmodes/teleop/ExampleTeleOp.java` into `opmodes/teleop/` or `opmodes/auto/`.
2. Change the class name, and the `name` in `@TeleOp(...)` (or use `@Autonomous(...)`).
3. Build and deploy, then pick it on the Driver Station.
