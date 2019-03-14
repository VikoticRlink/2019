/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
  public static Climber m_Climber;
  public static Dashboard m_Dashboard;
  public static Drivebase m_Drivebase;
  public static Elevator m_Elevator;
  public static Pneumatics m_Pneumatics;
  public static Vision m_Vision;
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    RobotMap.init();
    m_Climber = new Climber();
    m_Dashboard = new Dashboard();
    m_Drivebase = new Drivebase();
    m_Elevator = new Elevator();
    m_Pneumatics = new Pneumatics();
    m_Vision = new Vision();
    m_oi = new OI();
    //Set pneumatics to "start" positions
    m_Pneumatics.FourBarStore();
    m_Pneumatics.PistonStore();
    
    // Testing Buttons
        //SmartDashboard.putData("Elevator 1", new HatchHeight(1));
        //SmartDashboard.putData("Elevator 2", new HatchHeight(2));
        //SmartDashboard.putData("Elevator 3", new HatchHeight(3));
        SmartDashboard.putData("Deploy Fourbar", new FourBar(true));
        SmartDashboard.putData("Retract Fourbar", new FourBar(false));
        //SmartDashboard.putData("Deploy BotFace", new BotFace(true));
        //SmartDashboard.putData("Retract BotFace", new BotFace(false));
        SmartDashboard.putData("Deploy pistons", new HatchEject(true));
        SmartDashboard.putData("Retract pistons", new HatchEject(false));
        SmartDashboard.putData("Zero Elevator", new ZeroElevator());
        //SmartDashboard.putData("Climber to Home", new ClimberTo(0));
        //SmartDashboard.putData("Climber to Floor", new ClimberTo(1));
        //SmartDashboard.putData("Climber to Climb", new ClimberTo(2));
        SmartDashboard.putData("Abort Commands", new AbortAll());
        //SmartDashboard.putData("Store All", new StoreAll());
        SmartDashboard.putData("Drive to Port", new DriveToPort());
        //SmartDashboard.putData("Drive to Port A", new DriveToPortA());
        //SmartDashboard.putData("Ready To Climb", new ReadyToClimb());
        SmartDashboard.putData("HAB 1-2", new HatchHeight(RobotMap.ElevatorClimb[0]));
        SmartDashboard.putData("HAB 1-3", new HatchHeight(RobotMap.ElevatorClimb[2]));
        SmartDashboard.putData("HAB 2-3", new HatchHeight(RobotMap.ElevatorClimb[1]));
    // Remove before going live.

    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {    
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(2);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    RobotMap._elevatorMotor.setSelectedSensorPosition(0, 0, 10);
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
