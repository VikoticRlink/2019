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

        if (RobotMap.controlManualMode){
  				RobotMap._elevatorMotor.set(ControlMode.PercentOutput, 0.5 * getJoystickWithDeadBand(OI.OperatorRightJoystick()));
        }


    }
    public void Home(){
      RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorLevels[0]);
    }

    public void Level1(){
      if (RobotMap.FourBarOut==true){
        RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorLevels[1]);
      }
    }
    public void Level2(){
      RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorLevels[2]);
    }
    public void Level3(){
      RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorLevels[3]);
    }
    public void StartClimb(){
      RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorClimb[0]);
    }
    public void FinishClimb(){
      RobotMap._elevatorMotor.set(ControlMode.Position, RobotMap.ElevatorClimb[1]);
    }
    public void Goto(int Target){
      RobotMap._elevatorMotor.set(ControlMode.Position, Target);
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
