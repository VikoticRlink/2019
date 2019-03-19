/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
/**
 * Add your docs here.
 */ 
public class Camera extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public Camera(){
    new Thread(() -> {
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      //Set camera resolution.  640x480 may be too high, testing needed
      camera.setResolution(640, 480);
      
      //Steamworks example of putting overlay on the screen
      //https://www.programcreek.com/java-api-examples/index.php%3Fsource_dir=servdroid-master/android/ServDroid.web/src/org/servDroid/server/?code=Team2537/Cogsworth/Cogsworth-master/src/org/usfirst/frc/team2537/robot/cameras/Cameras.java

      
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Cargo", 640, 480);
      
      Mat source = new Mat();
      Mat output = new Mat();
      
      while(!Thread.interrupted()) {
        cvSink.grabFrame(source);

        Imgproc.putText(source, "CARGO", new Point(output.cols() - 75, 25), 4, 0.8, new Scalar(255, 0, 0), 3);
        Imgproc.line(source, new Point((output.cols()/2)-100, 0), new Point((output.cols()/2)-100, output.rows()), new Scalar(255, 0, 0), 1);
        Imgproc.line(source, new Point((output.cols()/2)-99, 0), new Point((output.cols()/2)-100, output.rows()), new Scalar(255, 255, 0), 1);
        Imgproc.line(source, new Point((output.cols()/2)+99, 0), new Point((output.cols()/2)+100, output.rows()), new Scalar(255, 255, 0), 1);
        Imgproc.line(source, new Point((output.cols()/2)+100, 0), new Point((output.cols()/2)+100, output.rows()), new Scalar(255, 0, 0), 1);
           
          //Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
          outputStream.putFrame(output);
          
      }
  }).start();
  }
}
