/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterWheelSetSpeed extends CommandBase {
  /**
   * Creates a new ShooterWheelSetSpeed.
   */

  private final ShooterSubsystem m_shooterSubsystem;
  private final double setSpeed;

  public ShooterWheelSetSpeed(ShooterSubsystem subsystem, double inputSetSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooterSubsystem = subsystem;
    setSpeed = inputSetSpeed;
    addRequirements(m_shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooterSubsystem.setShooterWheelSpeed(setSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
