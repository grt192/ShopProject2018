package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.config.Config;

import java.lang.Math;

public class Arm {

    private TalonSRX armMotorU;

    private String whichMotor;

    private double targetAngle;

    public Arm(String whichMotor) {

        armMotorU = new TalonSRX(Config.getInt("armMotor" + whichMotor));

    }

    public void rotateArm(double radians) {

        double targetPos = radians / (Math.PI * 2);

    }

}
