package frc.mechs;

public class MechCollection {

    public Arm arm;
    public Intake intake;

    public MechCollection() {

        arm = new Arm(null);
        intake = new Intake();

    }

}