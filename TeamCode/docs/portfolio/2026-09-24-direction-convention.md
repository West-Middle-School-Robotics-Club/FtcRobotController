# One direction convention for all our code

**Date(s):** 2026-09-24
**Who worked on it:** Team programmers and mentors, with help from Claude (AI coding assistant)
**Links:** issue #6, PR #10, decision 007
**Status:** Done

## The problem
While learning how the goBILDA Pinpoint odometry computer works (for autonomous, issue #6), we noticed
that it and our new drive code **disagreed about which way is positive**:

| | Sideways | Turning |
|---|---|---|
| Pinpoint | +Y = **left** | +heading = **counter-clockwise** |
| Our `Drivetrain.drive()` (first version) | +strafe = **right** | +turn = **clockwise** |

Our drive code matched the gamepad sticks (stick right = positive), which felt natural. But in autonomous,
code reads the Pinpoint and then tells the drivetrain where to go. Mixing the two would send the robot
**the wrong way, with no error message**:

```java
double errorY = targetY - pose.getY(DistanceUnit.INCH); // positive = target is to the LEFT
robot.drivetrain.drive(0, errorY * kP, 0);              // positive strafe = RIGHT ❌ drives away!
```

## What we thought
This kind of bug is hard to find later, because each piece of code looks correct on its own.
It's better to pick **one** convention for everything, before we write any autonomous code.

## What we tried
We compared two options:
1. **Match the gamepad sticks everywhere** (+right, +clockwise)
2. **Match the Pinpoint everywhere** (+left, +counter-clockwise)

We checked what other tools use: Road Runner, Pedro Pathing and the FTC field coordinate system all use
option 2, the same as the Pinpoint.

## Results
- We chose option 2. `Drivetrain.drive(forward, left, turn)` now uses +X forward, +Y left, +turn counter-clockwise.
- The gamepad sticks still need flipping. At first each TeleOp did its own flipping. We realized every
  new TeleOp would copy those lines and could get a sign wrong, so we moved it into **one** class,
  `DriverControls`, which every TeleOp uses.
- We checked the math by hand: every wheel gets exactly the same power as our original `manualDriver`
  for every stick position, so driving feels the same.

## What we learned and decided
- **Decision 007:** one direction convention: +X forward, +Y left, +heading counter-clockwise.
- **Code rule:** only `DriverControls` turns gamepad sticks into drive commands (see `TeamCode/docs/code-structure.md`).
- Lesson: when two parts of a system describe the same thing (like "left"), check they agree **before** connecting them.

## What's next
- Test driving on the robot to confirm (PR #10 test).
- Use the convention in Pinpoint tuning (#6) and autonomous (#8).
