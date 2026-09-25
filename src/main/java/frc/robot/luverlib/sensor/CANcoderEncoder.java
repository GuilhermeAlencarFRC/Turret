package frc.robot.luverlib.sensor;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.SensorDirectionValue;

/**
 * Implementação da interface IEncoder para o CANcoder da CTRE.
 * O CANcoder é um encoder absoluto magnético que se comunica via barramento CAN,
 * oferecendo alta precisão e a capacidade de manter a posição mesmo após desligar.
 * 
 * Muito utilizado em FRC para swerve drive e mecanismos que necessitam de
 * posição absoluta.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class CANcoderEncoder implements IEncoder {
    /** Instância do CANcoder da CTRE */
    private CANcoder cancoder;
    
    /** ID do dispositivo no barramento CAN */
    private int deviceID;

    /**
     * Construtor da classe CANcoderEncoder.
     * Inicializa o CANcoder com o ID especificado no barramento CAN padrão.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     */
    public CANcoderEncoder(int deviceID) {
        this.deviceID = deviceID;
        this.cancoder = new CANcoder(deviceID);
    }

    /**
     * Construtor da classe CANcoderEncoder com barramento CAN específico.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     * @param canbus O nome do barramento CAN (ex: "rio" ou "canivore")
     */
    public CANcoderEncoder(int deviceID, String canbus) {
        this.deviceID = deviceID;
        this.cancoder = new CANcoder(deviceID, canbus);
    }

    /**
     * Obtém a posição absoluta do encoder em rotações.
     * Esta posição é mantida mesmo após desligar o robô.
     * 
     * @return A posição absoluta em rotações (0 a 1 para uma rotação completa)
     */
    @Override
    public double getPosition() {
        return cancoder.getPosition().getValueAsDouble();
    }

    /**
     * Obtém a posição absoluta do encoder em graus.
     * 
     * @return A posição absoluta em graus (0 a 360)
     */
    public double getPositionDegrees() {
        return cancoder.getPosition().getValueAsDouble() * 360.0;
    }

    /**
     * Obtém a posição absoluta do encoder em radianos.
     * 
     * @return A posição absoluta em radianos (0 a 2π)
     */
    public double getPositionRadians() {
        return cancoder.getPosition().getValueAsDouble() * 2.0 * Math.PI;
    }

    /**
     * Obtém a velocidade atual do encoder em rotações por segundo.
     * 
     * @return A velocidade em rotações por segundo
     */
    @Override
    public double getVelocity() {
        return cancoder.getVelocity().getValueAsDouble();
    }

    /**
     * Obtém a velocidade atual do encoder em graus por segundo.
     * 
     * @return A velocidade em graus por segundo
     */
    public double getVelocityDegrees() {
        return cancoder.getVelocity().getValueAsDouble() * 360.0;
    }

    /**
     * Obtém a velocidade atual do encoder em RPM.
     * 
     * @return A velocidade em rotações por minuto
     */
    public double getVelocityRPM() {
        return cancoder.getVelocity().getValueAsDouble() * 60.0;
    }

    /**
     * Reseta a posição do encoder para zero.
     */
    @Override
    public void reset() {
        cancoder.setPosition(0);
    }

    /**
     * Define a posição atual do encoder.
     * 
     * @param position A nova posição em rotações
     */
    @Override
    public void setPosition(double position) {
        cancoder.setPosition(position);
    }

    /**
     * Verifica se o encoder está conectado e funcionando.
     * 
     * @return true se o encoder está conectado, false caso contrário
     */
    @Override
    public boolean isConnected() {
        return cancoder.getPosition().getStatus().isOK();
    }

    /**
     * Obtém a posição absoluta sem wrapping (posição contínua).
     * 
     * @return A posição absoluta em rotações
     */
    public double getAbsolutePosition() {
        return cancoder.getAbsolutePosition().getValueAsDouble();
    }

    /**
     * Obtém a posição absoluta em graus sem wrapping.
     * 
     * @return A posição absoluta em graus
     */
    public double getAbsolutePositionDegrees() {
        return cancoder.getAbsolutePosition().getValueAsDouble() * 360.0;
    }

    /**
     * Define a direção do sensor.
     * 
     * @param clockwisePositive Se true, rotação horária é positiva
     */
    public void setSensorDirection(boolean clockwisePositive) {
        var config = cancoder.getConfigurator();
        var magnetSensorConfig = new com.ctre.phoenix6.configs.MagnetSensorConfigs();
        magnetSensorConfig.SensorDirection = clockwisePositive ? 
            SensorDirectionValue.Clockwise_Positive : 
            SensorDirectionValue.CounterClockwise_Positive;
        config.apply(magnetSensorConfig);
    }

    /**
     * Define o offset do magneto para calibração.
     * 
     * @param offset O offset em rotações
     */
    public void setMagnetOffset(double offset) {
        var config = cancoder.getConfigurator();
        var magnetSensorConfig = new com.ctre.phoenix6.configs.MagnetSensorConfigs();
        magnetSensorConfig.MagnetOffset = offset;
        config.apply(magnetSensorConfig);
    }

    /**
     * Obtém o ID do dispositivo no barramento CAN.
     * 
     * @return O ID do dispositivo
     */
    public int getDeviceID() {
        return deviceID;
    }

    /**
     * Retorna a instância do CANcoder da CTRE.
     * 
     * @return A instância do CANcoder
     */
    public CANcoder getCANcoder() {
        return cancoder;
    }
}
