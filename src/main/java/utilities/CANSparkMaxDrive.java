/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package utilities;


import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class CANSparkMaxDrive {
    CANSparkMax k_Motor1,k_Motor2,k_Motor3,k_Motor4,k_Motor5,k_Motor6;

DriveMode driveMode;
    public static enum DriveMode{
        TWO,FOUR,SIX;
    }

    public CANSparkMaxDrive(CANSparkMax right, CANSparkMax left){
        driveMode = DriveMode.TWO;
        k_Motor1 = right;
        k_Motor2 = left;

    }

    public CANSparkMaxDrive(CANSparkMax rightFront, CANSparkMax rightBack, CANSparkMax leftFront, CANSparkMax leftBack){
        driveMode = DriveMode.FOUR;
        k_Motor1 = rightFront;
        k_Motor2 = rightBack;
        k_Motor3 = leftFront;
        k_Motor4 = leftBack;

    }

    public CANSparkMaxDrive(CANSparkMax rightFront, CANSparkMax rightMiddle, CANSparkMax rightBack, CANSparkMax leftFront, CANSparkMax leftMiddle, CANSparkMax leftBack){
        driveMode = DriveMode.SIX;
        k_Motor1 = rightFront;
        k_Motor2 = rightMiddle;
        k_Motor3 = rightBack;
        k_Motor4 = leftFront;
        k_Motor5 = leftMiddle;
        k_Motor6 = leftBack;
        
    }

    public void setMotorOutputs(double right, double left)
	{
		if (driveMode == DriveMode.TWO)
		{
			k_Motor1.set(right);
			k_Motor2.set(left);

		}
		if (driveMode == DriveMode.FOUR)
		{
			k_Motor1.set(right);
			k_Motor2.set(right);
			k_Motor3.set(left);
			k_Motor4.set(left);
		}
		if (driveMode == DriveMode.SIX)
		 {
			k_Motor1.set(right);
			k_Motor2.set(right);
			k_Motor3.set(right);
			k_Motor4.set(left);
			k_Motor5.set(left);
			k_Motor6.set(left);

		}
	}

	public double limitValue(double value){
		if (value > 1.0)
			value = 1.0;
		if (value < -1.0)
			value = -1.0;
		return value;
	} 




    public void driveArcade(double moveValue, double rotateValue){
		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limitValue(moveValue);
		rotateValue = limitValue(rotateValue);
		
		if (moveValue >= 0.0)
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			}
			else
			{
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		}
		else
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
			else
			{
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}
		setMotorOutputs(rightMotorSpeed, leftMotorSpeed);
    }
    
    public void driveTank(double leftSpeed, double rightSpeed){
        double leftMotorSpeed;
        double rightMotorSpeed;

        leftMotorSpeed = leftSpeed;
        rightMotorSpeed = rightSpeed;

        setMotorOutputs(rightMotorSpeed, leftMotorSpeed);

    }


}

