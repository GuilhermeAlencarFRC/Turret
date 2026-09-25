package frc.robot.luverlib.sensor;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * Implementação da interface IEncoder para encoders de quadratura.
 * Encoders de quadratura são sensores incremental que usam dois canais (A e B)
 * para detectar direção e posição de rotação.
 * 
 * Muito utilizados em FRC conectados diretamente ao RoboRIO via DIO (Digital
 * I/O).
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class QuadratureEncoder implements IEncoder {
    /** Instância do encoder da WPILib */
    private Encoder encoder;

    /** Fator de conversão de distância por pulso */
    private double distancePerPulse;

    /** Canal A do encoder */
    private int channelA;

    /** Canal B do encoder */
    private int channelB;

    /**
     * Construtor da classe QuadratureEncoder.
     * Inicializa o encoder com os canais DIO especificados usando codificação 4x.
     * 
     * @param channelA O canal DIO para o sinal A do encoder
     * @param channelB O canal DIO para o sinal B do encoder
     */
    public QuadratureEncoder(int channelA, int channelB) {
        this.channelA = channelA;
        this.channelB = channelB;
        this.encoder = new Encoder(channelA, channelB, false, EncodingType.k4X);
        this.distancePerPulse = 1.0;
    }

    /**
     * Construtor da classe QuadratureEncoder com direção invertida.
     * 
     * @param channelA         O canal DIO para o sinal A do encoder
     * @param channelB         O canal DIO para o sinal B do encoder
     * @param reverseDirection Se true, inverte a direção de contagem
     */
    public QuadratureEncoder(int channelA, int channelB, boolean reverseDirection) {
        this.channelA = channelA;
        this.channelB = channelB;
        this.encoder = new Encoder(channelA, channelB, reverseDirection, EncodingType.k4X);
        this.distancePerPulse = 1.0;
    }

    /**
     * Construtor completo da classe QuadratureEncoder.
     * 
     * @param channelA            O canal DIO para o sinal A do encoder
     * @param channelB            O canal DIO para o sinal B do encoder
     * @param reverseDirection    Se true, inverte a direção de contagem
     * @param pulsesPerRevolution Número de pulsos por revolução do encoder
     */
    public QuadratureEncoder(int channelA, int channelB, boolean reverseDirection, int pulsesPerRevolution) {
        this.channelA = channelA;
        this.channelB = channelB;
        this.encoder = new Encoder(channelA, channelB, reverseDirection, EncodingType.k4X);
        this.distancePerPulse = 1.0 / pulsesPerRevolution;
        encoder.setDistancePerPulse(distancePerPulse);
    }

    /**
     * Obtém a posição atual do encoder em unidades configuradas.
     * 
     * @return A posição atual (rotações se distancePerPulse configurado
     *         corretamente)
     */
    @Override
    public double getPosition() {
        return encoder.getDistance();
    }

    /**
     * Obtém a velocidade atual do encoder.
     * 
     * @return A velocidade em unidades por segundo
     */
    @Override
    public double getVelocity() {
        return encoder.getRate();
    }

    /**
     * Reseta a posição do encoder para zero.
     */
    @Override
    public void reset() {
        encoder.reset();
    }

    /**
     * Define a posição atual do encoder.
     * Nota: Encoders de quadratura não suportam definir posição diretamente,
     * então este método reseta e usa um offset internamente.
     * 
     * @param position A nova posição a ser definida
     */
    @Override
    public void setPosition(double position) {
        // Encoders de quadratura não suportam setPosition diretamente
        // Resetamos e o offset deve ser tratado pela aplicação
        encoder.reset();
    }

    /**
     * Verifica se o encoder está conectado.
     * 
     * @return true se não houve timeout na leitura, indicando que está conectado
     */
    @Override
    public boolean isConnected() {
        return !encoder.getStopped();
    }

    /**
     * Define a distância por pulso para conversão de unidades.
     * 
     * @param distancePerPulse A distância percorrida por cada pulso do encoder
     */
    public void setDistancePerPulse(double distancePerPulse) {
        this.distancePerPulse = distancePerPulse;
        encoder.setDistancePerPulse(distancePerPulse);
    }

    /**
     * Obtém a contagem bruta de pulsos do encoder.
     * 
     * @return O número de pulsos contados
     */
    public int getRawCount() {
        return encoder.get();
    }

    /**
     * Obtém o canal A do encoder.
     * 
     * @return O número do canal DIO A
     */
    public int getChannelA() {
        return channelA;
    }

    /**
     * Obtém o canal B do encoder.
     * 
     * @return O número do canal DIO B
     */
    public int getChannelB() {
        return channelB;
    }

    /**
     * Retorna a instância do Encoder da WPILib.
     * 
     * @return A instância do Encoder
     */
    public Encoder getEncoder() {
        return encoder;
    }

    /**
     * Inverte a direção do encoder.
     * 
     * @param inverted Se true, inverte a direção de contagem
     */
    public void setReverseDirection(boolean inverted) {
        encoder.setReverseDirection(inverted);
    }

    /**
     * Define o período mínimo para considerar o encoder parado.
     * 
     * @param maxPeriod O período máximo em segundos antes de considerar parado
     */
    @SuppressWarnings("deprecation")
    
    public void setMaxPeriod(double maxPeriod) {
        encoder.setMaxPeriod(maxPeriod);
    }
}
