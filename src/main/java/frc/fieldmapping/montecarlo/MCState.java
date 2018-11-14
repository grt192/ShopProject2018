package frc.fieldmapping.montecarlo;

class MCState {

    public double x, y, angle;

    public MCState(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void update(double encoderV, double encoderW, double dT) {
        if (encoderW < 0.01) {
            x += encoderV * Math.cos(angle) * dT;
            y += encoderV * Math.sin(angle) * dT;
        } else {
            double r = encoderV / encoderW;
            double theta = angle + encoderW * dT;
            x += r * (Math.sin(theta) - Math.sin(angle));
            y += r * (Math.cos(angle) - Math.cos(theta));
            angle = theta;
        }
    }

    public MCState copy() {
        return new MCState(x, y, angle);
    }
}
