/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.OI;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class Drivebase extends Subsystem {	
	//public static AHRS ahrs;

	//double last_world_linear_accel_x;
	//double last_world_linear_accel_y;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    //DifferentialDrive MainDrive = new DifferentialDrive(RobotMap._frontLeftMotor, RobotMap._frontRightMotor);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Drivebase(){

  }
  @Override
	public void periodic() {  
		/*double curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
        double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
        last_world_linear_accel_x = curr_world_linear_accel_x;
        double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
        double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
        last_world_linear_accel_y = curr_world_linear_accel_y;
        
        if ( ( Math.abs(currentJerkX) > RobotMap.kCollisionThreshold_DeltaG ) ||
             ( Math.abs(currentJerkY) > RobotMap.kCollisionThreshold_DeltaG) ) {
            OI.DriverRumbleEnhanced(300, 1, true, true);
            OI.OperatorRumbleEnhanced(300, 1, true, true);
		}*/
		
		if (!RobotMap.autoControl){
			DriveWithSpeed();
		} else {
			if (RobotMap.visTargetLock){
				HitTheTarget();
			}
		}
  }
    
  public void Stop() {
		//MainDrive.tankDrive(0, 0);
	//	RobotMap._frontLeftMotor.set(0);
	//	RobotMap._frontRightMotor.set(0);
  }

  public void DriveWithSpeed() {
			//double Velocity = RobotMap.standardSpeed;
			if (OI.DriverLeftBumper()){
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverRightJoystick()));
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput, RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverLeftJoystick()));
			
			}else{
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, RobotMap.maxSpeed * getJoystickWithDeadBand(OI.DriverRightJoystick()));
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput, RobotMap.maxSpeed * getJoystickWithDeadBand(OI.DriverLeftJoystick()));
			}
	}

	public boolean DriveTo(int tickLocation){
		if (tickLocation>0){
			if (tickLocation >= RobotMap._frontRightMotor.getSelectedSensorPosition(0)){
				return true;
			}else{
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, 0.6);
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput, 0.6);
				return false;
			}
		}else{
			if (tickLocation <= RobotMap._frontRightMotor.getSelectedSensorPosition(0)){
				return true;
			}else{
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.6);
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput, -0.6);
				return false;
			}
		}
	}

	public void HitTheTarget(){
		if(RobotMap.visXOffset>0.1){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  .15);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, .2);
		}
		if(RobotMap.visXOffset<-0.1){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  .2);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, .15);
		}
		if((RobotMap.visXOffset<0.1)&&(RobotMap.visXOffset>-0.1)){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  .2);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, .2);
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
