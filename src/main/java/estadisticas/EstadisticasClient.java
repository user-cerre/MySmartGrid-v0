package estadisticas;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import energy.Consumo;
import grpc.EstadisticasGrpc;
import grpc.EstadisticasOuterClass.DatoRequest;
import grpc.EstadisticasOuterClass.MediaReply;

public class EstadisticasClient {
	ManagedChannel channel;
    Ventana v;
    
    public EstadisticasClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE ESTADÍSTICAS");
    }
    
    public void iniciarCanal(String ip, int port) {
        // Construye y establece el canal de comunicación. Usa texto plano para la conexión.
    	channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
    }
    
    public void cerrarCanal() {
    	channel.shutdown(); 
    }
    
    public void consultarEstadísticas(List<Consumo> listaConsumos) {
    	v.traza("ENVIANDO FLUJO DE DATOS PARA MEDIA...",Ventana.AZUL);
    	
    	CountDownLatch latch = new CountDownLatch(1);
    	
    	EstadisticasGrpc.EstadisticasStub stub = EstadisticasGrpc.newStub(channel);
    	
    	StreamObserver<MediaReply> responseObserver = new StreamObserver<MediaReply>() {
			
			@Override
			public void onNext(MediaReply value) {
				v.traza("MEDIA RECIBIDA: " + value.getMediaGlobal(), Ventana.VERDE);
	            v.traza("Total procesados por servidor: " + value.getTotalProcesados(), Ventana.GRIS);
	        }

	        @Override
	        public void onError(Throwable t) {
	            v.traza("Error en respuesta: " + t.getMessage(), Ventana.ROJO);
	            latch.countDown();
	        }

	        @Override
	        public void onCompleted() {
	            v.traza("Servidor cerró el canal de respuesta.", Ventana.GRIS);
	            latch.countDown();
	        }
	    };
	    
	    StreamObserver<DatoRequest> streamEnvio = stub.calcularMedia(responseObserver);
	    
	    try {
			for(Consumo c: listaConsumos) {
				DatoRequest req = DatoRequest.newBuilder()
						.setKwh((int)c.getTotalKWh())
						.build();
				streamEnvio.onNext(req);
				v.traza("Enviado: " + c.getTotalKWh() + " kWh", Ventana.NEGRO);
			}
			
			streamEnvio.onCompleted();
			
			latch.await(1,TimeUnit.MINUTES);
		} catch (Exception e) {
			v.traza("ERROR DURANTE EL ENVÍO",Ventana.ROJO);
		}
    	
    }
    public static void main(String[] args) {
    	EstadisticasClient client = new EstadisticasClient();
    	
    	client.iniciarCanal(Config.HOST_FILTRO, Config.PUERTO_DEFENSA);
    	List<Consumo> listaConsumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
    	client.consultarEstadísticas(listaConsumos);
    	client.cerrarCanal();
		
	}
}
