package operators;
import java.util.Objects;
import java.util.concurrent.locks.*;

import storage.Bateria;

public class OperarioCarga implements Runnable{
    private final Bateria bateria;
    private final int idOperario;
    private final double cantidadRecarga;
    private volatile boolean activo = true;

    public OperarioCarga(Bateria _b, int _i, double _c){
        this.bateria = Objects.requireNonNull(_b, "La batería no puede ser nula");
        this.idOperario = _i;
        this.cantidadRecarga = _c;

    }

    @Override
    public void run(){
        try {
            while (activo) {
                bateria.esperarSolicitudRecarga();
                if (!activo) break;  // Chequea si debe terminar
                Thread.sleep(100);
                bateria.carga(cantidadRecarga);
                System.out.println("[OperarioCarga " + idOperario + "] Recargados " + cantidadRecarga + " kWh");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.err.println("[OperarioCarga " + idOperario + "] Interrumpido");
        }
    }
    public void detener (){
        activo = false;
    }
}
