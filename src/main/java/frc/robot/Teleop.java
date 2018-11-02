package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class Teleop {

	private XboxController xboxMechs;
	private XboxController xboxDrive;

	private Pickup pickup;
	private Elevator elevator;

	public Teleop(Pickup pickup, Elevator elevator) {
		this.pickup = pickup;
		this.elevator = elevator;

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

		// Set Pickup pivot position (manual and to position)

		// Set Tray pivot Position (to position)

		// Set Elevator height (manual and to position)
	}

}
