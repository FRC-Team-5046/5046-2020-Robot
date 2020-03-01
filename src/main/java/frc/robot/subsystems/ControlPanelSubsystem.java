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
//import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class ControlPanelSubsystem extends SubsystemBase {
  /**
   * Creates a new ControlPanelSubsystem.
   */

  private final CANSparkMax m_controlpanelMotor = new CANSparkMax(ControlPanel.kControlPanelMotorID,MotorType.kBrushless);
  private final CANPIDController m_controlpanelPIDController = m_controlpanelMotor.getPIDController();
//private final CANEncoder m_controlpanelEncoder = m_controlpanelMotor.getEncoder();
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(ControlPanel.i2cPort);

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ControlPanelSubsystem() {
    m_controlpanelMotor.restoreFactoryDefaults();
    setInverted();
    setBrakeMode(ControlPanel.kControlPanelMotorBrakeMode);
    setPIDs();
    setColors();
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

  public void setColors(){
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    
   
  }

  @Override
  public void periodic() {
 
    // This method will be called once per scheduler run

    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);


  }
}
