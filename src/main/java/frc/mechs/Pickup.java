package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import frc.config.Config;

public class Pickup {

    private Solenoid pickupPneumatic;

    private TalonSRX pickupPivot;

    public static final int armDown = 10000;
    public static final int armUp = 5000;

    public Pickup() {
        pickupPneumatic = new Solenoid(Config.getInt("pickup_pneumatic"));

        pickupPivot = new TalonSRX(Config.getInt("pickup_pivot"));

        pickupPivot.config_kP(0, 1, 0);
        pickupPivot.config_kI(0, 0, 0);
        pickupPivot.config_kD(0, 0, 0);

        pickupPivot.config_kF(0, 0, 0);
    }

    // Would setGrab be a better name?
    public void setPickup(boolean state) {
        pickupPneumatic.set(state);
    }

    public void setPickupPivotPosition(int position) {
        // to implement
        pickupPivot.set(ControlMode.Position, position);
    }

    public void setPickupPivotPower(double power) {
        pickupPivot.set(ControlMode.PercentOutput, power);
    }
}