package frc.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Config {
	private static Map<String, String> map;

	public static int getInt(String key) {
		try {
			return Integer.parseInt(map.get(key));
		} catch (Exception e) {
			return -1;
		}
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(map.get(key));
	}

	public static String getString(String key) {
		String result = map.get(key);
		if (result == null) {
			return "";
		}
		return result;
	}

	public static double getDouble(String key) {
		try {
			return Double.parseDouble(map.get(key));
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static void start() {
		map = new HashMap<>();
		String fileName = "config.txt";
		System.out.println("reading from file " + fileName);
		InputStream stream = Config.class.getResourceAsStream(fileName);
		Scanner scanner = new Scanner(stream);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			while (line.startsWith(" "))
				line = line.substring(1);

			if (line.length() > 0 && line.charAt(0) != '#') {
				String[] splitted = line.trim().split("=");
				if (splitted.length == 2)
					map.put(splitted[0], splitted[1]);
			}
		}
		scanner.close();

		for (String s : map.keySet()) {
			System.out.println(s + ": " + getString(s));
		}

	}

	public static void defaultConfigTalon(TalonSRX talon) {
		talon.configForwardSoftLimitEnable(false, 0);
		talon.configReverseSoftLimitEnable(false, 0);
		talon.setNeutralMode(NeutralMode.Brake);
		talon.configOpenloopRamp(0, 0);
	}
}
