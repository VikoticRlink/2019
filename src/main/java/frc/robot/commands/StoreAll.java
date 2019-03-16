/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StoreAll extends CommandGroup {
  /**
   * set climber and elevator back to their 0 position, and pull in the 4 bar.
   */
  public StoreAll() {
    addParallel(new ClimberTo(0));
    addParallel(new HatchHeight(0));
    addSequential(new FourBarIn());
  }
}
