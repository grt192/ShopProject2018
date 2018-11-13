package frc.robot;

import frc.config.Config;
import frc.drivetrain.Tank;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class Autonomous {
	private final Robot robot;

	public Autonomous(Robot robot) {
		this.robot = robot;
	}

	public void init() {
	}

	public void periodic() {
		robot.getTank().set(2.7 / 4, 2.7 / 4);
		sleep(4000);
		robot.getTank().set(0, 0);
		robot.getPickup().setPickupPosition(Pickup.downPosition);
		sleep(1000);
		robot.getPickup().setPickup(true);
		sleep(1000);
		robot.getPickup().setPickupPosition(Pickup.upPosition);
		sleep(1000);
		robot.getTank().setPolar(0, 0.73025);
		sleep(1000);
		robot.getElevator().setElevatorPosition(Elevator.ElevatorPosition.TOP);
		sleep(1000);
		robot.getTank().set(4.5 / 4, 4.5 / 4);
		sleep(4000);
		robot.getElevator().setTrayPosition(Elevator.downTray);
		sleep(1000);
		robot.getElevator().setTrayPosition(Elevator.upTray);
		sleep(1000);
		robot.getElevator().setElevatorPosition(Elevator.ElevatorPosition.GROUND);
	}

	private static final void sleep(long sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
