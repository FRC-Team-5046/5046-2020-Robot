/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.ShooterConstants;
import utilities.CANSparkMaxDrive;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
//import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */
  private final CANSparkMax m_ShooterWheelMotor = new CANSparkMax(ShooterConstants.kShooterWheelMotorID,MotorType.kBrushless);
  private final CANSparkMax m_ShooterWheelMotor2 =new CANSparkMax(ShooterConstants.kShooterWheelMotorFollowerID,MotorType.kBrushless);
  private final CANSparkMax m_ShooterHoodMotor = new CANSparkMax(ShooterConstants.kShooterHoodMotorID,MotorType.kBrushless);
  private final CANSparkMax m_HopperMotor = new CANSparkMax(ShooterConstants.kHopperMotorID,MotorType.kBrushless);
  private final Servo m_ShooterServo = new Servo(ShooterConstants.kShooterServoID);
  private final CANPIDController m_ShooterWheelPIDController = m_ShooterWheelMotor.getPIDController();
//  private final CANEncoder m_ShooterWheelEncoder = m_ShooterWheelMotor.getEncoder();
  private final CANPIDController m_ShooterHoodPIDController = m_ShooterHoodMotor.getPIDController();
// private final CANEncoder m_ShooterHoodEncoder = m_ShooterHoodMotor.getEncoder();


  public ShooterSubsystem() {
    m_ShooterWheelMotor.restoreFactoryDefaults();
    m_ShooterHoodMotor.restoreFactoryDefaults();
    m_HopperMotor.restoreFactoryDefaults();
    m_ShooterWheelMotor.setIdleMode(IdleMode.kCoast);
    m_ShooterHoodMotor.setIdleMode(IdleMode.kBrake);
    m_HopperMotor.setIdleMode(IdleMode.kBrake);
    setInverted();
    setShooterWheelPIDs();
    setShooterHoodPIDs();
    //m_ShooterWheelMotor2.follow(m_ShooterWheelMotor);


  }

  private void setInverted(){
    m_ShooterWheelMotor.setInverted(ShooterConstants.kShooterWheelMotorInverted);
    m_ShooterHoodMotor.setInverted(ShooterConstants.kShooterHoodMotorInverted);
    m_HopperMotor.setInverted(ShooterConstants.kHopperMotorInverted);
    m_ShooterWheelMotor2.setInverted(ShooterConstants.kShooterWheelMotorFollowerInverted);
  }



  public void setAngle(double degrees){
    if (degrees >= ShooterConstants.kShooterServoMin && degrees <= ShooterConstants.kShooterServoMax)
    m_ShooterServo.setAngle(degrees);
    else if (Math.abs(getAngle()-ShooterConstants.kShooterServoMin) < Math.abs(getAngle()-ShooterConstants.kShooterServoMax))
    m_ShooterServo.setAngle(ShooterConstants.kShooterServoMin);
    }
  
  public double getAngle() {
    return m_ShooterServo.getAngle();
    }


  private void setShooterWheelPIDs(){
    m_ShooterWheelPIDController.setP(ShooterConstants.kShooterWheelP);
    m_ShooterWheelPIDController.setI(ShooterConstants.kShooterWheelI);
    m_ShooterWheelPIDController.setD(ShooterConstants.kShooterWheelD);
    m_ShooterWheelPIDController.setIZone(ShooterConstants.kShooterWheelIz);
    m_ShooterWheelPIDController.setFF(ShooterConstants.kShooterWheelFF);
    m_ShooterWheelPIDController.setOutputRange(ShooterConstants.kShooterWheelMinOutput, ShooterConstants.kShooterWheelMaxOutput);

  }

  private void setShooterHoodPIDs(){
    m_ShooterHoodPIDController.setP(ShooterConstants.kShooterHoodP);
    m_ShooterHoodPIDController.setI(ShooterConstants.kShooterHoodI);
    m_ShooterHoodPIDController.setD(ShooterConstants.kShooterHoodD);
    m_ShooterHoodPIDController.setIZone(ShooterConstants.kShooterHoodIz);
    m_ShooterHoodPIDController.setFF(ShooterConstants.kShooterHoodFF);
    m_ShooterHoodPIDController.setOutputRange(ShooterConstants.kShooterHoodMinOutput, ShooterConstants.kShooterHoodMaxOutput);

  }


  public void setShooterWheelVelocity(double inputSetPoint){
    double setPoint = inputSetPoint * ShooterConstants.kShooterWheelMaxRPM;
    m_ShooterWheelPIDController.setReference(setPoint, ControlType.kVelocity);
  }

  public void setShooterHoodPosition(double inputSetPoint){
    double setPoint = inputSetPoint;
    m_ShooterHoodPIDController.setReference(setPoint, ControlType.kPosition);
  }


 


  public void setShooterWheelSpeed(double inputSpeed){
    m_ShooterWheelMotor.set(inputSpeed);
    m_ShooterWheelMotor2.set(-1 * inputSpeed);
    
  }

  public void setHopperSpeed(double inputSpeed){
    m_HopperMotor.set(inputSpeed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

