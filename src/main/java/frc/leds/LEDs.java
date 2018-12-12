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
import frc.drivetrain.TankData;
import frc.mechs.MechCollection;
import java.lang.Runnable;

public class LEDs implements Runnable {
  private Tank tankInfo;
  private MechCollection mechs;
  private PowerDistributionPanel pdp;

  private SerialPort arduino;

  public LEDs() {
    try {
      pdp = new PowerDistributionPanel();
      arduino = new SerialPort(9600, Port.kUSB);
    } catch (Exception e) {
      // TODO: handle exception
    }

  }

  public void run() {
    sendVoltage(pdp.getVoltage());
  }

  public void sayHi() {
    arduino.writeString("hi");
  }

  public void sendVoltage(Double voltage) {
    try {
      System.out.println("Voltage: " + voltage);

      // if (arduino != null)
      arduino.writeString(voltage + "\n");
    } catch (Exception e) {

    }

  }

  public void sendTank(Double left, Double right) {
    System.out.println("Motor Power: " + left + ", " + right);

    if (arduino != null) {
      arduino.writeString("l" + left + "\n");
      arduino.writeString("r" + right + "\n");
    }
  }
}
