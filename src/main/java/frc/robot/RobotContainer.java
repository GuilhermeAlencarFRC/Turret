package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.TurretManualCommand;
import frc.robot.luverlib.controller.XboxController;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {

    public final XboxController controller = new XboxController(Constants.ControllerConstants.DRIVER_CONTROLLER_PORT);

    private final TurretSubsystem turret = new TurretSubsystem();

    private final TurretManualCommand turretManualCommand = new TurretManualCommand(turret, controller);

    /*
     * A torreta não tem mais homing: o zero é a posição de
     * referência física em que ela está quando o robô liga
     * (ver BOOT_ANGLE_DEGREES em Constants). O comando manual é
     * o default e roda assim que o robô é habilitado.
     */

    public RobotContainer() {

        configureDefaultCommands();

        configureBindings();
    }


    private void configureDefaultCommands() {

        turret.setDefaultCommand(turretManualCommand);
    }


    private void configureBindings() {

        // Futuramente:
        //
        // botão para ativar tracking
        // botão para modo manual
        // etc.
        //
    }


    public Command getAutonomousCommand() {
        return null;
    }
}