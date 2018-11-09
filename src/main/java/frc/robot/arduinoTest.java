package frc.robot;

import frc.drivetrain.Tank;
import frc.leds.LEDs;
import frc.mechs.MechCollection;

public class ArduinoTest {

	public LEDs arduinoSerial;

	private Tank tank;
	private MechCollection mechs;

	public void init() {
		arduinoSerial = new LEDs(tank, mechs);

		tank = new Tank();
		mechs = new MechCollection();
	}

	public void periodic() {
		arduinoSerial.sayHi();
	}

}
