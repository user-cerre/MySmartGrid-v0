package storage;

import pcd.util.Ventana;

public class Bateria {
    private final double capacidadMaxKWh;
    private double nivelActualKWh;
    private boolean recargaNecesaria;
    Ventana v;

    public Bateria(double capacidadMaxKWh, double nivelInicialKWh) {
        if (capacidadMaxKWh <= 0) throw new IllegalArgumentException("capacidadMaxKWh debe ser > 0");
        if (nivelInicialKWh < 0) throw new IllegalArgumentException("nivelInicialKWh debe ser >= 0");
        this.capacidadMaxKWh = capacidadMaxKWh;
        this.nivelActualKWh = Math.min(nivelInicialKWh, capacidadMaxKWh);
        this.recargaNecesaria = false;
    }

    public double getCapacidadMaxKWh() { return capacidadMaxKWh; }
    public double getNivelActualKWh() { return nivelActualKWh; }

    public synchronized boolean puedeSuministrar(double kWh) {
        return kWh <= nivelActualKWh;
    }

    public synchronized double suministra(double kWh) {
        if (kWh <= 0) return 0.0;

        if(nivelActualKWh < kWh){
            solicitarRecarga();
        }
        double suministrado = Math.min(kWh, nivelActualKWh);
        nivelActualKWh -= suministrado;
        return suministrado;
    }

    public synchronized void carga(double kWh) {
        if (kWh <= 0) return;
        nivelActualKWh = Math.min(capacidadMaxKWh, nivelActualKWh + kWh);
        notifyAll();
    }
    
    public void setVentana (Ventana _v) {
    	v=_v;
    }

    @Override
    public String toString() {
        return "Bateria{capacidadMaxKWh=" + capacidadMaxKWh + ", nivelActualKWh=" + nivelActualKWh + "}";
    }
    public synchronized void solicitarRecarga(){
        recargaNecesaria = true;
        notifyAll();
    }
    public synchronized void esperarRecarga(double kWhNecesarios) throws InterruptedException{
        while (nivelActualKWh<kWhNecesarios) {
            wait();
        }
    }
    public synchronized void esperarSolicitudRecarga() throws InterruptedException{
        while (!recargaNecesaria) {
            wait();
        }
        recargaNecesaria = false;
    }
    
    public synchronized void despertarOperariosCarga() {
        notifyAll();
    }
}
