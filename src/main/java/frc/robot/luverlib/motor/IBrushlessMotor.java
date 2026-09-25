package frc.robot.luverlib.motor;

public interface IBrushlessMotor {
    public double getSpeed();

    public void setSpeed(double speed, boolean applyProtection);

    public double getVoltage();

    public void setVoltage(double voltage, boolean applyProtection);

    public int getID();
}
