package frc.mechs;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;

public class SensorToAngle {

    private Encoder output;
    private Encoder output2;
    private double lowVal;
    private double highVal;
    private double lowAngle = 0;
    private double highAngle = 270;
    private XboxController controller;

    public SensorToAngle() {
        Encoder output = new Encoder(0, 0);
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
        lowVal = output.get();
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
        highVal = output.get();
    }

    public double getArmAngle() {
        return (double) ((output.get() - lowVal) / (highVal - lowVal) * (highAngle - lowAngle));
    }
}