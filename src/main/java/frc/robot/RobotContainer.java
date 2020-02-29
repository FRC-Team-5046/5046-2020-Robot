/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.ClimberLiftDown;
import frc.robot.commands.ClimberLiftStop;
import frc.robot.commands.ClimberLiftUp;
import frc.robot.commands.ClimberWinchDown;
import frc.robot.commands.ClimberWinchStop;
import frc.robot.commands.ClimberWinchUp;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.ControlPanelPosition;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.IntakeArmDown;
import frc.robot.commands.IntakeArmUp;
import frc.robot.commands.ShootPowerCell;
import frc.robot.commands.ShootPowerCellStop;
import frc.robot.commands.ShooterHoodSetPosition;
import frc.robot.commands.StartIntakeRollers;
import frc.robot.commands.StopIntakeRollers;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ControlPanelSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import static edu.wpi.first.wpilibj.XboxController.Button;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems
        private final DriveSubsystem m_robotDrive = new DriveSubsystem();
        private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
        private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
        private final ControlPanelSubsystem m_controlpanelSubsystem = new ControlPanelSubsystem();
        private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

        // The autonomous routines

        // A simple auto routine that drives forward a specified distance, and then
        // stops.
        private final Command m_simpleAuto = new DriveDistance(AutoConstants.kAutoDriveDistanceInches,
                        AutoConstants.kAutoDriveSpeed, m_robotDrive);

        // A complex auto routine that drives forward, drops a hatch, and then drives
        // backward.
        private final Command m_complexAuto = new ComplexAuto(m_robotDrive); // include other subsytems

        // A chooser for autonomous commands
        SendableChooser<Command> m_chooser = new SendableChooser<>();

        // The driver's controller
        Joystick m_driverController = new Joystick(OIConstants.kDriverControllerPort);
        Joystick m_operatorController = new Joystick(OIConstants.kOperatorControllerPort);

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the button bindings
                configureButtonBindings();

                // Configure default commands
                // Set the default drive command to split-stick arcade drive
                m_robotDrive.setDefaultCommand(
                                // A split-stick arcade command, with forward/backward controlled by the left
                                // hand, and turning controlled by the right.
                                new DefaultDrive(m_robotDrive, () -> m_driverController.getRawAxis(1),
                                                () -> m_driverController.getRawAxis(4)));

                // Add commands to the autonomous command chooser
                m_chooser.addOption("Simple Auto", m_simpleAuto);
                m_chooser.addOption("Complex Auto", m_complexAuto);

                // Put the chooser on the dashboard
                Shuffleboard.getTab("Autonomous").add(m_chooser);
        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by instantiating a {@link GenericHID} or one of its subclasses
         * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
         * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
         */
        private void configureButtonBindings() {
              
                // While holding the shoulder button, drive at half speed
                // new JoystickButton(m_driverController,
                // Button.kBumperRight.value).whenHeld(new HalveDriveSpeed(m_robotDrive));
  
        new JoystickButton(m_driverController, Button.kBumperLeft.value)
                .whenPressed(new ShootPowerCellStop(m_shooterSubsystem));

        new JoystickButton(m_driverController, Button.kBumperRight.value)
                .whenPressed(new ShootPowerCell(m_shooterSubsystem));

        new JoystickButton(m_driverController, Button.kBack.value)
                .whenPressed(new ShooterHoodSetPosition(m_shooterSubsystem, ShooterConstants.kShooterHoodPosition1));

        new JoystickButton(m_driverController, Button.kStart.value)
                .whenPressed(new ShooterHoodSetPosition(m_shooterSubsystem, ShooterConstants.kShooterHoodPosition2));


        new JoystickButton(m_operatorController, Button.kX.value)
                .whenPressed(new StartIntakeRollers(m_intakeSubsystem));

        new JoystickButton(m_operatorController, Button.kB.value)
                .whenPressed(new StopIntakeRollers(m_intakeSubsystem));

        new JoystickButton(m_operatorController, Button.kY.value)
                .whenPressed(new IntakeArmUp(m_intakeSubsystem));
        
        new JoystickButton(m_operatorController, Button.kA.value)
                .whenPressed(new IntakeArmDown(m_intakeSubsystem));

        new JoystickButton(m_operatorController, Button.kStart.value)    
                .whenPressed(new ControlPanelPosition(m_controlpanelSubsystem,0));

        new JoystickButton(m_operatorController, Button.kBack.value)
                .whenPressed(new ControlPanelPosition(m_controlpanelSubsystem,40));






    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
