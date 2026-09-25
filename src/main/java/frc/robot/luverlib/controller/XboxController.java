package frc.robot.luverlib.controller;

/**
 * Classe wrapper para o controle Xbox da WPILib.
 * Fornece métodos simplificados e otimizados para acessar botões, analógicos e
 * D-Pad do controle Xbox.
 * Implementa zonas mortas (deadzone) para os analógicos e triggers, evitando
 * leituras indesejadas.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class XboxController {
    /** Instância do controle Xbox nativo da WPILib */
    private edu.wpi.first.wpilibj.XboxController controller;

    /** Porta USB onde o controle está conectado */
    private int activePort;

    /**
     * Construtor da classe XboxController.
     * Inicializa o controle Xbox na porta especificada.
     * 
     * @param port A porta USB onde o controle Xbox está conectado (geralmente 0-5)
     */
    public XboxController(int port) {
        controller = new edu.wpi.first.wpilibj.XboxController(port);
        activePort = port;
    }

    /**
     * Verifica se o botão X foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão X acabou de ser pressionado, false caso contrário
     */
    public boolean getXButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_X);
    }

    /**
     * Verifica se o botão Y foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão Y acabou de ser pressionado, false caso contrário
     */
    public boolean getYButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_Y);
    }

    public boolean getYButton() {
        return controller.getRawButton(XboxConstants.BUTTON_Y);
    }

    /**
     * Verifica se o botão A foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão A acabou de ser pressionado, false caso contrário
     */
    public boolean getAButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_A);
    }

    /**
     * Verifica se o botão B foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão B acabou de ser pressionado, false caso contrário
     */
    public boolean getBButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_B);
    }

    public boolean getBButton() {
        return controller.getRawButton(XboxConstants.BUTTON_B);
    }

    /**
     * Verifica se o botão Start foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão Start acabou de ser pressionado, false caso contrário
     */
    public boolean getStartButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_START);
    }

    /**
     * Verifica se o botão Back foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão Back acabou de ser pressionado, false caso contrário
     */
    public boolean getBackButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_BACK);
    }

    /**
     * Verifica se o botão do analógico direito (R3) foi pressionado.
     * Este botão é acionado quando o jogador pressiona o analógico direito para
     * baixo.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão do analógico direito acabou de ser pressionado, false
     *         caso contrário
     */
    public boolean getRightAnalogButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_ANALOG_RIGHT);
    }

    public boolean getRightAnalogButton() {
        return controller.getRawButton(XboxConstants.BUTTON_ANALOG_RIGHT);
    }

    /**
     * Verifica se o botão do analógico esquerdo (L3) foi pressionado.
     * Este botão é acionado quando o jogador pressiona o analógico esquerdo para
     * baixo.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão do analógico esquerdo acabou de ser pressionado,
     *         false caso contrário
     */
    public boolean getLeftAnalogButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_ANALOG_LEFT);
    }

    public boolean getLeftAnalogButton() {
        return controller.getRawButton(XboxConstants.BUTTON_ANALOG_LEFT);
    }

    /**
     * Verifica se o botão LB (Left Bumper) foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão LB acabou de ser pressionado, false caso contrário
     */
    public boolean getLBButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_LB);
    }

    /**
    * Verifica se o botão LB (Left Bumper) está sendo pressionado.
    * Este método retorna true continuamente enquanto o botão estiver sendo
    * segurado (estado contínuo), e false quando o botão for solto.
    * 
    * Diferente de métodos baseados em "pressed", este NÃO detecta apenas
    * o instante do clique (rising edge), sendo indicado para ações que
    * devem permanecer ativas enquanto o botão estiver pressionado.
    *  
    * @return true enquanto o botão LB estiver pressionado, false caso contrário
    */
    public boolean getLBButton() {
        return controller.getRawButton(XboxConstants.BUTTON_LB);
    }

    /**
     * Verifica se o botão RB (Right Bumper) foi pressionado.
     * Este método detecta apenas o momento em que o botão é pressionado (rising
     * edge),
     * não retorna true continuamente enquanto o botão está sendo segurado.
     * 
     * @return true se o botão RB acabou de ser pressionado, false caso contrário
     */
    public boolean getRBButtonPressed() {
        return controller.getRawButtonPressed(XboxConstants.BUTTON_RB);
    }

    public boolean getRBButton() {
        return controller.getRawButton(XboxConstants.BUTTON_RB);
    }

    /**
     * Obtém a leitura do analógico direito no eixo horizontal (esquerda-direita).
     * Implementa uma zona morta (deadzone) para evitar drift e leituras indesejadas
     * quando o analógico está em repouso.
     * 
     * @return Valor entre -1.0 e 1.0 representando a posição horizontal do
     *         analógico direito.
     *         Retorna 0.0 se o valor estiver dentro da zona morta.
     *         Valores negativos indicam movimento para a esquerda, positivos para a
     *         direita.
     */
    public double getRightAnalogLeftRightReading() {
    double value = controller.getRawAxis(XboxConstants.AXIS_ANALOG_RIGHT_LR);
    if (Math.abs(value) > XboxConstants.DEADZONE) {
        return value;
    }
    return 0.0;
    }

    /**
     * Obtém a leitura do analógico direito no eixo vertical (cima-baixo).
     * Implementa uma zona morta (deadzone) para evitar drift e leituras indesejadas
     * quando o analógico está em repouso.
     * 
     * @return Valor entre -1.0 e 1.0 representando a posição vertical do analógico
     *         direito.
     *         Retorna 0.0 se o valor estiver dentro da zona morta.
     *         Valores negativos indicam movimento para cima, positivos para baixo.
     */
    public double getRightAnalogTopDownReading() {
        if (controller.getRawAxis(XboxConstants.AXIS_ANALOG_RIGHT_TD) > XboxConstants.DEADZONE) {
            return controller.getRawAxis(XboxConstants.AXIS_ANALOG_RIGHT_TD);
        } else {
            return 0.0;
        }
    }

    /**
     * Obtém a leitura do analógico esquerdo no eixo horizontal (esquerda-direita).
     * Implementa uma zona morta (deadzone) para evitar drift e leituras indesejadas
     * quando o analógico está em repouso.
     * 
     * @return Valor entre -1.0 e 1.0 representando a posição horizontal do
     *         analógico esquerdo.
     *         Retorna 0.0 se o valor estiver dentro da zona morta.
     *         Valores negativos indicam movimento para a esquerda, positivos para a
     *         direita.
     */
    public double getLeftAnalogLeftRightReading() {
    double val = controller.getRawAxis(XboxConstants.AXIS_ANALOG_LEFT_LR);
    
    // Math.abs transforma -0.8 em 0.8 para a comparação
    if (Math.abs(val) > XboxConstants.DEADZONE) {
        return val; // Retorna o valor original (positivo ou negativo)
    } else {
        return 0.0;
    }
 }

    /**
     * Obtém a leitura do analógico esquerdo no eixo vertical (cima-baixo).
     * Implementa uma zona morta (deadzone) para evitar drift e leituras indesejadas
     * quando o analógico está em repouso.
     * 
     * @return Valor entre -1.0 e 1.0 representando a posição vertical do analógico
     *         esquerdo.
     *         Retorna 0.0 se o valor estiver dentro da zona morta.
     *         Valores negativos indicam movimento para cima, positivos para baixo.
     */
    public double getLeftAnalogTopDownReading() {
        if (controller.getRawAxis(XboxConstants.AXIS_ANALOG_LEFT_TD) > XboxConstants.DEADZONE) {
            return controller.getRawAxis(XboxConstants.AXIS_ANALOG_LEFT_TD);
        } else {
            return 0.0;
        }
    }

    /**
     * Obtém a leitura do gatilho direito (RT - Right Trigger).
     * Implementa uma zona morta (deadzone) para evitar leituras indesejadas quando
     * o gatilho não está sendo pressionado.
     * O RT é um controle analógico que permite controle gradual da intensidade.
     * 
     * @return Valor entre 0.0 e 1.0 representando o quanto o gatilho direito está
     *         pressionado.
     *         Retorna 0.0 se o valor estiver dentro da zona morta ou se o gatilho
     *         não estiver pressionado.
     *         Quanto maior o valor, mais o gatilho está pressionado.
     */
    public double getRTButtonReading() {
        if (controller.getRawAxis(XboxConstants.AXIS_RT) > XboxConstants.DEADZONE) {
            return controller.getRawAxis(XboxConstants.AXIS_RT);
        } else {
            return 0.0;
        }
    }

    /**
     * Obtém a leitura do gatilho esquerdo (LT - Left Trigger).
     * Implementa uma zona morta (deadzone) para evitar leituras indesejadas quando
     * o gatilho não está sendo pressionado.
     * O LT é um controle analógico que permite controle gradual da intensidade.
     * 
     * @return Valor entre 0.0 e 1.0 representando o quanto o gatilho esquerdo está
     *         pressionado.
     *         Retorna 0.0 se o valor estiver dentro da zona morta ou se o gatilho
     *         não estiver pressionado.
     *         Quanto maior o valor, mais o gatilho está pressionado.
     */
    public double getLTButtonReading() {
        if (controller.getRawAxis(XboxConstants.AXIS_LT) > XboxConstants.DEADZONE) {
            return controller.getRawAxis(XboxConstants.AXIS_LT);
        } else {
            return 0.0;
        }
    }

    /**
     * Obtém o ângulo atual do D-Pad (direcional digital).
     * Converte o valor POV (Point of View) do controle em constantes mais legíveis.
     * O D-Pad possui 8 direções possíveis mais o estado neutro (não pressionado).
     * 
     * @return Uma constante da classe XboxControllerDPad representando a direção do
     *         D-Pad:
     * 
     *         XboxControllerDPad.NORTH (norte)
     *         XboxControllerDPad.NORTH_EAST (nordeste)
     *         XboxControllerDPad.EAST (leste)
     *         XboxControllerDPad.SOUTH_EAST (sudoleste)
     *         XboxControllerDPad.SOUTH (sul)
     *         XboxControllerDPad.SOUTH_WEST (sudoeste)
     *         XboxControllerDPad.WEST (oeste)
     *         XboxControllerDPad.NORTH_WEST (noroeste)
     *         -1 se o D-Pad não estiver sendo pressionado
     */
    public int getDPadAngle() {
        switch (controller.getPOV()) {
            case 0:
                return XboxControllerDPad.NORTH;
            case 45:
                return XboxControllerDPad.NORTH_EAST;
            case 90:
                return XboxControllerDPad.EAST;
            case 135:
                return XboxControllerDPad.SOUTH_EAST;
            case 180:
                return XboxControllerDPad.SOUTH;
            case 225:
                return XboxControllerDPad.SOUTH_WEST;
            case 270:
                return XboxControllerDPad.WEST;
            case 315:
                return XboxControllerDPad.NORTH_WEST;
            default:
                return -1;
        }
    }

    /**
     * Retorna a instância nativa do XboxController da WPILib.
     * Este método pode ser usado quando é necessário acessar funcionalidades
     * adicionais do controle que não estão encapsuladas nesta classe wrapper.
     * 
     * @return A instância do edu.wpi.first.wpilibj.XboxController
     */
    public edu.wpi.first.wpilibj.XboxController getXboxController() {
        return controller;
    }

    /**
     * Obtém a porta USB ativa onde o controle está conectado.
     * Útil para debugging e para identificar qual controle está sendo usado
     * quando múltiplos controles estão conectados ao RoboRIO.
     * 
     * @return O número da porta USB (geralmente entre 0 e 5)
     */
    public int getActivePort() {
        return activePort;
    }
}
