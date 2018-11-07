package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.config.Config;

public class Elevator {

    private TalonSRX winchLeft;
    private TalonSRX winchRight;

    public static final int TOP = 10000;
    public static final int MIDDLE = 5000;
    public static final int BOTTOM = 0;

    public Elevator() {
        winchLeft = new TalonSRX(Config.getInt("winch-left"));
        winchRight = new TalonSRX(Config.getInt("winch-right"));

        winchRight.follow(winchLeft);
    }

    public void setElevatorPosition(int position) {
        winchLeft.set(ControlMode.Position, position);
    }

    public void setElevatorPower(double power) {
        // Remeber to mess with direction
        winchLeft.set(ControlMode.PercentOutput, power);
        winchRight.set(ControlMode.Follower, winchLeft.getDeviceID());
    }
}