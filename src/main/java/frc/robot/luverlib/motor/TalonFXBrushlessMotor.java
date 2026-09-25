package frc.robot.luverlib.motor;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VoltageOut;

/**
 * Implementação da interface IBrushlessMotor para motores brushless TalonFX
 * (Falcon 500 / Kraken X60).
 * Esta classe encapsula a funcionalidade do motor TalonFX usando a biblioteca
 * Phoenix 6.
 * 
 * O TalonFX é um motor brushless integrado com controlador, muito utilizado em
 * FRC
 * por sua alta potência e encoder integrado de alta resolução.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class TalonFXBrushlessMotor implements IBrushlessMotor {
    /** Instância do motor TalonFX */
    private TalonFX motor;

    /** ID do dispositivo CAN */
    private int currentID;

    /** Controle de saída por duty cycle (porcentagem) */
    private DutyCycleOut dutyCycleControl;

    /** Controle de saída por voltagem */
    private VoltageOut voltageControl;

    /**
     * Construtor da classe TalonFXBrushlessMotor.
     * Inicializa o motor TalonFX com o ID especificado no barramento CAN padrão.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     */
    public TalonFXBrushlessMotor(int deviceID) {
        motor = new TalonFX(deviceID);
        this.currentID = deviceID;
        this.dutyCycleControl = new DutyCycleOut(0);
        this.voltageControl = new VoltageOut(0);
    }

    /**
     * Construtor da classe TalonFXBrushlessMotor com barramento CAN específico.
     * Inicializa o motor TalonFX com o ID e barramento CAN especificados.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     * @param canbus   O nome do barramento CAN (ex: "rio" ou "canivore")
     */
    public TalonFXBrushlessMotor(int deviceID, String canbus) {
        motor = new TalonFX(deviceID, canbus);
        this.currentID = deviceID;
        this.dutyCycleControl = new DutyCycleOut(0);
        this.voltageControl = new VoltageOut(0);
    }

    /**
     * Obtém a velocidade atual do motor em RPM.
     * Utiliza o encoder integrado do TalonFX para medir a velocidade.
     * 
     * @return A velocidade atual do motor em rotações por minuto (RPM)
     */
    @Override
    public double getSpeed() {
        // Converte rotações por segundo para RPM (multiplica por 60)
        return motor.getVelocity().getValueAsDouble() * 60.0;
    }

    /**
     * Define a velocidade do motor em RPM.
     * Converte o valor de RPM para duty cycle baseado nas especificações do motor.
     * 
     * @param rpm             A velocidade desejada em rotações por minuto
     * @param applyProtection Se true, limita a velocidade aos valores máximos
     *                        permitidos
     */
    @Override
    public void setSpeed(double rpm, boolean applyProtection) {
        if (rpm == 0) {
            motor.setControl(dutyCycleControl.withOutput(0));
            return;
        }

        if (applyProtection) {
            if (rpm > TalonFXBrushlessConfig.DEFAULT_RPM_FORWARD) {
                rpm = TalonFXBrushlessConfig.DEFAULT_RPM_FORWARD;
            } else if (rpm < TalonFXBrushlessConfig.DEFAULT_RPM_REVERSE) {
                rpm = TalonFXBrushlessConfig.DEFAULT_RPM_REVERSE;
            }
        }

        double dutyCycle;
        if (rpm > 0) {
            dutyCycle = rpm / TalonFXBrushlessConfig.DEFAULT_RPM_FORWARD;
        } else {
            dutyCycle = rpm / Math.abs(TalonFXBrushlessConfig.DEFAULT_RPM_REVERSE);
        }

        motor.setControl(dutyCycleControl.withOutput(dutyCycle));
    }

    /**
     * Obtém a voltagem atual aplicada ao motor.
     * 
     * @return A voltagem atual em volts
     */
    @Override
    public double getVoltage() {
        return motor.getMotorVoltage().getValueAsDouble();
    }

    /**
     * Define a voltagem do motor.
     * 
     * @param voltage         A voltagem desejada em volts
     * @param applyProtection Se true, limita a voltagem aos valores seguros
     */
    @Override
    public void setVoltage(double voltage, boolean applyProtection) {
        if (applyProtection) {
            voltage = Math.max(TalonFXBrushlessConfig.MIN_VOLTAGE,
                    Math.min(TalonFXBrushlessConfig.MAX_VOLTAGE, voltage));
        }
        motor.setControl(voltageControl.withOutput(voltage));
    }

    /**
     * Obtém o ID do dispositivo no barramento CAN.
     * 
     * @return O ID do dispositivo CAN
     */
    @Override
    public int getID() {
        return currentID;
    }

    /**
     * Retorna a instância do motor TalonFX.
     * Útil para acessar funcionalidades avançadas não encapsuladas nesta classe.
     * 
     * @return A instância do TalonFX
     */
    public TalonFX getMotor() {
        return motor;
    }

    /**
     * Obtém a posição atual do encoder integrado em rotações.
     * 
     * @return A posição em rotações
     */
    public double getPosition() {
        return motor.getPosition().getValueAsDouble();
    }

    /**
     * Zera a posição do encoder integrado.
     */
    public void resetPosition() {
        motor.setPosition(0);
    }

    /**
     * Obtém a corrente de saída do motor em amperes.
     * Útil para monitoramento e proteção contra sobrecarga.
     * 
     * @return A corrente de saída em amperes
     */
    public double getOutputCurrent() {
        return motor.getStatorCurrent().getValueAsDouble();
    }

    /**
     * Obtém a temperatura do motor em graus Celsius.
     * Útil para monitoramento térmico e proteção do motor.
     * 
     * @return A temperatura em graus Celsius
     */
    public double getTemperature() {
        return motor.getDeviceTemp().getValueAsDouble();
    }

    /**
     * Para o motor imediatamente.
     */
    public void stop() {
        motor.stopMotor();
    }
}
