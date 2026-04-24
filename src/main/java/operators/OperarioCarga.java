package operators;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import storage.Bateria;

public class OperarioCarga implements Runnable{
    private final Bateria bateria;
    private final int idOperario;
    private final double cantidadRecarga;
    private volatile boolean activo = true;
    private CountDownLatch arranqueRedLatch;

    public OperarioCarga(Bateria _b, int _i, double _c, CountDownLatch latch){
        this.bateria = Objects.requireNonNull(_b, "La batería no puede ser nula");
        this.idOperario = _i;
        this.cantidadRecarga = _c;
        this.arranqueRedLatch = latch;

    }

    @Override
    public void run(){
        try {
            arranqueRedLatch.await();
            while (activo) {
                bateria.esperarSolicitudRecarga();
                if (!activo) break;  // Chequea si debe terminar
                Thread.sleep(100);
                bateria.carga(cantidadRecarga);
                System.out.println("[OperarioCarga " + idOperario + "] Recargados " + cantidadRecarga + " kWh");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[OperarioCarga " + idOperario + "] Interrumpido");
        }
    }
    public void detener (){
        activo = false;
    }
}
