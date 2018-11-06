package frc.fieldmapping;

import frc.drivetrain.Tank;

public class FieldMappingThread extends Thread {

    private static final long TIME_STEP = 10;
    private static final double dT = TIME_STEP / 1000.0;

    private Tank tank;
    private EncoderPositionTracker tracker;

    public FieldMappingThread(Tank tank) {
        this.tank = tank;
        this.tracker = new EncoderPositionTracker(dT);
    }

    @Override
    public void run() {
        long nextLoop = System.currentTimeMillis();
        while (true) {
            nextLoop += TIME_STEP;

            this.tracker.update(this.tank.getTankData());

            long sleepTime = nextLoop - System.currentTimeMillis();
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public EncoderPositionTracker getTracker() {
        return this.tracker;
    }

}