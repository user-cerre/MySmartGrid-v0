package simulacroDefensa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import energy.Consumo;
import grpc.FiltradoGrpc;
import grpc.FiltradoGrpc.FiltradoBlockingStub;
import grpc.FiltradoGrpc.FiltradoStub;
import grpc.FiltroOuterClass.FiltroReply;
import grpc.FiltroOuterClass.FiltroRequest;
import grpc.FiltroOuterClass.FiltroRequest.Builder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class filtroClient {
	ManagedChannel channel;
	FiltradoBlockingStub filtradoBlockingStub;
	FiltradoStub filtradoStub;
	Ventana v;
	
	public filtroClient() {
		v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE FILTRO");
	}
	public void iniciarCanal(String ip , int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		filtradoBlockingStub = FiltradoGrpc.newBlockingStub(channel);
		filtradoStub = FiltradoGrpc.newStub(channel);
		v.traza("Canal abierto con ip: " + ip+ ":" + port, Ventana.VERDE);
	}
	public void cerrarCanal() {
		if(channel != null) {
			channel.shutdown();
			v.traza("Canal cerrado", Ventana.GRIS);
		}
	}
	
	public List<FiltroReply> consultarFiltrado(List<Consumo> consumos) {
        v.traza("--------------------------------------------------");
        v.traza("Consulta de Filtrado >= 5:", Ventana.AZUL);
        CountDownLatch latch = new CountDownLatch(1);
        List<FiltroReply> recibidos = Collections.synchronizedList(new ArrayList<>());
        
        StreamObserver<FiltroReply> respuesta = new StreamObserver<FiltroReply>() {
			
			@Override
			public void onNext(FiltroReply value) {
				recibidos.add(value);
				v.traza("FILTRADO -> " + value.getIdConsumo() + " : "+ value.getTotalKWh());
				
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("ERROR AL FILTRAR"+ t.getMessage(), Ventana.ROJO);
				latch.countDown();
			}
			
			@Override
			public void onCompleted() {
				v.traza("FILTRADO COMPLETADO", Ventana.GRIS);
				latch.countDown();
				
			}
		};
		
		StreamObserver<FiltroRequest> peticiones = filtradoStub.filtrar(respuesta);
		for(Consumo c : consumos) {
			FiltroRequest req = FiltroRequest.newBuilder()
	                .setIdConsumo(c.getIdConsumo())
	                .setDireccion(c.getDireccion())
	                .setTotalKWh((int) c.getTotalKWh())
	                .build();
			peticiones.onNext(req);
			v.traza("Enviado para filtrar: " + c.getIdConsumo(), Ventana.NEGRO);
		}
		peticiones.onCompleted();
		try {
			latch.await(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			v.traza("Interrumpido esperando respuesta", Ventana.ROJO);
		}
		return recibidos;
	}
	public void examenAbril(List<Consumo> consumos) {
		List<FiltroReply>  filtrados = consultarFiltrado(consumos);
		Observable.fromIterable(filtrados)
		.observeOn(Schedulers.computation())
		.subscribe(
                reply -> System.out.println("CONCURRENTE (ID): "+ reply.getIdConsumo()  + " en " + Thread.currentThread().getName()),
                Throwable::printStackTrace
            );
		try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}
	public static void main(String[] args) throws IOException, InterruptedException{
		filtroClient cliente = new filtroClient();
		cliente.iniciarCanal(Config.HOST_FILTRO, Config.PUERTO_DEFENSA);
		List<Consumo> consumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
		try {
			cliente.examenAbril(consumos);
		} finally {
			cliente.cerrarCanal();
		}
		
		
	}
}

