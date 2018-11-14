/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.config.Config;
import frc.drivetrain.Tank;
import frc.fieldmapping.EncoderPositionTracker;
import frc.fieldmapping.FieldMappingThread;
import frc.fieldmapping.montecarlo.MonteCarloLocalizer;

public class Robot extends IterativeRobot {

    private Autonomous auto;
    private Teleop teleop;

    private Tank tank;
    private FieldMappingThread fieldMappingThread;
    private EncoderPositionTracker tracker;
    private MonteCarloLocalizer mcl;

    private XboxController xbox;

    @Override
    public void robotInit() {
        Config.start();
        xbox = new XboxController(0);
        tank = new Tank();
        fieldMappingThread = new FieldMappingThread(tank);
        fieldMappingThread.start();
        tracker = fieldMappingThread.getTracker();
        mcl = new MonteCarloLocalizer(tank, 100);
        mcl.initialize(0, 0, 0);
        mcl.start();
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
        double l = (-2.0) * JoystickProfile.applyDeadband(xbox.getY(Hand.kLeft));
        double r = (-2.0) * JoystickProfile.applyDeadband(xbox.getY(Hand.kRight));
        tank.set(l, r);
        SmartDashboard.putNumber("Basic X", tracker.getX());
        SmartDashboard.putNumber("Basic Y", tracker.getY());
        SmartDashboard.putNumber("Gyro Angle", tank.getTankData().gyroAngle);
        SmartDashboard.putNumber("MCL X", mcl.getX());
        SmartDashboard.putNumber("MCL Y", mcl.getY());
        SmartDashboard.putNumber("MCL Angle", mcl.getAngle());
    }

    @Override
    public void testPeriodic() {
    }
}
