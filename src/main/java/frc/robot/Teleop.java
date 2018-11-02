package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.drivetrain.Tank;
import frc.drivetrain.TankData;

public class Teleop {

	private Tank tank;
	private Joystick joystick;
	private XboxController xbox;

	public Teleop(Tank tank) {
		this.tank = tank;
		xbox = new XboxController(0);
	}

	public void init() {

	}

	public void periodic() {
		if (xbox.getAButton()) {
			tank.setRaw(0.75, 0.75);
		} else if (xbox.getBButton()) {
			tank.setRaw(-0.75, -0.75);
		} else {
			tank.setRaw(-xbox.getY(Hand.kLeft), -xbox.getY(Hand.kRight));
		}
		TankData td = tank.getTankData();
		System.out.println(td.leftSpeed + ", " + td.rightSpeed + ", " + td.avgSpeed);
	}

}
