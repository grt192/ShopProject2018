/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import frc.config.Config;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    @Override
    public void robotInit() {
        Config.start();
        auto = new Autonomous();
        teleop = new Teleop();
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

    @Override
    public void disabledInit() {
        auto.disable();
    }
}
