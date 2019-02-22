/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.OI;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {

      if (!RobotMap.autoControl){
        RobotMap._climbRightArm.set(ControlMode.PercentOutput,  getJoystickWithDeadBand(OI.OperatorLeftJoystick()));
        RobotMap._climbRightDrive.set(ControlMode.PercentOutput,  getJoystickWithDeadBand(OI.OperatorRightTrigger()));
      }

  }

  private double getJoystickWithDeadBand(double joystickvalue) {
    if (Math.abs(joystickvalue)<.1) {
      return 0 * RobotMap.robotDirection;
    } else if (joystickvalue > .9) {
      return 1 * RobotMap.robotDirection;
    }else if (joystickvalue < -0.9) {
      return -1 * RobotMap.robotDirection;
    } else {
      return joystickvalue * RobotMap.robotDirection;
    }
  }
}
