/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import frc.config.Config;
import frc.mechs.Elevator;
import frc.mechs.Pickup;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private Pickup pickup;
    private Elevator elevator;

    @Override
    public void robotInit() {
        Config.start();

        pickup = new Pickup();
        elevator = new Elevator();

        auto = new Autonomous();
        teleop = new Teleop(pickup, elevator);
    }

    @Override
    public void autonomousInit() {
        auto.init();
    }

    @Override
    public void autonomousPeriodic() {
        auto.periodic();
    }

    @Override
    public void teleopInit() {
        teleop.init();
    }

    @Override
    public void teleopPeriodic() {
        auto.periodic();
    }
}
