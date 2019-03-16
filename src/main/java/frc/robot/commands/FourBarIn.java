/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

public class FourBarIn extends Command {
  Boolean Deploy;
  Boolean HasRan = false;
  public FourBarIn() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    HasRan=false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
   // Scheduler.getInstance().removeAll();
      Robot.m_Pneumatics.FourBarStore();
      HasRan = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return HasRan;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    HasRan=false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
