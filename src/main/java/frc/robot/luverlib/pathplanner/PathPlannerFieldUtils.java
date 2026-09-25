package frc.robot.luverlib.pathplanner;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import java.util.Optional;

/**
 * Classe utilitária para operações de geometria de campo e aliança.
 * Contém métodos para inversão de poses, verificação de aliança e
 * cálculos geométricos relacionados ao campo FRC.
 * 
 * <p>
 * O campo FRC padrão tem 16.54m de largura. As trajetórias do PathPlanner
 * são desenhadas para a aliança azul e devem ser invertidas quando
 * o robô está na aliança vermelha.
 * </p>
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public final class PathPlannerFieldUtils {

    /** Largura padrão do campo FRC em metros */
    public static final double FIELD_WIDTH_METERS = 16.54;

    /**
     * Construtor privado para prevenir instanciação.
     * Esta classe contém apenas métodos estáticos.
     */
    private PathPlannerFieldUtils() {
    }

    // ==================== Utilidades de Aliança ====================

    /**
     * Verifica se deve inverter o caminho baseado na aliança atual.
     * Caminhos do PathPlanner são desenhados para a aliança azul;
     * quando na aliança vermelha, os caminhos devem ser espelhados.
     * 
     * @return {@code true} se a aliança é vermelha e deve inverter,
     *         {@code false} caso contrário
     */
    public static boolean shouldFlipPath() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        return alliance.isPresent() && alliance.get() == Alliance.Red;
    }

    /**
     * Obtém a aliança atual do DriverStation.
     * 
     * @return Um {@link Optional} contendo a aliança, ou vazio se não disponível
     */
    public static Optional<Alliance> getCurrentAlliance() {
        return DriverStation.getAlliance();
    }

    /**
     * Verifica se o robô está na aliança vermelha.
     * 
     * @return {@code true} se está na aliança vermelha
     */
    public static boolean isRedAlliance() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        return alliance.isPresent() && alliance.get() == Alliance.Red;
    }

    /**
     * Verifica se o robô está na aliança azul.
     * 
     * @return {@code true} se está na aliança azul
     */
    public static boolean isBlueAlliance() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        return alliance.isPresent() && alliance.get() == Alliance.Blue;
    }

    // ==================== Utilidades de Geometria ====================

    /**
     * Inverte uma pose para o lado oposto do campo.
     * Espelha a posição X e rotação para mapear da aliança azul
     * para a vermelha (ou vice-versa).
     * 
     * <p>
     * A inversão funciona da seguinte forma:
     * </p>
     * <ul>
     * <li>X é espelhado: {@code FIELD_WIDTH - X}</li>
     * <li>Y permanece igual</li>
     * <li>Rotação é espelhada: {@code 180° - rotação original}</li>
     * </ul>
     * 
     * @param pose A pose original no lado azul do campo
     * @return A pose invertida para o lado vermelho do campo
     */
    public static Pose2d flipFieldPose(Pose2d pose) {
        return new Pose2d(
                FIELD_WIDTH_METERS - pose.getX(),
                pose.getY(),
                new Rotation2d(Math.PI).minus(pose.getRotation()));
    }

    /**
     * Aplica a inversão de pose condicionalmente baseado na aliança.
     * Se na aliança vermelha, inverte a pose; caso contrário, retorna a original.
     * 
     * @param pose A pose original (desenhada para aliança azul)
     * @return A pose ajustada para a aliança atual
     */
    public static Pose2d adjustPoseForAlliance(Pose2d pose) {
        if (shouldFlipPath()) {
            return flipFieldPose(pose);
        }
        return pose;
    }

    /**
     * Calcula a distância entre duas poses no campo.
     * 
     * @param pose1 A primeira pose
     * @param pose2 A segunda pose
     * @return A distância euclidiana em metros entre as duas poses
     */
    public static double distanceBetweenPoses(Pose2d pose1, Pose2d pose2) {
        double dx = pose2.getX() - pose1.getX();
        double dy = pose2.getY() - pose1.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Verifica se uma pose está dentro dos limites do campo FRC.
     * 
     * @param pose              A pose a verificar
     * @param fieldLengthMeters O comprimento do campo em metros (padrão: 16.54)
     * @param fieldWidthMeters  A largura do campo em metros (padrão: 8.21)
     * @return {@code true} se a pose está dentro dos limites do campo
     */
    public static boolean isPoseInField(Pose2d pose, double fieldLengthMeters, double fieldWidthMeters) {
        return pose.getX() >= 0 && pose.getX() <= fieldLengthMeters
                && pose.getY() >= 0 && pose.getY() <= fieldWidthMeters;
    }

    /**
     * Verifica se uma pose está dentro dos limites do campo FRC padrão.
     * Usa as dimensões padrão: 16.54m x 8.21m.
     * 
     * @param pose A pose a verificar
     * @return {@code true} se a pose está dentro dos limites do campo
     */
    public static boolean isPoseInField(Pose2d pose) {
        return isPoseInField(pose, FIELD_WIDTH_METERS, 8.21);
    }
}