package operators;

import storage.Solar;

public class RobotSolar implements Runnable {
    private final Solar bateriaSolar;
    private final int idRobot;
    private volatile boolean activo = true;

    public RobotSolar(Solar bateriaSolar, int idRobot) {
        this.bateriaSolar = bateriaSolar;
        this.idRobot = idRobot;
    }

    @Override
    public void run() {
        try {
            while (activo) {
                bateriaSolar.depositar(1.0); // 1 kWh por ciclo
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("[RobotSolar " + idRobot + "] Finalizando");
        }
    }

    public void detener() {
        activo = false;
    }
}