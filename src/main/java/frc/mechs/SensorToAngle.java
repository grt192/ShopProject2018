package frc.mechs;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;

public class SensorToAngle {

    private Encoder enc;
    private double lowVal;
    private double highVal;
    private double lowAngle = 0;
    private double highAngle = 270;
    private XboxController controller;

    public SensorToAngle() {
        enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    }

    public void calibrate() {
        while (!controller.getAButton())
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        while (controller.getAButton()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        lowVal = enc.get();
        while (!controller.getAButton())
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        while (controller.getAButton()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        highVal = enc.get();
    }

    public double getArmAngle() {
        return (double) ((enc.get() - lowVal) / (highVal - lowVal) * (highAngle - lowAngle));
    }

}