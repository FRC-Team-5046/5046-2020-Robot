/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.commands.ShooterServoOpen;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.HopperStart;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootPowerCell extends SequentialCommandGroup {
  /**
   * Creates a new ShootPowerCell.
   */
  public ShootPowerCell(ShooterSubsystem m_shooterSubsystem) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super( 
      new ShooterWheelSetSpeed(m_shooterSubsystem, 1),
      new WaitCommand(.5),
      new ShooterServoOpen(m_shooterSubsystem),
      new WaitCommand(.35),
      new HopperStart(m_shooterSubsystem));
  }
 
}
