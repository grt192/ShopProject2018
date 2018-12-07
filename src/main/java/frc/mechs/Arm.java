package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;

public class Arm {

    private TalonSRX armMotor;

    public Arm(XboxController controller) {
        armMotor = new TalonSRX(Config.getInt("armmotor"));

        int ticksPerR = 36;

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
