package frc.robot.luverlib.pathplanner;

import frc.robot.luverlib.util.PIDConstants;

import com.pathplanner.lib.config.ModuleConfig;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;

/**
 * Classe para armazenar e gerenciar configurações do PathPlanner.
 * Facilita a configuração de parâmetros como PID, limites de velocidade,
 * dimensões do robô e outras características necessárias para o AutoBuilder.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class PathPlannerConfig {
    // ==================== Constantes PID ====================

    /** Constantes PID para controle de translação (X e Y) */
    private PIDConstants translationPID;

    /** Constantes PID para controle de rotação (theta) */
    private PIDConstants rotationPID;

    // ==================== Limites do Robô ====================

    /** Velocidade linear máxima em metros por segundo */
    private double maxVelocityMetersPerSecond;

    /** Aceleração linear máxima em metros por segundo ao quadrado */
    private double maxAccelerationMetersPerSecondSquared;

    /** Velocidade angular máxima em radianos por segundo */
    private double maxAngularVelocityRadPerSecond;

    /** Aceleração angular máxima em radianos por segundo ao quadrado */
    private double maxAngularAccelerationRadPerSecondSquared;

    // ==================== Dimensões do Robô ====================

    /** Massa do robô em quilogramas */
    private double robotMassKg;

    /** Momento de inércia do robô em kg*m² */
    private double robotMomentOfInertiaKgM2;

    /** Distância do centro do robô até as rodas (raio) em metros */
    private double driveBaseRadiusMeters;

    /** Largura do robô (com bumpers) em metros */
    private double robotWidthMeters;

    /** Comprimento do robô (com bumpers) em metros */
    private double robotLengthMeters;

    // ==================== Configurações Adicionais ====================

    /** Se deve usar controlador de caminho (path following) ou trajetória */
    private boolean usePathFollowing;

    /** Período de controle em segundos (geralmente 0.02 para 20ms) */
    private double controlPeriodSeconds;

    /** Tolerância de posição para considerar que chegou ao alvo (metros) */
    private double positionToleranceMeters;

    /** Tolerância de rotação para considerar que chegou ao alvo (graus) */
    private double rotationToleranceDegrees;

    /**
     * Construtor padrão com valores conservadores.
     * Cria uma configuração básica que deve ser ajustada para o robô específico.
     */
    public PathPlannerConfig() {
        // PID padrão para translação
        this.translationPID = new PIDConstants(5.0, 0.0, 0.0);

        // PID padrão para rotação
        this.rotationPID = new PIDConstants(5.0, 0.0, 0.0);

        // Limites padrão (conservadores)
        this.maxVelocityMetersPerSecond = 3.0;
        this.maxAccelerationMetersPerSecondSquared = 3.0;
        this.maxAngularVelocityRadPerSecond = Math.PI;
        this.maxAngularAccelerationRadPerSecondSquared = Math.PI;

        // Dimensões padrão para robô FRC típico
        this.robotMassKg = 45.0; // ~100 lbs
        this.robotMomentOfInertiaKgM2 = 6.0;
        this.driveBaseRadiusMeters = 0.4;
        this.robotWidthMeters = Units.inchesToMeters(32); // 32 polegadas com bumpers
        this.robotLengthMeters = Units.inchesToMeters(32);

        // Configurações padrão
        this.usePathFollowing = true;
        this.controlPeriodSeconds = 0.02; // 20ms
        this.positionToleranceMeters = 0.05; // 5cm
        this.rotationToleranceDegrees = 2.0; // 2 graus
    }

    // ==================== Getters e Setters ====================

    /**
     * Obtém as constantes PID para controle de translação (X e Y).
     * 
     * @return As constantes PID de translação
     */
    public PIDConstants getTranslationPID() {
        return translationPID;
    }

    /**
     * Define as constantes PID para controle de translação.
     * Utiliza encadeamento fluente (fluent/builder pattern).
     * 
     * @param kP Ganho proporcional
     * @param kI Ganho integral
     * @param kD Ganho derivativo
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setTranslationPID(double kP, double kI, double kD) {
        this.translationPID = new PIDConstants(kP, kI, kD);
        return this;
    }

    /**
     * Define as constantes PID para controle de translação a partir de um objeto
     * PIDConstants.
     * 
     * @param pid As constantes PID a serem utilizadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setTranslationPID(PIDConstants pid) {
        this.translationPID = pid;
        return this;
    }

    /**
     * Obtém as constantes PID para controle de rotação (theta).
     * 
     * @return As constantes PID de rotação
     */
    public PIDConstants getRotationPID() {
        return rotationPID;
    }

    /**
     * Define as constantes PID para controle de rotação.
     * 
     * @param kP Ganho proporcional
     * @param kI Ganho integral
     * @param kD Ganho derivativo
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRotationPID(double kP, double kI, double kD) {
        this.rotationPID = new PIDConstants(kP, kI, kD);
        return this;
    }

    /**
     * Define as constantes PID para controle de rotação a partir de um objeto
     * PIDConstants.
     * 
     * @param pid As constantes PID a serem utilizadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRotationPID(PIDConstants pid) {
        this.rotationPID = pid;
        return this;
    }

    /**
     * Obtém a velocidade linear máxima do robô.
     * 
     * @return A velocidade máxima em metros por segundo
     */
    public double getMaxVelocityMetersPerSecond() {
        return maxVelocityMetersPerSecond;
    }

    /**
     * Define a velocidade linear máxima do robô.
     * 
     * @param maxVelocity A velocidade máxima em metros por segundo
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setMaxVelocityMetersPerSecond(double maxVelocity) {
        this.maxVelocityMetersPerSecond = maxVelocity;
        return this;
    }

    /**
     * Obtém a aceleração linear máxima do robô.
     * 
     * @return A aceleração máxima em metros por segundo ao quadrado
     */
    public double getMaxAccelerationMetersPerSecondSquared() {
        return maxAccelerationMetersPerSecondSquared;
    }

    /**
     * Define a aceleração linear máxima do robô.
     * 
     * @param maxAcceleration A aceleração máxima em metros por segundo ao quadrado
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setMaxAccelerationMetersPerSecondSquared(double maxAcceleration) {
        this.maxAccelerationMetersPerSecondSquared = maxAcceleration;
        return this;
    }

    /**
     * Obtém a velocidade angular máxima do robô.
     * 
     * @return A velocidade angular máxima em radianos por segundo
     */
    public double getMaxAngularVelocityRadPerSecond() {
        return maxAngularVelocityRadPerSecond;
    }

    /**
     * Define a velocidade angular máxima do robô em radianos por segundo.
     * 
     * @param maxAngularVelocity A velocidade angular máxima em rad/s
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setMaxAngularVelocityRadPerSecond(double maxAngularVelocity) {
        this.maxAngularVelocityRadPerSecond = maxAngularVelocity;
        return this;
    }

    /**
     * Define a velocidade angular máxima do robô em graus por segundo.
     * Converte automaticamente de graus para radianos.
     * 
     * @param maxAngularVelocityDeg A velocidade angular máxima em graus/s
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setMaxAngularVelocityDegPerSecond(double maxAngularVelocityDeg) {
        this.maxAngularVelocityRadPerSecond = Math.toRadians(maxAngularVelocityDeg);
        return this;
    }

    /**
     * Obtém a aceleração angular máxima do robô.
     * 
     * @return A aceleração angular máxima em radianos por segundo ao quadrado
     */
    public double getMaxAngularAccelerationRadPerSecondSquared() {
        return maxAngularAccelerationRadPerSecondSquared;
    }

    /**
     * Define a aceleração angular máxima do robô.
     * 
     * @param maxAngularAcceleration A aceleração angular máxima em rad/s²
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setMaxAngularAccelerationRadPerSecondSquared(double maxAngularAcceleration) {
        this.maxAngularAccelerationRadPerSecondSquared = maxAngularAcceleration;
        return this;
    }

    /**
     * Obtém a massa do robô.
     * 
     * @return A massa em quilogramas
     */
    public double getRobotMassKg() {
        return robotMassKg;
    }

    /**
     * Define a massa do robô em quilogramas.
     * Inclua bumpers e bateria no valor.
     * 
     * @param massKg A massa em quilogramas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotMassKg(double massKg) {
        this.robotMassKg = massKg;
        return this;
    }

    /**
     * Define a massa do robô em libras (lbs).
     * Converte automaticamente de libras para quilogramas.
     * 
     * @param massLbs A massa em libras
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotMassLbs(double massLbs) {
        this.robotMassKg = massLbs * 0.453592;
        return this;
    }

    /**
     * Obtém o momento de inércia do robô.
     * 
     * @return O momento de inércia em kg*m²
     */
    public double getRobotMomentOfInertiaKgM2() {
        return robotMomentOfInertiaKgM2;
    }

    /**
     * Define o momento de inércia do robô.
     * Pode ser estimado com CAD ou experimentalmente.
     * 
     * @param moi O momento de inércia em kg*m²
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotMomentOfInertiaKgM2(double moi) {
        this.robotMomentOfInertiaKgM2 = moi;
        return this;
    }

    /**
     * Obtém a distância do centro do robô até as rodas.
     * 
     * @return O raio do drive base em metros
     */
    public double getDriveBaseRadiusMeters() {
        return driveBaseRadiusMeters;
    }

    /**
     * Define a distância do centro do robô até as rodas em metros.
     * Para swerve, use {@link #calculateDriveBaseRadius(Translation2d[])} para
     * calcular.
     * 
     * @param radius O raio em metros
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setDriveBaseRadiusMeters(double radius) {
        this.driveBaseRadiusMeters = radius;
        return this;
    }

    /**
     * Define a distância do centro do robô até as rodas em polegadas.
     * Converte automaticamente de polegadas para metros.
     * 
     * @param radiusInches O raio em polegadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setDriveBaseRadiusInches(double radiusInches) {
        this.driveBaseRadiusMeters = Units.inchesToMeters(radiusInches);
        return this;
    }

    /**
     * Obtém a largura do robô (com bumpers).
     * 
     * @return A largura em metros
     */
    public double getRobotWidthMeters() {
        return robotWidthMeters;
    }

    /**
     * Define a largura do robô (com bumpers) em metros.
     * 
     * @param width A largura em metros
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotWidthMeters(double width) {
        this.robotWidthMeters = width;
        return this;
    }

    /**
     * Define a largura do robô (com bumpers) em polegadas.
     * Converte automaticamente de polegadas para metros.
     * 
     * @param widthInches A largura em polegadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotWidthInches(double widthInches) {
        this.robotWidthMeters = Units.inchesToMeters(widthInches);
        return this;
    }

    /**
     * Obtém o comprimento do robô (com bumpers).
     * 
     * @return O comprimento em metros
     */
    public double getRobotLengthMeters() {
        return robotLengthMeters;
    }

    /**
     * Define o comprimento do robô (com bumpers) em metros.
     * 
     * @param length O comprimento em metros
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotLengthMeters(double length) {
        this.robotLengthMeters = length;
        return this;
    }

    /**
     * Define o comprimento do robô (com bumpers) em polegadas.
     * Converte automaticamente de polegadas para metros.
     * 
     * @param lengthInches O comprimento em polegadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRobotLengthInches(double lengthInches) {
        this.robotLengthMeters = Units.inchesToMeters(lengthInches);
        return this;
    }

    /**
     * Verifica se o modo de path following está habilitado.
     * 
     * @return {@code true} se o path following está habilitado
     */
    public boolean isUsePathFollowing() {
        return usePathFollowing;
    }

    /**
     * Habilita ou desabilita o modo de path following.
     * 
     * @param usePathFollowing {@code true} para habilitar path following
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setUsePathFollowing(boolean usePathFollowing) {
        this.usePathFollowing = usePathFollowing;
        return this;
    }

    /**
     * Obtém o período do loop de controle.
     * 
     * @return O período em segundos (geralmente 0.02 para 20ms)
     */
    public double getControlPeriodSeconds() {
        return controlPeriodSeconds;
    }

    /**
     * Define o período do loop de controle.
     * O padrão do WPILib é 0.02s (20ms / 50Hz).
     * 
     * @param period O período em segundos
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setControlPeriodSeconds(double period) {
        this.controlPeriodSeconds = period;
        return this;
    }

    /**
     * Obtém a tolerância de posição para considerar que o robô chegou ao alvo.
     * 
     * @return A tolerância em metros
     */
    public double getPositionToleranceMeters() {
        return positionToleranceMeters;
    }

    /**
     * Define a tolerância de posição em metros.
     * Valores menores exigem mais precisão do robô.
     * 
     * @param tolerance A tolerância em metros
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setPositionToleranceMeters(double tolerance) {
        this.positionToleranceMeters = tolerance;
        return this;
    }

    /**
     * Define a tolerância de posição em polegadas.
     * Converte automaticamente de polegadas para metros.
     * 
     * @param toleranceInches A tolerância em polegadas
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setPositionToleranceInches(double toleranceInches) {
        this.positionToleranceMeters = Units.inchesToMeters(toleranceInches);
        return this;
    }

    /**
     * Obtém a tolerância de rotação para considerar que o robô chegou à orientação
     * alvo.
     * 
     * @return A tolerância em graus
     */
    public double getRotationToleranceDegrees() {
        return rotationToleranceDegrees;
    }

    /**
     * Define a tolerância de rotação em graus.
     * Valores menores exigem mais precisão do robô.
     * 
     * @param toleranceDeg A tolerância em graus
     * @return Esta instância para encadeamento de métodos
     */
    public PathPlannerConfig setRotationToleranceDegrees(double toleranceDeg) {
        this.rotationToleranceDegrees = toleranceDeg;
        return this;
    }

    /**
     * Obtém a tolerância de rotação convertida para radianos.
     * Método de conveniência para APIs que usam radianos.
     * 
     * @return A tolerância em radianos
     */
    public double getRotationToleranceRadians() {
        return Math.toRadians(rotationToleranceDegrees);
    }

    // ==================== Métodos de Conveniência ====================

    /**
     * Calcula o raio do drive base a partir das posições dos módulos.
     * Útil para swerve drive.
     * 
     * @param modulePositions Array de Translation2d com as posições dos módulos
     * @return O raio calculado em metros
     */
    public static double calculateDriveBaseRadius(Translation2d[] modulePositions) {
        double maxDistance = 0.0;
        for (Translation2d position : modulePositions) {
            double distance = position.getNorm();
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return maxDistance;
    }

    /**
     * Cria uma configuração otimizada para um robô swerve drive rápido.
     * 
     * @return Uma nova instância de PathPlannerConfig
     */
    public static PathPlannerConfig createSwerveFastConfig() {
        return new PathPlannerConfig()
                .setMaxVelocityMetersPerSecond(4.5)
                .setMaxAccelerationMetersPerSecondSquared(4.0)
                .setMaxAngularVelocityDegPerSecond(360)
                .setTranslationPID(8.0, 0.0, 0.5)
                .setRotationPID(8.0, 0.0, 0.5);
    }

    /**
     * Cria uma configuração conservadora para testes iniciais.
     * 
     * @return Uma nova instância de PathPlannerConfig
     */
    public static PathPlannerConfig createConservativeConfig() {
        return new PathPlannerConfig()
                .setMaxVelocityMetersPerSecond(2.0)
                .setMaxAccelerationMetersPerSecondSquared(2.0)
                .setMaxAngularVelocityDegPerSecond(180)
                .setTranslationPID(3.0, 0.0, 0.0)
                .setRotationPID(3.0, 0.0, 0.0);
    }

    /**
     * Imprime a configuração atual no console.
     * Útil para debugging.
     */
    public void printConfig() {
        System.out.println("========== PathPlanner Config ==========");
        System.out.println("Translation PID: kP=" + translationPID.kP +
                ", kI=" + translationPID.kI + ", kD=" + translationPID.kD);
        System.out.println("Rotation PID: kP=" + rotationPID.kP +
                ", kI=" + rotationPID.kI + ", kD=" + rotationPID.kD);
        System.out.println("Max Velocity: " + maxVelocityMetersPerSecond + " m/s");
        System.out.println("Max Acceleration: " + maxAccelerationMetersPerSecondSquared + " m/s²");
        System.out.println("Max Angular Velocity: " +
                Math.toDegrees(maxAngularVelocityRadPerSecond) + " deg/s");
        System.out.println("Robot Mass: " + robotMassKg + " kg");
        System.out.println("Drive Base Radius: " + driveBaseRadiusMeters + " m");
        System.out.println("Robot Dimensions: " + robotWidthMeters + "m x " + robotLengthMeters + "m");
        System.out.println("========================================");
    }

    /**
     * Carrega o RobotConfig diretamente das configurações do PathPlanner GUI.
     * Este é o método recomendado pelo PathPlanner para obter a configuração.
     * As configurações são definidas na interface gráfica do PathPlanner
     * e salvas no diretório de deploy.
     * 
     * @return O RobotConfig carregado das configurações da GUI
     * @throws RuntimeException se o arquivo de configuração não for encontrado
     */
    public static RobotConfig fromGUISettings() {
        try {
            return RobotConfig.fromGUISettings();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar RobotConfig do PathPlanner GUI. "
                    + "Verifique se as configurações foram salvas na GUI.", e);
        }
    }

    /**
     * Cria um RobotConfig do PathPlanner para um robô swerve (holonômico)
     * a partir desta configuração.
     * Necessário para AutoBuilder.configure().
     * 
     * @param wheelRadiusMeters          Raio das rodas em metros
     * @param maxModuleSpeedMetersPerSec Velocidade máxima de cada módulo em m/s
     * @param driveMotor                 O tipo de motor de tração (ex:
     *                                   DCMotor.getNEO(1))
     * @param driveGearRatio             Relação de engrenagens da tração
     *                                   (motor:roda)
     * @param moduleOffsets              Posições dos módulos relativas ao centro do
     *                                   robô
     *                                   (FL, FR, BL, BR)
     * @return Um RobotConfig configurado para swerve drive
     */
    public RobotConfig toSwerveRobotConfig(
            double wheelRadiusMeters,
            double maxModuleSpeedMetersPerSec,
            DCMotor driveMotor,
            double driveGearRatio,
            Translation2d... moduleOffsets) {

        ModuleConfig moduleConfig = new ModuleConfig(
                wheelRadiusMeters,
                maxModuleSpeedMetersPerSec,
                1.0, // Coeficiente de atrito roda-carpet
                driveMotor,
                driveGearRatio,
                1 // Número de motores por módulo
        );

        return new RobotConfig(
                robotMassKg,
                robotMomentOfInertiaKgM2,
                moduleConfig,
                moduleOffsets);
    }

    /**
     * Cria um RobotConfig do PathPlanner para um robô diferencial.
     * Necessário para AutoBuilder.configure().
     * 
     * @param wheelRadiusMeters          Raio das rodas em metros
     * @param maxModuleSpeedMetersPerSec Velocidade máxima de cada módulo em m/s
     * @param driveMotor                 O tipo de motor de tração (ex:
     *                                   DCMotor.getNEO(1))
     * @param driveGearRatio             Relação de engrenagens da tração
     *                                   (motor:roda)
     * @param trackwidthMeters           Largura entre as rodas esquerdas e direitas
     *                                   em metros
     * @return Um RobotConfig configurado para diferencial
     */
    public RobotConfig toDifferentialRobotConfig(
            double wheelRadiusMeters,
            double maxModuleSpeedMetersPerSec,
            DCMotor driveMotor,
            double driveGearRatio,
            double trackwidthMeters) {

        ModuleConfig moduleConfig = new ModuleConfig(
                wheelRadiusMeters,
                maxModuleSpeedMetersPerSec,
                1.0, // Coeficiente de atrito roda-carpet
                driveMotor,
                driveGearRatio,
                1 // Número de motores por módulo
        );

        return new RobotConfig(
                robotMassKg,
                robotMomentOfInertiaKgM2,
                moduleConfig,
                trackwidthMeters);
    }

    /**
     * Cria um PPHolonomicDriveController com as configurações PID atuais.
     * Converte automaticamente os PIDConstants da luverlib para o formato
     * do PathPlanner ({@code com.pathplanner.lib.config.PIDConstants}).
     * 
     * @return Um novo PPHolonomicDriveController configurado
     */
    public PPHolonomicDriveController createHolonomicController() {
        return new PPHolonomicDriveController(
                translationPID.toPathPlannerPID(),
                rotationPID.toPathPlannerPID());
    }

    /**
     * Cria um PPHolonomicDriveController com período de controle customizado.
     * 
     * @param period Período do loop de controle em segundos (padrão: 0.02)
     * @return Um novo PPHolonomicDriveController configurado
     */
    public PPHolonomicDriveController createHolonomicController(double period) {
        return new PPHolonomicDriveController(
                translationPID.toPathPlannerPID(),
                rotationPID.toPathPlannerPID(),
                period);
    }
}