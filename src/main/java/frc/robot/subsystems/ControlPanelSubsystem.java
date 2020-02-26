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


public class ControlPanelSubsystem extends SubsystemBase {
  /**
   * Creates a new ControlPanelSubsystem.
   */

  private final CANSparkMax m_controlpanelMotor = new CANSparkMax(ControlPanel.kControlPanelMotorID,MotorType.kBrushless);


  public ControlPanelSubsystem() {
    m_controlpanelMotor.restoreFactoryDefaults();
    setInverted();
    setBrakeMode(ControlPanel.kControlPanelMotorBrakeMode);

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


  public void spinRight() {
    m_controlpanelMotor.set(1);
  }

  public void spinLeft() {
    m_controlpanelMotor.set(-1);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
