package operators;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
    private LinkedBlockingQueue<ConsumoPendiente> colaConsumo;
    private Semaphore semArranque;
    private Semaphore semConsumos;
    private AtomicInteger activeProducers;
    private CyclicBarrier barrera;
    Color color = Color.BLUE;

    public CentroControl() {
        colaConsumo = new LinkedBlockingQueue<>();
        activeProducers = new AtomicInteger();
        semArranque = new Semaphore(0);
        semConsumos = new Semaphore(Config.MAX_CONSUMOS_EN_TRAMITACION);
        barrera = new CyclicBarrier(Config.NUM_OPERADORES_POR_ZONA);
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
            zona.getCuenta().anotaConsumo(suministradoPrincipal,suministradoEolica,suministradoSolar);
            traza("Trabajo completado para: " + c.getIdConsumo());
            return "OK: suministrados "+ totalSuministrado +"kWh";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "ERROR: Interrumpido durante tramitación";
        }
    }

    public void traza(String msg) {
        System.out.println("[CentroControl Z" + zona.getIdZona() + "] " + msg);
    }

    public void depositar(ConsumoPendiente cp) {
        if (cp != null) {
            colaConsumo.offer(cp);
            traza("Consumo depositado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
        } else {
            throw new IllegalArgumentException("Consumo pendiente no puede ser null");
        }
    }

    public ConsumoPendiente tomar() throws InterruptedException {
        ConsumoPendiente cp = null;
        while (cp == null) {
            cp = colaConsumo.poll(200, TimeUnit.MILLISECONDS);
            if (cp != null) {
                traza("Consumo tomado: " + cp.getConsumo().getIdConsumo() + " (cola: " + colaConsumo.size() + ")");
                return cp; // No hay productores y cola vacía: terminar
            }
            if(activeProducers.get() == 0){
                return null;
            }
            
        }
        return cp;
    }

    public void producerStarted() {
        traza("Productor iniciado(activos :" + activeProducers.incrementAndGet() + ")");
    }

    public void producerFinished() {
        traza("Productor finalizado (activos: " + activeProducers.decrementAndGet() + ")");
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

    public void esperarArranqueBarreras() throws InterruptedException {
        try {
            barrera.await();
        } catch (BrokenBarrierException e ) {
            Thread.currentThread().interrupt();
        }
    }
}
