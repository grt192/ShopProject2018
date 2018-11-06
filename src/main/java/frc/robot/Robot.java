/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.config.Config;
import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.fieldmapping.FieldMappingThread;
import frc.drivetrain.TankData;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private Tank tank;
    private FieldMappingThread fieldMappingThread;
    private EncoderPositionTracker tracker;

    @Override
    public void robotInit() {
        Config.start();
        tank = new Tank();
        fieldMappingThread = new FieldMappingThread(tank);
        fieldMappingThread.start();
        tracker = fieldMappingThread.getTracker();
        auto = new Autonomous();
        teleop = new Teleop(tank);
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
        printPosTracking();
    }

    private void printPosTracking() {
        SmartDashboard.putNumber("x position", tracker.getX());
        SmartDashboard.putNumber("y position", tracker.getY());
    }

    private XboxController xbox = new XboxController(0);

    @Override
    public void testPeriodic() {
        if (xbox.getAButton()) {
            tank.setRaw(0.5, 0.5);
        } else if (xbox.getBButton()) {
            tank.setRaw(-0.5, -0.5);
        } else {
            tank.setRaw(-xbox.getY(Hand.kLeft), -xbox.getY(Hand.kRight));
        }
        TankData td = tank.getTankData();
        System.out.println(td.leftSpeed + ", " + td.rightSpeed + ", " + td.avgSpeed);
        printPosTracking();
    }
}
