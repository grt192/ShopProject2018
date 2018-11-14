package frc.mechs;

import frc.mechs.SensorToAngle;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;

public class Arm {

    private TalonSRX motor1;
    private TalonSRX motor2;
    private TalonSRX motor3;
    private double power;
    private SensorToAngle output;

    public Arm(XboxController controller) {
        motor1 = new TalonSRX(Config.getInt("arm_motor1"));
        motor2 = new TalonSRX(Config.getInt("arm_motor2"));
        motor3 = new TalonSRX(Config.getInt("arm_motor3"));
        output = new SensorToAngle();

        motor1.config_kP(0, 1, 0);
        motor1.config_kI(0, 0, 0);
        motor1.config_kD(0, 0, 0);

        motor1.config_kF(0, 0, 0);

        motor2.follow(motor1);
        motor3.follow(motor1);
    }

    private void moveArmTo(double angle) {
        while (output.getArmAngle() < angle) {
            motor1.set(ControlMode.PercentOutput, power);

        }
    }

    public void setArmPower(int position) {
        motor1.set(ControlMode.Position, position);
    }

    public void setArmPosition(int position) {
        motor1.set(ControlMode.PercentOutput, position);

    }

}
