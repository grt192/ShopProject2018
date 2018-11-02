package frc.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.config.Config;

public class Tank {

    private static final double TICKS_TO_METERS = 0;
    private static final double WIDTH = 0.73025;

    private TalonSRX leftMotor;
    private TalonSRX rightMotor;

    public Tank() {
        leftMotor = new TalonSRX(Config.getInt("left_master"));
        TalonSRX leftFollower = new TalonSRX(Config.getInt("left_follower"));
        leftFollower.set(ControlMode.Follower, leftMotor.getDeviceID());

        rightMotor = new TalonSRX(Config.getInt("right_master"));
        TalonSRX rightFollower = new TalonSRX(Config.getInt("right_follower"));
        rightFollower.set(ControlMode.Follower, rightMotor.getDeviceID());
    }

    public void set(double lSpeed, double rSpeed) {
        leftMotor.set(ControlMode.Velocity, lSpeed / (TICKS_TO_METERS * 10));
        rightMotor.set(ControlMode.Velocity, rSpeed / (TICKS_TO_METERS * 10));
    }

    public void setPolar(double speed, double angVel) {

    }

}