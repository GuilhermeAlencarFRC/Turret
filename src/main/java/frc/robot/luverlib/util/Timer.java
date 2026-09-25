package frc.robot.luverlib.util;

/**
 * Classe utilitária para medição de tempo e delays.
 * Fornece métodos simplificados para trabalhar com temporizadores em FRC,
 * útil para sequências autônomas, debouncing e controle de timing.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class Timer {
    /** Instância do Timer da WPILib */
    private edu.wpi.first.wpilibj.Timer timer;

    /** Tempo alvo para comparação */
    private double targetTime;

    /** Indica se o timer está em execução */
    private boolean isRunning;

    /**
     * Construtor da classe Timer.
     * Cria um novo timer parado.
     */
    public Timer() {
        this.timer = new edu.wpi.first.wpilibj.Timer();
        this.targetTime = 0;
        this.isRunning = false;
    }

    /**
     * Inicia o timer.
     * Se o timer já estava rodando, continua de onde parou.
     */
    public void start() {
        timer.start();
        isRunning = true;
    }

    /**
     * Para o timer.
     * O tempo acumulado é mantido.
     */
    public void stop() {
        timer.stop();
        isRunning = false;
    }

    /**
     * Reseta o timer para zero e para a contagem.
     */
    public void reset() {
        timer.reset();
        isRunning = false;
    }

    /**
     * Reseta o timer e inicia imediatamente.
     */
    public void restart() {
        timer.restart();
        isRunning = true;
    }

    /**
     * Obtém o tempo decorrido em segundos.
     * 
     * @return O tempo em segundos desde o início ou último reset
     */
    public double get() {
        return timer.get();
    }

    /**
     * Obtém o tempo decorrido em milissegundos.
     * 
     * @return O tempo em milissegundos
     */
    public double getMilliseconds() {
        return timer.get() * 1000.0;
    }

    /**
     * Verifica se o tempo especificado já passou.
     * 
     * @param seconds O tempo em segundos para verificar
     * @return true se o tempo decorrido é maior ou igual ao especificado
     */
    public boolean hasElapsed(double seconds) {
        return timer.hasElapsed(seconds);
    }

    /**
     * Verifica se o tempo especificado já passou desde a última verificação.
     * Útil para ações periódicas.
     * 
     * @param seconds O período em segundos
     * @return true se o período passou (e reseta o contador interno)
     */
    public boolean advanceIfElapsed(double seconds) {
        return timer.advanceIfElapsed(seconds);
    }

    /**
     * Define um tempo alvo para uso com hasReachedTarget().
     * 
     * @param seconds O tempo alvo em segundos
     */
    public void setTargetTime(double seconds) {
        this.targetTime = seconds;
    }

    /**
     * Verifica se o tempo alvo foi atingido.
     * 
     * @return true se o tempo decorrido atingiu ou ultrapassou o alvo
     */
    public boolean hasReachedTarget() {
        return timer.get() >= targetTime;
    }

    /**
     * Obtém o tempo restante até o alvo.
     * 
     * @return O tempo restante em segundos (pode ser negativo se ultrapassou)
     */
    public double getTimeRemaining() {
        return targetTime - timer.get();
    }

    /**
     * Verifica se o timer está rodando.
     * 
     * @return true se o timer está em execução
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Obtém o tempo atual do FPGA em segundos.
     * Este é um timestamp absoluto desde a inicialização do robô.
     * 
     * @return O tempo do FPGA em segundos
     */
    public static double getFPGATimestamp() {
        return edu.wpi.first.wpilibj.Timer.getFPGATimestamp();
    }

    /**
     * Obtém o tempo atual do match em segundos.
     * Retorna o tempo desde o início do modo autônomo ou teleop.
     * 
     * @return O tempo do match em segundos
     */
    public static double getMatchTime() {
        return edu.wpi.first.wpilibj.Timer.getMatchTime();
    }

    /**
     * Pausa a execução por um tempo especificado.
     * ATENÇÃO: Use com cuidado! Bloquear o thread principal pode causar problemas.
     * Preferir usar timers não-bloqueantes quando possível.
     * 
     * @param seconds O tempo para pausar em segundos
     */
    public static void delay(double seconds) {
        edu.wpi.first.wpilibj.Timer.delay(seconds);
    }

    /**
     * Retorna a instância do Timer da WPILib.
     * 
     * @return A instância do Timer
     */
    public edu.wpi.first.wpilibj.Timer getWPILibTimer() {
        return timer;
    }
}
