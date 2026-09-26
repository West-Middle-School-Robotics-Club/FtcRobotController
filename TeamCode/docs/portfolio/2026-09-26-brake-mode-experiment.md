# What brake mode do our drive motors really use?

**Date(s):** 2026-09-26 – ___
**Who worked on it:** ___ (experiment), mentors, with help from Claude (AI coding assistant) reading the SDK code
**Links:** issue #17, PR #16
**Status:** Waiting on results (#17)

## The problem
While writing the Pinpoint Test OpMode, we set the drive motors to **FLOAT** so the robot is easy to push
by hand. That raised a question: **what brake mode do our motors normally use?** Last year we never set it
and assumed motors **BRAKE** by default.

Brake mode ("zero power behavior") decides what a motor does when its power is 0:
- **BRAKE:** the robot stops quickly and is hard to push
- **FLOAT:** the robot coasts and is easy to push

## What we thought (hypothesis)
We assumed BRAKE was the default. But when we read the FTC SDK's own code, it suggested something different:
1. When the robot **powers up**, the SDK sets all motors to **FLOAT**.
2. When an OpMode **starts**, the SDK resets motor direction, but **not** brake mode. The setting
   **carries over** from the last OpMode.
3. So `manualDriver`, which never sets brake mode, would **coast** after power-up or after Pinpoint Test,
   and would only brake after an OpMode that sets BRAKE.

We don't trust the code reading alone. **We need to test it on the real robot.**

**Our predictions** (fill in before testing):
| Step | Prediction |
|---|---|
| A1: after power-up, leftover brake mode | |
| A2: after Pinpoint Test | |
| A3: after Example TeleOp | |
| B1: manualDriver after power-up (stops or coasts?) | |
| B2: manualDriver after Pinpoint Test | |
| B3: manualDriver after Example TeleOp | |

## What we tried
- Wrote a **Brake Mode Check** test OpMode that shows the brake mode left over from before, and lets us
  switch between BRAKE and FLOAT.
- **On purpose**, left out the line that switches Pinpoint Test back to BRAKE, so we could see the problem happen.
- Designed a 3-part experiment (issue #17): what's left over, does it change `manualDriver`, and measured
  stopping distance in each mode.

## Results
_Copy the results tables from issue #17 here when the experiment is done. Add photos and a video of the stopping test._

## What we learned and decided
_Was the hypothesis right? What did we fix? What did we decide about `manualDriver`?_

## What's next
- Un-comment the fix in `PinpointTest.java` and show the problem is gone.
- Record the team's decision in `TeamCode/docs/decisions.md`.
