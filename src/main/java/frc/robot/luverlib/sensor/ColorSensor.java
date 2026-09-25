package frc.robot.luverlib.sensor;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/**
 * Classe wrapper para o Color Sensor V3 da REV Robotics.
 * Fornece métodos simplificados para detecção de cores e proximidade,
 * muito utilizado em FRC para identificação de game pieces.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class ColorSensor {
    /** Instância do Color Sensor V3 */
    private ColorSensorV3 colorSensor;

    /** Matcher de cores para identificação */
    private ColorMatch colorMatcher;

    /** Cores padrão para identificação */
    private Color targetRed;
    private Color targetBlue;
    private Color targetGreen;
    private Color targetYellow;

    /**
     * Construtor da classe ColorSensor.
     * Inicializa o sensor na porta I2C padrão do RoboRIO (onboard).
     */
    public ColorSensor() {
        this.colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        initializeColorMatcher();
    }

    /**
     * Construtor da classe ColorSensor com porta I2C específica.
     * 
     * @param port A porta I2C a ser usada (kOnboard ou kMXP)
     */
    public ColorSensor(I2C.Port port) {
        this.colorSensor = new ColorSensorV3(port);
        initializeColorMatcher();
    }

    /**
     * Inicializa o color matcher com cores padrão.
     * As cores podem ser ajustadas via calibração para melhor precisão.
     */
    private void initializeColorMatcher() {
        colorMatcher = new ColorMatch();

        // Cores padrão (ajuste conforme necessário para seu ambiente)
        targetRed = new Color(0.561, 0.232, 0.114);
        targetBlue = new Color(0.143, 0.427, 0.429);
        targetGreen = new Color(0.197, 0.561, 0.240);
        targetYellow = new Color(0.361, 0.524, 0.113);

        colorMatcher.addColorMatch(targetRed);
        colorMatcher.addColorMatch(targetBlue);
        colorMatcher.addColorMatch(targetGreen);
        colorMatcher.addColorMatch(targetYellow);
    }

    /**
     * Obtém a cor detectada pelo sensor.
     * 
     * @return Um objeto Color com os valores RGB normalizados
     */
    public Color getDetectedColor() {
        return colorSensor.getColor();
    }

    /**
     * Obtém o valor de vermelho detectado (normalizado 0-1).
     * 
     * @return O valor de vermelho
     */
    public double getRed() {
        return colorSensor.getColor().red;
    }

    /**
     * Obtém o valor de verde detectado (normalizado 0-1).
     * 
     * @return O valor de verde
     */
    public double getGreen() {
        return colorSensor.getColor().green;
    }

    /**
     * Obtém o valor de azul detectado (normalizado 0-1).
     * 
     * @return O valor de azul
     */
    public double getBlue() {
        return colorSensor.getColor().blue;
    }

    /**
     * Obtém a distância do objeto detectado (valor de proximidade).
     * Valores maiores indicam objetos mais próximos (0-2047).
     * 
     * @return O valor de proximidade (0-2047)
     */
    public int getProximity() {
        return colorSensor.getProximity();
    }

    /**
     * Verifica se há um objeto próximo ao sensor.
     * 
     * @param threshold O limiar de proximidade (0-2047)
     * @return true se um objeto está mais próximo que o threshold
     */
    public boolean isObjectDetected(int threshold) {
        return colorSensor.getProximity() > threshold;
    }

    /**
     * Tenta identificar a cor detectada entre as cores configuradas.
     * 
     * @return O resultado da correspondência de cor, ou null se nenhuma cor
     *         corresponder
     */
    public ColorMatchResult matchColor() {
        return colorMatcher.matchClosestColor(colorSensor.getColor());
    }

    /**
     * Verifica se a cor detectada é vermelha.
     * 
     * @param confidenceThreshold Confiança mínima para considerar match (0-1)
     * @return true se a cor é vermelha com confiança suficiente
     */
    public boolean isRed(double confidenceThreshold) {
        ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
        return match.color.equals(targetRed) && match.confidence >= confidenceThreshold;
    }

    /**
     * Verifica se a cor detectada é azul.
     * 
     * @param confidenceThreshold Confiança mínima para considerar match (0-1)
     * @return true se a cor é azul com confiança suficiente
     */
    public boolean isBlue(double confidenceThreshold) {
        ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
        return match.color.equals(targetBlue) && match.confidence >= confidenceThreshold;
    }

    /**
     * Verifica se a cor detectada é verde.
     * 
     * @param confidenceThreshold Confiança mínima para considerar match (0-1)
     * @return true se a cor é verde com confiança suficiente
     */
    public boolean isGreen(double confidenceThreshold) {
        ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
        return match.color.equals(targetGreen) && match.confidence >= confidenceThreshold;
    }

    /**
     * Verifica se a cor detectada é amarela.
     * 
     * @param confidenceThreshold Confiança mínima para considerar match (0-1)
     * @return true se a cor é amarela com confiança suficiente
     */
    public boolean isYellow(double confidenceThreshold) {
        ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
        return match.color.equals(targetYellow) && match.confidence >= confidenceThreshold;
    }

    /**
     * Configura a cor alvo para vermelho.
     * Use os valores obtidos durante calibração com a cor real.
     * 
     * @param red   Valor vermelho normalizado (0-1)
     * @param green Valor verde normalizado (0-1)
     * @param blue  Valor azul normalizado (0-1)
     */
    public void setTargetRed(double red, double green, double blue) {
        targetRed = new Color(red, green, blue);
        rebuildColorMatcher();
    }

    /**
     * Configura a cor alvo para azul.
     * 
     * @param red   Valor vermelho normalizado (0-1)
     * @param green Valor verde normalizado (0-1)
     * @param blue  Valor azul normalizado (0-1)
     */
    public void setTargetBlue(double red, double green, double blue) {
        targetBlue = new Color(red, green, blue);
        rebuildColorMatcher();
    }

    /**
     * Configura a cor alvo para verde.
     * 
     * @param red   Valor vermelho normalizado (0-1)
     * @param green Valor verde normalizado (0-1)
     * @param blue  Valor azul normalizado (0-1)
     */
    public void setTargetGreen(double red, double green, double blue) {
        targetGreen = new Color(red, green, blue);
        rebuildColorMatcher();
    }

    /**
     * Configura a cor alvo para amarelo.
     * 
     * @param red   Valor vermelho normalizado (0-1)
     * @param green Valor verde normalizado (0-1)
     * @param blue  Valor azul normalizado (0-1)
     */
    public void setTargetYellow(double red, double green, double blue) {
        targetYellow = new Color(red, green, blue);
        rebuildColorMatcher();
    }

    /**
     * Adiciona uma cor customizada ao matcher.
     * 
     * @param color A cor a ser adicionada
     */
    public void addCustomColor(Color color) {
        colorMatcher.addColorMatch(color);
    }

    /**
     * Reconstrói o color matcher com as cores atualizadas.
     */
    private void rebuildColorMatcher() {
        colorMatcher = new ColorMatch();
        colorMatcher.addColorMatch(targetRed);
        colorMatcher.addColorMatch(targetBlue);
        colorMatcher.addColorMatch(targetGreen);
        colorMatcher.addColorMatch(targetYellow);
    }

    /**
     * Obtém o valor bruto de IR (infravermelho) do sensor.
     * 
     * @return O valor de IR
     */
    public int getIR() {
        return colorSensor.getIR();
    }

    /**
     * Retorna a instância do ColorSensorV3 da REV.
     * 
     * @return A instância do ColorSensorV3
     */
    public ColorSensorV3 getSensor() {
        return colorSensor;
    }

    /**
     * Retorna o color matcher para configurações avançadas.
     * 
     * @return A instância do ColorMatch
     */
    public ColorMatch getColorMatcher() {
        return colorMatcher;
    }
}
