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
//import com.kauailabs.navx.frc.AHRS;

/**
 * Add your docs here.
 */
public class Drivebase extends Subsystem {	
	
	double last_world_linear_accel_x;
	double last_world_linear_accel_y;
	//Testing Arcade drive for limelight.
	//private DifferentialDrive MainDrive = new DifferentialDrive(RobotMap._frontLeftMotor, RobotMap._frontRightMotor);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Drivebase(){

  }
  @Override
	public void periodic() {  
		if (!RobotMap.autoControl){
			DriveWithSpeed();
		}
  }
    
  public void Stop() {
	//	MainDrive.tankDrive(0, 0);
	//	RobotMap._frontLeftMotor.set(0);
	//	RobotMap._frontRightMotor.set(0);
  }

  public void DriveWithSpeed() {
			if (OI.DriverLeftBumper()){
			//	MainDrive.tankDrive(RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverLeftJoystick()), RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverRightJoystick()));
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverRightJoystick()));
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput, RobotMap.slowSpeed * getJoystickWithDeadBand(OI.DriverLeftJoystick()));
			}else{
			//	MainDrive.tankDrive(RobotMap.maxSpeed * getJoystickWithDeadBand(OI.DriverLeftJoystick()), RobotMap.maxSpeed * getJoystickWithDeadBand(OI.DriverRightJoystick()));
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
		
		RobotMap.autoControl=true;
		
		if (Math.abs(RobotMap.visXOffset)<0.1){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.6);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.6);
		}else{
			if(RobotMap.visXOffset>0){
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.4);
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.5);
			}else{
				RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.5);
				RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.4);
			}
		}

		//old drive code
		/*if(RobotMap.visXOffset<0.5){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.4);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.5);
		}
		if(RobotMap.visXOffset>-0.5){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.5);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.4);
		}
		if((RobotMap.visXOffset<0.1)&&(RobotMap.visXOffset>-0.1)){
			RobotMap._frontRightMotor.set(ControlMode.PercentOutput,  -0.6);
			RobotMap._frontLeftMotor.set(ControlMode.PercentOutput, -0.6);
		}*/
	}
	/*public void HitTheTargetA(){
		final double STEER_K = 0.03;                    // how hard to turn toward the target
		final double DRIVE_K = 0.26;   					// how hard to drive fwd toward the target
		final double MAX_DRIVE = 0.6;                   // Simple speed limit so we don't drive too fast

		MainDrive.arcadeDrive(MAX_DRIVE * DRIVE_K, RobotMap.visXOffset * STEER_K);
	}*/

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
