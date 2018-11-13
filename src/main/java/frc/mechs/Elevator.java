package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.config.Config;

public class Elevator {

    private TalonSRX winchLeft;
    private TalonSRX winchRight;

    public static final int topFloor = Config.getInt("top_floor_elevator");
    public static final int middleFloor = Config.getInt("middle_floor_elevator");
    public static final int groundFloor = Config.getInt("ground_floor_elevator");

    public static final int upTray = Config.getInt("tray_up");
    public static final int downTray = Config.getInt("tray_down");

    private TalonSRX trayPivot;

    public Elevator() {
        winchLeft = new TalonSRX(Config.getInt("winch-left"));
        winchRight = new TalonSRX(Config.getInt("winch-right"));

        trayPivot = new TalonSRX(Config.getInt("tray-pivot"));
    }

    public enum ElevatorPosition {
        GROUND, MIDDLE, TOP
    }

    public void setElevatorPosition(ElevatorPosition position) {
        // Set position
        switch (position) {
        case GROUND:
            // Go to ground level
            break;

        case MIDDLE:
            // go to Middle level
            break;

        case TOP:
            // go to top level
            break;

        default:
            System.out.println("Elevator Position did not like to work");
        }
    }

    public void setElevatorPower(double power) {
        // Remeber to mess with direction
        winchLeft.set(ControlMode.PercentOutput, power);
        winchRight.set(ControlMode.Follower, winchLeft.getDeviceID());
    }

    public void setTrayPosition(int i) {
        // Set position of Tray
    }

    public void setTrayPower(double power) {
        trayPivot.set(ControlMode.PercentOutput, power);
    }
}