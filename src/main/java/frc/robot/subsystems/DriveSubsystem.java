/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import utilities.CANSparkMaxDrive;


import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANPIDController;

import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {

  private CANSparkMaxDrive drive;  //Setup DriveTrain Utility


  
  private final CANSparkMax m_leftMotor1 = new CANSparkMax(DriveConstants.kLeftMotor1ID,MotorType.kBrushless);
  private final CANSparkMax m_leftMotor2 = new CANSparkMax(DriveConstants.kLeftMotor2ID,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor1 = new CANSparkMax(DriveConstants.kRightMotor1ID,MotorType.kBrushless);
  private final CANSparkMax m_rightMotor2 = new CANSparkMax(DriveConstants.kRightMotor2ID,MotorType.kBrushless);
  

  private final CANPIDController m_leftMotorsPIDController = m_leftMotor1.getPIDController();
  private final CANPIDController m_rightMotorsPIDController = m_rightMotor1.getPIDController();
  // private final CANEncoder m_leftCanEncoder = m_leftMotor1.getEncoder();
  // private final CANEncoder m_rightCanEncoder = m_rightMotor1.getEncoder();





  public DriveSubsystem() {
    setFollower();
    setInverted();
    setBrakeMode(DriveConstants.kBrakeMode);
    setPIDs();

    drive = new CANSparkMaxDrive(m_rightMotor1,m_leftMotor1);

  }

  public void setFollower(){
    m_leftMotor2.follow(m_leftMotor1);
    m_rightMotor2.follow(m_rightMotor1);
  }

    private void setInverted() {
		m_leftMotor1.setInverted(DriveConstants.kLeftMotorsInverted);
    m_rightMotor1.setInverted(DriveConstants.kRightMotorsInverted);
    m_leftMotor2.setInverted(DriveConstants.kLeftMotorsInverted);
    m_rightMotor2.setInverted(DriveConstants.kRightMotorsInverted);
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

	//converts inches to encoder pulses (needs to be tuned to the pulses of your encoder
	public double inchToEncoder(double inches)
	{
		System.out.println("inchtoencoder: "+(inches / DriveConstants.kWheelCicumference) * ((double) DriveConstants.kEncoderCPR * DriveConstants.kGearboxRatio));
    return (inches / DriveConstants.kWheelCicumference) * ((double) DriveConstants.kEncoderCPR * DriveConstants.kGearboxRatio);
    
    
	}
	
	//converts degrees to encoder pulses
	public double degreesToEncoder(double degrees)
	{
		return inchToEncoder((DriveConstants.kRobotCircumference / 360) * degrees);
	}
	



	//Sets the distance that you are trying to reach in inches
	public void setSetpointDrive(double setpointinches)
	{
		System.out.println("Target "+inchToEncoder(setpointinches));

    m_leftMotorsPIDController.setReference(inchToEncoder(setpointinches), ControlType.kPosition);
    m_rightMotorsPIDController.setReference(inchToEncoder(setpointinches), ControlType.kPosition);
    
	}


  public void setSetpointTurn(double setpointdegrees)
	{
		// zeroEncoders();
		// zeroGyro();

		System.out.println("setpoint: " + degreesToEncoder(setpointdegrees));

    m_leftMotorsPIDController.setReference(inchToEncoder(-setpointdegrees), ControlType.kPosition);
    m_rightMotorsPIDController.setReference(inchToEncoder(setpointdegrees), ControlType.kPosition);
	}
	




  public void arcadeDrive(double throttle, double wheel){
    drive.driveArcade(throttle, wheel);
  }

}


//   }

//   /**
//    * Drives the robot using arcade controls.
//    *
//    * @param d the commanded forward movement
//    * @param e the commanded rotation
//    */
//   public void arcadeDrive(double d, double e) {
//     m_drive.arcadeDrive(d, -e *.75);
//   }



//   public void driveDistance(double left,double right){
//     m_leftMotorsPIDController.setReference(left, ControlType.kPosition);
//     m_rightMotorsPIDController.setReference(right, ControlType.kPosition);
//     //m_drive.feedWatchdog();
    
//   }
//   /**
//    * Resets the drive encoders to currently read a position of 0.
//    */
//   public void resetEncoders() {
//     m_leftEncoder.reset();
//     m_rightEncoder.reset();
//   }

//   /**
//    * Gets the average distance of the TWO encoders.
//    *
//    * @return the average of the TWO encoder readings
//    */
//   public double getAverageEncoderDistance() {
//     return( m_leftCanEncoder.getPosition() + m_rightCanEncoder.getPosition()) / 2.0;

//     // return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
//   }

//   /**
//    * Gets the left drive encoder.
//    *
//    * @return the left drive encoder
//    */
//   public Encoder getLeftEncoder() {
//     return m_leftEncoder;
//   }

//   /**
//    * Gets the right drive encoder.
//    *
//    * @return the right drive encoder
//    */
//   public Encoder getRightEncoder() {
//     return m_rightEncoder;
//   }




  



// public void arcadeDrive(int i, int j) {
// }

