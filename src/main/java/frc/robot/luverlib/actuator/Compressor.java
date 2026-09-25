package frc.robot.luverlib.actuator;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * Classe wrapper para o compressor pneumático.
 * Fornece métodos simplificados para controle e monitoramento do compressor,
 * que é responsável por manter a pressão do sistema pneumático.
 * 
 * O compressor tipicamente liga automaticamente quando a pressão cai
 * e desliga quando atinge a pressão máxima (120 PSI).
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class Compressor {
    /** Instância do Compressor da WPILib */
    private edu.wpi.first.wpilibj.Compressor compressor;

    /**
     * Construtor da classe Compressor para CTRE PCM.
     * Usa o módulo PCM padrão (ID 0).
     */
    public Compressor() {
        this.compressor = new edu.wpi.first.wpilibj.Compressor(PneumaticsModuleType.CTREPCM);
    }

    /**
     * Construtor da classe Compressor com tipo de módulo especificado.
     * 
     * @param moduleType O tipo do módulo (CTREPCM ou REVPH)
     */
    public Compressor(PneumaticsModuleType moduleType) {
        this.compressor = new edu.wpi.first.wpilibj.Compressor(moduleType);
    }

    /**
     * Construtor da classe Compressor com ID do módulo e tipo especificados.
     * 
     * @param moduleId   O ID do módulo de pneumática
     * @param moduleType O tipo do módulo (CTREPCM ou REVPH)
     */
    public Compressor(int moduleId, PneumaticsModuleType moduleType) {
        this.compressor = new edu.wpi.first.wpilibj.Compressor(moduleId, moduleType);
    }

    /**
     * Habilita o compressor em modo de malha fechada digital.
     * O compressor ligará automaticamente quando a pressão cair abaixo do limite.
     */
    public void enableDigital() {
        compressor.enableDigital();
    }

    /**
     * Desabilita o compressor.
     * O compressor não ligará independente da pressão.
     */
    public void disable() {
        compressor.disable();
    }

    /**
     * Habilita o compressor em modo de malha fechada analógica.
     * Requer sensor de pressão analógico (disponível no REV PH).
     * 
     * @param minPressure Pressão mínima em PSI para ligar o compressor
     * @param maxPressure Pressão máxima em PSI para desligar o compressor
     */
    public void enableAnalog(double minPressure, double maxPressure) {
        compressor.enableAnalog(minPressure, maxPressure);
    }

    /**
     * Habilita o compressor em modo híbrido.
     * Usa tanto o sensor digital quanto o analógico (disponível no REV PH).
     * 
     * @param minPressure Pressão mínima em PSI
     * @param maxPressure Pressão máxima em PSI
     */
    public void enableHybrid(double minPressure, double maxPressure) {
        compressor.enableHybrid(minPressure, maxPressure);
    }

    /**
     * Verifica se o compressor está atualmente ligado (funcionando).
     * 
     * @return true se o compressor está ligado, false caso contrário
     */
    public boolean isRunning() {
        return compressor.isEnabled();
    }

    /**
     * Verifica se o switch de pressão indica que a pressão está baixa.
     * Quando true, o sistema precisa de mais pressão.
     * 
     * @return true se a pressão está baixa, false se está adequada
     */
    public boolean isPressureLow() {
        return compressor.getPressureSwitchValue();
    }

    /**
     * Obtém a corrente consumida pelo compressor em amperes.
     * Útil para monitoramento e diagnóstico do sistema.
     * 
     * @return A corrente em amperes
     */
    public double getCurrent() {
        return compressor.getCurrent();
    }

    /**
     * Obtém a pressão do sistema em PSI (requer sensor analógico).
     * Disponível apenas com REV Pneumatic Hub.
     * 
     * @return A pressão em PSI, ou 0 se sensor não disponível
     */
    public double getPressure() {
        return compressor.getPressure();
    }

    /**
     * Retorna a instância do Compressor da WPILib.
     * 
     * @return A instância do Compressor
     */
    public edu.wpi.first.wpilibj.Compressor getCompressor() {
        return compressor;
    }
}
