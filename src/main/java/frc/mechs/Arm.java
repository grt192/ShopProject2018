package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;

public class Arm {

    private TalonSRX armMotor1;

    public Arm(XboxController controller) {
        armMotor1 = new TalonSRX(Config.getInt("armmotor1"));

        int ticksPerR = 36;

        armMotor1.config_kP(0, 1, 0);
        armMotor1.config_kI(0, 0, 0);
        armMotor1.config_kD(0, 0, 0);

        armMotor1.config_kF(0, 0, 0);

    }

    public void setArmPower(double power) {
        armMotor1.set(ControlMode.PercentOutput, power);
    }

    public void setArmPosition(int position) {
        armMotor1.set(ControlMode.Position, position);

    }

}
