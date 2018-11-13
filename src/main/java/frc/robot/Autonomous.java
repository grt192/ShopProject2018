package frc.robot;

public class Autonomous implements Runnable {

	private Thread thread;

	public Autonomous() {
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
		// autonoumous code here
	}

	public void disable() {
		if (thread != null)
			thread.interrupt();
	}

	public void periodic() {

	}

}
