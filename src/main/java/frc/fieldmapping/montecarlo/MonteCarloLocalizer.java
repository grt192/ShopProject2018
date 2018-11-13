package frc.fieldmapping.montecarlo;

import java.util.Random;

import frc.drivetrain.Tank;
import frc.drivetrain.TankData;
import frc.robot.GRTUtil;

public class MonteCarloLocalizer {

    private static final double GYRO_STDDEV = 0.02;
    private static final double WHEEL_NOISE = 0.05;

    private static final long TIME_STEP = 20;
    private static final double dT = TIME_STEP / 1000.0;

    private Tank drive;
    private MCState[] states;
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
    }

    private void updateState(MCState state, TankData data) {
        double lSpeed = data.leftSpeed + rand.nextGaussian() * WHEEL_NOISE;
        double rSpeed = data.rightSpeed + rand.nextGaussian() * WHEEL_NOISE;
        double v = (lSpeed + rSpeed) / 2;
        double w = (lSpeed - rSpeed) / drive.WIDTH;
        state.update(v, w, dT);
    }

    private double getWeight(MCState state, TankData data) {
        return GRTUtil.normPDF(state.angle, data.gyroAngle, GYRO_STDDEV);
    }
}