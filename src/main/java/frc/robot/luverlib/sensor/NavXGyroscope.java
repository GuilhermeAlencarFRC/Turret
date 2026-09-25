package frc.robot.luverlib.sensor;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU.IMUAxis;

/**
 * Implementação da interface IGyroscope para o ADIS16470 (IMU incluída no Kit
 * de Peças).
 * O ADIS16470 é uma IMU de alta precisão conectada via SPI ao RoboRIO,
 * oferecendo medições de yaw, pitch e roll.
 * 
 * Esta IMU é fornecida gratuitamente no Kit of Parts e é uma opção popular em
 * FRC.
 * 
 * @author Equipe Luvercontrol - FRC 10298
 * @version 1.0
 */
public class NavXGyroscope implements IGyroscope {
    /** Instância da IMU ADIS16470 */
    private ADIS16470_IMU imu;

    /** Offset do yaw para calibração manual */
    private double yawOffset;

    /** Eixo configurado como yaw */
    private IMUAxis yawAxis;

    /**
     * Construtor padrão da classe NavXGyroscope.
     * Inicializa a IMU com o eixo Z como yaw (configuração padrão).
     */
    public NavXGyroscope() {
        this.imu = new ADIS16470_IMU();
        this.yawAxis = IMUAxis.kZ;
        this.yawOffset = 0;
    }

    /**
     * Construtor da classe NavXGyroscope com eixo de yaw customizado.
     * Permite configurar qual eixo físico da IMU será usado como yaw.
     * 
     * @param yawAxis O eixo a ser usado como yaw (kX, kY, ou kZ)
     */
    public NavXGyroscope(IMUAxis yawAxis) {
        this.imu = new ADIS16470_IMU(ADIS16470_IMU.IMUAxis.kZ, ADIS16470_IMU.IMUAxis.kX, yawAxis);
        this.yawAxis = yawAxis;
        this.yawOffset = 0;
    }

    /**
     * Obtém o ângulo de rotação em torno do eixo configurado como yaw.
     * 
     * @return O ângulo em graus (positivo = sentido anti-horário)
     */
    @Override
    public double getYaw() {
        return imu.getAngle(yawAxis) - yawOffset;
    }

    /**
     * Obtém o ângulo de inclinação (pitch).
     * O eixo depende da configuração de montagem da IMU.
     * 
     * @return O ângulo em graus
     */
    @Override
    public double getPitch() {
        // Com Z como yaw, Y tipicamente é pitch
        if (yawAxis == IMUAxis.kZ) {
            return imu.getAngle(IMUAxis.kY);
        } else if (yawAxis == IMUAxis.kY) {
            return imu.getAngle(IMUAxis.kZ);
        } else {
            return imu.getAngle(IMUAxis.kY);
        }
    }

    /**
     * Obtém o ângulo de rotação lateral (roll).
     * O eixo depende da configuração de montagem da IMU.
     * 
     * @return O ângulo em graus
     */
    @Override
    public double getRoll() {
        // Com Z como yaw, X tipicamente é roll
        if (yawAxis == IMUAxis.kZ) {
            return imu.getAngle(IMUAxis.kX);
        } else if (yawAxis == IMUAxis.kX) {
            return imu.getAngle(IMUAxis.kZ);
        } else {
            return imu.getAngle(IMUAxis.kX);
        }
    }

    /**
     * Obtém o yaw como um objeto Rotation2d da WPILib.
     * 
     * @return O ângulo como Rotation2d
     */
    @Override
    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(getYaw());
    }

    /**
     * Obtém a taxa de rotação do eixo configurado como yaw.
     * 
     * @return A taxa de rotação em graus por segundo
     */
    @Override
    public double getYawRate() {
        return imu.getRate(yawAxis);
    }

    /**
     * Reseta o yaw para zero.
     * Define a posição atual como referência.
     */
    @Override
    public void resetYaw() {
        yawOffset = imu.getAngle(yawAxis);
    }

    /**
     * Define o yaw para um valor específico.
     * Implementado através de offset pois a IMU não suporta set direto.
     * 
     * @param angle O ângulo em graus para definir como yaw atual
     */
    @Override
    public void setYaw(double angle) {
        yawOffset = imu.getAngle(yawAxis) - angle;
    }

    /**
     * Verifica se a IMU está conectada.
     * A ADIS16470 conectada via SPI está sempre "conectada" se inicializada.
     * 
     * @return true (sempre conectada se inicializada corretamente)
     */
    @Override
    public boolean isConnected() {
        return true; // SPI sempre conectado se inicializado
    }

    /**
     * Verifica se a IMU está calibrando.
     * 
     * @return true se está calibrando, false caso contrário
     */
    @Override
    public boolean isCalibrating() {
        return false; // ADIS16470 calibra na inicialização automaticamente
    }

    /**
     * Calibra a IMU.
     * Este método bloqueia brevemente enquanto a calibração é realizada.
     */
    public void calibrate() {
        imu.calibrate();
    }

    /**
     * Reseta completamente a IMU.
     * Zera todos os ângulos acumulados.
     */
    public void reset() {
        imu.reset();
        yawOffset = 0;
    }

    /**
     * Obtém a aceleração no eixo X em g's.
     * 
     * @return A aceleração em g
     */
    public double getAccelerationX() {
        return imu.getAccelX();
    }

    /**
     * Obtém a aceleração no eixo Y em g's.
     * 
     * @return A aceleração em g
     */
    public double getAccelerationY() {
        return imu.getAccelY();
    }

    /**
     * Obtém a aceleração no eixo Z em g's.
     * 
     * @return A aceleração em g
     */
    public double getAccelerationZ() {
        return imu.getAccelZ();
    }

    /**
     * Obtém o eixo atualmente configurado como yaw.
     * 
     * @return O eixo de yaw atual
     */
    public IMUAxis getYawAxis() {
        return yawAxis;
    }

    /**
     * Retorna a instância da IMU ADIS16470.
     * 
     * @return A instância da ADIS16470_IMU
     */
    public ADIS16470_IMU getIMU() {
        return imu;
    }
}
