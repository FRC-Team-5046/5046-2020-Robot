/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;


import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DrivePosition extends CommandBase {
  private final DriveSubsystem m_drive;
  private final int m_left;
  private final int m_right;
  private final double m_distance;


  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DrivePosition(DriveSubsystem subsystem, int i, int j) {
    m_drive = subsystem;
    m_left = i;
    m_right = j;
    addRequirements(m_drive);
    m_distance = 40;
  }

  @Override
  public void execute() {
   
    m_drive.driveDistance(m_left, m_right);
  }

  
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    m_drive.arcadeDrive(0, 0);
    return Math.abs(m_drive.getAverageEncoderDistance()) >= m_distance;
  }

}
