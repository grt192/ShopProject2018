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

    public boolean modePosition = false;

    public Elevator() {
        winchLeft = new TalonSRX(Config.getInt("winch_left"));
        winchRight = new TalonSRX(Config.getInt("winch_right"));

        winchLeft.config_kP(0, 1, 0);
        winchLeft.config_kI(0, 0, 0);
        winchLeft.config_kD(0, 0, 0);

        winchLeft.config_kF(0, 0, 0);

        winchRight.follow(winchLeft);

    }

    public void setElevatorPosition(int position) {
        modePosition = true;
        winchLeft.set(ControlMode.Position, position);
    }

    public void setElevatorPower(double power) {
        // Remeber to mess with direction
        modePosition = false;
        winchLeft.set(ControlMode.PercentOutput, power);
    }
}