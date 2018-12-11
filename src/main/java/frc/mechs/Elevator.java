package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.config.Config;

public class Elevator {

    private TalonSRX winch;

    // public static final int TOP = 10000;
    // public static final int MIDDLE = 5000;
    // public static final int BOTTOM = 0;

    public boolean modePosition = false;

    public Elevator() {
        winch = new TalonSRX(Config.getInt("winch"));

        winch.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,
                winch.getDeviceID());
        // winchLeft.config_kP(0, 1, 0);
        // winchLeft.config_kI(0, 0, 0);
        // winchLeft.config_kD(0, 0, 0);

        // winchLeft.config_kF(0, 0, 0);

        // winchRight.follow(winchLeft);

    }

    // public void setElevatorPosition(int position) {
    // modePosition = true;

    // if (position == TOP) {
    // winch.set(ControlMode.PercentOutput, 100);
    // }
    // modePosition = true;
    // winch.set(ControlMode.Position, position);
    // }

    public void setElevatorPower(double power) {
        // Remeber to mess with direction

        modePosition = false;

        if (power < 0 /* going away from limit switch */) {
            winch.overrideLimitSwitchesEnable(true);
        } else {
            winch.overrideLimitSwitchesEnable(false);

        }
        winch.set(ControlMode.PercentOutput, power);
    }
}