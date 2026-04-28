
package operators;

import main.Config;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import energy.Consumo;
import energy.ConsumoPendiente;
import energy.Demanda;
import grpc.MonitorizacionGrpc;
import grpc.MonitorizacionGrpc.MonitorizacionStub;
import grpc.MonitorizacionProto.ConsumoRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OperarioRed implements Runnable {

    private CentroControl centro;
    private int idOperario;
    private CountDownLatch arranqueRedLatch;
    private final ManagedChannel monitorizacionChannel;
    private final MonitorizacionStub monitorizacionStub;//MonitorizacionBlockingStub
    
    public OperarioRed(CentroControl _c, int _i, CountDownLatch latch){
        this.centro = _c;
        this.idOperario = _i;
        this.arranqueRedLatch = Objects.requireNonNull(latch);
        this.monitorizacionChannel = ManagedChannelBuilder
        		.forAddress(Config.HOST_MONITORIZACION, Config.PUERTO_MONITORIZACION).usePlaintext().build();
        this.monitorizacionStub = MonitorizacionGrpc.newStub(monitorizacionChannel);
    }
    @Override
    public void run(){
        try{
            if(Config.MODO_DE_ARRANQUE_OPERARIOS == 0){
                centro.esperarArranque();
            }else if( Config.MODO_DE_ARRANQUE_OPERARIOS == 1){
                centro.esperarArranqueBarreras();
            }
            arranqueRedLatch.countDown();
            while (true) {
                ConsumoPendiente cp = centro.tomar();
                if (cp == null){
                    System.out.println("[Operario " + idOperario + " Z" + centro.getIdZona() + "] Finalizando");
                    break;
                }
                centro.adquirirSlotConsumo();
                try{
                	Consumo consumo = cp.getConsumo();
                	String resultado = centro.procesarConsumo(consumo);

                	boolean solar = false;
                	for (Demanda d : consumo.getDemandas()) {
                	    if ("SOLAR".equalsIgnoreCase(d.getIdTipo())) {
                	        solar = true;
                	        break;
                	    }
                	}

                	ConsumoRequest req = ConsumoRequest.newBuilder()
                	.setIdConsumo(consumo.getIdConsumo())
                	.setIdZona(consumo.getZona())
                	.setKWh(consumo.getTotalKWh())
                	.setDireccion(consumo.getDireccion())
                	.setSolar(solar)
                	.build();

//                	monitorizacionStub.anotarConsumo(req);
                	monitorizacionStub.anotarConsumo(req, new io.grpc.stub.StreamObserver<grpc.MonitorizacionProto.ConsumoReply>() {
                	    @Override
                	    public void onNext(grpc.MonitorizacionProto.ConsumoReply value) {
                	        // opcional: manejar ack
                	    }
                	    @Override
                	    public void onError(Throwable t) {
                	        System.err.println("[Operario " + idOperario + "] Error anotarConsumo: " + t.getMessage());
                	    }
                	    @Override
                	    public void onCompleted() {
                	        // no-op
                	    }
                	});

                    cp.markProcessed(resultado);
                }finally{
                    centro.liberarSlotConsumo();
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.err.println("[Operario " + idOperario + "] Interrumpido");
        } finally {
            monitorizacionChannel.shutdown();
        }
    }
}
