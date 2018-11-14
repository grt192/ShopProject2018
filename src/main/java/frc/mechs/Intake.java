package frc.mechs;

import edu.wpi.first.wpilibj.Solenoid;
import frc.config.Config;

public class Intake {

    private Solenoid rightSol, leftSol;

    public Intake() {

        leftSol = new Solenoid(Config.getInt("leftSol"));
        rightSol = new Solenoid(Config.getInt("rightSol"));

    }

    public void pickOpen() {

        leftSol.set(true);
        rightSol.set(true);

    }

    public void pickClose() {

        leftSol.set(false);
        rightSol.set(false);

    }
}