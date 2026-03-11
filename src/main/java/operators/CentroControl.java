package operators;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.awt.Color;

import energy.Consumo;
import energy.ConsumoPendiente;
import energy.ZonaEnergetica;
import storage.Bateria;
import storage.Eolica;
import storage.Solar;
import energy.Demanda;
import main.Config;

public class CentroControl {
    private ZonaEnergetica zona;
    private Queue<ConsumoPendiente> colaConsumo;
    private Semaphore semArranque;
    private Semaphore semConsumos;
    int activeProducers;
    Color color = Color.BLUE;

    public CentroControl() {
        colaConsumo = new LinkedList<>();
        activeProducers = 0;
        semArranque = new Semaphore(0);
        semConsumos = new Semaphore(Config.MAX_CONSUMOS_EN_TRAMITACION);
    }

    public void setZona(ZonaEnergetica zona) {
        this.zona = zona;
    }

    public int getIdZona() {
        return zona.getIdZona();
    }

    public String procesarConsumo(Consumo c) {

        zona.getVentana().traza(c.getIdConsumo() + " solicita: " + c.getTotalKWh(), color);
        double kWhPrincipal = 0;
        double kWhSolar = 0;
        double kWhEolica = 0;
        Bateria bateria = zona.getBateria();
        Eolica eolica = zona.getEolica();
        Solar solar = zona.getSolar();
        for (Demanda d: c.getDemandas()) {
            String tipo = d.getIdTipo();
            double kWh = d.getKWh();
            if(tipo.equals("RAPIDA")){
                kWhPrincipal += kWh;
            }else if(tipo.equals("SOLAR")){
                kWhSolar += kWh;
            }else if(tipo.equals("EOLICA")){
                kWhEolica += kWh;
            }else{
                return "ERROR tipo demanda desconocido";
            }     
        }
        try {
            if(kWhPrincipal > 0){
                while (!bateria.puedeSuministrar(kWhPrincipal)) {
                    bateria.solicitarRecarga();
                    bateria.esperarRecarga(kWhPrincipal);
                    
                }
            }
            if(kWhEolica > 0){
                eolica.esperarDisponibilidad(kWhEolica);
            }
            if(kWhSolar > 0){
                solar.esperarDisponibilidad(kWhSolar);
            }
            double suministradoPrincipal = 0;
            double suministradoSolar = 0;
            double suministradoEolica = 0;

            if(kWhPrincipal > 0){
                suministradoPrincipal = bateria.suministra(kWhPrincipal);
            }
            if(kWhEolica > 0){
                eolica.retirar(kWhEolica);
                suministradoEolica = kWhEolica;
            }
            if(kWhSolar > 0){
                solar.retirar(kWhSolar);
                suministradoSolar = kWhSolar;
            }
            double totalSuministrado = suministradoPrincipal + suministradoEolica + suministradoSolar;
            if(totalSuministrado != (kWhPrincipal +kWhEolica +kWhSolar)){
                return "ERROR: inconsistencia";
            }
            zona.getCuenta().anotaConsumo(totalSuministrado);
            traza("Trabajo completado para: " + c.getIdConsumo());
            return "OK: suministrados "+ totalSuministrado +"kWh";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "ERROR: Interrumpido durante tramitación";
        }
        

        // String resultado = (suministrado >= total)
        //         ? "OK: suministrados " + fmt(total) + " kWh"
        //         : "PARCIAL: suministrados " + fmt(suministrado) + " kWh (faltan " + fmt(total - suministrado) + " kWh)";
        
        // return resultado;
    }

    private String fmt(double x) {
        return String.format(java.util.Locale.ROOT, "%.2f", x);
    }

    public void traza(String msg) {
        System.out.println("[CentroControl Z" + zona.getIdZona() + "] " + msg);
    }

    public synchronized void depositar(ConsumoPendiente cp) {
        if (cp != null) {
            colaConsumo.add(cp);
            notify();
            traza("Consumo depositado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
        } else {
            throw new IllegalArgumentException("Consumo pendiente no puede ser null");
        }
    }

    // private synchronized boolean shouldTerminate(){
    // return colaConsumo.isEmpty() && activeProducers == 0;
    // }
    public synchronized ConsumoPendiente tomar() throws InterruptedException {
        while (colaConsumo.isEmpty()) {
            if (activeProducers == 0) {
                return null; // No hay productores y cola vacía: terminar
            }
            wait(); // Esperar a que depositen o termine un productor
        }
        ConsumoPendiente cp = colaConsumo.poll();
        traza("Consumo tomado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
        return cp;
    }

    public synchronized void producerStarted() {
        activeProducers++;
        traza("Productor iniciado(activos :" + activeProducers + ")");
    }

    public synchronized void producerFinished() {
        activeProducers--;
        notifyAll();
        traza("Productor finalizado (activos: " + activeProducers + ")");
    }
    public void liberarOperarios(int n){
        semArranque.release(n);
    }
    public void esperarArranque() throws InterruptedException {
        semArranque.acquire();
    }
    
    public void adquirirSlotConsumo() throws InterruptedException {
        semConsumos.acquire();
    }
    
    public void liberarSlotConsumo() {
        semConsumos.release();
    }
}
