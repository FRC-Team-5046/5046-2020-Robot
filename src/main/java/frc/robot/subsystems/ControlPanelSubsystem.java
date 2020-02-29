/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants.ControlPanel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;


public class ControlPanelSubsystem extends SubsystemBase {
  /**
   * Creates a new ControlPanelSubsystem.
   */

  private final CANSparkMax m_controlpanelMotor = new CANSparkMax(ControlPanel.kControlPanelMotorID,MotorType.kBrushless);
  private final CANPIDController m_controlpanelPIDController = m_controlpanelMotor.getPIDController();
  private final CANEncoder m_controlpanelEncoder = m_controlpanelMotor.getEncoder();

  public ControlPanelSubsystem() {
    m_controlpanelMotor.restoreFactoryDefaults();
    setInverted();
    setBrakeMode(ControlPanel.kControlPanelMotorBrakeMode);
    setPIDs();

  }

  private void setBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
      m_controlpanelMotor.setIdleMode(IdleMode.kBrake);
    	}
		else
		{
      m_controlpanelMotor.setIdleMode(IdleMode.kCoast);
    	}
  }

  private void setInverted() {
		
		m_controlpanelMotor.setInverted(ControlPanel.kControlPanelMotorInverted);
    
  }

  private void setPIDs(){
    m_controlpanelPIDController.setP(ControlPanel.kControlPanelMotorP);
    m_controlpanelPIDController.setI(ControlPanel.kControlPanelMotorI);
    m_controlpanelPIDController.setD(ControlPanel.kControlPanelMotorD);
    m_controlpanelPIDController.setIZone(ControlPanel.kControlPanelMotorIz);
    m_controlpanelPIDController.setFF(ControlPanel.kControlPanelMotorFF);
    m_controlpanelPIDController.setOutputRange(ControlPanel.kControlPanelMotorMinOutput, ControlPanel.kControlPanelMotorMaxOutput);
  
  }


  public void spinRight() {
    m_controlpanelMotor.set(1);
  }

  public void spinLeft() {
    m_controlpanelMotor.set(-1);
  }

  public void setControlPanelPosition(double inputSetPoint){
    double setPoint = inputSetPoint;
    m_controlpanelPIDController.setReference(setPoint, ControlType.kPosition);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
