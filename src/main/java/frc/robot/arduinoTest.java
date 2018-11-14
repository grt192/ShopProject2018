package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import frc.drivetrain.Tank;
import frc.leds.LEDs;
import frc.mechs.MechCollection;

public class ArduinoTest {

	public LEDs leds;

	private Tank tank;
	private MechCollection mechs;

	private PowerDistributionPanel pdp;

	private SerialPort arduino;

	public void init() {
		arduino = new SerialPort(9600, Port.kUSB);
		leds = new LEDs(arduino, tank, mechs, pdp);

		tank = new Tank();
		mechs = new MechCollection();

		pdp = new PowerDistributionPanel();

		leds.sayHi();
	}

	public void periodic() {

	}
}
