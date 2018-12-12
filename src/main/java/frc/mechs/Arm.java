package frc.mechs;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;

public class Arm {

    private TalonSRX armMotor_master;
    private TalonSRX armMotor_follower;

    private Solenoid sol1;

    public Arm(XboxController controller) {
        armMotor_master = new TalonSRX(Config.getInt("armmotor_master"));
        armMotor_follower = new TalonSRX(Config.getInt("armmotor_follower"));

        armMotor_master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        armMotor_follower.follow(armMotor_master);

        int ticksPerR = 36;

        sol1 = new Solenoid(Config.getInt("sol1"));
        sol1.set(false);

        armMotor_master.config_kP(0, 1, 0);
        armMotor_master.config_kI(0, 0, 0);
        armMotor_master.config_kD(0, 0, 0);

        armMotor_master.config_kF(0, 0, 0);

    }

    public void setArmPower(double power) {
        armMotor_master.set(ControlMode.PercentOutput, power);
        if (power == 0) {
            sol1.set(false);
        } else {
            sol1.set(true);
        }

    }

    public void setArmPosition(int position) {
        armMotor_master.set(ControlMode.Position, position);

    }

}
