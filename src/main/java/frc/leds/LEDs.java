/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.leds;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import frc.drivetrain.Tank;
import frc.mechs.MechCollection;

public class LEDs {
  private Tank tank;
  private MechCollection mechs;

  public LEDs(Tank tank, MechCollection mechs) {
    this.tank = tank;
    this.mechs = mechs;
  }

  private SerialPort arduinoPort = new SerialPort(9600, Port.kUSB);

  public void sayHi() {
    arduinoPort.writeString("hi");
  }
}
