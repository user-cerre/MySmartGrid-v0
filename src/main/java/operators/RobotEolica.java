package operators;

import storage.Eolica;

public class RobotEolica implements Runnable {
    private final Eolica bateriaEolica;
    private final int idRobot;
    private volatile boolean activo = true;

    public RobotEolica(Eolica bateriaEolica, int idRobot) {
        this.bateriaEolica = bateriaEolica;
        this.idRobot = idRobot;
    }

    @Override
    public void run() {
        try {
            while (activo) {
                bateriaEolica.depositar(1.0); // 1 kWh por ciclo
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("[RobotEolica " + idRobot + "] Finalizando");
        }
    }

    public void detener() {
        activo = false;
    }
}