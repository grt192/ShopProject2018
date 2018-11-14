package frc.robot;

import frc.config.Config;
import frc.drivetrain.Tank;
import frc.drivetrain.TankData;
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
		tank.setPolar(2.7 / 4, 0);
		while (tracker.getX() < 1) {
			Thread.sleep(50);
		}
		tank.setPolar(0, 0);
		System.out.print("Done moving forward\n");
		pickup.setPickupPivotPosition(Pickup.downPosition);
		Thread.sleep(1000);
		pickup.setPickup(true);
		Thread.sleep(1000);
		pickup.setPickupPivotPosition(Pickup.upPosition);
		Thread.sleep(1000);
		tank.setPolar(0, -Math.PI / 2);
		System.out.print("Turning\n");
		System.out.println(tank.getTankData().gyroAngle);
		while (tank.getTankData().gyroAngle > -Math.PI / 2) {
			Thread.sleep(50);
			System.out.println(tank.getTankData().gyroAngle);
		}
		System.out.println("Done turning");
		tank.setPolar(4.5 / 4, 0);
		while (tracker.getY() > -2) {
			Thread.sleep(50);
		}
		tank.set(0, 0);
		System.out.println("Done moving");
		elevator.setElevatorPosition(Elevator.TOP);
		Thread.sleep(1000);
		elevator.setTrayPosition(Elevator.UP);
		Thread.sleep(1000);
		elevator.setTrayPosition(Elevator.UP);
		Thread.sleep(1000);
		elevator.setElevatorPosition(Elevator.BOTTOM);
	}

	public void disable() {
		if (thread != null)
			thread.interrupt();
	}

	public void periodic() {
	}

}
