package frc.robot;

import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.mechs.Elevator;
import frc.mechs.MechCollection;
import frc.mechs.Pickup;

public class Autonomous implements Runnable {

	private final Elevator elevator;
	private final Tank tank;
	private final Pickup pickup;
	private Thread thread;
	private final EncoderPositionTracker tracker;

	public Autonomous(MechCollection mechCollection, Tank tank, EncoderPositionTracker tracker) {
		elevator = mechCollection.elevator;
		pickup = mechCollection.pickup;
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
		tank.setPolar(3.2 / 4, 0);
		while (tracker.getX() < 1) { // 3.2
			Thread.sleep(50);
		}
		tank.set(0, 0);
		pickup.setPickupPivotPosition(Pickup.downPosition);
		Thread.sleep(2000);
		pickup.setPickup(true);
		Thread.sleep(2000);
		pickup.setPickupPivotPosition(Pickup.upPosition);
		Thread.sleep(2000);
		while (tank.getTankData().gyroAngle > -Math.PI / 2) {
			tank.setPolar(0, -Math.PI - 2 * tank.getTankData().gyroAngle - 0.2);
			Thread.sleep(10);
		}

		tank.setPolar(5 / 4, 0);
		while (tracker.getY() > -2) { // -5
			Thread.sleep(50);
		}
		tank.set(0, 0);
		while (tank.getTankData().gyroAngle > -Math.PI) {
			tank.setPolar(0, -Math.PI - 2 * tank.getTankData().gyroAngle - 0.2);
			Thread.sleep(10);
		}
		tank.setPolar(1.5 / 2, 0);
		while (tracker.getX() > 0) { // 1.5
			Thread.sleep(50);
		}

		elevator.setElevatorPosition(Elevator.TOP);
		Thread.sleep(2000);
		pickup.setPickup(false);
		Thread.sleep(2000);
		pickup.setPickupPivotPosition(Pickup.downPosition);
		Thread.sleep(2000);
		elevator.setElevatorPosition(Elevator.BOTTOM);
		Thread.sleep(2000);

		while (tank.getTankData().gyroAngle > 0) {
			tank.setPolar(0, Math.PI - 2 * tank.getTankData().gyroAngle + 0.2);
			Thread.sleep(10);
		}
	}

	public void disable() {
		if (thread != null)
			thread.interrupt();
	}

	public void periodic() {
	}

}
