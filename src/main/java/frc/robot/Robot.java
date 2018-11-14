/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import frc.config.Config;
<<<<<<< HEAD
=======
import frc.mechs.MechCollection;
>>>>>>> cb89c50a230ef754bc36c723f4e7c5688d44d0e1
import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.fieldmapping.FieldMappingThread;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private Tank tank;
    private FieldMappingThread fieldMappingThread;
    private EncoderPositionTracker tracker;
<<<<<<< HEAD
=======
    private MechCollection mechCollection;
>>>>>>> cb89c50a230ef754bc36c723f4e7c5688d44d0e1

    @Override
    public void robotInit() {
        Config.start();
<<<<<<< HEAD
=======

        mechCollection = new MechCollection();
>>>>>>> cb89c50a230ef754bc36c723f4e7c5688d44d0e1
        tank = new Tank();
        fieldMappingThread = new FieldMappingThread(tank);
        fieldMappingThread.start();
        tracker = fieldMappingThread.getTracker();
<<<<<<< HEAD
        auto = new Autonomous();
        teleop = new Teleop();
=======
        auto = new Autonomous(mechCollection, tank, tracker);
        teleop = new Teleop(mechCollection, tank);
>>>>>>> cb89c50a230ef754bc36c723f4e7c5688d44d0e1
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
<<<<<<< HEAD

}
=======
}
>>>>>>> origin/autonumous-framework
