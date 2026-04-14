package main;

import energy.Consumo;
import energy.ConsumoRunnable;
import energy.RedEnergetica;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Observable;
import operators.OperarioRed;
import storage.Bateria;
import operators.CentroControl;
import operators.OperarioCarga;
import operators.RobotEolica;
import operators.RobotSolar;

import java.util.ArrayList;
import java.util.List;

public class MySmartGrid {

    public static void main(String[] args) {
        RedEnergetica red = new RedEnergetica(
                Config.NUM_ZONAS,
                Config.CAPACIDAD_BATERIA,
                Config.NIVEL_INICIAL_BATERIA);
        //Lectura de consumos 
        List<Consumo> consumos;
        Observable<Consumo> consumosObs = Consumo.consumosDesdeFicheroObservable(Config.FICHERO_CONSUMOS);
        if(Config.CONSUMO_DESDE_FICHERO == 1){
            consumos = consumosObs.toList().blockingGet();
        }else{
            consumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
        }
        System.out.println("Leidos " + consumos.size() + " consumos desde " + Config.FICHERO_CONSUMOS);
        double totalSolicitadoVentanas = consumos.stream().mapToDouble(Consumo::getTotalKWh).sum();
        System.out.println("Total solicitado (lineas 'solicita' en ventanas): "
            + String.format(java.util.Locale.ROOT, "%.2f", totalSolicitadoVentanas) + " kWh");
        //TODO: Implementar aqui los observables
        Observable <Consumo> fuente = Observable.fromIterable(consumos);
        fuente
        .subscribeOn(Schedulers.computation())
        .map(Consumo::getTotalKWh)
        .reduce(0.0, Double::sum)
        .subscribe(total-> System.out.println("SUMA RXJAVA: "+ Thread.currentThread().getName()+ " |Total:"+ total), Throwable::printStackTrace);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }
        
        fuente
        .subscribeOn(Schedulers.computation())
        .filter(mayores-> mayores.getTotalKWh() > 20)
        .subscribe(mayores-> System.out.println("MAYORES QUE 20 EN RXJAVA: "+Thread.currentThread().getName()+"|ID:"+ mayores.getIdConsumo()+"|Consumo: "+ mayores.getTotalKWh()), Throwable::printStackTrace);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }

        List<RobotEolica> listaEolicas = new ArrayList<>();
        List<Thread> threadsEolica = new ArrayList<>();
        for (int z = 0 ; z< Config.NUM_ZONAS; z++) {
            RobotEolica robotEolica = new RobotEolica(red.getZona(z).getEolica(), z);
            Thread robotThread = new Thread(robotEolica);
            threadsEolica.add(robotThread);
            listaEolicas.add(robotEolica);
            robotThread.setDaemon(true);//
            robotThread.start();
        }

        List<RobotSolar> listaSolares = new ArrayList<>();
        List<Thread> threadsSolar = new ArrayList<>();
        for (int z = 0 ; z< Config.NUM_ZONAS; z++) {
            RobotSolar robotSolar = new RobotSolar(red.getZona(z).getSolar(), z);
            Thread robotThread = new Thread(robotSolar);
            threadsSolar.add(robotThread);
            listaSolares.add(robotSolar);
            robotThread.setDaemon(true);//
            robotThread.start();
        }
        // LANZAMIENTO DE PRODUCTORES (primero para que activeProducers > 0)
        List<Thread> threadList = new ArrayList<Thread>();
        Observable<String> lanzamientos = Observable.empty();
        if(Config.CONSUMO_DESDE_FICHERO == 0){
            for (Consumo c : consumos) {
                ConsumoRunnable c1 = new ConsumoRunnable(c, red);
                Thread t1 = new Thread(c1);
                threadList.add(t1);
                t1.start();
            }
        }else{
           lanzamientos = consumosObs
            .flatMap(c ->
                Observable.fromCallable(() ->{
                    ConsumoRunnable c1 = new ConsumoRunnable(c, red);
                    Thread t1 = new Thread(c1);
                    threadList.add(t1);
                    t1.start();
                    return c.getIdConsumo();
                }).subscribeOn(Schedulers.computation())
            );
        }
        if(Config.CONSUMO_DESDE_FICHERO == 1){
            lanzamientos.blockingSubscribe();
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
                threadCarga.setDaemon(true);//
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
            cc.liberarOperarios(Config.NUM_OPERADORES_POR_ZONA);
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
