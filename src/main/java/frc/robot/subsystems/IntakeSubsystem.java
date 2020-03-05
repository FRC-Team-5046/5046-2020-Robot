/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANPIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;



public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  private final VictorSPX m_intakeRollersMotor = new VictorSPX(IntakeConstants.kIntakeRollersMotorID);
  private final CANSparkMax m_intakeArmMotor = new CANSparkMax(IntakeConstants.kIntakeArmMotorID,MotorType.kBrushless);
  private final CANPIDController m_intakeArmPIDController = m_intakeArmMotor.getPIDController();
  
  
  public IntakeSubsystem() {
    m_intakeArmMotor.restoreFactoryDefaults();
    setBrakeMode(IntakeConstants.kBrakeMode);
    setLimitMode();
    setPIDs();
  

  }

  public void startIntakeRollers() {
    m_intakeRollersMotor.set(ControlMode.PercentOutput,1);
  }
  public void stopIntakeRollers() {
    m_intakeRollersMotor.set(ControlMode.PercentOutput,0);
  }

  public void intakeArmUp() {
    m_intakeArmMotor.set(-.5);
  }
  public void intakeArmDown() {
    m_intakeArmMotor.set(.25);
  }
  public void intakeArmStop() {
    m_intakeArmMotor.set(0);
  }
  public void intakeArmPostion(double inputSetPoint){
    double setPoint = inputSetPoint;
    m_intakeArmPIDController.setReference(setPoint, ControlType.kPosition);
  }

  private void setBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
      m_intakeArmMotor.setIdleMode(IdleMode.kBrake);
    	}
		else
		{
      m_intakeArmMotor.setIdleMode(IdleMode.kCoast);
    	}
  }

  private void setLimitMode(){
     /**
     * A CANDigitalInput object is constructed using the getForwardLimitSwitch() or
     * getReverseLimitSwitch() method on an existing CANSparkMax object, depending
     * on which direction you would like to limit
     * 
     * Limit switches can be configured to one of two polarities:
     *  com.revrobotics.CANDigitalInput.LimitSwitchPolarity.kNormallyOpen
     *  com.revrobotics.CANDigitalInput.LimitSwitchPolarity.kNormallyClosed
     */
    m_intakeArmMotor.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
    m_intakeArmMotor.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
  }
  
  private void setPIDs(){
    m_intakeArmPIDController.setP(IntakeConstants.kIntakeArmMotorP);
    m_intakeArmPIDController.setI(IntakeConstants.kIntakeArmMotorI);
    m_intakeArmPIDController.setD(IntakeConstants.kIntakeArmMotorD);
    m_intakeArmPIDController.setIZone(IntakeConstants.kIntakeArmMotorIz);
    m_intakeArmPIDController.setFF(IntakeConstants.kIntakeArmMotorFF);
    m_intakeArmPIDController.setOutputRange(IntakeConstants.kIntakeArmMotorMinOutput, IntakeConstants.kIntakeArmMotorMaxOutput);
  }  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Voltage", m_intakeArmMotor.getBusVoltage());
    SmartDashboard.putNumber("Temperature", m_intakeArmMotor.getMotorTemperature());
    SmartDashboard.putNumber("Output", m_intakeArmMotor.getAppliedOutput());
    SmartDashboard.putNumber("Position",m_intakeArmMotor.getEncoder().getPosition());
    
  }
}
