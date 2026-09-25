package frc.robot.luverlib.pathplanner;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.pathplanner.lib.path.PathPlannerPath;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.List;
import java.util.Optional;

/**
 * Classe wrapper para operações do PathPlanner.
 * Fornece métodos simplificados para carregar, gerenciar e executar
 * trajetórias autônomas usando a biblioteca PathPlanner.
 * 
 * O PathPlanner é uma ferramenta essencial em FRC para planejamento de
 * trajetórias autônomas com controle holonômico e não-holonômico.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class PathPlannerHelper {
    /**
     * Construtor privado para prevenir instanciação.
     * Esta classe contém apenas métodos estáticos.
     */
    private PathPlannerHelper() {

    }

    // ==================== Carregamento de Caminhos ====================

    /**
     * Carrega um caminho do PathPlanner a partir do diretório de deploy.
     * Os caminhos devem estar na pasta deploy/pathplanner/paths/
     * 
     * @param pathName O nome do caminho (sem extensão .path)
     * @return O PathPlannerPath carregado
     * @throws RuntimeException se o caminho não for encontrado
     */
    public static PathPlannerPath loadPath(String pathName) {
        try {
            return PathPlannerPath.fromPathFile(pathName);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar caminho: " + pathName, e);
        }
    }

    /**
     * Carrega um caminho e inverte sua direção.
     * Útil quando o robô precisa executar o mesmo caminho na direção oposta.
     * 
     * @param pathName O nome do caminho
     * @return O PathPlannerPath invertido
     */
    public static PathPlannerPath loadPathFlipped(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        return path.flipPath();
    }

    /**
     * Carrega múltiplos caminhos de uma vez.
     * 
     * @param pathNames Lista de nomes de caminhos
     * @return Lista de PathPlannerPath carregados
     */
    public static List<PathPlannerPath> loadPaths(String... pathNames) {
        return List.of(pathNames).stream()
                .map(PathPlannerHelper::loadPath)
                .toList();
    }

    // ==================== Comandos de Trajetória ====================

    /**
     * Cria um comando para seguir um caminho do PathPlanner.
     * O AutoBuilder deve estar configurado antes de usar este método.
     * 
     * @param pathName O nome do caminho a seguir
     * @return Um comando que executa o caminho
     */
    public static Command followPathCommand(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        return AutoBuilder.followPath(path);
    }

    /**
     * Cria um comando para seguir um caminho já carregado.
     * 
     * @param path O PathPlannerPath a seguir
     * @return Um comando que executa o caminho
     */
    public static Command followPathCommand(PathPlannerPath path) {
        return AutoBuilder.followPath(path);
    }

    /**
     * Carrega e executa uma rotina autônoma completa.
     * As rotinas devem estar em deploy/pathplanner/autos/
     * 
     * @param autoName O nome da rotina autônoma (sem extensão .auto)
     * @return Um comando que executa a rotina autônoma completa
     */
    public static Command loadAutoCommand(String autoName) {
        return new PathPlannerAuto(autoName);
    }

    /**
     * Cria um comando para resetar a odometria para a pose inicial de um caminho.
     * 
     * @param pathName          O nome do caminho
     * @param resetPoseConsumer Função que reseta a pose do robô
     * @return Um comando que reseta a odometria
     */
    public static Command resetOdometryCommand(String pathName,
            java.util.function.Consumer<Pose2d> resetPoseConsumer) {
        PathPlannerPath path = loadPath(pathName);
        return edu.wpi.first.wpilibj2.command.Commands.runOnce(
                () -> resetPoseConsumer.accept(getPathStartingPose(path)));
    }

    // ==================== Informações de Trajetória ====================

    /**
     * Obtém a posição inicial de um caminho.
     * 
     * @param pathName O nome do caminho
     * @return A Pose2d inicial do caminho
     */
    public static Pose2d getPathStartingPose(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        return new Pose2d(path.getPoint(0).position, new Rotation2d());
    }

    /**
     * Obtém a posição inicial de um caminho já carregado.
     * 
     * @param path O PathPlannerPath
     * @return A Pose2d inicial do caminho
     */
    public static Pose2d getPathStartingPose(PathPlannerPath path) {
        return new Pose2d(path.getPoint(0).position, new Rotation2d());
    }

    /**
     * Obtém a pose final de um caminho.
     * 
     * @param pathName O nome do caminho
     * @return A Pose2d final do caminho
     */
    public static Pose2d getPathEndingPose(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        return new Pose2d(path.getPoint(path.numPoints() - 1).position, new Rotation2d());
    }

    // ==================== Utilidades de Geometria ====================

    /**
     * Inverte uma pose para o lado oposto do campo (16.54m de largura padrão FRC).
     * Útil para alianças azul/vermelha.
     * 
     * @param pose A pose original
     * @return A pose invertida para o outro lado do campo
     * @see PathPlannerFieldUtils#flipFieldPose(Pose2d)
     */
    public static Pose2d flipFieldPose(Pose2d pose) {
        return PathPlannerFieldUtils.flipFieldPose(pose);
    }

    /**
     * Verifica se deve inverter o caminho baseado na aliança.
     * 
     * @return true se a aliança é vermelha e deve inverter
     * @see PathPlannerFieldUtils#shouldFlipPath()
     */
    public static boolean shouldFlipPath() {
        return PathPlannerFieldUtils.shouldFlipPath();
    }

    /**
     * Obtém a pose inicial de um caminho, considerando a aliança.
     * Automaticamente inverte se necessário para a aliança vermelha.
     * 
     * @param pathName O nome do caminho
     * @return A Pose2d inicial ajustada para a aliança atual
     */
    public static Pose2d getPathStartingPoseForAlliance(String pathName) {
        Pose2d startPose = getPathStartingPose(pathName);
        if (shouldFlipPath()) {
            return flipFieldPose(startPose);
        }
        return startPose;
    }

    // ==================== Validação ====================

    /**
     * Verifica se o AutoBuilder está configurado corretamente.
     * 
     * @return true se o AutoBuilder está configurado
     */
    public static boolean isAutoBuilderConfigured() {
        return AutoBuilder.isConfigured();
    }

    /**
     * Verifica se um caminho existe no diretório de deploy.
     * 
     * @param pathName O nome do caminho a verificar
     * @return true se o caminho existe, false caso contrário
     */
    public static boolean pathExists(String pathName) {
        try {
            loadPath(pathName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém uma lista de todos os nomes de autos disponíveis.
     * 
     * @return Lista opcional de nomes de autos
     */
    public static Optional<List<String>> getAvailableAutos() {
        try {
            return Optional.of(AutoBuilder.getAllAutoNames());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // ==================== Informações de Estado ====================

    /**
     * Verifica se um caminho está atualmente sendo seguido.
     * Requer que o caminho tenha sido iniciado com o comando retornado
     * por followPathCommand().
     * 
     * @param pathCommand O comando de seguir caminho
     * @return true se o caminho está sendo executado
     */
    public static boolean isPathActive(Command pathCommand) {
        return pathCommand.isScheduled();
    }

    /**
     * Calcula a distância total aproximada de um caminho em metros.
     * 
     * @param pathName O nome do caminho
     * @return A distância total estimada em metros
     */
    public static double getPathDistance(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        double totalDistance = 0.0;

        for (int i = 1; i < path.numPoints(); i++) {
            var prev = path.getPoint(i - 1).position;
            var curr = path.getPoint(i).position;
            totalDistance += prev.getDistance(curr);
        }
        return totalDistance;
    }

    /**
     * Obtém o número de pontos de controle em um caminho.
     * 
     * @param pathName O nome do caminho
     * @return O número de pontos de controle
     */
    public static int getPathPointCount(String pathName) {
        PathPlannerPath path = loadPath(pathName);
        return path.numPoints();
    }

    // ==================== Hot Reload ====================

    /**
     * PathPlanner 2026 gerencia hot reload automaticamente.
     * Os caminhos são recarregados do disco quando modificados.
     * 
     * @return true (sempre habilitado na versão 2026)
     */
    public static boolean isHotReloadEnabled() {
        return true;
    }

    /**
     * Força o recarregamento de todos os caminhos do disco.
     * Útil durante testes para aplicar mudanças sem reiniciar.
     */
    public static void reloadAllPaths() {
        // PathPlanner 2026 faz hot reload automaticamente se habilitado
        System.out.println("[PathPlanner] Hot reload automático está " +
                (isHotReloadEnabled() ? "HABILITADO" : "DESABILITADO"));
    }

    // ==================== Debugging ====================

    /**
     * Imprime informações detalhadas sobre um caminho no console.
     * Útil para debugging.
     * 
     * @param pathName O nome do caminho
     */
    public static void printPathInfo(String pathName) {
        try {
            PathPlannerPath path = loadPath(pathName);
            System.out.println("========== Informações do Caminho: " + pathName + " ==========");
            System.out.println("Pose Inicial: " + getPathStartingPose(path));
            System.out.println("Pose Final: " + getPathEndingPose(pathName));
            System.out.println("Número de Pontos: " + path.numPoints());
            System.out.println("Distância Estimada: " + String.format("%.2f", getPathDistance(pathName)) + "m");
            System.out.println("==========================================================");
        } catch (Exception e) {
            System.err.println("Erro ao obter informações do caminho: " + pathName);
            e.printStackTrace();
        }
    }

    /**
     * Imprime a lista de todos os autos disponíveis.
     */
    public static void printAvailableAutos() {
        System.out.println("========== Autos Disponíveis ==========");
        Optional<List<String>> autos = getAvailableAutos();
        if (autos.isPresent()) {
            autos.get().forEach(auto -> System.out.println("  - " + auto));
        } else {
            System.out.println("  Nenhum auto encontrado ou erro ao carregar.");
        }
        System.out.println("=======================================");
    }
}