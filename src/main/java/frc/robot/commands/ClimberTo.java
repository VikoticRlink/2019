/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ClimberTo extends Command {
  int ClimberLevel;
  Boolean HasRan = false;
  public ClimberTo(int Climb) {
    requires(Robot.m_Climber);
    ClimberLevel = Climb;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      switch (ClimberLevel){
      case 0:
        Robot.m_Climber.Home();
        if (RobotMap.ClimbArmLevels[0] - RobotMap.ClimbError < RobotMap._climbRightArm.getSelectedSensorPosition(0) && RobotMap._climbRightArm.getSelectedSensorPosition(0) < RobotMap.ClimbArmLevels[0]+RobotMap.ClimbError){HasRan=true;}
      break;
      case 1:
        Robot.m_Climber.Floor();
        if (RobotMap.ClimbArmLevels[1] - RobotMap.ClimbError < RobotMap._climbRightArm.getSelectedSensorPosition(0) && RobotMap._climbRightArm.getSelectedSensorPosition(0) < RobotMap.ClimbArmLevels[1]+RobotMap.ClimbError){HasRan=true;}
      break;
      case 2:
        Robot.m_Climber.Lift();
        if (RobotMap.ClimbArmLevels[2] - RobotMap.ClimbError < RobotMap._climbRightArm.getSelectedSensorPosition(0) && RobotMap._climbRightArm.getSelectedSensorPosition(0) < RobotMap.ClimbArmLevels[2]+RobotMap.ClimbError){HasRan=true;}
      break;
      default:
      Robot.m_Climber.Goto(ClimberLevel);
      if (ClimberLevel - RobotMap.ClimbError < RobotMap._climbRightArm.getSelectedSensorPosition(0) && RobotMap._climbRightArm.getSelectedSensorPosition(0) < ClimberLevel+RobotMap.ClimbError){HasRan=true;}
    break;
      }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return HasRan;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
