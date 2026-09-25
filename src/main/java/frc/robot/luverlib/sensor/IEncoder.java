package frc.robot.luverlib.sensor;

/**
 * Interface para encoders de posição e velocidade.
 * Define os métodos básicos que qualquer implementação de encoder deve
 * fornecer.
 * 
 * Encoders são sensores que medem a rotação de um eixo, muito utilizados em FRC
 * para medir posição e velocidade de mecanismos como rodas, braços e
 * elevadores.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public interface IEncoder {
    /**
     * Obtém a posição atual do encoder.
     * 
     * @return A posição em unidades definidas pela implementação (rotações, graus,
     *         etc.)
     */
    double getPosition();

    /**
     * Obtém a velocidade atual do encoder.
     * 
     * @return A velocidade em unidades definidas pela implementação (RPM, graus/s,
     *         etc.)
     */
    double getVelocity();

    /**
     * Reseta a posição do encoder para zero.
     */
    void reset();

    /**
     * Define a posição atual do encoder.
     * 
     * @param position A nova posição a ser definida
     */
    void setPosition(double position);

    /**
     * Verifica se o encoder está conectado e funcionando.
     * 
     * @return true se o encoder está conectado, false caso contrário
     */
    boolean isConnected();
}
