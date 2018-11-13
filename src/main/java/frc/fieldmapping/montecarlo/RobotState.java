package frc.fieldmapping.montecarlo;

class RobotState {

    private double x, y, angle;

    public RobotState(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void update(double lSpeed, double rSpeed, double dT) {

    }

    public RobotState copy() {
        return new RobotState(x, y, angle);
    }
}
