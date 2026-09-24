## Welcome!

This repository contains the code for WMS Irondogs's BIOBUZZ robot for the (2026-2027) competition season.

## Requirements
For this year, we are using Android Studio to develop the code for the robot, refer to the README_FTC for more information on setup. 

## Administrators
Jonathan Rakozy, Matt Becker, & Nadinne Motta

## Changes Made
Please update with short description of any changes you make to the code so we can keep better track of what's been added.
All code should be added in the TeamCode folder NOT the FtcRobotController.

9/23:
Added HelloWorld, manualDriver, and manualDriver_kids_copy.
These are to make sure repo is in sync with current robot status
Robot status as of 9/23: with manualDriver, can drive robot with controller. 

9/24:
Added the robot code skeleton (issue #2). New: `Robot.java`, `subsystems/` (Drive, Intake, Launcher), `opmodes/teleop/ExampleTeleOp.java`.
Drive uses the same config names and motor directions as manualDriver. Intake and Launcher are empty starters (issues #4 and #5).
See `docs/code-structure.md` for how the code is organized and `docs/hardware-config.md` for config names.
