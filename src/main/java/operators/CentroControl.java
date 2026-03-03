package operators;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;

import energy.Consumo;
import energy.ConsumoPendiente;
import energy.ZonaEnergetica;

public class CentroControl {
    private ZonaEnergetica zona;
    private Queue<ConsumoPendiente> colaConsumo;
    int activeProducers;

    public CentroControl() {
        colaConsumo = new LinkedList<>();
        activeProducers = 0;
    }
    
    public void setZona(ZonaEnergetica zona) {
    	this.zona=zona;
    }

    public int getIdZona() { return zona.getIdZona(); }

    public String procesarConsumo(Consumo c) {

        zona.getVentana().traza(c.getIdConsumo() + " solicita: " + c.getTotalKWh(), Color.BLUE);

    	Random r = new Random();
        // Simulamos un tiempo de tramitación del consumo
        try {
			Thread.sleep(r.nextInt(200));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
            return "ERROR: Interrumpido durante tramitación";
		}
       
        double total = c.getTotalKWh();
        double suministrado;
        try {
            while (!zona.getBateria().puedeSuministrar(total)) {
                zona.getBateria().solicitarRecarga();
                zona.getBateria().esperarRecarga(total);
            }
            suministrado = zona.getBateria().suministra(total);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "ERROR: Interrumpido esperando recarga";
        }
        zona.getCuenta().anotaConsumo(suministrado);
        
        String resultado = (suministrado >= total)
            ? "OK: suministrados " + fmt(total) + " kWh"
            : "PARCIAL: suministrados " + fmt(suministrado) + " kWh (faltan " + fmt(total - suministrado) + " kWh)";
    	traza ("Trabajo completado para: "+c.getIdConsumo());
        return resultado;
    }
    
    private String fmt(double x) {
        return String.format(java.util.Locale.ROOT, "%.2f", x);
    }

    public void traza(String msg) {
        System.out.println("[CentroControl Z" + zona.getIdZona() + "] " + msg);
    }
    public synchronized void depositar (ConsumoPendiente cp){
        if ( cp != null){
            colaConsumo.add(cp);
            notify();
            traza("Consumo depositado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
        }else{
            throw new IllegalArgumentException ("Consumo pendiente no puede ser null");
        }
    }
    // private synchronized boolean shouldTerminate(){
    //     return colaConsumo.isEmpty() && activeProducers == 0;
    // }
    public synchronized ConsumoPendiente tomar() throws InterruptedException{
        while (colaConsumo.isEmpty()){
            if (activeProducers == 0){
                return null;  // No hay productores y cola vacía: terminar
            }
            wait();  // Esperar a que depositen o termine un productor
        }
        ConsumoPendiente cp = colaConsumo.poll();
        traza("Consumo tomado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
        return cp;
    }
    public synchronized void producerStarted(){
        activeProducers++;
        traza("Productor iniciado(activos :"+activeProducers+")");
    }
    public synchronized void producerFinished(){
        activeProducers--;
        notifyAll();
        traza("Productor finalizado (activos: "+activeProducers+")");
    }
}
