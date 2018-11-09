package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class arduinoTest {

	SerialPort arduino;

	public arduinoTest() {
		arduino = new SerialPort(9600, SerialPort.Port.kUSB);
	}

	public void init() {

	}

	public void periodic() {
		
	}

}
