package frc.mechs;

import edu.wpi.first.wpilibj.Encoder;
import frc.config.Config;

public class EncoderStuff {
    private Encoder enc;

    public EncoderStuff() {
        enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        enc.setMaxPeriod(.1);
        enc.setMinRate(10);
        enc.setDistancePerPulse(5);
        enc.setReverseDirection(true);
        enc.setSamplesToAverage(7);
    }

    public double distanceToAngle() {
        return Config.getInt("ticks_to_meters") * 2 * Math.PI;
    }
}