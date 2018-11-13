package frc.mechs;

import edu.wpi.first.wpilibj.XboxController;

public class Arm {

    public Arm(XboxController controller) {

    }

    private void moveArmTo(double angle) {

    }

    public void raise() {
        moveArmTo(180);
    }

    public void lower() {
        moveArmTo(0);
    }

    public void flip() {
        moveArmTo(270);
    }

}