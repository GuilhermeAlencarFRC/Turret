package frc.robot.luverlib.sensor;

import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Implementação da interface IGyroscope para o Pigeon 2 da CTRE.
 * O Pigeon 2 é uma IMU de alta precisão que se comunica via CAN,
 * oferecendo medições de yaw, pitch e roll com fusão de sensores.
 * 
 * Muito utilizado em FRC para swerve drive, auto-balance e navegação autônoma.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class Pigeon2Gyroscope implements IGyroscope {
    /** Instância do Pigeon 2 da CTRE */
    private Pigeon2 pigeon;

    /** ID do dispositivo no barramento CAN */
    private int deviceID;

    /** Offset do yaw para calibração manual */
    private double yawOffset;

    /**
     * Construtor da classe Pigeon2Gyroscope.
     * Inicializa o Pigeon 2 com o ID especificado no barramento CAN padrão.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     */
    public Pigeon2Gyroscope(int deviceID) {
        this.deviceID = deviceID;
        this.pigeon = new Pigeon2(deviceID);
        this.yawOffset = 0;
    }

    /**
     * Construtor da classe Pigeon2Gyroscope com barramento CAN específico.
     * 
     * @param deviceID O ID do dispositivo no barramento CAN (0-62)
     * @param canbus   O nome do barramento CAN (ex: "rio" ou "canivore")
     */
    public Pigeon2Gyroscope(int deviceID, String canbus) {
        this.deviceID = deviceID;
        this.pigeon = new Pigeon2(deviceID, canbus);
        this.yawOffset = 0;
    }

    /**
     * Obtém o ângulo de rotação em torno do eixo Z (yaw/heading).
     * O valor é contínuo e não limitado a 0-360 graus.
     * 
     * @return O ângulo em graus (positivo = sentido anti-horário)
     */
    @Override
    public double getYaw() {
        return pigeon.getYaw().getValueAsDouble() - yawOffset;
    }

    /**
     * Obtém o ângulo de inclinação em torno do eixo X (pitch).
     * 
     * @return O ângulo em graus
     */
    @Override
    public double getPitch() {
        return pigeon.getPitch().getValueAsDouble();
    }

    /**
     * Obtém o ângulo de rotação em torno do eixo Y (roll).
     * 
     * @return O ângulo em graus
     */
    @Override
    public double getRoll() {
        return pigeon.getRoll().getValueAsDouble();
    }

    /**
     * Obtém o yaw como um objeto Rotation2d da WPILib.
     * O sinal é invertido para seguir a convenção da WPILib
     * (positivo = sentido anti-horário).
     * 
     * @return O ângulo como Rotation2d
     */
    @Override
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getYaw());
    }

    /**
     * Obtém a taxa de rotação em torno do eixo Z.
     * 
     * @return A taxa de rotação em graus por segundo
     */
    @Override
    public double getYawRate() {
        return pigeon.getAngularVelocityZWorld().getValueAsDouble();
    }

    /**
     * Obtém a taxa de rotação em torno do eixo X (pitch rate).
     * 
     * @return A taxa de rotação em graus por segundo
     */
    public double getPitchRate() {
        return pigeon.getAngularVelocityXWorld().getValueAsDouble();
    }

    /**
     * Obtém a taxa de rotação em torno do eixo Y (roll rate).
     * 
     * @return A taxa de rotação em graus por segundo
     */
    public double getRollRate() {
        return pigeon.getAngularVelocityYWorld().getValueAsDouble();
    }

    /**
     * Reseta o yaw para zero.
     * Define a posição atual como referência.
     */
    @Override
    public void resetYaw() {
        yawOffset = pigeon.getYaw().getValueAsDouble();
    }

    /**
     * Define o yaw para um valor específico.
     * 
     * @param angle O ângulo em graus para definir como yaw atual
     */
    @Override
    public void setYaw(double angle) {
        pigeon.setYaw(angle);
        yawOffset = 0;
    }

    /**
     * Verifica se o Pigeon 2 está conectado.
     * 
     * @return true se conectado, false caso contrário
     */
    @Override
    public boolean isConnected() {
        return pigeon.getYaw().getStatus().isOK();
    }

    /**
     * Verifica se o Pigeon 2 está calibrando.
     * O Pigeon 2 calibra automaticamente na inicialização.
     * 
     * @return true se está calibrando, false caso contrário
     */
    @Override
    public boolean isCalibrating() {
        // O Pigeon 2 calibra automaticamente, verificamos o status
        return false; // O Phoenix 6 não expõe diretamente o estado de calibração
    }

    /**
     * Obtém a aceleração no eixo X em g's.
     * 
     * @return A aceleração em g (1g = 9.81 m/s²)
     */
    public double getAccelerationX() {
        return pigeon.getAccelerationX().getValueAsDouble();
    }

    /**
     * Obtém a aceleração no eixo Y em g's.
     * 
     * @return A aceleração em g
     */
    public double getAccelerationY() {
        return pigeon.getAccelerationY().getValueAsDouble();
    }

    /**
     * Obtém a aceleração no eixo Z em g's.
     * 
     * @return A aceleração em g
     */
    public double getAccelerationZ() {
        return pigeon.getAccelerationZ().getValueAsDouble();
    }

    /**
     * Obtém a temperatura do dispositivo em graus Celsius.
     * 
     * @return A temperatura em °C
     */
    public double getTemperature() {
        return pigeon.getTemperature().getValueAsDouble();
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
     * Retorna a instância do Pigeon 2 da CTRE.
     * 
     * @return A instância do Pigeon2
     */
    public Pigeon2 getPigeon() {
        return pigeon;
    }
}
