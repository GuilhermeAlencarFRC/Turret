package frc.robot.luverlib.controller;

public class PS4Controller {
    private edu.wpi.first.wpilibj.PS4Controller controller;
    private int activePort;

    public PS4Controller(int port) {
        controller = new edu.wpi.first.wpilibj.PS4Controller(port);
        activePort = port;
    }

    public edu.wpi.first.wpilibj.PS4Controller getPS4Controller() {
        return controller;
    }

    public int getActivePort() {
        return activePort;
    }
}
