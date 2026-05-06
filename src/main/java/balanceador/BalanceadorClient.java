package balanceador;

import grpc.BalanceadorGrpc;
import grpc.BalanceadorGrpc.BalanceadorStub;
import grpc.BalanceadorOuterClass.EstadoBateria;
import grpc.BalanceadorOuterClass.InstruccionCarga;
import grpc.FiltradoGrpc;
import grpc.FiltradoGrpc.FiltradoBlockingStub;
import grpc.FiltradoGrpc.FiltradoStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;
import storage.Bateria;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import energy.Consumo;

public class BalanceadorClient {
	ManagedChannel channel;
	FiltradoBlockingStub filtradoBlockingStub;
	FiltradoStub filtradoStub;
	Ventana v;
	
	public BalanceadorClient() {
		v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE FILTRO");
	}
	public void iniciarCanal(String ip , int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		filtradoBlockingStub = FiltradoGrpc.newBlockingStub(channel);//unary y server streaming
		filtradoStub = FiltradoGrpc.newStub(channel);//server streaming y bidireccional
		v.traza("Canal abierto con ip: " + ip+ ":" + port, Ventana.VERDE);
	}
	public void cerrarCanal() {
		if(channel != null) {
			channel.shutdown();
			v.traza("Canal cerrado", Ventana.GRIS);
		}
	}
	public void ajustarCarga(List<Bateria> baterias) {
		CountDownLatch latch = new CountDownLatch(1);
		BalanceadorGrpc.BalanceadorStub stub = BalanceadorGrpc.newStub(channel);
		int idBateria = 0;
		
		StreamObserver<InstruccionCarga> responseObserver = new StreamObserver<InstruccionCarga>() {
			
			@Override
			public void onNext(InstruccionCarga value) {
				v.traza("INSTRUCCION RECIBIDA: "+ value.getAccion(), Ventana.AZUL);				
			}
			
			@Override
			public void onError(Throwable t) {
				latch.countDown();
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("FLUJO DE INSTRUCCIONES DEL SERVIDOR TERMINADO",Ventana.GRIS);
				latch.countDown();
			}
		};
		StreamObserver<EstadoBateria> requestObserver = stub.ajustarCarga(responseObserver);
		
		try {
			for(Bateria b: baterias) {
				EstadoBateria req = EstadoBateria.newBuilder()
						.setIdBateria(idBateria)
						.setPorcentaje((int)b.getNivelActualKWh())
						.build();
				requestObserver.onNext(req);
				v.traza("Enviado estado Batería " + idBateria + " (" + b.getNivelActualKWh()+ "%)", Ventana.NEGRO);
                Thread.sleep(500);
                idBateria++;
			}
			requestObserver.onCompleted();
			latch.await(1,TimeUnit.MINUTES);
		} catch (Exception e) {
			v.traza("ERROR: "+ e.getMessage(),Ventana.ROJO);
		}
		
		
	}
	public static void main(String[] args) {
		BalanceadorClient client = new BalanceadorClient();
		List<Bateria> listaBaterias = null ; /////
		client.iniciarCanal(Config.HOST_PRECIOS, Config.PUERTO_DEFENSA);
		client.ajustarCarga(listaBaterias);
		client.cerrarCanal();
		
	}
}
