/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANPIDController;

import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_leftMotor1 = new CANSparkMax(DriveConstants.kLeftMotor1ID,MotorType.kBrushless);
  private final CANSparkMax m_leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2ID,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor1 = new CANSparkMax(DriveConstants.kRightMotor1ID,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2ID,MotorType.kBrushless);
  
  private final CANPIDController m_leftMotorsPIDController = m_leftMotor1.getPIDController();
  private final CANPIDController m_rightMotorsPIDController = m_rightMotor1.getPIDController();
  private final CANEncoder m_leftCanEncoder = m_leftMotor1.getEncoder();
  private final CANEncoder m_rightCanEncoder = m_rightMotor1.getEncoder();


  // private final SpeedControllerGroup m_leftMotors = 
  //     new SpeedControllerGroup(m_leftMotor1, m_leftMotor2);

  // private final SpeedControllerGroup m_rightMotors = 
  //     new SpeedControllerGroup(m_rightMotor1, m_rightMotor2);

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor1, m_rightMotor1);

  // The left-side drive encoder
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
                  DriveConstants.kLeftEncoderReversed);

  // The right-side drive encoder
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1],
                  DriveConstants.kRightEncoderReversed);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);

    //added for followers
    m_leftMotor2.follow(m_leftMotor1);
    m_rightMotor2.follow(m_rightMotor1);

    setInverted();
    setBrakeMode(DriveConstants.kBrakeMode);
    setPIDs();
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, -rot *.75);
  }

  public void driveDistance(double left,double right){
    m_leftMotorsPIDController.setReference(left, ControlType.kPosition);
    m_rightMotorsPIDController.setReference(right, ControlType.kPosition);
    
  }
  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    return( m_leftCanEncoder.getPosition() + m_rightCanEncoder.getPosition()) / 2.0;

    // return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  private void setInverted() {
		
		m_leftMotor1.setInverted(DriveConstants.kLeftMotorsInverted);
    m_rightMotor1.setInverted(DriveConstants.kRightMotorsInverted);
    m_leftMotor2.setInverted(DriveConstants.kLeftMotorsInverted);
    m_rightMotor2.setInverted(DriveConstants.kRightMotorsInverted);
	  
  }
  
  private void setPIDs(){
    m_leftMotorsPIDController.setP(DriveConstants.kP);
    m_leftMotorsPIDController.setI(DriveConstants.kI);
    m_leftMotorsPIDController.setD(DriveConstants.kD);
    m_leftMotorsPIDController.setIZone(DriveConstants.kIz);
    m_leftMotorsPIDController.setFF(DriveConstants.kFF);
    m_leftMotorsPIDController.setOutputRange(DriveConstants.kMinOutput, DriveConstants.kMaxOutput);
    m_rightMotorsPIDController.setP(DriveConstants.kP);
    m_rightMotorsPIDController.setI(DriveConstants.kI);
    m_rightMotorsPIDController.setD(DriveConstants.kD);
    m_rightMotorsPIDController.setIZone(DriveConstants.kIz);
    m_rightMotorsPIDController.setFF(DriveConstants.kFF);
    m_rightMotorsPIDController.setOutputRange(DriveConstants.kMinOutput, DriveConstants.kMaxOutput);
  }  


  private void setBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
      m_leftMotor1.setIdleMode(IdleMode.kBrake);
      m_leftMotor2.setIdleMode(IdleMode.kBrake);
      m_rightMotor1.setIdleMode(IdleMode.kBrake);
      m_rightMotor2.setIdleMode(IdleMode.kBrake);
    	}
		else
		{
      m_leftMotor1.setIdleMode(IdleMode.kCoast);
      m_leftMotor2.setIdleMode(IdleMode.kCoast);
      m_rightMotor1.setIdleMode(IdleMode.kCoast);
      m_rightMotor2.setIdleMode(IdleMode.kCoast);
    	}
	}
}
