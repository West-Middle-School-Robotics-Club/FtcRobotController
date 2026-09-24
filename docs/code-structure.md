# How Our Code Is Organized

All of our code lives in `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/` (decision 001).
We use **structured subsystems** (decision 006).

```
teamcode/
├── Robot.java              ← holds every subsystem
├── subsystems/             ← one class per mechanism; the ONLY code that touches hardware
│   ├── Drive.java
│   ├── Intake.java
│   └── Launcher.java
└── opmodes/                ← what shows up on the Driver Station
    ├── teleop/
    │   └── ExampleTeleOp.java
    └── auto/
```

## The rules

1. **Only subsystems touch hardware.** Motors, servos and sensors are `private` inside their subsystem.
   OpModes call methods like `robot.drive.drive(...)` or `robot.intake.in()`, never `motor.setPower(...)`.
2. **Every OpMode starts with `Robot robot = new Robot(hardwareMap);`.** See `ExampleTeleOp.java`.
3. **No magic numbers.** Config names, speeds and positions are named constants at the top of the subsystem,
   e.g. `FRONT_LEFT_NAME = "frontLeftMotor"`.
4. **Every subsystem has `stop()` and `addTelemetry(telemetry)`**, so `Robot` can stop everything
   and show everything with one call.
5. **Config names match `docs/hardware-config.md`.**

## Adding a new mechanism

1. Create `subsystems/YourThing.java`. Copy the shape of `Intake.java`.
2. Add its config names to `docs/hardware-config.md`.
3. Add it to `Robot.java` in the field, the constructor, `stop()` and `addTelemetry()`.
4. Call its methods from an OpMode.

## Adding a new OpMode

1. Copy `opmodes/teleop/ExampleTeleOp.java` into `opmodes/teleop/` or `opmodes/auto/`.
2. Change the class name, and the `name` in `@TeleOp(...)` (or use `@Autonomous(...)`).
3. Build and deploy, then pick it on the Driver Station.
