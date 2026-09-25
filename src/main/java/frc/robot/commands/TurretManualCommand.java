package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.luverlib.controller.XboxController;
import frc.robot.subsystems.TurretSubsystem;

public class TurretManualCommand extends Command {

    private final TurretSubsystem turret;

    private final XboxController controller;


    public TurretManualCommand(TurretSubsystem turret,XboxController controller) {

        this.turret = turret;

        this.controller = controller;

        addRequirements(turret);
    }


    @Override
    public void execute() {

        /*
         * O joystick agora NÃO manda potência diretamente
         * para o NEO.
         *
         * Ele movimenta o TARGET ANGLE.
         *
         * Isso é o que permite a lógica automática de
         * reversão/desenrolamento.
         */

        double joystickValue = controller.getLeftAnalogLeftRightReading();

        turret.updateTargetFromJoystick(joystickValue);
    }


    @Override
    public void end(
            boolean interrupted
    ) {

        turret.stop();
    }


    @Override
    public boolean isFinished() {

        return false;
    }
}