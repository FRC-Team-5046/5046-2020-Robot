/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import javax.management.modelmbean.RequiredModelMBean;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTankStop extends CommandBase {
  private final DriveSubsystem m_drive;

  private final DoubleSupplier fwd;
  private final DoubleSupplier rot;
  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveTankStop(DriveSubsystem drive, DoubleSupplier forward, DoubleSupplier rotation) {
      m_drive = drive;
      fwd = forward;
      rot = rotation;
      addRequirements(m_drive);
      
  }

  @Override
  public void initialize() {
     
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(0,0);
  }
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0,0); 
   }

  @Override
  public boolean isFinished() {
    m_drive.arcadeDrive(0,0);
    return true;
  }
}
