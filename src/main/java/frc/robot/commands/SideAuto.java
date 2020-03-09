/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import frc.robot.Constants.ShooterConstants;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives backward.
 */
public class SideAuto extends SequentialCommandGroup {
  /**
   * Creates a new ComplexAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public SideAuto(DriveSubsystem drive, IntakeSubsystem intake, ShooterSubsystem shooter)  //after drive, include other subsystems
   {
    addCommands(
        // Drive forward the specified distance
        // new DriveDistance(AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed,
        //                   drive),

        // Release the hatch
        //new ReleaseHatch(hatch),

        // Drive backward the specified distance
        // new DriveDistance(AutoConstants.kAutoBackupDistanceInches, -AutoConstants.kAutoDriveSpeed,
                          // drive));
        new IntakeArmDown(intake), 
        new WaitCommand(1),   
        new ShooterHoodSetPosition(shooter, ShooterConstants.kShooterHoodPosition2 ), 
        new ShootPowerCell(shooter),  
        new WaitCommand(3),   
        new ShootPowerCellStop(shooter),
        new WaitCommand(1));      
        //new DrivePosition(drive,10,-10));
  }

}
