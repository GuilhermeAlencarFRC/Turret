package frc.robot.luverlib.actuator;

import edu.wpi.first.wpilibj.Servo;

/**
 * Classe wrapper para servo motores.
 * Fornece métodos simplificados para controle de posição de servos,
 * muito utilizados em FRC para mecanismos de precisão como garras e travas.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class ServoMotor {
    /** Instância do Servo da WPILib */
    private Servo servo;

    /** Canal PWM onde o servo está conectado */
    private int channel;

    /** Ângulo mínimo do servo em graus */
    private double minAngle;

    /** Ângulo máximo do servo em graus */
    private double maxAngle;

    /**
     * Construtor da classe ServoMotor.
     * Configura o servo com range padrão de 0 a 180 graus.
     * 
     * @param channel O canal PWM onde o servo está conectado (0-9)
     */
    public ServoMotor(int channel) {
        this.channel = channel;
        this.servo = new Servo(channel);
        this.minAngle = 0.0;
        this.maxAngle = 180.0;
    }

    /**
     * Construtor da classe ServoMotor com range customizado.
     * 
     * @param channel  O canal PWM onde o servo está conectado
     * @param minAngle O ângulo mínimo do servo em graus
     * @param maxAngle O ângulo máximo do servo em graus
     */
    public ServoMotor(int channel, double minAngle, double maxAngle) {
        this.channel = channel;
        this.servo = new Servo(channel);
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
    }

    /**
     * Define a posição do servo usando valor normalizado (0-1).
     * 0.0 = posição mínima, 1.0 = posição máxima.
     * 
     * @param position Valor entre 0.0 e 1.0
     */
    public void setPosition(double position) {
        position = Math.max(0.0, Math.min(1.0, position));
        servo.set(position);
    }

    /**
     * Define o ângulo do servo em graus.
     * O ângulo é limitado ao range configurado.
     * 
     * @param angle O ângulo desejado em graus
     */
    public void setAngle(double angle) {
        angle = Math.max(minAngle, Math.min(maxAngle, angle));
        servo.setAngle(angle);
    }

    /**
     * Obtém a posição atual do servo (valor normalizado 0-1).
     * 
     * @return A posição atual entre 0.0 e 1.0
     */
    public double getPosition() {
        return servo.get();
    }

    /**
     * Obtém o ângulo atual do servo em graus.
     * 
     * @return O ângulo atual em graus
     */
    public double getAngle() {
        return servo.getAngle();
    }

    /**
     * Move o servo para a posição mínima.
     */
    public void goToMin() {
        servo.set(0.0);
    }

    /**
     * Move o servo para a posição máxima.
     */
    public void goToMax() {
        servo.set(1.0);
    }

    /**
     * Move o servo para a posição central.
     */
    public void goToCenter() {
        servo.set(0.5);
    }

    /**
     * Obtém o canal PWM do servo.
     * 
     * @return O número do canal PWM
     */
    public int getChannel() {
        return channel;
    }

    /**
     * Obtém o ângulo mínimo configurado.
     * 
     * @return O ângulo mínimo em graus
     */
    public double getMinAngle() {
        return minAngle;
    }

    /**
     * Obtém o ângulo máximo configurado.
     * 
     * @return O ângulo máximo em graus
     */
    public double getMaxAngle() {
        return maxAngle;
    }

    /**
     * Define o range de ângulos do servo.
     * 
     * @param minAngle O novo ângulo mínimo em graus
     * @param maxAngle O novo ângulo máximo em graus
     */
    public void setAngleRange(double minAngle, double maxAngle) {
        this.minAngle = minAngle;
        this.maxAngle = maxAngle;
    }

    /**
     * Retorna a instância do Servo da WPILib.
     * 
     * @return A instância do Servo
     */
    public Servo getServo() {
        return servo;
    }
}
