/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void FourBarDeploy() {
    RobotMap.FourBarDeployer.set(DoubleSolenoid.Value.kForward);
    RobotMap.FourBarOut=true;
  }
  public void FourBarStore() {
    if (RobotMap._elevatorMotor.getSelectedSensorPosition(0)<=0){
      RobotMap.FourBarDeployer.set(DoubleSolenoid.Value.kReverse);
      RobotMap.FourBarOut=false;
    }
  }  
  public void PistonDeploy() {
    if (RobotMap.FourBarOut){
      RobotMap.HatchDeployer.set(DoubleSolenoid.Value.kForward);
      RobotMap.HatchDeployerOut=true;
    }
  }
  public void PistonStore() {
    RobotMap.HatchDeployer.set(DoubleSolenoid.Value.kReverse);
    RobotMap.HatchDeployerOut=false;
  }
  public void CargoDeploy() {
      RobotMap.CargoDeployer.set(DoubleSolenoid.Value.kForward);
      RobotMap.Deployer=true;
  }
  public void CargoStore() {
    RobotMap.CargoDeployer.set(DoubleSolenoid.Value.kReverse);
    RobotMap.Deployer=false;
  }
}
