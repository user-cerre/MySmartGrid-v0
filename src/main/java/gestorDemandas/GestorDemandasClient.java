package gestorDemandas;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import energy.Consumo;
import grpc.FiltradoGrpc;
import grpc.GestorDemandaGrpc;
import grpc.GestorDemandaGrpc.GestorDemandaBlockingStub;
import grpc.GestorDemandaGrpc.GestorDemandaStub;
import grpc.GestorDemandaOuterClass.ClasificacionReply;
import grpc.GestorDemandaOuterClass.ConsumoRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class GestorDemandasClient {
	ManagedChannel channel;
	Ventana v;
	public GestorDemandasClient() {//MISMO MÉTODO SIEMPRE
		v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE GESTOR DE DEMANDAS");
	}
	public void iniciarCanal(String ip , int port) {//MISMO MÉTODO SIEMPRE
		 channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		
		v.traza("Canal abierto con ip: " + ip+ ":" + port, Ventana.VERDE);
	}
	public void cerrarCanal() {//MISMO MÉTODO SIEMPRE
		if(channel != null) {
			channel.shutdown();
			v.traza("Canal cerrado", Ventana.GRIS);
		}
	}
	public void consultarGestorDemandas(List<Consumo> listaConsumos) {
		GestorDemandaBlockingStub bStub =GestorDemandaGrpc.newBlockingStub(channel);//UNARY Y SERVER STREAMING
		
		GestorDemandaStub stub = GestorDemandaGrpc.newStub(channel);//CLIENT STREAMING Y BIDIRECCIONAL
		CountDownLatch latch = new CountDownLatch(1);
		
		StreamObserver<ClasificacionReply> responseObserver = new StreamObserver<ClasificacionReply>() {
			
			@Override
			public void onNext(ClasificacionReply value) {
				v.traza("DEMANDA RECIBIDA: "+ value.getCategoria()+ " EN CONSUMO: "+ value.getIdConsumo(),Ventana.AZUL);	
				
			}
			
			@Override
			public void onError(Throwable t) {
				latch.countDown();
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("FLUJO DE INSTRUCCIONES DEL CLIENTE TERMINADO",Ventana.GRIS);
				latch.countDown();
				
			}
		};	
		StreamObserver<ConsumoRequest> reqObs =
				  stub.clasificarDemanda(responseObserver);
		try {
			for(Consumo c : listaConsumos) {
				reqObs.onNext(ConsumoRequest.newBuilder().setIdConsumo(c.getIdConsumo())
						.setIdZona(c.getZona())
						.setTotalKWh(c.getTotalKWh())
						.build());
			}
			reqObs.onCompleted();
			latch.await(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			v.traza("ERROR EN "+e.getMessage(),Ventana.ROJO);
		}
		
	}
	public static void main(String[] args) {
		GestorDemandasClient client = new GestorDemandasClient();
		client.iniciarCanal(null, 0);
		client.consultarGestorDemandas(null);
		client.cerrarCanal();
		
	}
	
}
