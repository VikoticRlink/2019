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
import frc.robot.OI;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class DriveToPortA extends Command {
  Boolean HasRan = false;
  public static AHRS ahrs;
	double last_world_linear_accel_x;
	double last_world_linear_accel_y;
  public DriveToPortA() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    HasRan=false;
	 //last_world_linear_accel_x=0;
	 //last_world_linear_accel_y=0;
    try {	ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  //  Robot.m_Drivebase.HitTheTargetA();
    //check for collision
    		double curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
        double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
        double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > RobotMap.kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > RobotMap.kCollisionThreshold_DeltaG) ) {
            OI.DriverRumbleEnhanced(300, 1, true, true);
            OI.OperatorRumbleEnhanced(300, 1, true, true);
            HasRan=true;
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
