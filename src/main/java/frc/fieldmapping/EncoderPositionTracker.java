package frc.fieldmapping;

import frc.drivetrain.TankData;

public class EncoderPositionTracker {

    private double dt;
    private double x, y;

    public EncoderPositionTracker(double dt) {
        this.dt = dt;
    }

    public void update(TankData data) {
        x += data.avgSpeed * Math.cos(data.gyroAngle) * dt;
        y += data.avgSpeed * Math.sin(data.gyroAngle) * dt;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void reset() {
        set(0, 0);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

}