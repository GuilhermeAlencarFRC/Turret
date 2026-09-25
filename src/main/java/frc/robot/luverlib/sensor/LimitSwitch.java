package frc.robot.luverlib.sensor;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Classe wrapper para sensores de fim de curso (limit switches).
 * Limit switches são sensores digitais simples que detectam quando um
 * mecanismo atinge uma posição limite, muito usados para proteção e homing.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class LimitSwitch {
    /** Instância do DigitalInput da WPILib */
    private DigitalInput limitSwitch;

    /** Canal DIO onde o sensor está conectado */
    private int channel;

    /** Se true, inverte a lógica do sensor (normalmente fechado) */
    private boolean inverted;

    /**
     * Construtor da classe LimitSwitch.
     * Configura o sensor como normalmente aberto (NO).
     * 
     * @param channel O canal DIO onde o sensor está conectado (0-9)
     */
    public LimitSwitch(int channel) {
        this.channel = channel;
        this.limitSwitch = new DigitalInput(channel);
        this.inverted = false;
    }

    /**
     * Construtor da classe LimitSwitch com opção de inversão.
     * Use inverted=true para sensores normalmente fechados (NC).
     * 
     * @param channel  O canal DIO onde o sensor está conectado (0-9)
     * @param inverted Se true, inverte a lógica (para sensores NC)
     */
    public LimitSwitch(int channel, boolean inverted) {
        this.channel = channel;
        this.limitSwitch = new DigitalInput(channel);
        this.inverted = inverted;
    }

    /**
     * Verifica se o limit switch está acionado (pressionado).
     * Considera a configuração de inversão.
     * 
     * @return true se o sensor está acionado, false caso contrário
     */
    public boolean isPressed() {
        if (inverted) {
            return !limitSwitch.get();
        }
        return limitSwitch.get();
    }

    /**
     * Verifica se o limit switch não está acionado (solto).
     * 
     * @return true se o sensor não está acionado, false caso contrário
     */
    public boolean isReleased() {
        return !isPressed();
    }

    /**
     * Obtém o valor bruto do sensor (sem inversão).
     * 
     * @return true se o circuito está fechado, false se aberto
     */
    public boolean getRawValue() {
        return limitSwitch.get();
    }

    /**
     * Obtém o canal DIO do sensor.
     * 
     * @return O número do canal DIO
     */
    public int getChannel() {
        return channel;
    }

    /**
     * Verifica se o sensor está configurado como invertido.
     * 
     * @return true se invertido, false caso contrário
     */
    public boolean isInverted() {
        return inverted;
    }

    /**
     * Define se o sensor deve ter a lógica invertida.
     * 
     * @param inverted Se true, inverte a lógica do sensor
     */
    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    /**
     * Retorna a instância do DigitalInput da WPILib.
     * 
     * @return A instância do DigitalInput
     */
    public DigitalInput getDigitalInput() {
        return limitSwitch;
    }
}
