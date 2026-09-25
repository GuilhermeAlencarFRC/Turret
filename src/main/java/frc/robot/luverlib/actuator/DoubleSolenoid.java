package frc.robot.luverlib.actuator;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * Classe wrapper para solenóides duplos (cilindros pneumáticos de dupla ação).
 * Fornece métodos simplificados para controle de cilindros pneumáticos,
 * muito utilizados em FRC para mecanismos como garras, braços e elevadores.
 * 
 * Solenóides duplos possuem duas posições estáveis (estendido e retraído)
 * e mantêm a posição mesmo sem ar comprimido.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class DoubleSolenoid {
    /** Instância do DoubleSolenoid da WPILib */
    private edu.wpi.first.wpilibj.DoubleSolenoid solenoid;

    /** Canal do módulo de pneumática para posição forward */
    private int forwardChannel;

    /** Canal do módulo de pneumática para posição reverse */
    private int reverseChannel;

    /**
     * Construtor da classe DoubleSolenoid para CTRE PCM.
     * 
     * @param forwardChannel O canal para estender o cilindro
     * @param reverseChannel O canal para retrair o cilindro
     */
    public DoubleSolenoid(int forwardChannel, int reverseChannel) {
        this.forwardChannel = forwardChannel;
        this.reverseChannel = reverseChannel;
        this.solenoid = new edu.wpi.first.wpilibj.DoubleSolenoid(
                PneumaticsModuleType.CTREPCM, forwardChannel, reverseChannel);
    }

    /**
     * Construtor da classe DoubleSolenoid com ID do módulo e tipo especificados.
     * 
     * @param moduleId       O ID do módulo de pneumática (0 para PCM padrão)
     * @param moduleType     O tipo do módulo (CTREPCM ou REVPH)
     * @param forwardChannel O canal para estender o cilindro
     * @param reverseChannel O canal para retrair o cilindro
     */
    public DoubleSolenoid(int moduleId, PneumaticsModuleType moduleType,
            int forwardChannel, int reverseChannel) {
        this.forwardChannel = forwardChannel;
        this.reverseChannel = reverseChannel;
        this.solenoid = new edu.wpi.first.wpilibj.DoubleSolenoid(
                moduleId, moduleType, forwardChannel, reverseChannel);
    }

    /**
     * Estende o cilindro pneumático.
     */
    public void extend() {
        solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward);
    }

    /**
     * Retrai o cilindro pneumático.
     */
    public void retract() {
        solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse);
    }

    /**
     * Desliga ambos os solenóides.
     * O cilindro manterá sua posição atual por inércia.
     */
    public void off() {
        solenoid.set(edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff);
    }

    /**
     * Alterna entre estendido e retraído.
     */
    public void toggle() {
        solenoid.toggle();
    }

    /**
     * Verifica se o cilindro está estendido.
     * 
     * @return true se estendido, false caso contrário
     */
    public boolean isExtended() {
        return solenoid.get() == edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
    }

    /**
     * Verifica se o cilindro está retraído.
     * 
     * @return true se retraído, false caso contrário
     */
    public boolean isRetracted() {
        return solenoid.get() == edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
    }

    /**
     * Verifica se o solenóide está desligado.
     * 
     * @return true se desligado, false caso contrário
     */
    public boolean isOff() {
        return solenoid.get() == edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
    }

    /**
     * Obtém o estado atual do solenóide.
     * 
     * @return O estado atual (kForward, kReverse, ou kOff)
     */
    public edu.wpi.first.wpilibj.DoubleSolenoid.Value getState() {
        return solenoid.get();
    }

    /**
     * Obtém o canal forward do solenóide.
     * 
     * @return O número do canal forward
     */
    public int getForwardChannel() {
        return forwardChannel;
    }

    /**
     * Obtém o canal reverse do solenóide.
     * 
     * @return O número do canal reverse
     */
    public int getReverseChannel() {
        return reverseChannel;
    }

    /**
     * Verifica se o canal forward está desabilitado devido a curto-circuito.
     * 
     * @return true se desabilitado por curto, false caso contrário
     */
    public boolean isFwdSolenoidDisabled() {
        return solenoid.isFwdSolenoidDisabled();
    }

    /**
     * Verifica se o canal reverse está desabilitado devido a curto-circuito.
     * 
     * @return true se desabilitado por curto, false caso contrário
     */
    public boolean isRevSolenoidDisabled() {
        return solenoid.isRevSolenoidDisabled();
    }

    /**
     * Retorna a instância do DoubleSolenoid da WPILib.
     * 
     * @return A instância do DoubleSolenoid
     */
    public edu.wpi.first.wpilibj.DoubleSolenoid getSolenoid() {
        return solenoid;
    }
}
