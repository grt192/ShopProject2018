/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import frc.config.Config;
import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.fieldmapping.FieldMappingThread;
import frc.leds.LEDs;
import frc.mechs.MechCollection;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private Tank tank;
    private FieldMappingThread fieldMappingThread;
    private EncoderPositionTracker tracker;
    private MechCollection mechCollection;

    private LEDs arduino;

    @Override
    public void robotInit() {
        Config.start();

        mechCollection = new MechCollection();
        tank = new Tank();
        fieldMappingThread = new FieldMappingThread(tank);
        fieldMappingThread.start();
        tracker = fieldMappingThread.getTracker();
        auto = new Autonomous(mechCollection, tank, tracker);
        teleop = new Teleop(mechCollection, tank);

        arduino = new LEDs(tank, mechCollection, new PowerDistributionPanel());

        new Notifier(arduino).startPeriodic(0.25);
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
        teleop.periodic();
    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void disabledInit() {
        auto.disable();
    }
}
