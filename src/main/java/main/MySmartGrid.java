package main;

import energy.Consumo;
import energy.ConsumoRunnable;
import energy.ConsumosMayoresTask;
import energy.Demanda;
import energy.MaxConsumoTask;
import energy.RedEnergetica;
import energy.Task;
import grpc.*;
import grpc.PreciosGrpc.PreciosStub;
import grpc.PreciosProto.DemandaRequest;
import grpc.PreciosProto.PreciosReply;
import grpc.PreciosProto.PreciosRequest;
import grpc.PreciosProto.PreciosRequest.Builder;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.reactivex.rxjava3.core.Observable;
import operators.OperarioRed;
import storage.Bateria;
import operators.CentroControl;
import operators.OperarioCarga;
import operators.RobotEolica;
import operators.RobotSolar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class MySmartGrid {

    public static void streamsVersion4(List<Consumo> consumos){
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

    public static void lanzamientoDeRobots(RedEnergetica red){
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
    }

    public static void lanzamientoDeOperarios(RedEnergetica red, List<Thread> operarios, CountDownLatch latch){
        // LANZAMIENTO DE OPERARIOS DE CARGA
        List<OperarioCarga> listaOperariosCarga = new ArrayList<>();
        List<Thread> threadsCarga = new ArrayList<>();
        for (int z = 0; z < Config.NUM_ZONAS; z++) {
            Bateria bateria = red.getZona(z).getBateria();
            for (int op = 0; op < Config.NUM_OPERARIOS_CARGA_POR_ZONA; op++) {
                OperarioCarga operarioCarga = new OperarioCarga(bateria, op, Config.CANTIDAD_RECARGA_POR_CICLO, latch);
                listaOperariosCarga.add(operarioCarga);
                Thread threadCarga = new Thread(operarioCarga);
                threadsCarga.add(threadCarga);
                threadCarga.setDaemon(true);//
                threadCarga.start();
            }
        }
        
        //LANZAMIENTO DE OPERARIOS
        for (int i = 0; i< Config.NUM_ZONAS; i++){
            CentroControl cc = red.getCentroControl(i);
            for (int j = 0; j< Config.NUM_OPERADORES_POR_ZONA; j++){
                Thread operario = new Thread(new OperarioRed(cc, j, latch));
                operarios.add(operario);
                operario.start();
            }
            cc.liberarOperarios(Config.NUM_OPERADORES_POR_ZONA);
        }
    }
    public static void espera(List<Thread> threadList, List<Thread> operarios){
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
    }

    public static void observablesVersion7(List<Consumo> consumos){
        Observable <Consumo> fuente = Observable.fromIterable(consumos);
        fuente
        .subscribeOn(Schedulers.computation())
        .map(Consumo::getTotalKWh)
        .reduce(0.0, (a, b) -> a + b)
        .subscribe(total-> System.out.println("SUMA RXJAVA: "+ Thread.currentThread().getName()+ " |Total:"+ total), Throwable::printStackTrace);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
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
    }
    public static void ForkJoinVersion8(List<Consumo>consumos){
        ForkJoinPool pool = new ForkJoinPool();
        try {
            ConsumosMayoresTask task = new ConsumosMayoresTask(consumos, 0, consumos.size());
            List<Consumo> mayores20 = pool.invoke(task);

            System.out.println("Consumos > 20kWh (ForkJoin): " + mayores20.size());
            for (Consumo c : mayores20) {
                System.out.println(c.getIdConsumo() + " | " + c.getTotalKWh());
         }
        } finally {
         pool.shutdown();
         pool.close();
        }
    }

    public  static void main(String[] args) {
        RedEnergetica red = new RedEnergetica(
                Config.NUM_ZONAS,
                Config.CAPACIDAD_BATERIA,
                Config.NIVEL_INICIAL_BATERIA);
        //Lectura de consumos 
        List<Consumo> consumos;
        Observable<Consumo> consumosObs = Consumo.consumosDesdeFicheroObservable(Config.FICHERO_CONSUMOS);
        ExecutorService executorProductores = null;
        CountDownLatch latch = new CountDownLatch(Config.NUM_ZONAS*Config.NUM_OPERADORES_POR_ZONA);
        /**
         * Tenemos que hacer un switch con Config.FICHERO_CONSUMOS
         * Si es 0:
         *  -Se usa la lista
         *  -Se sigue la ejecución tal cual está ahora
         * 
         * Si es 1:
         *  -Se usa el Observable
         *  -Tal cual está ahora pero metiendo solo lo necesario en su case
         * 
         * Si es 2:
         *  -Usamos Executor
         *  -Lanzamos los consumos con ExecutorService con un pool fijo de tamaño
         */
        switch (Config.CONSUMO_DESDE_FICHERO) {
            case 0://Se usa una lista
                consumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
                break;
        
            case 1://Se usa el Observable
                consumos = consumosObs.toList().blockingGet();
                break;
            default:
                consumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
                break;//! Esto es solo para que no de error de que puede que no esté inicializado el valor consumos
        }

        System.out.println("Leidos " + consumos.size() + " consumos desde " + Config.FICHERO_CONSUMOS);
        double totalSolicitadoVentanas = consumos.stream().mapToDouble(Consumo::getTotalKWh).sum();
        System.out.println("Total solicitado (lineas 'solicita' en ventanas): "
            + String.format(java.util.Locale.ROOT, "%.2f", totalSolicitadoVentanas) + " kWh");
        
        //Estos son los observables pedidos en la versión 7
        observablesVersion7(consumos);
        //Lanzamiento de robots
        lanzamientoDeRobots(red);
        // LANZAMIENTO DE PRODUCTORES (primero para que activeProducers > 0)
        List<Thread> threadList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> operarios = new ArrayList<>();
        Observable<String> lanzamientos = Observable.empty();

        switch (Config.CONSUMO_DESDE_FICHERO) {
            case 0://Lista
                for (Consumo c : consumos) {
                    ConsumoRunnable c1 = new ConsumoRunnable(c, red);
                    Thread t1 = new Thread(c1);
                    threadList.add(t1);
                    t1.start();
                }
                lanzamientoDeOperarios(red, operarios, latch);
                break;
        
            case 1://Observable
                lanzamientos = Observable.fromIterable(consumos)
                .flatMap(c ->
                    Observable.fromCallable(() ->{
                    ConsumoRunnable c1 = new ConsumoRunnable(c, red);
                    Thread t1 = new Thread(c1);
                    threadList.add(t1);
                    t1.start();
                    return c.getIdConsumo();
                }).subscribeOn(Schedulers.computation())
                );
                lanzamientoDeOperarios(red, operarios, latch);
                lanzamientos.blockingSubscribe();
                
                break;
            case 2://Executor
                executorProductores = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                for (Consumo c : consumos){
                    Task task = new Task(c, red);
                    executorProductores.execute(task);
                }
                lanzamientoDeOperarios(red, operarios, latch);
                
                break;
        }
 
        espera(threadList, operarios);
        
        if(executorProductores != null){
            executorProductores.shutdown();
            try {
                if(!executorProductores.awaitTermination(60, TimeUnit.SECONDS)){
                    executorProductores.shutdownNow();
                    if(!executorProductores.awaitTermination(60, TimeUnit.SECONDS)){
                        System.err.println("El pool no se ha cerrado");
                    }
                }
            } catch (InterruptedException e) {
                executorProductores.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        //Consumo más alto con callable
        ExecutorService maxExecutor = Executors.newSingleThreadExecutor();
        try {
            Future<Consumo> futuroMax = maxExecutor.submit(new MaxConsumoTask(consumos));
            Consumo maxConsumo = futuroMax.get();

            if (maxConsumo != null) {
                System.out.println("Consumo más alto (Callable): " + maxConsumo.getIdConsumo() + " | kWh=" + maxConsumo.getTotalKWh());
            } else {
                System.out.println("No hay consumos para calcular máximo.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrumpido calculando consumo máximo.");
        } catch (ExecutionException e) {
            System.err.println("Error en MaxConsumoTask: " + e.getCause());
        } finally {
            maxExecutor.shutdown();
        }
        ForkJoinVersion8(consumos);

        //Versión 4: streams
        streamsVersion4(consumos);

        // Auditoría final
        red.imprimeAuditoria();
//        ------------------------------------------------------------------------------------------------------------------------------------------
        //TODO: Aquí necesito integrar el cliente de Precios
        ManagedChannel preciosChannel = ManagedChannelBuilder
                .forAddress(Config.HOST_PRECIOS, Config.PUERTO_PRECIOS)
                .usePlaintext()
                .build();

        PreciosStub preciosStub = PreciosGrpc.newStub(preciosChannel);
        CountDownLatch preciosLatch = new CountDownLatch(1);
        AtomicReference<Throwable> preciosError = new AtomicReference<>(null);

        StreamObserver<PreciosReply> preciosResponseObserver = new StreamObserver<PreciosReply>() {
            @Override
            public void onNext(PreciosReply value) {
                System.out.println("PRECIO -> " + value.getIdConsumo() + " : " + value.getPrecio());
            }

            @Override
            public void onError(Throwable t) {
                preciosError.set(t);
                System.err.println("Error en calcularPrecios: " + t.getMessage());
                preciosLatch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Calculo de precios completado");
                preciosLatch.countDown();
            }
        };

        StreamObserver<PreciosRequest> preciosRequestObserver = preciosStub.calcularPrecios(preciosResponseObserver);

        for (Consumo c : consumos) {
            Builder reqBuilder = PreciosRequest.newBuilder()
                    .setIdConsumo(c.getIdConsumo())
                    .setIdZona(c.getZona());

            for (Demanda d : c.getDemandas()) {
                DemandaRequest demandaReq = DemandaRequest.newBuilder()
                        .setIdTipo(d.getIdTipo())
                        .setKWh(d.getKWh())
                        .setPrioridad(d.getPrioridad())
                        .build();
                reqBuilder.addDemandas(demandaReq);
            }

            preciosRequestObserver.onNext(reqBuilder.build());
        }

        preciosRequestObserver.onCompleted();

        try {
            preciosLatch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrumpido esperando precios");
        } finally {
            preciosChannel.shutdown();
        }

        if (preciosError.get() != null) {
            System.err.println("Hubo error en el servicio de precios");
        }
        
    }
}
