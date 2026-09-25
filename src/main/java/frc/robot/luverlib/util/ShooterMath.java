package frc.robot.luverlib.util;

public final class ShooterMath {

    private ShooterMath() {}

    public static double rpmFromDistance(double distanceMeters) {

        double d = clamp(distanceMeters, 1.5, 6.0);

        if (d <= 2.0)
            return 2500;

        if (d <= 3.0)
            return lerp(2.0, 3.0, 2500, 3000, d);

        if (d <= 4.0)
            return lerp(3.0, 4.0, 3000, 3600, d);

        if (d <= 5.0)
            return lerp(4.0, 5.0, 3600, 4200, d);

        return lerp(5.0, 6.0, 4200, 4800, d);
    }

    private static double lerp(
        double x0, double x1,
        double y0, double y1,
        double x
    ) {
        return y0 + (x - x0) * (y1 - y0) / (x1 - x0);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
