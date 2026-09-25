package frc.robot.luverlib.actuator;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Classe wrapper para solenóides simples (cilindros pneumáticos de ação
 * simples).
 * Fornece métodos simplificados para controle de cilindros com retorno por
 * mola,
 * onde apenas uma direção é controlada pelo ar comprimido.
 * 
 * Solenóides simples são mais econômicos e adequados quando uma posição
 * pode ser mantida pela gravidade ou por mola de retorno.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class SingleSolenoid {
    /** Instância do Solenoid da WPILib */
    private Solenoid solenoid;

    /** Canal do módulo de pneumática */
    private int channel;

    /**
     * Construtor da classe SingleSolenoid para CTRE PCM.
     * 
     * @param channel O canal do módulo de pneumática
     */
    public SingleSolenoid(int channel) {
        this.channel = channel;
        this.solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, channel);
    }

    /**
     * Construtor da classe SingleSolenoid com ID do módulo e tipo especificados.
     * 
     * @param moduleId   O ID do módulo de pneumática
     * @param moduleType O tipo do módulo (CTREPCM ou REVPH)
     * @param channel    O canal do módulo de pneumática
     */
    public SingleSolenoid(int moduleId, PneumaticsModuleType moduleType, int channel) {
        this.channel = channel;
        this.solenoid = new Solenoid(moduleId, moduleType, channel);
    }

    /**
     * Ativa o solenóide (estende o cilindro).
     */
    public void activate() {
        solenoid.set(true);
    }

    /**
     * Desativa o solenóide (permite retorno por mola).
     */
    public void deactivate() {
        solenoid.set(false);
    }

    /**
     * Define o estado do solenóide.
     * 
     * @param on Se true, ativa o solenóide; se false, desativa
     */
    public void set(boolean on) {
        solenoid.set(on);
    }

    /**
     * Alterna o estado do solenóide.
     */
    public void toggle() {
        solenoid.toggle();
    }

    /**
     * Verifica se o solenóide está ativado.
     * 
     * @return true se ativado, false se desativado
     */
    public boolean isActivated() {
        return solenoid.get();
    }

    /**
     * Obtém o canal do solenóide.
     * 
     * @return O número do canal
     */
    public int getChannel() {
        return channel;
    }

    /**
     * Verifica se o solenóide está desabilitado devido a curto-circuito.
     * 
     * @return true se desabilitado por curto, false caso contrário
     */
    public boolean isDisabled() {
        return solenoid.isDisabled();
    }

    /**
     * Define um pulso no solenóide com duração especificada.
     * O solenóide será ativado e depois desativado automaticamente.
     * 
     * @param durationSeconds A duração do pulso em segundos
     */
    public void pulse(double durationSeconds) {
        solenoid.setPulseDuration(durationSeconds);
        solenoid.startPulse();
    }

    /**
     * Retorna a instância do Solenoid da WPILib.
     * 
     * @return A instância do Solenoid
     */
    public Solenoid getSolenoid() {
        return solenoid;
    }
}
