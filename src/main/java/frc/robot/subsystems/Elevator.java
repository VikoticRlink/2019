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
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Elevator(){

  }
  @Override
    public void periodic() {

        if (!RobotMap.autoControl){
          
				RobotMap._elevatorMotor.set(ControlMode.PercentOutput,  getJoystickWithDeadBand(OI.OperatorRightJoystick()));
        }

    }
    public void FourArmDeploy(){

    }
    public void Level1(){
      if (RobotMap.FourBarOut==true){
        RobotMap._elevatorMotor.set(ControlMode.Position, 6798);
      }
    }
    public void Level2(){
      RobotMap._elevatorMotor.set(ControlMode.Position, -4555);
    }
    public void Level3(){
      RobotMap._elevatorMotor.set(ControlMode.Position, -18723);
    }
    public void Climb(){
      RobotMap._elevatorMotor.set(ControlMode.Position, 7000);//We need to find the real value.
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
