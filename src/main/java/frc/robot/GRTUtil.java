package frc.robot;

public class GRTUtil {

	// GRTUtil because there are too many other Util classes
	private GRTUtil() {
	}

	public static final double TWO_PI = 2 * Math.PI;

	public static double clamp(double min, double x, double max) {
		return Math.min(Math.max(min, x), max);
	}

	public static double positiveMod(double x, double mod) {
		return (((x % mod) + mod) % mod);
	}

	public static double modAngle(double x) {
		return positiveMod(x, TWO_PI);
	}

	public static double distanceBetweenAngles(double a1, double a2) {
		a1 = modAngle(a1);
		a2 = modAngle(a2);
		double d = a1 - a2;
		if (d > Math.PI) {
			d -= TWO_PI;
		}
		if (d < -Math.PI) {
			d += TWO_PI;
		}
		return d;
	}

	public static boolean inRange(double min, double x, double max) {
		return x >= min && x <= max;
	}

	public static double normPDF(double x) {
		return Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
	}

	public static double normPDF(double x, double mu, double sigma) {
		return normPDF((x - mu) / sigma) / sigma;
	}

}
