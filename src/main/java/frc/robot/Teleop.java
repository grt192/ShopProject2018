package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import frc.drivetrain.Tank;
import frc.leds.LEDs;
import frc.mechs.Elevator;
import frc.mechs.MechCollection;
import frc.mechs.Pickup;

public class Teleop {

	private XboxController xboxMechs;
	private XboxController xboxDrive;

	private MechCollection mechs;
	private Tank drive;

	private LEDs arduino;

	private Double leftPower;
	private Double rightPower;

	public Teleop(MechCollection mechs, Tank drive) {
		this.mechs = mechs;
		this.drive = drive;

		xboxMechs = new XboxController(0);
		xboxDrive = new XboxController(1);

		arduino = new LEDs();
	}

	public void init() {

	}

	public void periodic() {
		leftPower = (-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft));
		rightPower = (-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kRight));

		drive.set(leftPower, rightPower);

		try {
			arduino.sendTank(leftPower, rightPower);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (xboxMechs.getAButton()) {
			mechs.pickup.setPickup(true);
		} else if (xboxMechs.getBButton()) {
			mechs.pickup.setPickup(false);
		}

		if (xboxMechs.getPOV() == 0) {
			mechs.elevator.setElevatorPosition(Elevator.TOP);
		} else if (xboxMechs.getPOV() == 2) {
			mechs.elevator.setElevatorPosition(Elevator.MIDDLE);
		} else if (xboxMechs.getPOV() == 4) {
			mechs.elevator.setElevatorPosition(Elevator.BOTTOM);
		}

		mechs.pickup.setPickupPivotPower((-0.5) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft)));

		mechs.elevator.setElevatorPower((-0.5) * JoystickProfile.applyDeadband(xboxMechs.getY(Hand.kRight)));

		if (xboxMechs.getXButton()) {
			mechs.pickup.setPickupPivotPosition(Pickup.armDown);
		} else if (xboxMechs.getYButton()) {
			mechs.pickup.setPickupPivotPosition(Pickup.armUp);
		}

	}
}
