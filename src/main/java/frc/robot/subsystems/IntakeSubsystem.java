/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;



public class IntakeSubsystem extends SubsystemBase {
  /**
   * Creates a new IntakeSubsystem.
   */

  private final VictorSPX m_intakeRollersMotor = new VictorSPX(IntakeConstants.kIntakeRollersMotorID);
  private final CANSparkMax m_intakeArmMotor = new CANSparkMax(IntakeConstants.kIntakeArmMotorID,MotorType.kBrushless);
  
  private CANDigitalInput m_forwardLimit;
  private CANDigitalInput m_reverseLimit;

  
  public void startIntakeRollers() {
    m_intakeRollersMotor.set(ControlMode.PercentOutput,1);
  }
  public void stopIntakeRollers() {
    m_intakeRollersMotor.set(ControlMode.PercentOutput,0);
  }

  public void intakeArmUp() {
    m_intakeArmMotor.set(-1);
  }
  public void intakeArmDown() {
    m_intakeArmMotor.set(.5);
  }
  public void intakeArmStop() {
    m_intakeArmMotor.set(0);
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
    m_forwardLimit = m_intakeArmMotor.getForwardLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
    m_reverseLimit = m_intakeArmMotor.getReverseLimitSwitch(LimitSwitchPolarity.kNormallyClosed);
  }
  

  public IntakeSubsystem() {
    m_intakeArmMotor.restoreFactoryDefaults();
    setBrakeMode(IntakeConstants.kBrakeMode);
    setLimitMode();

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
