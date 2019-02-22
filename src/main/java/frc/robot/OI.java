/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;


public class OI {
	private static XboxController DriverController, OperatorController;
	final Button DriverStart, DriverBack;
	final Button OperatorX, OperatorY, OperatorA, OperatorB, OperatorlBump, OperatorrBump, OperatorBack, OperatorStart;
	
	public OI() {
		DriverController = new XboxController(0);
		DriverBack = new JoystickButton(DriverController, 7);
		DriverStart = new JoystickButton(DriverController, 8);
		//DriverStart.whenPressed(new HatchDeploy(1));
 		//DriverStart.whenHeld(new HatchDeploy(1));

		OperatorController = new XboxController(1);
		OperatorA= new JoystickButton(OperatorController, 1);
	//	OperatorA.whenPressed(new PlaceHatch1());
		OperatorB = new JoystickButton(OperatorController, 2);
	//	OperatorB.whenPressed(new PlaceHatch2());
		OperatorX = new JoystickButton(OperatorController, 3);
	//	OperatorX.whenPressed(new AquireHatch());
		OperatorY = new JoystickButton(OperatorController, 4);
	//	OperatorY.whenPressed(new PlaceHatch3());
		OperatorlBump = new JoystickButton(OperatorController, 5);
		OperatorrBump = new JoystickButton(OperatorController, 6);
		OperatorBack = new JoystickButton(OperatorController, 7);
		OperatorStart = new JoystickButton(OperatorController, 8);
	}
	
	//Driver Code
	public static double DriverLeftJoystick(){
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);	}
	public static double DriverRightJoystick() {
		return DriverController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);	}
	
	public static boolean DriverRightBumper() {return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);}
	public static boolean DriverLeftBumper() {return DriverController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);}
	public static boolean DriverX() {return DriverController.getXButton();}
	public static boolean DriverY() {return DriverController.getYButton();}
	public static boolean DriverA() {return DriverController.getAButton();}
	public static boolean DriverB() {return DriverController.getBButton();}

	public static void DriverRumbleEnhanced(int Duration, double intensity, boolean rLeft, boolean rRight) {
		RumbleEnhanced(DriverController, Duration, intensity, rLeft, rRight);
	}

	//Operator control
	public static double OperatorLeftJoystick(){
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);
	}
	public static double OperatorRightJoystick() {
		return OperatorController.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
	}
	public static void OperatorRumbleEnhanced(int Duration, double intensity, boolean rLeft, boolean rRight) {
		RumbleEnhanced(OperatorController, Duration, intensity, rLeft, rRight);
	}
	

	//----Do not use this section if commands are attached to buttons above -----
	//public static boolean OperatorRightBumper() {return OperatorController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);}
	//public static boolean OperatorLeftBumper() {return OperatorController.getBumper(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft);}
	//public static boolean OperatorX() {return OperatorController.getXButton();}
	//public static boolean OperatorY() {return OperatorController.getYButton();}
	//public static boolean OperatorA() {return OperatorController.getAButton();}
	//public static boolean OperatorB() {return OperatorController.getBButton();}

private static void RumbleEnhanced(XboxController myController, int Duration, double intensity, boolean rLeft, boolean rRight) {
	Thread t1 = new Thread(new Runnable() {
			 public void run() {
			 if (rLeft) {myController.setRumble(GenericHID.RumbleType.kLeftRumble, intensity);}
			 if (rRight) {myController.setRumble(GenericHID.RumbleType.kRightRumble, intensity);}
			try {
				Thread.sleep(Duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (rLeft) {myController.setRumble(GenericHID.RumbleType.kLeftRumble, 0);}
			if (rRight) {myController.setRumble(GenericHID.RumbleType.kRightRumble, 0);}
			 }
	 });
	t1.start();
}
}
