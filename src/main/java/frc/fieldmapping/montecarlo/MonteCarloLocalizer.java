package frc.fieldmapping.montecarlo;

import java.util.Random;

import frc.drivetrain.Tank;
import frc.drivetrain.TankData;
import frc.robot.GRTUtil;

public class MonteCarloLocalizer extends Thread {

    private static final double GYRO_STDDEV = 0.01;
    private static final double WHEEL_NOISE = 0.05 * 0;

    private static final long TIME_STEP = 20;
    private static final double dT = TIME_STEP / 1000.0;

    private Tank drive;
    private MCState[] states;
    private MCState averageState;
    private Random rand;
    private WeightedChooser chooser;

    public MonteCarloLocalizer(Tank drive, int n) {
        this.drive = drive;
        states = new MCState[n];
        chooser = new WeightedChooser(n);
        rand = new Random();
    }

    public void initialize(double x, double y, double theta) {
        for (int i = 0; i < states.length; i++) {
            states[i] = new MCState(x, y, theta);
        }
        updateAverage();
    }

    public void update() {
        TankData data = drive.getTankData();
        chooser.clear();
        for (int i = 0; i < states.length; i++) {
            updateState(states[i], data);
            chooser.add(getWeight(states[i], data));
        }
        MCState[] nextStates = new MCState[states.length];
        for (int i = 0; i < states.length; i++) {
            nextStates[i] = states[chooser.choose()].copy();
        }
        states = nextStates;
        updateAverage();
    }

    private void updateAverage() {
        double x = 0;
        double y = 0;
        double theta = 0;
        for (int i = 0; i < states.length; i++) {
            x += states[i].x;
            y += states[i].y;
            theta += GRTUtil.positiveMod(states[i].angle, GRTUtil.TWO_PI);
        }
        x /= states.length;
        y /= states.length;
        theta /= states.length;
        averageState = new MCState(x, y, theta);
    }

    private void updateState(MCState state, TankData data) {
        double lSpeed = data.leftSpeed + rand.nextGaussian() * WHEEL_NOISE;
        double rSpeed = data.rightSpeed + rand.nextGaussian() * WHEEL_NOISE;
        double v = (lSpeed + rSpeed) / 2;
        double w = (lSpeed - rSpeed) / drive.WIDTH;
        state.update(v, w, dT);
    }

    private double getWeight(MCState state, TankData data) {
        double gyroAngle = GRTUtil.positiveMod(data.gyroAngle, GRTUtil.TWO_PI);
        double angle = GRTUtil.positiveMod(state.angle, GRTUtil.TWO_PI);
        return GRTUtil.normPDF(angle, gyroAngle, GYRO_STDDEV);
    }

    public double getX() {
        return averageState.x;
    }

    public double getY() {
        return averageState.y;
    }

    public double getAngle() {
        return averageState.angle;
    }

    public void run() {
        long nextLoop = System.currentTimeMillis();
        while (true) {
            nextLoop += TIME_STEP;
            update();
            long sleepTime = nextLoop - System.currentTimeMillis();
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Field mapping thread too slow");
            }
        }
    }
}