package frc.robot;

public class GRTUtil {

	// GRTUtil because there are too many other Util classes
	private GRTUtil() {
	}

	public static double clamp(double min, double x, double max) {
		return Math.min(Math.max(min, x), max);
	}

	public static double positiveMod(double x, double mod) {
		return (((x % mod) + mod) % mod);
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
