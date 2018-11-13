package frc.mechs;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.config.Config;

public class Pickup {

    private Solenoid pickupPneumatic;

    private TalonSRX pickupPivot;
    public static final int downPosition = Config.getInt("pick_up_position_down");
    public static final int upPosition = Config.getInt("pick_up_position_up");

    public Pickup() {
        pickupPneumatic = new Solenoid(Config.getInt("pickup-pneumatic"));

        pickupPivot = new TalonSRX(Config.getInt("pickup-pivot"));
    }

    // Would setGrab be a better name?
    public void setPickup(boolean state) {
        pickupPneumatic.set(state);
    }

    public void setPickupPosition(int i) {
        // to implement
    }
}