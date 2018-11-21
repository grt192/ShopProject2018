package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.drivetrain.Tank;
import frc.mechs.Arm;
import frc.mechs.MechCollection;

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

		if (xboxMechs.getBumperPressed(Hand.kLeft)) {
			mechs.arm.setArmPosition(Arm.oppositeLow);
		}
		if (xboxMechs.getBumperPressed(Hand.kRight)) {
			mechs.arm.setArmPosition(Arm.lowest);
		}

	}
}