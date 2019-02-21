/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import edu.wpi.first.networktables.NetworkTable;
//import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static Double lltv;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Vision(){
  }
  @Override
  public void periodic(){
    lltv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    if(lltv.intValue()==1){
      RobotMap.visTargetLock=true;
    } else {
      RobotMap.visTargetLock=false;
    }
    if(RobotMap.visTargetLock){
      RobotMap.visXOffset=NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      RobotMap.visYOffset=NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    }else{
      RobotMap.visYOffset=0;
      RobotMap.visXOffset=0;
    }
  }
  public void Stop(){

  }
}
