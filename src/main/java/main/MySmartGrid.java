package main;

import energy.Consumo;
import energy.ConsumoRunnable;
import energy.RedEnergetica;
import operators.OperarioRed;
import storage.Bateria;
import operators.CentroControl;
import operators.OperarioCarga;

import java.util.ArrayList;
import java.util.List;

public class MySmartGrid {

    public static void main(String[] args) {
        RedEnergetica red = new RedEnergetica(
                Config.NUM_ZONAS,
                Config.CAPACIDAD_BATERIA,
                Config.NIVEL_INICIAL_BATERIA);

        List<Consumo> consumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
        System.out.println("Leidos " + consumos.size() + " consumos desde " + Config.FICHERO_CONSUMOS);
        
        // LANZAMIENTO DE PRODUCTORES (primero para que activeProducers > 0)
        List<Thread> threadList = new ArrayList<Thread>();
        for (Consumo c : consumos) {
            ConsumoRunnable c1 = new ConsumoRunnable(c, red);
            Thread t1 = new Thread(c1);
            threadList.add(t1);
            t1.start();
        }
        
        // LANZAMIENTO DE OPERARIOS DE CARGA
        List<OperarioCarga> listaOperariosCarga = new ArrayList<>();
        List<Thread> threadsCarga = new ArrayList<>();
        for (int z = 0; z < Config.NUM_ZONAS; z++) {
            Bateria bateria = red.getZona(z).getBateria();
            for (int op = 0; op < Config.NUM_OPERARIOS_CARGA_POR_ZONA; op++) {
                OperarioCarga operarioCarga = new OperarioCarga(bateria, op, Config.CANTIDAD_RECARGA_POR_CICLO);
                listaOperariosCarga.add(operarioCarga);
                Thread threadCarga = new Thread(operarioCarga);
                threadsCarga.add(threadCarga);
                threadCarga.start();
            }
        }
        
        //LANZAMIENTO DE OPERARIOS
        List<Thread> operarios = new ArrayList<>();
        for (int i = 0; i< Config.NUM_ZONAS; i++){
            CentroControl cc = red.getCentroControl(i);
            for (int j = 0; j< Config.NUM_OPERADORES_POR_ZONA; j++){
                Thread operario = new Thread(new OperarioRed(cc, j));
                operarios.add(operario);
                operario.start();
            }
        }
        
        // Esperar productores
        for (Thread t : threadList) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Esperar operarios
        for (Thread op : operarios) {
            try {
                op.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Detener operarios de carga
        for (OperarioCarga opCarga : listaOperariosCarga) {
            opCarga.detener();
        }
        
        // Despertar operarios de carga esperando en bateria
        for (int z = 0; z < Config.NUM_ZONAS; z++) {
            red.getZona(z).getBateria().despertarOperariosCarga();
        }

        // Esperar a que terminen, con timeout para evitar bloqueo indefinido
        for (Thread threadCarga : threadsCarga) {
            try {
                threadCarga.join(100); // waait max 100ms
                if (threadCarga.isAlive()) {
                    threadCarga.interrupt();  // Interrupt if still running
                    threadCarga.join(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Auditoría final
        red.imprimeAuditoria();

        //Versión 4: streams
        System.out.println("Consumos con kwh < 5:");
        consumos.parallelStream()
        .filter(d->d.getTotalKWh() < 5)
        .map(a->a.getIdConsumo())
        .forEachOrdered(d->System.out.println(d));

        System.out.println("Consumo más alto:");

        consumos.parallelStream()               
        .mapToDouble(c -> c.getTotalKWh())
        .max()
        .ifPresent(max -> System.out.println(max));
    
        // Prueba 1: Sagitario, 24 (No debería imprimir nada)
        if (consumos.parallelStream()
        .anyMatch(c -> c.getDireccion().equals("Sagitario, 24"))) {
            System.out.println("Encontrado");
        }

        // Prueba 2: Berna, 11 (Debería imprimir "Encontrado")
        if (consumos.parallelStream()
        .anyMatch(c -> c.getDireccion().equals("Berna, 11"))) {
            System.out.println("Encontrado");
        }
    }
}
