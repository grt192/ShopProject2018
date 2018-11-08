package frc.mechs;

import frc.config.*;
import edu.wpi.first.wpilibj.Solenoid;

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