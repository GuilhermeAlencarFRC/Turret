package frc.robot.luverlib.util;

/**
 * Classe utilitária com funções matemáticas comumente usadas em robótica FRC.
 * Inclui funções para deadband, limites, conversões e interpolação.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class MathUtils {
    /**
     * Construtor privado para prevenir instanciação.
     * Esta classe contém apenas métodos estáticos.
     */
    private MathUtils() {

    }

    /**
     * Aplica uma zona morta (deadband) a um valor.
     * Valores dentro da zona morta são retornados como zero.
     * 
     * @param value    O valor de entrada
     * @param deadband O tamanho da zona morta (0-1)
     * @return O valor com deadband aplicado
     */
    public static double applyDeadband(double value, double deadband) {
        if (Math.abs(value) < deadband) {
            return 0.0;
        }
        // Escala o valor para começar de 0 após o deadband
        return Math.signum(value) * ((Math.abs(value) - deadband) / (1.0 - deadband));
    }

    /**
     * Limita um valor a um intervalo especificado.
     * 
     * @param value O valor de entrada
     * @param min   O valor mínimo permitido
     * @param max   O valor máximo permitido
     * @return O valor limitado ao intervalo [min, max]
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    /**
     * Limita um valor ao intervalo [-limit, limit].
     * 
     * @param value O valor de entrada
     * @param limit O limite absoluto (positivo)
     * @return O valor limitado ao intervalo [-limit, limit]
     */
    public static double clampSymmetric(double value, double limit) {
        return clamp(value, -Math.abs(limit), Math.abs(limit));
    }

    /**
     * Interpola linearmente entre dois valores.
     * 
     * @param start O valor inicial (t=0)
     * @param end   O valor final (t=1)
     * @param t     O parâmetro de interpolação (0-1)
     * @return O valor interpolado
     */
    public static double lerp(double start, double end, double t) {
        return start + (end - start) * t;
    }

    /**
     * Calcula o parâmetro de interpolação inversa.
     * Dado um valor, retorna onde ele está entre start e end.
     * 
     * @param start O valor inicial
     * @param end   O valor final
     * @param value O valor a ser mapeado
     * @return O parâmetro t (0-1) ou valor fora do intervalo
     */
    public static double inverseLerp(double start, double end, double value) {
        if (start == end) {
            return 0.0;
        }
        return (value - start) / (end - start);
    }

    /**
     * Mapeia um valor de um intervalo para outro.
     * 
     * @param value  O valor de entrada
     * @param inMin  O mínimo do intervalo de entrada
     * @param inMax  O máximo do intervalo de entrada
     * @param outMin O mínimo do intervalo de saída
     * @param outMax O máximo do intervalo de saída
     * @return O valor mapeado para o novo intervalo
     */
    public static double map(double value, double inMin, double inMax,
            double outMin, double outMax) {
        return lerp(outMin, outMax, inverseLerp(inMin, inMax, value));
    }

    /**
     * Converte graus para radianos.
     * 
     * @param degrees O ângulo em graus
     * @return O ângulo em radianos
     */
    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    /**
     * Converte radianos para graus.
     * 
     * @param radians O ângulo em radianos
     * @return O ângulo em graus
     */
    public static double radiansToDegrees(double radians) {
        return Math.toDegrees(radians);
    }

    /**
     * Normaliza um ângulo para o intervalo [-180, 180] graus.
     * 
     * @param degrees O ângulo em graus
     * @return O ângulo normalizado
     */
    public static double normalizeAngleDegrees(double degrees) {
        degrees = degrees % 360;
        if (degrees > 180) {
            degrees -= 360;
        } else if (degrees < -180) {
            degrees += 360;
        }
        return degrees;
    }

    /**
     * Normaliza um ângulo para o intervalo [-π, π] radianos.
     * 
     * @param radians O ângulo em radianos
     * @return O ângulo normalizado
     */
    public static double normalizeAngleRadians(double radians) {
        radians = radians % (2 * Math.PI);
        if (radians > Math.PI) {
            radians -= 2 * Math.PI;
        } else if (radians < -Math.PI) {
            radians += 2 * Math.PI;
        }
        return radians;
    }

    /**
     * Calcula a diferença mais curta entre dois ângulos em graus.
     * Útil para controle de rotação onde o caminho mais curto é desejado.
     * 
     * @param current O ângulo atual em graus
     * @param target  O ângulo alvo em graus
     * @return A diferença em graus (-180 a 180)
     */
    public static double angleDifferenceDegrees(double current, double target) {
        return normalizeAngleDegrees(target - current);
    }

    /**
     * Calcula a diferença mais curta entre dois ângulos em radianos.
     * 
     * @param current O ângulo atual em radianos
     * @param target  O ângulo alvo em radianos
     * @return A diferença em radianos (-π a π)
     */
    public static double angleDifferenceRadians(double current, double target) {
        return normalizeAngleRadians(target - current);
    }

    /**
     * Converte polegadas para metros.
     * 
     * @param inches O valor em polegadas
     * @return O valor em metros
     */
    public static double inchesToMeters(double inches) {
        return inches * 0.0254;
    }

    /**
     * Converte metros para polegadas.
     * 
     * @param meters O valor em metros
     * @return O valor em polegadas
     */
    public static double metersToInches(double meters) {
        return meters / 0.0254;
    }

    /**
     * Converte pés para metros.
     * 
     * @param feet O valor em pés
     * @return O valor em metros
     */
    public static double feetToMeters(double feet) {
        return feet * 0.3048;
    }

    /**
     * Converte metros para pés.
     * 
     * @param meters O valor em metros
     * @return O valor em pés
     */
    public static double metersToFeet(double meters) {
        return meters / 0.3048;
    }

    /**
     * Verifica se dois valores são aproximadamente iguais.
     * 
     * @param a       O primeiro valor
     * @param b       O segundo valor
     * @param epsilon A tolerância para comparação
     * @return true se os valores são aproximadamente iguais
     */
    public static boolean isApproximatelyEqual(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

    /**
     * Aplica uma curva de resposta quadrática à entrada.
     * Aumenta a precisão em baixas velocidades mantendo o range completo.
     * 
     * @param input O valor de entrada (-1 a 1)
     * @return O valor com curva quadrática aplicada
     */
    public static double squareInput(double input) {
        return Math.signum(input) * input * input;
    }

    /**
     * Aplica uma curva de resposta cúbica à entrada.
     * Ainda mais precisão em baixas velocidades que a quadrática.
     * 
     * @param input O valor de entrada (-1 a 1)
     * @return O valor com curva cúbica aplicada
     */
    public static double cubeInput(double input) {
        return input * input * input;
    }
}
