/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;
import frc.drivetrain.Tank;
import frc.drivetrain.TankData;
import frc.fieldmapping.EncoderPositionTracker;
import frc.fieldmapping.FieldMappingThread;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private XboxController xbox;
    private Tank tank;
    private FieldMappingThread fieldMappingThread;
    private EncoderPositionTracker tracker;

    @Override
    public void robotInit() {
        Config.start();
        xbox = new XboxController(0);
        tank = new Tank();
        fieldMappingThread = new FieldMappingThread(tank);
        fieldMappingThread.start();
        tracker = fieldMappingThread.getTracker();
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
        teleop.periodic();
        double left = JoystickProfile.applyDeadband(-xbox.getY(Hand.kLeft));
        double right = JoystickProfile.applyDeadband(-xbox.getY(Hand.kRight));
        if (xbox.getAButton()) {
            left = 0.5;
            right = 0.5;
        } else if (xbox.getBButton()) {
            left = -0.5;
            right = -0.5;
        }
        tank.set(left, right);
        TankData data = tank.getTankData();
        System.out.println(data.leftSpeed / left);
        System.out.println(data.rightSpeed / right);
    }

    @Override
    public void testPeriodic() {
    }
}
