package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.drivetrain.Tank;
import frc.mechs.Elevator;
import frc.mechs.MechCollection;
import frc.mechs.Pickup;

public class Teleop {

	private XboxController xboxMechs;
	private XboxController xboxDrive;

	private MechCollection mechs;
	private Tank drive;

	private Double mechsLeftJoystick;
	private Double mechsRightJoystick;

	public Teleop(MechCollection mechs, Tank drive) {
		this.drive = drive;
		this.mechs = mechs;

		xboxDrive = new XboxController(0);
		xboxMechs = new XboxController(1);

	}

	public void init() {

	}

	public void periodic() {
		drive.set((-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft)),
				(-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kRight)));

		if (xboxMechs.getAButton()) {
			mechs.pickup.setPickup(true);
		} else if (xboxMechs.getBButton()) {
			mechs.pickup.setPickup(false);
		}

		// if (xboxMechs.getPOV() == 0) {
		// mechs.elevator.setElevatorPosition(Elevator.TOP);
		// } else if (xboxMechs.getPOV() == 2) {
		// mechs.elevator.setElevatorPosition(Elevator.MIDDLE);
		// } else if (xboxMechs.getPOV() == 4) {
		// mechs.elevator.setElevatorPosition(Elevator.BOTTOM);
		// }

		// if (xboxMechs.getXButton()) {
		// mechs.pickup.setPickupPivotPosition(Pickup.armDown);
		// } else if (xboxMechs.getYButton()) {
		// mechs.pickup.setPickupPivotPosition(Pickup.armUp);
		// }

		mechsLeftJoystick = JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft));
		mechsRightJoystick = JoystickProfile.applyDeadband(xboxMechs.getY(Hand.kRight));

		mechs.pickup.setPickupPivotPower(mechsLeftJoystick);
		mechs.elevator.setElevatorPower(mechsRightJoystick);

		// if (mechsLeftJoystick != 0 || !mechs.pickup.modePosition) {
		// mechs.pickup.setPickupPivotPower((-0.5) * mechsLeftJoystick);
		// }

		// if (mechsRightJoystick != 0 || !mechs.elevator.modePosition) {
		// mechs.elevator.setElevatorPower((-0.5) * mechsRightJoystick);
		// }
	}
}
