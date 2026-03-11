package storage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Eolica {
    private final double capacidadMaxKWh;
    private double nivelActualKWh;
    private final Lock lock = new ReentrantLock();
    private final Condition hayEnergia = lock.newCondition();
    private final Condition hayEspacio = lock.newCondition();
    public Eolica(double capacidadMaxKWh, double nivelInicialKWh){
        if (capacidadMaxKWh <= 0) throw new IllegalArgumentException("capacidadMaxKWh debe ser > 0");
        if (nivelInicialKWh < 0) throw new IllegalArgumentException("nivelInicialKWh debe ser >= 0");
        this.capacidadMaxKWh = capacidadMaxKWh;
        this.nivelActualKWh = Math.min(nivelInicialKWh, capacidadMaxKWh);
    }
    public double getCapacidadMaxKWh() { return capacidadMaxKWh; }
    public double getNivelActualKWh() {
        // proteger con lock
        lock.lock();
        try{
            return nivelActualKWh;
        }finally{
            lock.unlock();
        }
    }

    public boolean puedeSuministrar(double kWh) {
        lock.lock();
        try{
            return kWh <= nivelActualKWh;
        }finally{
            lock.unlock();
        }
    }
    public void depositar(double kWh) throws InterruptedException{
        // Bloquea si no hay espacio
        if(kWh<=0)return;
        lock.lock();
        try {
            while (nivelActualKWh +kWh > capacidadMaxKWh) {
                hayEspacio.await();
            }
            this.nivelActualKWh +=kWh;
            this.hayEnergia.signal();
        
        }finally{
            lock.unlock();
        }
    }
    public void retirar(double kWh) throws InterruptedException{
        // Bloquea si no hay energia suficiente
        if(kWh<=0)return;
        lock.lock();
        try {
            while (nivelActualKWh < kWh) {
                hayEnergia.await();
            }
            this.nivelActualKWh -= kWh;
            this.hayEspacio.signal();
        
        }finally{
            lock.unlock();

        }
    }
    public void esperarDisponibilidad(double kWh)throws InterruptedException{
        lock.lock();
        try {
            while (nivelActualKWh < kWh) {
                hayEnergia.await();
            }
        } finally {
            lock.unlock();
        }
    }
}