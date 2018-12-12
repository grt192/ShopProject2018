package frc.robot;

import frc.config.Config;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import frc.drivetrain.Tank;
import frc.mechs.Arm;
import frc.mechs.MechCollection;

public class Teleop {

	private XboxController xboxMechs;
	private Joystick joyDrive;

	private MechCollection mechs;
	private Tank drive;

	public Teleop(MechCollection mechs, Tank drive) {
		this.mechs = mechs;
		this.drive = drive;

		xboxMechs = new XboxController(0);
		joyDrive = new Joystick(1);
	}

	public void init() {

	}

	public void periodic() {

		double radians = joyDrive.getDirectionRadians();
		double magnitude = joyDrive.getMagnitude();

		drive.setPolar((-0.9) * JoystickProfile.applyDeadband(joyDrive.getY()),
				((2.46) * JoystickProfile.applyDeadband(joyDrive.getX())));

		if (xboxMechs.getAButton()) {
			mechs.intake.pickOpen();

		} else if (xboxMechs.getBButton()) {
			mechs.intake.pickClose();

		}

		if (xboxMechs.getY(Hand.kLeft) > 0) {
			mechs.arm.setArmPower(JoystickProfile.applyDeadband(xboxMechs.getY(Hand.kLeft) / -6));
		} else {
			mechs.arm.setArmPower(JoystickProfile.applyDeadband(xboxMechs.getY(Hand.kLeft) / -3));
		}

	}
}