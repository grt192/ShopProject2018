package frc.robot;

public class JoystickProfile {

<<<<<<< HEAD
	private static final double DEFAULT_DEADBAND = 0.1;

	private JoystickProfile() {
	}

	public static double applyDeadband(double x, double deadband) {
		return (Math.abs(x) > deadband ? x : 0);
	}

	public static double applyDeadband(double x) {
		return applyDeadband(x, DEFAULT_DEADBAND);
	}

	public static double square(double x) {
		return Math.copySign(x * x, x);
	}

	public static double clipAndSquare(double x) {
		return square(applyDeadband(x));
	}

}
=======
    private static final double DEFAULT_DEADBAND = 0.1;

    private JoystickProfile() {
    }

    public static double applyDeadband(double x, double deadband) {
        return (Math.abs(x) > deadband ? x : 0);
    }

    public static double applyDeadband(double x) {
        return applyDeadband(x, DEFAULT_DEADBAND);
    }

    public static double square(double x) {
        return Math.copySign(x * x, x);
    }

    public static double clipAndSquare(double x) {
        return square(applyDeadband(x));
    }

}
>>>>>>> cb89c50a230ef754bc36c723f4e7c5688d44d0e1
