package frc.mechs;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;

public class SensorToAngle {

    private double armPos;
    private Encoder output;
    private double lowVal;
    private double highVal;
    private double lowAngle = 0;
    private double highAngle = 270;
    private XboxController controller;

    public SensorToAngle(Encoder output, XboxController controller) {
        this.output = output;
        this.lowVal = lowVal;
        this.highVal = highVal;
        this.controller = controller;
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

    public double getArmPos() {
        return (double) ((output.get() - lowVal) / (highVal - lowVal) * (highAngle - lowAngle));
    }
}