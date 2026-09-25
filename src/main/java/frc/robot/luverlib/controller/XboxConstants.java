package frc.robot.luverlib.controller;

/**
 * Classe contendo constantes para o controle Xbox.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class XboxConstants {
    private XboxConstants() {

    }

    /** Botão A do controle Xbox */
    public static final int BUTTON_A = 1;
    /** Botão B do controle Xbox */
    public static final int BUTTON_B = 2;

    /** Botão X do controle Xbox */
    public static final int BUTTON_X = 3;
    /** Botão Y do controle Xbox */
    public static final int BUTTON_Y = 4;

    /** Botão RB (Right Bumper) do controle Xbox */
    public static final int BUTTON_RB = 6;
    /** Botão LB (Left Bumper) do controle Xbox */
    public static final int BUTTON_LB = 5;

    /** Botão Start do controle Xbox */
    public static final int BUTTON_START = 8;
    /** Botão Back do controle Xbox */
    public static final int BUTTON_BACK = 7;

    /** Botão do analógico esquerdo (L3) do controle Xbox */
    public static final int BUTTON_ANALOG_LEFT = 9;
    /** Botão do analógico direito (R3) do controle Xbox */
    public static final int BUTTON_ANALOG_RIGHT = 10;

    /** Eixo vertical (top-down) do analógico direito */
    public static final int AXIS_ANALOG_RIGHT_TD = 5;
    /** Eixo horizontal (left-right) do analógico direito */
    public static final int AXIS_ANALOG_RIGHT_LR = 4;

    /** Eixo vertical (top-down) do analógico esquerdo */
    public static final int AXIS_ANALOG_LEFT_TD = 1;
    /** Eixo horizontal (left-right) do analógico esquerdo */
    public static final int AXIS_ANALOG_LEFT_LR = 0;

    /** Eixo do gatilho esquerdo (Left Trigger) */
    public static final int AXIS_LT = 2;
    /** Eixo do gatilho direito (Right Trigger) */
    public static final int AXIS_RT = 3;

    /** Zona morta (deadzone) para os analógicos e triggers */
    public static final double DEADZONE = 0.05;
}
