package sumatorioZonal;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import energy.Consumo;
import grpc.EstadisticasGrpc;
import grpc.EstadisticasOuterClass;
import grpc.EstadisticasOuterClass.MediaReply;
import grpc.SumatorioZonalGrpc;
import grpc.SumatorioZonalProto.ConsumoZonalRequest;
import grpc.SumatorioZonalProto.SumaReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class sumatorioZonalClient {
	ManagedChannel channel;
    Ventana v;
    
    public sumatorioZonalClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE SUMATORIO ZONAL");
    }
    
    public void iniciarCanal(String ip, int port) {
        // Construye y establece el canal de comunicación. Usa texto plano para la conexión.
    	channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
    }
    
    public void cerrarCanal() {
    	channel.shutdown(); 
    }
    
    public void consultarsumatorioZonal(List<Consumo> listaConsumos) {
    	v.traza("ENVIANDO FLUJO DE DATOS PARA SUMATORIO ZONAL...",Ventana.AZUL);
    	
    	CountDownLatch latch = new CountDownLatch(1);
    	
    	SumatorioZonalGrpc.SumatorioZonalStub stub = SumatorioZonalGrpc.newStub(channel);
    	
    	StreamObserver<SumaReply> responseObserver = new StreamObserver<SumaReply>() {
			
			@Override
			public void onNext(SumaReply value) {
				v.traza("RECIBIDO "+ value.getTotalKWh());
				//Metodo que me pide la defensa
				paralelSumatorioZonal(listaConsumos);
				
			}
			
			@Override
			public void onError(Throwable t) {
				latch.countDown();
				
			}
			
			@Override
			public void onCompleted() {
				latch.countDown();
				
			}
		};
		StreamObserver<ConsumoZonalRequest> streamEnvio = stub.calcularSuma(responseObserver);
		
		try {
			for (Consumo c: listaConsumos){
				if (c.getZona() == 1) {
					ConsumoZonalRequest req = ConsumoZonalRequest.newBuilder()
							.setIdConsumo(c.getIdConsumo())
							.setIdZona(c.getZona())
							.setKWh(c.getTotalKWh())
							.build();
					streamEnvio.onNext(req);
					//traza
				}
			}
			streamEnvio.onCompleted();
			latch.await(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			//traza de error
		}
    }
    public void paralelSumatorioZonal(List<Consumo> listaConsumos){
    	v.traza("EJECUTANDO PARALEL STREAM");
    	listaConsumos.parallelStream()
    	.filter(c->c.getTotalKWh() < 5)
    	.forEach(c->System.out.println("Consumo bajo: "+c.getIdConsumo()));
    }
    public static void main(String[] args) {
    	sumatorioZonalClient client = new sumatorioZonalClient();
    	List<Consumo> listaConsumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
    	client.iniciarCanal(Config.HOST_FILTRO, Config.PUERTO_DEFENSA);
    	client.consultarsumatorioZonal(listaConsumos);
    	client.cerrarCanal();
		
	}
	

}
