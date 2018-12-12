package frc.mechs;

import edu.wpi.first.wpilibj.Solenoid;
import frc.config.Config;

public class Intake {

    private Solenoid sol;

    public Intake() {

        sol = new Solenoid(Config.getInt("sol"));

    }

    public void pickOpen() {

        sol.set(false);

    }

    public void pickClose() {

        sol.set(true);

    }
}