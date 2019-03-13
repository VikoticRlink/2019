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
    public static double maxSpeed = 0.6;
    public static double slowSpeed = 0.3;
    public static double encoderPerRev = 4096;
    public static final double kSensorUnitsPerRotation = 4096;
    public static Boolean autoControl=false;
    public static Boolean controlManualMode=false;
    
    //--- Settings for Encoders ---//
    //find value for Climb
    //public static int[] ElevatorLevels= {7000, 6798, 300, -4555, -18723}; //Climb, Level1, Home, Level 2, Level 3
    public static int[] ElevatorLevels= {-500, 8000, -6795, -20800}; //Home, Level1, Level 2, Level 3
    public static int[] ElevatorClimb={5590, 1500, -2000};//HAB1-2, HAB2-3, HAB 1-3
    public static int ElevatorError = 150;
    //find values for Floor and Climb
    public static int[] ClimbArmLevels = {0, -538000, -538000}; //Home, HAB2, HAB3
    public static int ClimbError = 300;

    //--- Collision Threshold ---//
    public static double kCollisionThreshold_DeltaG = 0.4f;

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
    public static WPI_TalonSRX _climbWorm = new WPI_TalonSRX(4);
    public static WPI_TalonSRX _climbDrive = new WPI_TalonSRX(14);

    //--- vision targets ---//
    public static boolean visTargetLock=false;
    public static double visXOffset=0;
    public static double visYOffset=0;

    public static void init(){
      autoControl=false;

      //--- Limit Switches ---//
      LimitLowerElevator = new DigitalInput(0);
      LimitLower2Elevator = new DigitalInput(1);
      LimitUpperElevator = new DigitalInput(2);
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
     _climbWorm.configFactoryDefault();
      _climbDrive.configFactoryDefault();
      
      _frontLeftMotor.set(ControlMode.PercentOutput, 0);
      _frontRightMotor.set(ControlMode.PercentOutput, 0);
      _elevatorMotor.set(ControlMode.PercentOutput, 0);
      _climbWorm.set(ControlMode.PercentOutput, 0);
      _climbWorm.set(ControlMode.Position, 0);

      _rearLeftMotor.follow(_frontLeftMotor);
      _rearRightMotor.follow(_frontRightMotor);
      _elevatorSlave.follow(_elevatorMotor);
      
      _rearLeftMotor.setInverted(InvertType.FollowMaster);
      _rearRightMotor.setInverted(InvertType.FollowMaster);
      _elevatorSlave.setInverted(InvertType.FollowMaster);

      _frontLeftMotor.setNeutralMode(NeutralMode.Brake);
      _frontRightMotor.setNeutralMode(NeutralMode.Brake);
      _elevatorMotor.setNeutralMode(NeutralMode.Brake);
      _climbWorm.setNeutralMode(NeutralMode.Brake);
      _climbDrive.setNeutralMode(NeutralMode.Brake);
     
      _frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
      _frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
      _elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      _climbWorm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
      _climbDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

      _elevatorMotor.setSelectedSensorPosition(0, 0, 10);
      _frontLeftMotor.setSelectedSensorPosition(0, 0, 10);
      _frontRightMotor.setSelectedSensorPosition(0, 0, 10);
      _climbWorm.setSelectedSensorPosition(0, 0, 10);
      _climbDrive.setSelectedSensorPosition(0, 0, 10);

      _frontLeftMotor.setInverted(false); 
      _frontRightMotor.setInverted(true); 
      _elevatorMotor.setInverted(false);
      _climbWorm.setInverted(false);

      _elevatorMotor.setSensorPhase(true);
      _climbDrive.setSensorPhase(true);


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
  
        _climbWorm.configNominalOutputForward(0, Constants.kTimeoutMs);
        _climbWorm.configNominalOutputReverse(0, Constants.kTimeoutMs);
	    	_climbWorm.configPeakOutputForward(1, Constants.kTimeoutMs);
		    _climbWorm.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        _climbWorm.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _climbWorm.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        _climbWorm.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        _climbWorm.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        _climbWorm.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
        
        _climbDrive.configNominalOutputForward(0, Constants.kTimeoutMs);
        _climbDrive.configNominalOutputReverse(0, Constants.kTimeoutMs);
	    	_climbDrive.configPeakOutputForward(1, Constants.kTimeoutMs);
		    _climbDrive.configPeakOutputReverse(-1, Constants.kTimeoutMs);
        _climbDrive.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _climbDrive.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
        _climbDrive.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
        _climbDrive.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
        _climbDrive.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    }
}
