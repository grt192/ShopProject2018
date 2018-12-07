package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.config.Config;

public class Pickup {

    private Solenoid pickupLeft;
    private Solenoid pickupRight;

    private TalonSRX pickupPivot;
    public static final int downPosition = Config.getInt("pick_up_position_down");
    public static final int upPosition = Config.getInt("pick_up_position_up");

    public static final int armDown = 10000;
    public static final int armUp = 5000;

    public boolean modePosition;

    public Pickup() {
        pickupLeft = new Solenoid(Config.getInt("pickup_left"));
        pickupRight = new Solenoid(Config.getInt("pickup_right"));

        pickupPivot = new TalonSRX(Config.getInt("pickup_pivot"));

        // pickupPivot.config_kP(0, 1, 0);
        // pickupPivot.config_kI(0, 0, 0);
        // pickupPivot.config_kD(0, 0, 0);

        // pickupPivot.config_kF(0, 0, 0);
    }

    // Would setGrab be a better name?
    public void setPickup(boolean state) {
        pickupLeft.set(state);
        pickupRight.set(state);
    }

    public void setPickupPivotPosition(int position) {
        // to implement
        modePosition = true;
        pickupPivot.set(ControlMode.Position, position);
    }

    public void setPickupPivotPower(double power) {
        modePosition = false;
        pickupPivot.set(ControlMode.PercentOutput, power);
    }
}