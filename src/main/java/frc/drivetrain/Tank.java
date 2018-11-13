package frc.drivetrain;

import frc.config.Config;
import frc.drivetrain.ChooseMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Tank {

    private SpeedController SpeedControllerLeft;
    private SpeedController SpeedControllerRight;
    private ChooseMode DriverJoystick;
    private DifferentialDrive RobotDrive;

    private TalonSRX leftMotor;
    private TalonSRX rightMotor;

    private Encoder EncoderLeft;
    private Encoder EncoderRight;

    private double RightEncDistance;
    private double LeftEncDistance;

    /**
     * Takes 2 speed controllers and joy stick arguments
     * 
     * @param aSpeedControllerLeft
     *                                  Argument for left Speed Controller
     * @param aSpeedControllerRight
     *                                  Argument for right Speed Controller
     * @param aDriverJoystick
     *                                  Argument Driver Joy stick
     */
    public Tank(SpeedController SpeedControllerLeft, SpeedController SpeedControllerRight, ChooseMode DriverJoystick,
            Encoder EncoderLeft, Encoder EncoderRight) {

        SpeedControllerLeft = SpeedControllerLeft;
        SpeedControllerRight = SpeedControllerRight;
        DriverJoystick = DriverJoystick;
        RobotDrive = new DifferentialDrive(SpeedControllerLeft, SpeedControllerRight);
        EncoderLeft = EncoderLeft;
        EncoderRight = EncoderRight;

        RobotDrive.setSafetyEnabled(false);

        EncoderLeft.setReverseDirection(true);
    }

    public void update() {
        RightEncDistance = EncoderLeft.getDistance();
        LeftEncDistance = EncoderRight.getDistance();
        SpeedControllerLeft.get();
        SpeedControllerRight.get();

    }

    public void control() {

        if (ChooseMode.DriveMode.Tank == DriverJoystick.getDriveMode()) {

            double left = DriverJoystick.getLeftY();
            double right = DriverJoystick.getRightY();
            double autoDriveSpeed = 0.65;

            if (DriverJoystick.getDriveForward()) {

                RobotDrive.tankDrive(autoDriveSpeed, autoDriveSpeed);
            } else if (DriverJoystick.getDriveBackward()) {

                RobotDrive.tankDrive(-autoDriveSpeed, -autoDriveSpeed);
            } else {

                RobotDrive.tankDrive(left, right, true);
            }
        }
    }

    public void rereadPreferences() {

        EncoderLeft.setDistancePerPulse(Config.getInt("encLeft"));
        EncoderRight.setDistancePerPulse(Config.getInt("encRight"));
    }

    public void stop() {

        this.setMotorSpeed(0, 0);

    }

    public void setMotorSpeed(double aLeft, double aRight) {
        RobotDrive.setLeftRightMotorOutputs(aLeft, aRight);
    }

    public double calculateDistanceRight() {

        return LeftEncDistance;
    }

    public double calculateDistanceLeft() {

        return RightEncDistance;
    }

    public void resetEncoders() {

        this.EncoderLeft.reset();
        this.EncoderRight.reset();
    }
}