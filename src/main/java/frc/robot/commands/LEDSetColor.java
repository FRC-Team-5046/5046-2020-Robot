/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class LEDSetColor extends CommandBase {
  /**
   * Creates a new LEDSetColor.
   */

   private final LEDSubsystem m_ledSubsystem;
   private final int red;
   private final int green;
   private final int blue;
  

  public LEDSetColor(LEDSubsystem subsystem,int inputRed,int inputGreen,int inputBlue) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ledSubsystem = subsystem;
    addRequirements(m_ledSubsystem);
    red = inputRed;
    green = inputGreen;
    blue = inputBlue;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ledSubsystem.setColor(red, green, blue);
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
