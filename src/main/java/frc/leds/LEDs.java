/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.leds;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import frc.drivetrain.Tank;
import frc.mechs.MechCollection;

public class LEDs implements Runnable {
  private Tank tank;
  private MechCollection mechs;
  private PowerDistributionPanel pdp;

  private SerialPort arduino;

  public LEDs(Tank tank, MechCollection mechs, PowerDistributionPanel pdp) {
    this.tank = tank;
    this.mechs = mechs;
    this.pdp = pdp;

    arduino = new SerialPort(9600, Port.kUSB);
  }

  public void sayHi() {
    arduino.writeString("hi");
  }

  public void run() {
    sendVoltage();
  }

  public void sendVoltage() {
    System.out.println("Voltage: " + pdp.getVoltage());
    arduino.writeString(pdp.getVoltage() + "\n");
  }
}
