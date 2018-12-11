package frc.robot;

import frc.config.Config;

import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.mechs.Arm;
import frc.mechs.Intake;
import frc.mechs.MechCollection;

public class Autonomous implements Runnable {

	private final Arm arm;
	private final Tank tank;
	private final Intake intake;
	private Thread thread;
	private final EncoderPositionTracker tracker;

	public Autonomous(MechCollection mechCollection, Tank tank, EncoderPositionTracker tracker) {
		arm = mechCollection.arm;
		intake = mechCollection.intake;
		this.tank = tank;
		this.tracker = tracker;
	}

	public void init() {
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		try {
			runAutonomous();
		} catch (InterruptedException e) {
			System.out.println("Interrupting autonomous");
		}
	}

	public void runAutonomous() throws InterruptedException {

		tank.setPolar(2.7 / 4, 0);

		while (tracker.getX() < 1) {
			Thread.sleep(50);
		}

		tank.setPolar(0, 0);

	}

	public void disable() {
		if (thread != null)
			thread.interrupt();
	}

	public void periodic() {

	}

}