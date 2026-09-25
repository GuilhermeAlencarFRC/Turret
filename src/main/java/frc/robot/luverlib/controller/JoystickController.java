package frc.robot.luverlib.controller;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickController {
    private Joystick joystick;
    private int activePort;

    public JoystickController(int port) {
        joystick = new Joystick(port);
        activePort = port;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public int getActivePort() {
        return activePort;
    }
}
