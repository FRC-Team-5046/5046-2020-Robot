/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

public class ClimberSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimberSubsystem.
   */

  private final VictorSPX m_climberLiftMotor = new VictorSPX(ClimberConstants.kClimberLiftMotorID);
  private final CANSparkMax m_climberWinchMotor = new CANSparkMax(ClimberConstants.kClimberWinchMoterID,MotorType.kBrushless);


  public ClimberSubsystem() {
    m_climberWinchMotor.restoreFactoryDefaults();
    setInverted();
    setLiftBrakeMode(ClimberConstants.kClimberLiftMotorBrakeMode);
    setWinchBrakeMode(ClimberConstants.kClimberWinchMotorBrakeMode);
  }

  private void setWinchBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
      m_climberWinchMotor.setIdleMode(IdleMode.kBrake);
    	}
		else
		{
      m_climberWinchMotor.setIdleMode(IdleMode.kCoast);
    	}
  }
  private void setLiftBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
      m_climberLiftMotor.setNeutralMode(NeutralMode.Brake);
    	}
		else
		{
      m_climberLiftMotor.setNeutralMode(NeutralMode.Coast);
    	}
  }

  private void setInverted() {
		
		m_climberWinchMotor.setInverted(ClimberConstants.kClimberWinchMotorInverted);
    m_climberLiftMotor.setInverted(ClimberConstants.kClimberLiftMotorInverted);
	  
  }

  public void climberWinchUp() {
    m_climberWinchMotor.set(-1);
  }
  public void climberWinchDown() {
    m_climberWinchMotor.set(.5);
  }
  public void climberWinchStop() {
    m_climberWinchMotor.set(0);
  }


  public void climberWinchSpeed(double speed){
    m_climberWinchMotor.set(speed);
  }

  public void climberLiftUp() {
    m_climberLiftMotor.set(ControlMode.PercentOutput,1);
  }
  public void climberLiftDown() {
    m_climberLiftMotor.set(ControlMode.PercentOutput,-1);
  }
  public void climberLiftStop() {
    m_climberLiftMotor.set(ControlMode.PercentOutput,0);
  }

  public void climberManual(double winch, double lift){
    
    m_climberLiftMotor.set(ControlMode.PercentOutput,lift);
    m_climberWinchMotor.set(winch);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
