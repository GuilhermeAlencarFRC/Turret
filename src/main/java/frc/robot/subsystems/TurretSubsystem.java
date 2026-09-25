package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

    private final SparkMax turretMotor;

    private final RelativeEncoder encoder;

    private final SparkClosedLoopController pidController;

    /*
     * Ângulo lógico que o joystick está comandando.
     *
     * IMPORTANTE:
     *
     * Esse valor pode ultrapassar 360°. Isso é proposital.
     *
     * O sistema usa esse valor para saber qual é a orientação
     * desejada, mesmo depois de uma reversão.
     */
    private double targetAngle = 0.0;

    /*
     * Setpoint REAL usado pelo PID.
     *
     * Diferente do targetAngle. Pode ser a representação
     * equivalente (target - 360, por exemplo) escolhida por
     * calculateSafeTarget().
     */
    private double pidSetpointAngle = 0.0;

    private double lastTime;

    @SuppressWarnings("removal")
    
    public TurretSubsystem() {

        turretMotor = new SparkMax(
                TurretConstants.MOTOR_ID,
                MotorType.kBrushless);

        encoder = turretMotor.getEncoder();

        pidController = turretMotor.getClosedLoopController();

        SparkMaxConfig config = new SparkMaxConfig();

        config
                .smartCurrentLimit(
                        TurretConstants.CURRENT_LIMIT)
                .idleMode(
                        SparkBaseConfig.IdleMode.kBrake);

        config.closedLoop
                .p(TurretConstants.KP)
                .i(TurretConstants.KI)
                .d(TurretConstants.KD);

        turretMotor.configure(
                config,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);

        lastTime = Timer.getFPGATimestamp();

        SmartDashboard.putNumber("Turret/TargetAngle", targetAngle);
        SmartDashboard.putNumber("Turret/PIDSetpoint", pidSetpointAngle);
        SmartDashboard.putNumber("Turret/Angle", getTurretAngle());
        SmartDashboard.putNumber("Turret/MaxAngle", TurretConstants.MAX_ANGLE);
        SmartDashboard.putNumber("Turret/MinAngle", TurretConstants.MIN_ANGLE);
        SmartDashboard.putNumber("Turret/MaxReverseAngle", TurretConstants.MAX_REVERSE_ANGLE);
        SmartDashboard.putNumber("Turret/MinReverseAngle", TurretConstants.MIN_REVERSE_ANGLE);
    }

    public void updateTargetFromJoystick(double joystickValue) {

        double currentTime = Timer.getFPGATimestamp();
        double dt = currentTime - lastTime;
        lastTime = currentTime;

        // Segurança contra valores absurdos de dt
        dt = Math.min(dt, 0.05);

        double targetVelocity = joystickValue * TurretConstants.JOYSTICK_TARGET_SPEED;

        targetAngle += targetVelocity * dt;
    }

    /**
     * Atualiza o setpoint do PID e controla a torreta.
     *
     * Chamado a cada ciclo pelo periodic().
     */

    @SuppressWarnings("removal")

    public void updateTurretControl() {

        double currentAngle = getTurretAngle();

        double selectedAngle = calculateSafeTarget(targetAngle, currentAngle);

        pidSetpointAngle = selectedAngle;

        // O encoder é 0 na posição de ligação (BOOT_ANGLE_DEGREES).
        double motorRotations = (pidSetpointAngle - TurretConstants.BOOT_ANGLE_DEGREES)
                / TurretConstants.DEGREES_PER_MOTOR_ROTATION;

        pidController.setReference(
                motorRotations,
                ControlType.kPosition);
    }

    /**
     * Encontra uma representação equivalente do alvo dentro da
     * faixa segura da torreta.
     */
    private double calculateSafeTarget(double target, double currentAngle) {

        double candidate = target;

        while (candidate - currentAngle > 180.0) {
            candidate -= 360.0;
        }

        while (candidate - currentAngle < -180.0) {
            candidate += 360.0;
        }

        if (candidate > TurretConstants.MAX_REVERSE_ANGLE) {

            double alternative = candidate - 360.0;

            if (alternative >= TurretConstants.MIN_ANGLE) {
                candidate = alternative;
            }
        }

        if (candidate < TurretConstants.MIN_REVERSE_ANGLE) {

            double alternative = candidate + 360.0;

            if (alternative <= TurretConstants.MAX_ANGLE) {
                candidate = alternative;
            }
        }

        candidate = MathUtil.clamp(candidate, TurretConstants.MIN_ANGLE, TurretConstants.MAX_ANGLE);

        return candidate;
    }

    /**
     * Ângulo real da torreta. O encoder começa em 0 quando o robô
     * é ligado, e a torreta é posta na referência física nesse
     * momento - então o ângulo é BOOT_ANGLE_DEGREES + o que o
     * encoder andou.
     *
     * (Não usamos encoder.setPosition() de propósito: se só o
     * código reiniciar sem cortar a energia do SparkMax, o
     * encoder continua válido.)
     */
    public double getTurretAngle() {

        double motorRotations = encoder.getPosition();

        return TurretConstants.BOOT_ANGLE_DEGREES
                + motorRotations * TurretConstants.DEGREES_PER_MOTOR_ROTATION;
    }

    public double getMotorRotations() {
        return encoder.getPosition();
    }

    public void stop() {
        turretMotor.stopMotor();
    }

    @Override
    public void periodic() {

        /*
         * Com o robô desabilitado, mantém o alvo colado na posição
         * real. Assim, ao habilitar, a torreta NÃO dá um salto pro
         * último alvo (nem se alguém girou ela na mão).
         */
        if (DriverStation.isDisabled()) {
            targetAngle = getTurretAngle();
            pidSetpointAngle = targetAngle;
        }

        updateTurretControl();

        SmartDashboard.putNumber("Turret/Angle", getTurretAngle());
        SmartDashboard.putNumber("Turret/MotorRotations", getMotorRotations());
        SmartDashboard.putNumber("Turret/TargetAngle", targetAngle);
        SmartDashboard.putNumber("Turret/PIDSetpoint", pidSetpointAngle);
        SmartDashboard.putNumber("Turret/Output", turretMotor.getAppliedOutput());

        SmartDashboard.putBoolean(
                "Turret/AtMaxLimit",
                getTurretAngle() >= TurretConstants.MAX_ANGLE);

        SmartDashboard.putBoolean(
                "Turret/AtMinLimit",
                getTurretAngle() <= TurretConstants.MIN_ANGLE);
    }
}