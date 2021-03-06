/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {


  
  public static final class ClimberConstants {
    public static final int kClimberWinchMoterID = 9; //sparkMax

    public static final boolean kClimberWinchMotorBrakeMode = true;
    public static final boolean kClimberWinchMotorInverted = true;


    public static final int kClimberLiftMotorID = 11;  //victorSPX

    public static final boolean kClimberLiftMotorBrakeMode = true;
    public static final boolean kClimberLiftMotorInverted = true;

  }

  public static final class ControlPanel {
    public static final int kControlPanelMotorID = 10; //sparkMax

    public static final boolean kControlPanelMotorBrakeMode = true;
    public static final boolean kControlPanelMotorInverted = true;

    public static final double kControlPanelMotorP = 1;
    public static final int kControlPanelMotorI = 0;
    public static final int kControlPanelMotorD = 0;
    public static final int kControlPanelMotorIz = 0;
    public static final double kControlPanelMotorFF = 0.000015;
    public static final double kControlPanelMotorMinOutput = -1;
    public static final double kControlPanelMotorMaxOutput = 1;
    public static final double kControlPanelMotorMaxPosition = 25;
    public static final int kControlPanelMotorMinPosition = 0;
    public static final int kControlPanelMotorPosition1 = 0;
    public static final int kControlPanelMotorPosition2 = 15;
    public static final int kControlPanelMotorPosition3 = 20;



  }

  public static final class DriveConstants {
    public static final int kLeftMotor1ID = 1; //sparkMax
    public static final int kLeftMotor2ID = 2; //sparkMax
    public static final int kRightMotor1ID = 3; //sparkMax
    public static final int kRightMotor2ID = 4; //sparkMax

    public static final boolean kLeftMotorsInverted = true;
    public static final boolean kRightMotorsInverted = true;

    public static final boolean kBrakeMode = true;

    public static final int[] kLeftEncoderPorts = new int[]{0, 1};
    public static final int[] kRightEncoderPorts = new int[]{2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;
  }

    public static final class IntakeConstants {

      public static final int kIntakeRollersMotorID =  12; 

      public static final int kIntakeArmMotorID = 7; //sparkMax
      public static final boolean kBrakeMode = true;
  }


  public static final class ShooterConstants {
    public static final int kShooterWheelMotorID = 6; //sparkMax
    public static final boolean kShooterWheelMotorBrakeMode = true;
    public static final boolean kShooterWheelMotorInverted = true;
    public static final double kShooterWheelP = 6e-5;
    public static final int kShooterWheelI = 0;
    public static final int kShooterWheelD = 0;
    public static final int kShooterWheelIz = 0;
    public static final double kShooterWheelFF = 0.000015;
    public static final int kShooterWheelMaxOutput = 1;
    public static final int kShooterWheelMinOutput = -1;
    public static final int kShooterWheelMaxRPM = 5700;
    

    public static final int kShooterHoodMotorID = 8; //sparkMax
    public static final boolean kShooterHoodMotorBrakeMode = true;
    public static final boolean kShooterHoodMotorInverted = true;
    public static final double kShooterHoodP = 1;
    public static final int kShooterHoodI = 0;
    public static final int kShooterHoodD = 0;
    public static final int kShooterHoodIz = 0;
    public static final double kShooterHoodFF = 0.000015;
    public static final double kShooterHoodMinOutput = -.5;
    public static final double kShooterHoodMaxOutput = .5;
    public static final double kShooterHoodMaxPosition = 25;
    public static final int kShooterHoodMinPosition = 0;
    public static final int kShooterHoodPosition1 = 0;
    public static final int kShooterHoodPosition2 = 15;
    public static final int kShooterHoodPosition3 = 20;

    public static final int kHopperMotorID = 5; //sparkMax
    public static final boolean kHopperMotorBrakeMode = true;
    public static final boolean kHopperMotorInverted = true;

    public static final int kShooterServoID = 9; //servo
    public static final double kShooterServoMin = 1;
    public static final double kShooterServoMax = 100;
    public static final double kShooterServoOpen = 5;
    public static final double kShooterServoClosed = 48;
    
  }



  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }
}
