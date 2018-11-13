package frc.robot;

import frc.config.Config;
import frc.drivetrain.Tank;
import frc.mechs.Elevator;
import frc.mechs.MechCollection;
import frc.mechs.Pickup;

public class Autonomous implements Runnable {

	private final Elevator elevator;
	private final Tank tank;
	private final Pickup pickup;
	private Thread thread;

	public Autonomous(MechCollection mechCollection, Tank tank) {
		elevator = mechCollection.elevator;
		pickup = mechCollection.pickup;
		this.tank = tank;
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
		tank.set(2.7 / 4, 2.7 / 4);
		thread.sleep(4000);
		tank.set(0, 0);
		pickup.setPickupPivotPosition(Pickup.downPosition);
		thread.sleep(1000);
		pickup.setPickup(true);
		thread.sleep(1000);
		pickup.setPickupPivotPosition(Pickup.upPosition);
		thread.sleep(1000);
		tank.setPolar(0, 0.73025);
		thread.sleep(1000);
		elevator.setElevatorPosition(Elevator.TOP);
		tank.set(4.5 / 4, 4.5 / 4);
		thread.sleep(4000);
		elevator.setTrayPosition(Elevator.UP);
		thread.sleep(1000);
		elevator.setTrayPosition(Elevator.UP);
		thread.sleep(1000);
		elevator.setElevatorPosition(Elevator.BOTTOM);
	}

	public void disable() {
		if (thread != null)
			thread.interrupt();
	}

	public void periodic() {

	}

}
