package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class ArduinoTest {

	SerialPort arduinoSerial;

	public ArduinoTest() {
		arduinoSerial = new SerialPort(9600, SerialPort.Port.kUSB);
	}

	public void init() {

	}

	public void periodic() {
		arduinoSerial.writeString("hi");
	}

}
