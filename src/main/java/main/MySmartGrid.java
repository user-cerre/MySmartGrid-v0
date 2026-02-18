package main;

import energy.Consumo;
import energy.ConsumoRunnable;
import energy.RedEnergetica;

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

        // Tramitamos los consumos de manera secuencial
        // String resultado;
        List<Thread> threadList = new ArrayList<Thread>();
        for (Consumo c : consumos) {
            ConsumoRunnable c1 = new ConsumoRunnable(c, red);
            Thread t1 = new Thread(c1);
            threadList.add(t1);
            t1.start();

        }
        for (int i = 0; i < consumos.size(); i++) {
            try {
                threadList.get(i).join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // Auditoría final después de que todos los consumos se hayan procesado
        red.imprimeAuditoria();
    }

}
