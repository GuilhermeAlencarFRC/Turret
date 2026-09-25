package frc.robot;

public final class Constants {

    private Constants() {}

    public static final class TurretConstants {

        private TurretConstants() {}

        // CAN ID
        public static final int MOTOR_ID = 1;

        // Engrenagem presa ao motor
        public static final int MOTOR_GEAR_TEETH = 20;

        // Engrenagem presa a torreta
        public static final int TURRET_GEAR_TEETH = 200;

        public static final double GEAR_RATIO = (double) TURRET_GEAR_TEETH / MOTOR_GEAR_TEETH;

        public static final double DEGREES_PER_MOTOR_ROTATION = 360.0 / GEAR_RATIO;

        /*
         * >>> MUITO SENSÍVEL <<<
         *
         * É o maior ângulo que a torreta pode atingir.
         *
         * NÃO coloque aqui o limite onde o cabo já está esticado.
         *
         * Deixe uma margem de segurança.
         */
        public static final double MAX_ANGLE = 330.0;
        public static final double MIN_ANGLE = -330.0;

        /*
         * >>> MUITO SENSÍVEL - PRINCIPAL CONFIGURAÇÃO <<<
         *
         * Quando a torreta estiver chegando nesse ângulo,
         * o sistema começará a procurar uma representação
         * alternativa do alvo (reversão).
         */
        public static final double MAX_REVERSE_ANGLE = 280.0;

        /*
         * >>> MUITO SENSÍVEL - PRINCIPAL CONFIGURAÇÃO <<<
         *
         * Mesmo conceito do lado negativo.
         */
        public static final double MIN_REVERSE_ANGLE = -280.0;

        public static final double MAX_SPEED = 0.05;

        /*
         * >>> SENSÍVEL <<<
         *
         * Quanto o SETPOINT muda por segundo quando o joystick
         * esta totalmente pressionado.
         */
        public static final double JOYSTICK_TARGET_SPEED = 10.0;

        public static final double KP = 0.02;
        public static final double KI = 0.0;
        public static final double KD = 0.0;

        public static final int CURRENT_LIMIT = 40;

        /*
         * >>> REFERÊNCIA FÍSICA DE LIGAÇÃO (substitui o homing) <<<
         *
         * Não há mais rotina de calibração nem sensor. O zero da
         * torreta é a posição em que ela está NO MOMENTO EM QUE O
         * ROBÔ É LIGADO (o encoder do NEO sempre começa em 0 ao
         * energizar).
         *
         * Por isso, a torreta PRECISA estar na posição de
         * referência física toda vez que o robô for ligado:
         *
         *   - marca de alinhamento no anel da torreta + na base
         *     (ou um pino/gabarito que trava a torreta), apontando
         *     pra frente -> deixe 0.0
         *   - encostada num batente mecânico -> coloque aqui o
         *     ângulo real desse batente
         *
         * ATENÇÃO: NÃO use uma referência dentro das zonas de
         * reversão (acima de MAX_REVERSE_ANGLE ou abaixo de
         * MIN_REVERSE_ANGLE). Ao habilitar, o calculateSafeTarget()
         * troca esse alvo pelo equivalente +/-360 e a torreta
         * giraria uma volta inteira.
         *
         * Se ligar fora da referência, TODOS os limites ficam
         * deslocados desse erro - e o cabo pode ser esticado.
         */
        public static final double BOOT_ANGLE_DEGREES = 0.0;

    }


    public static final class ControllerConstants {

        private ControllerConstants() {}

        public static final int DRIVER_CONTROLLER_PORT = 0;
    }
}