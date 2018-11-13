package frc.drivetrain;

public interface ChooseMode {

    public enum DriveMode {
        Arcade, Tank
    }

    /**
     * Gets input from Left Stick in tank mode
     * 
     * @return double for Left Joy stick (-1 to 1)
     */
    double getLeftY();

    /**
     * Gets Input from Right Stick in tank mode
     * 
     * @return double for Right Joy stick (-1 to 1)
     */
    double getRightY();

    /**
     * Get the speed for arcade mode
     * 
     * @return double for Speed
     */
    double getSpeed();

    /**
     * Gets angle for arcade mode
     * 
     * @return double for joy stick angle
     */
    double getRotate();

    /**
     * 
     * @return drive mode (Tank/Arcade)
     */
    DriveMode getDriveMode();

    /**
     * 
     * @return drive mode (Tank/Arcade)
     */
    void setDriveMode(DriveMode aDriveMode);

    boolean getDriveForward();

    boolean getDriveBackward();

    boolean isReducedSpeedMode();

}