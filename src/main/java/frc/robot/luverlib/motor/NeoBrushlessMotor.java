package frc.robot.luverlib.motor;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

/**
 * Implementação da interface IBrushlessMotor para motores brushless Neo.
 * Esta classe encapsula a funcionalidade do motor Neo usando a biblioteca
 * SparkMax.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class NeoBrushlessMotor implements IBrushlessMotor {
    private SparkMax encoder;
    private int currentID;

    public NeoBrushlessMotor(int deviceID) {
        encoder = new SparkMax(deviceID, MotorType.kBrushless);
        this.currentID = deviceID;
    }

    @Override
    public double getSpeed() {
        if (encoder.get() > 0) {
            return encoder.get() * NeoBrushlessConfig.DEFAULT_RPM_FORWARD;
        } else {
            return encoder.get() * NeoBrushlessConfig.DEFAULT_RPM_REVERSE;
        }
    }

    @Override
    public void setSpeed(double rpm, boolean applyProtection) {
        if (rpm == 0) {
            encoder.set(0);
            return;
        }

        if (applyProtection) {
            if (rpm > NeoBrushlessConfig.DEFAULT_RPM_FORWARD) {
                rpm = NeoBrushlessConfig.DEFAULT_RPM_FORWARD;
            } else if (rpm < NeoBrushlessConfig.DEFAULT_RPM_REVERSE) {
                rpm = NeoBrushlessConfig.DEFAULT_RPM_REVERSE;
            }
        }

        if (rpm > 0) {
            encoder.set(rpm / NeoBrushlessConfig.DEFAULT_RPM_FORWARD);
        } else {
            encoder.set(rpm / NeoBrushlessConfig.DEFAULT_RPM_REVERSE);
        }
    }

    @Override
    public double getVoltage() {
        if (encoder.get() > 0) {
            return encoder.get() * NeoBrushlessConfig.DEFAULT_RPM_FORWARD;
        } else {
            return encoder.get() * NeoBrushlessConfig.DEFAULT_RPM_REVERSE;
        }
    }

    @Override
    public void setVoltage(double voltage, boolean applyProtection) {
        if (applyProtection) {
            encoder.setVoltage(
                    Math.max(NeoBrushlessConfig.MIN_VOLTAGE, Math.min(NeoBrushlessConfig.MAX_VOLTAGE, voltage)));
        } else {
            encoder.setVoltage(voltage);
        }
    }

    @Override
    public int getID() {
        return currentID;
    }

    public SparkMax getEncoder() {
        return encoder;
    }
}
