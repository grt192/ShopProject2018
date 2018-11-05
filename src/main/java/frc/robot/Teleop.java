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
		double lSpeed = -xbox.getY(Hand.kLeft) * tank.MAX_SPEED;
		double rSpeed = -xbox.getY(Hand.kLeft) * tank.MAX_SPEED;
		tank.set(lSpeed, rSpeed);
		TankData td = tank.getTankData();
		System.out.println("Intended: " + lSpeed + ", " + rSpeed);
		System.out.println("Actual: " + td.leftSpeed + ", " + td.rightSpeed);
		tank.printError();
	}

}
