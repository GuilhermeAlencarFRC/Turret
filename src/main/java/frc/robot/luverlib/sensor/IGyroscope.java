package frc.robot.luverlib.sensor;

import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Interface para giroscópios e IMUs (Unidades de Medição Inercial).
 * Define os métodos básicos que qualquer implementação de giroscópio deve
 * fornecer.
 * 
 * Giroscópios são sensores essenciais em FRC para medir a orientação do robô,
 * utilizados em controle de heading, auto-balance e navegação autônoma.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public interface IGyroscope {
    /**
     * Obtém o ângulo de rotação em torno do eixo Z (yaw/heading).
     * Este é o ângulo mais comumente usado para controle de direção.
     * 
     * @return O ângulo em graus (positivo = sentido anti-horário)
     */
    double getYaw();

    /**
     * Obtém o ângulo de inclinação em torno do eixo X (pitch).
     * Representa a inclinação para frente/trás do robô.
     * 
     * @return O ângulo em graus
     */
    double getPitch();

    /**
     * Obtém o ângulo de rotação em torno do eixo Y (roll).
     * Representa a inclinação lateral do robô.
     * 
     * @return O ângulo em graus
     */
    double getRoll();

    /**
     * Obtém o yaw como um objeto Rotation2d da WPILib.
     * Útil para integração com classes de odometria e cinemática.
     * 
     * @return O ângulo como Rotation2d
     */
    Rotation2d getRotation2d();

    /**
     * Obtém a taxa de rotação em torno do eixo Z (velocidade angular do yaw).
     * 
     * @return A taxa de rotação em graus por segundo
     */
    double getYawRate();

    /**
     * Reseta o yaw para zero.
     * Mantém a posição atual como referência para leituras futuras.
     */
    void resetYaw();

    /**
     * Define o yaw para um valor específico.
     * 
     * @param angle O ângulo em graus para definir como yaw atual
     */
    void setYaw(double angle);

    /**
     * Verifica se o giroscópio está conectado e funcionando.
     * 
     * @return true se conectado, false caso contrário
     */
    boolean isConnected();

    /**
     * Verifica se o giroscópio está calibrando.
     * Durante a calibração, as leituras podem não ser precisas.
     * 
     * @return true se está calibrando, false caso contrário
     */
    boolean isCalibrating();
}
