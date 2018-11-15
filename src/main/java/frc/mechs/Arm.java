package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;

public class Arm {

    private TalonSRX armMotor;
    public static final int ticksPerR = 36;

    public static final int lowest = Config.getInt("lowestPos");
    public static final int oneeighty = Config.getInt("oneeighty");
    public static final int twotwentyfive = Config.getInt("twotwentyfive");

    public Arm(XboxController controller) {
        armMotor = new TalonSRX(Config.getInt("armmotor"));

        armMotor.config_kP(0, 1, 0);
        armMotor.config_kI(0, 0, 0);
        armMotor.config_kD(0, 0, 0);

        armMotor.config_kF(0, 0, 0);

    }

    public void setArmPower(double power) {
        armMotor.set(ControlMode.PercentOutput, power);
    }

    public void setArmPosition(int position) {
        armMotor.set(ControlMode.Position, position);

    }

}
