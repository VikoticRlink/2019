/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class FinishGet extends CommandGroup {
  /**
   * Abort all running commands, lift hatch, backup
   */
  public FinishGet() {
    addSequential(new AbortAll());
    addSequential(new HatchHeight(RobotMap._elevatorMotor.getSelectedSensorPosition(0)+500));
  }
}
