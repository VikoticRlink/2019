/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
	public void periodic() {  
    //Drive System
    SmartDashboard.putNumber("Right Speed", RobotMap._frontLeftMotor.getSelectedSensorVelocity(0)*-1);
    SmartDashboard.putNumber("Left Speed", RobotMap._frontRightMotor.getSelectedSensorVelocity(0));
    //Elevator
    SmartDashboard.putNumber("Elevator Position", RobotMap._elevatorMotor.getSelectedSensorPosition(0));
    //Climb Arms
    SmartDashboard.putNumber("Climb Arm Position", RobotMap._climbRightArm.getSelectedSensorPosition(0));
    SmartDashboard.putNumber("Climb Arm Speed", RobotMap._elevatorMotor.getSelectedSensorVelocity(0));
    //Vision Targeting
    SmartDashboard.putBoolean("Target Lock", RobotMap.visTargetLock);
    if (RobotMap.visTargetLock){
      SmartDashboard.putNumber("Vision x Offset", RobotMap.visXOffset);
      SmartDashboard.putNumber("Vision Y Offset", RobotMap.visYOffset);
    }
   //Control Mode
   SmartDashboard.putBoolean("Manual Mode", RobotMap.controlManualMode);
  }
}
