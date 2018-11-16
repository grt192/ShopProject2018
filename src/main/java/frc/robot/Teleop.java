package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.drivetrain.Tank;
import frc.mechs.Arm;
import frc.mechs.MechCollection;
import frc.mechs.Intake;

public class Teleop {

	private XboxController xboxMechs;
	private XboxController xboxDrive;

	private MechCollection mechs;
	private Tank drive;

	public Teleop(MechCollection mechs, Tank drive) {
		this.mechs = mechs;
		this.drive = drive;

		xboxMechs = new XboxController(0);
		xboxDrive = new XboxController(1);
	}

	public void init() {

	}

	public void periodic() {
		drive.set((-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft)),
				(-2.0) * JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kRight)));

		if (xboxMechs.getAButton()) {
			mechs.intake.pickOpen();

		} else if (xboxMechs.getBButton()) {
			mechs.intake.pickClose();

		}

		if (xboxMechs.getPOV() == 0) {
			// mechs.arm.raise();
		} else if (xboxMechs.getPOV() == 2) {
			// mechs.arm.flip();
		} else if (xboxMechs.getPOV() == 4) {
			// mechs.arm.lower();
		}

		// mechs.pickup.setPickupPivotPower((-0.5);
		// JoystickProfile.applyDeadband(xboxDrive.getY(Hand.kLeft)));

		// mechs.elevator.setElevatorPower((-0.5);
		// JoystickProfile.applyDeadband(xboxMechs.getY(Hand.kRight)));

		if (xboxMechs.getXButton()) {
			// mechs.arm.lower();
		} else if (xboxMechs.getYButton()) {
			// mechs.arm.raise();
		}

	}
}