package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.drivetrain.Tank;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class Teleop {

	private XboxController xboxMechs;
	private XboxController xboxDrive;

	private Pickup pickup;
	private Elevator elevator;
	private Tank drive;

	public Teleop(Pickup pickup, Elevator elevator, Tank drive) {
		this.pickup = pickup;
		this.elevator = elevator;
		this.drive = drive;

		xboxMechs = new XboxController(0);
		xboxDrive = new XboxController(1);
	}

	public void init() {

	}

	public void periodic() {
		if (xboxMechs.getAButton()) {
			pickup.setPickup(true);
		} else if (xboxMechs.getBButton()) {
			pickup.setPickup(false);
		}

		drive.setRaw(deadband(xboxDrive.getY(Hand.kLeft)), deadband(xboxDrive.getY(Hand.kRight)));

		// Set Pickup pivot position (manual and to position)

		// Set Tray pivot Position (to position)

		// Set Elevator height (manual and to position)
	}

	private double deadbandThreshold = 0.1;

	private double deadband(double input) {

		return (input < deadbandThreshold) ? deadbandThreshold : input;
	}
}
