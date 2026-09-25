package frc.robot.luverlib.util;

/**
 * Classe para armazenar constantes de controle PID.
 * Facilita a organização e passagem de parâmetros PID entre classes.
 * 
 * O controle PID (Proporcional-Integral-Derivativo) é fundamental em FRC
 * para controle preciso de posição, velocidade e outros setpoints.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class PIDConstants {
    /** Ganho proporcional - responde ao erro atual */
    public final double kP;

    /** Ganho integral - responde ao acúmulo de erro ao longo do tempo */
    public final double kI;

    /** Ganho derivativo - responde à taxa de mudança do erro */
    public final double kD;

    /** Feedforward - compensação para resistências conhecidas do sistema */
    public final double kF;

    /** Zona de tolerância onde o erro é considerado aceitável */
    public final double tolerance;

    /** Limite máximo do integrador para evitar windup */
    public final double iZone;

    /** Limite máximo da saída do controlador */
    public final double maxOutput;

    /** Limite mínimo da saída do controlador */
    public final double minOutput;

    /**
     * Construtor básico com apenas P, I e D.
     * 
     * @param kP Ganho proporcional
     * @param kI Ganho integral
     * @param kD Ganho derivativo
     */
    public PIDConstants(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = 0.0;
        this.tolerance = 0.0;
        this.iZone = Double.MAX_VALUE;
        this.maxOutput = 1.0;
        this.minOutput = -1.0;
    }

    /**
     * Construtor com P, I, D e feedforward.
     * 
     * @param kP Ganho proporcional
     * @param kI Ganho integral
     * @param kD Ganho derivativo
     * @param kF Ganho de feedforward
     */
    public PIDConstants(double kP, double kI, double kD, double kF) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.tolerance = 0.0;
        this.iZone = Double.MAX_VALUE;
        this.maxOutput = 1.0;
        this.minOutput = -1.0;
    }

    /**
     * Construtor completo com todos os parâmetros.
     * 
     * @param kP        Ganho proporcional
     * @param kI        Ganho integral
     * @param kD        Ganho derivativo
     * @param kF        Ganho de feedforward
     * @param tolerance Zona de tolerância
     * @param iZone     Limite da zona integral
     * @param minOutput Saída mínima do controlador
     * @param maxOutput Saída máxima do controlador
     */
    public PIDConstants(double kP, double kI, double kD, double kF,
            double tolerance, double iZone,
            double minOutput, double maxOutput) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.tolerance = tolerance;
        this.iZone = iZone;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }

    /**
     * Cria uma cópia das constantes com um novo valor de kP.
     * 
     * @param newKP O novo ganho proporcional
     * @return Uma nova instância de PIDConstants
     */
    public PIDConstants withKP(double newKP) {
        return new PIDConstants(newKP, kI, kD, kF, tolerance, iZone, minOutput, maxOutput);
    }

    /**
     * Cria uma cópia das constantes com um novo valor de kI.
     * 
     * @param newKI O novo ganho integral
     * @return Uma nova instância de PIDConstants
     */
    public PIDConstants withKI(double newKI) {
        return new PIDConstants(kP, newKI, kD, kF, tolerance, iZone, minOutput, maxOutput);
    }

    /**
     * Cria uma cópia das constantes com um novo valor de kD.
     * 
     * @param newKD O novo ganho derivativo
     * @return Uma nova instância de PIDConstants
     */
    public PIDConstants withKD(double newKD) {
        return new PIDConstants(kP, kI, newKD, kF, tolerance, iZone, minOutput, maxOutput);
    }

    /**
     * Cria uma cópia das constantes com uma nova tolerância.
     * 
     * @param newTolerance A nova tolerância
     * @return Uma nova instância de PIDConstants
     */
    public PIDConstants withTolerance(double newTolerance) {
        return new PIDConstants(kP, kI, kD, kF, newTolerance, iZone, minOutput, maxOutput);
    }

    /**
     * Cria uma cópia das constantes com novos limites de saída.
     * 
     * @param newMinOutput O novo limite mínimo
     * @param newMaxOutput O novo limite máximo
     * @return Uma nova instância de PIDConstants
     */
    public PIDConstants withOutputRange(double newMinOutput, double newMaxOutput) {
        return new PIDConstants(kP, kI, kD, kF, tolerance, iZone, newMinOutput, newMaxOutput);
    }

    /**
     * Retorna uma representação em string das constantes.
     * 
     * @return String formatada com os valores das constantes
     */
    @Override
    public String toString() {
        return String.format("PIDConstants[kP=%.4f, kI=%.4f, kD=%.4f, kF=%.4f]",
                kP, kI, kD, kF);
    }

    /**
     * Converte estas constantes PID para o formato do PathPlanner.
     * Necessário porque o PathPlanner usa sua própria classe PIDConstants
     * ({@code com.pathplanner.lib.config.PIDConstants}).
     * 
     * @return Uma instância de PIDConstants do PathPlanner com os mesmos valores
     *         kP, kI e kD
     */
    public com.pathplanner.lib.config.PIDConstants toPathPlannerPID() {
        return new com.pathplanner.lib.config.PIDConstants(kP, kI, kD);
    }

    /**
     * Constantes PID zeradas (sem controle).
     */
    public static final PIDConstants ZERO = new PIDConstants(0, 0, 0);

    /**
     * Constantes PID padrão para controle de posição de motor.
     * Ajuste conforme necessário para seu sistema específico.
     */
    public static final PIDConstants DEFAULT_POSITION = new PIDConstants(0.1, 0.0, 0.01);

    /**
     * Constantes PID padrão para controle de velocidade de motor.
     * Ajuste conforme necessário para seu sistema específico.
     */
    public static final PIDConstants DEFAULT_VELOCITY = new PIDConstants(0.0001, 0.0, 0.0, 0.0002);
}