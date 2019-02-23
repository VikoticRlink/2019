/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;

//import com.ctre.phoenix.motorcontrol.NeutralMode;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //---- General setup ---//
    public static int robotDirection = 1;
    public static double encoderPerRev = 4096;
    public static final double kSensorUnitsPerRotation = 4096;
    public static double slowSpeed = encoderPerRev / 300 * 100;//last number is RPM, converts to encoder ticks
    public static double standardSpeed = encoderPerRev / 300 * 300;//Ditto
    public static double turboSpeed = encoderPerRev / 300 * 470;//Ditto
    public static Boolean autoControl;
    
    //--- Settings for Encoders ---//
    //find value for Climb
    public static int[] ElevatorLevels= {7000, 6798, 300, -4555, -18723}; //Climb, Level1, Home, Level 2, Level 3
    //find values for Floor and Climb
    public static int[] ClimbArmLevels = {0, 4096, 8192}; //Home, Floor, Climb

    //--- Collision Threshold ---//
    public static double kCollisionThreshold_DeltaG = 0.2f;

    //--- Limit Switches ---//
    public static DigitalInput LimitLowerElevator;
    public static DigitalInput LimitUpperElevator;
    public static DigitalInput LimitLower2Elevator;
    public static DigitalInput LimitUpper2Elevator;

    //--- Pneumatics ---//
    public static Compressor airSupply;
    public static DoubleSolenoid HatchDeployer;
    public static Boolean HatchDeployerOut=false;
    public static DoubleSolenoid FourBarDeployer;
    public static Boolean FourBarOut=false;

  	//--- Motor Controllers ---//
    public static WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(1);
    private static WPI_VictorSPX _rearLeftMotor = new WPI_VictorSPX(11);
    public static WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(2);   
    private static WPI_VictorSPX _rearRightMotor = new WPI_VictorSPX(12);
    public static WPI_TalonSRX _elevatorMotor = new WPI_TalonSRX(3);
    private static WPI_VictorSPX _elevatorSlave = new WPI_VictorSPX(13);
    public static WPI_TalonSRX _climbRightArm = new WPI_TalonSRX(4);
    private static WPI_TalonSRX _climbLeftArm = new WPI_TalonSRX(14);
    public static WPI_TalonSRX _climbRightDrive = new WPI_TalonSRX(5);

    //--- vision targets ---//
    public static boolean visTargetLock=false;
    public static double visXOffset=0;
    public static double visYOffset=0;

    public static void init(){
      autoControl=false;

      //--- Limit Switches ---//
      LimitLowerElevator = new DigitalInput(0);
      LimitUpperElevator = new DigitalInput(1);
      LimitLower2Elevator = new DigitalInput(2);
      LimitUpper2Elevator = new DigitalInput(3);

      //--- Pneumatics ---//
      airSupply = new Compressor(60);
      HatchDeployer = new DoubleSolenoid(60, 0, 1);
    	FourBarDeployer = new DoubleSolenoid(60,2,3);

      //--- Motor Controller Setups ---//
      _frontLeftMotor.configFactoryDefault();
      _frontRightMotor.configFactoryDefault();  
      _rearLeftMotor.configFactoryDefault();
      _rearRightMotor.configFactoryDefault();  
      _elevatorMotor.configFactoryDefault();
      _elevatorSlave.configFactoryDefault();
      _climbLeftArm.configFactoryDefault();
      _climbRightArm.configFactoryDefault();
      _climbRightDrive.configFactoryDefault();
      
      _frontLeftMotor.set(ControlMode.PercentOutput, 0);
      _frontRightMotor.set(ControlMode.PercentOutput, 0);
      _elevatorMotor.set(ControlMode.PercentOutput, 0);
      _climbRightArm.set(ControlMode.PercentOutput, 0);
      _climbLeftArm.set(ControlMode.PercentOutput, 0);
      _climbRightArm.set(ControlMode.PercentOutput, 0);

      _rearLeftMotor.follow(_frontLeftMotor);
      _rearRightMotor.follow(_frontRightMotor);
      _elevatorSlave.follow(_elevatorMotor);
      _climbLeftArm.follow(_climbRightArm);
      
      _rearLeftMotor.setInverted(InvertType.FollowMaster);
      _rearRightMotor.setInverted(InvertType.FollowMaster);
      _elevatorSlave.setInverted(InvertType.FollowMaster);
      _climbLeftArm.setInverted(false);

      _frontLeftMotor.setNeutralMode(NeutralMode.Brake);
      _frontRightMotor.setNeutralMode(NeutralMode.Brake);
      _elevatorMotor.setNeutralMode(NeutralMode.Brake);
      _climbRightArm.setNeutralMode(NeutralMode.Brake);
      _climbRightDrive.setNeutralMode(NeutralMode.Brake);
     
      _frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
      _frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
      _elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      _climbRightArm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
      _climbRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

      _elevatorMotor.setSelectedSensorPosition(0, 0, 10);
      _frontLeftMotor.setSelectedSensorPosition(0, 0, 10);
      _frontRightMotor.setSelectedSensorPosition(0, 0, 10);
      _climbRightArm.setSelectedSensorPosition(0, 0, 10);
      _climbRightDrive.setSelectedSensorPosition(0, 0, 10);

      _frontLeftMotor.setInverted(false); 
      _frontRightMotor.setInverted(true); 
      _elevatorMotor.setInverted(false);
      _climbRightArm.setInverted(false);
      _climbRightDrive.setInverted(false);

      _elevatorMotor.setSensorPhase(true);
      _climbRightArm.setSensorPhase(true);
      _climbRightDrive.setSensorPhase(true);


      //-- PID setup ---//
        _elevatorMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
        _elevatorMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
	    	_elevatorMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
		    _elevatorMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        _elevatorMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _elevatorMotor.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        _elevatorMotor.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        _elevatorMotor.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        _elevatorMotor.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
  
        _climbRightArm.configNominalOutputForward(0, Constants.kTimeoutMs);
        _climbRightArm.configNominalOutputReverse(0, Constants.kTimeoutMs);
	    	_climbRightArm.configPeakOutputForward(1, Constants.kTimeoutMs);
		    _climbRightArm.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        _climbRightArm.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _climbRightArm.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        _climbRightArm.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        _climbRightArm.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        _climbRightArm.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
        
        _climbRightDrive.configNominalOutputForward(0, Constants.kTimeoutMs);
        _climbRightDrive.configNominalOutputReverse(0, Constants.kTimeoutMs);
	    	_climbRightDrive.configPeakOutputForward(1, Constants.kTimeoutMs);
		    _climbRightDrive.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        _climbRightDrive.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _climbRightDrive.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        _climbRightDrive.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        _climbRightDrive.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        _climbRightDrive.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    }
}
