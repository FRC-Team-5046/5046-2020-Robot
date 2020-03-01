/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDsConstants;
import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;


public class LEDSubsystem extends SubsystemBase {
  /**
   * Creates a new LEDSubsystem.
   */
  CANifier m_Canifier = new CANifier(LEDsConstants.kCANifierID);

  public LEDSubsystem() {

  }

  public void setColorA(){
    m_Canifier.setLEDOutput(50, LEDChannel.LEDChannelA);
  }
  public void setColorB(){
    m_Canifier.setLEDOutput(50, LEDChannel.LEDChannelB);
  }
  public void setColorC(){
    m_Canifier.setLEDOutput(50, LEDChannel.LEDChannelC);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
