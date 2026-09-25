package frc.robot.luverlib.motor;

/**
 * Classe contendo constantes de configuração para motores TalonFX (Falcon 500 /
 * Kraken X60).
 * Inclui especificações técnicas padrão e limites de operação seguros.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class TalonFXBrushlessConfig {
    /**
     * Construtor privado para prevenir instanciação.
     * Esta classe contém apenas constantes estáticas.
     */
    private TalonFXBrushlessConfig() {

    }

    // ==================== Limites de Voltagem ====================

    /** Voltagem mínima permitida em volts */
    public static final double MIN_VOLTAGE = -12.0;

    /** Voltagem máxima permitida em volts */
    public static final double MAX_VOLTAGE = 12.0;

    // ==================== Especificações Falcon 500 ====================

    /** RPM máximo do Falcon 500 em rotação para frente */
    public static final double FALCON_500_RPM_FORWARD = 6380.0;

    /** RPM máximo do Falcon 500 em rotação para trás */
    public static final double FALCON_500_RPM_REVERSE = -6380.0;

    /** Peso do Falcon 500 em libras */
    public static final double FALCON_500_WEIGHT_LB = 1.1;

    /** Peso do Falcon 500 em quilogramas */
    public static final double FALCON_500_WEIGHT_KG = 0.499;

    /** Torque de pico do Falcon 500 em Newton-metros */
    public static final double FALCON_500_PEAK_TORQUE_NM = 4.69;

    /** Corrente de pico do Falcon 500 em amperes */
    public static final double FALCON_500_PEAK_CURRENT_A = 257.0;

    // ==================== Especificações Kraken X60 ====================

    /** RPM máximo do Kraken X60 em rotação para frente */
    public static final double KRAKEN_X60_RPM_FORWARD = 6000.0;

    /** RPM máximo do Kraken X60 em rotação para trás */
    public static final double KRAKEN_X60_RPM_REVERSE = -6000.0;

    /** Peso do Kraken X60 em libras */
    public static final double KRAKEN_X60_WEIGHT_LB = 1.22;

    /** Peso do Kraken X60 em quilogramas */
    public static final double KRAKEN_X60_WEIGHT_KG = 0.553;

    /** Torque de pico do Kraken X60 em Newton-metros */
    public static final double KRAKEN_X60_PEAK_TORQUE_NM = 7.09;

    /** Corrente de pico do Kraken X60 em amperes */
    public static final double KRAKEN_X60_PEAK_CURRENT_A = 366.0;

    // ==================== Valores Padrão (Falcon 500) ====================

    /** RPM padrão máximo para frente (baseado no Falcon 500) */
    public static final double DEFAULT_RPM_FORWARD = FALCON_500_RPM_FORWARD;

    /** RPM padrão máximo para trás (baseado no Falcon 500) */
    public static final double DEFAULT_RPM_REVERSE = FALCON_500_RPM_REVERSE;

    // ==================== Encoder Integrado ====================

    /** Resolução do encoder integrado do TalonFX em ticks por rotação */
    public static final double ENCODER_TICKS_PER_ROTATION = 2048.0;

    // ==================== Limites de Corrente Recomendados ====================

    /** Limite de corrente contínua recomendado em amperes */
    public static final double RECOMMENDED_CONTINUOUS_CURRENT_LIMIT = 40.0;

    /** Limite de corrente de pico recomendado em amperes */
    public static final double RECOMMENDED_PEAK_CURRENT_LIMIT = 80.0;

    /** Duração do pico de corrente em segundos */
    public static final double PEAK_CURRENT_DURATION = 0.1;
}
