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



public class LEDSubsystem extends SubsystemBase {
  /**
   * Creates a new LEDSubsystem.
   */

private final CANifier m_CaNifier = new CANifier(LEDsConstants.kCANifierID); 

  public LEDSubsystem() {

  }

  public void setColor(double red, double green, double blue){
    double redPercent = red/255;
    double greenPercent = green/255;
    double bluePercent = blue/255;
    m_CaNifier.setLEDOutput(redPercent, CANifier.LEDChannel.LEDChannelA);
    m_CaNifier.setLEDOutput(greenPercent, CANifier.LEDChannel.LEDChannelB);
    m_CaNifier.setLEDOutput(bluePercent, CANifier.LEDChannel.LEDChannelC);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
