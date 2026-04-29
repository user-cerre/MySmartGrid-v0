package main;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import grpc.MonitorizacionGrpc;
import grpc.MonitorizacionGrpc.MonitorizacionBlockingStub;
import grpc.MonitorizacionGrpc.MonitorizacionStub;
import grpc.MonitorizacionProto.DemandaReply;
import grpc.MonitorizacionProto.DemandaRequest;
import grpc.MonitorizacionProto.DireccionReply;
import grpc.MonitorizacionProto.DireccionRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class ClienteConsultor {
	
	ManagedChannel channel;
    MonitorizacionBlockingStub monitorizacionBlockingStub;
    MonitorizacionStub monitorizacionStub;
	Ventana v;
	
	public ClienteConsultor() {
        v = new Ventana(Config.TAMAÑO_VENTANA * 2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "Cliente Consultor");
	}
	
    public void iniciarCanal(String ip, int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
        monitorizacionBlockingStub = MonitorizacionGrpc.newBlockingStub(channel);
        monitorizacionStub = MonitorizacionGrpc.newStub(channel);
        v.traza("Canal abierto con " + ip + ":" + port, Ventana.VERDE);
	}
	
	public void cerrarCanal() {
        if (channel != null) {
            channel.shutdown();
            v.traza("Canal cerrado", Ventana.GRIS);
        }
	}
	
    public int consultarDemandaSolar(int idZona) {
        v.traza("--------------------------------------------------");
        v.traza("Consulta demandaSolar para zona " + idZona, Ventana.AZUL);
        DemandaRequest request = DemandaRequest.newBuilder().setIdZona(idZona).build();
        Iterator<DemandaReply> respuestas = monitorizacionBlockingStub.demandaSolar(request);
        int total = 0;
        while (respuestas.hasNext()) {
            DemandaReply reply = respuestas.next();
            total++;
            v.traza("Consumo con solar: " + reply.getIdConsumo(),Ventana.NEGRO);
        }
        if (total == 0) {
            v.traza("No hay consumos solares en esa zona", Ventana.ROJO);
        }
        v.traza("Total consumos solares en zona " + idZona + ": " + total, Ventana.ROJO);
        return total;
    }
	
    public int consultarConsumosDireccion(List<String> direcciones) {
        v.traza("--------------------------------------------------");
        v.traza("Consulta consumosDireccion", Ventana.AZUL);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger total1 = new AtomicInteger(0);

        StreamObserver<DireccionReply> respuesta = new StreamObserver<DireccionReply>() {
            @Override
            public void onNext(DireccionReply value) {
                total1.set(value.getTotal());
                v.traza("Total direcciones con consumo solar: " + total1, Ventana.ROJO);
            }

            @Override
            public void onError(Throwable t) {
                v.traza("Error en consumosDireccion: " + t.getMessage(), Ventana.ROJO);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                v.traza("Consulta consumosDireccion finalizada", Ventana.GRIS);
                latch.countDown();
            }
        };

        StreamObserver<DireccionRequest> peticiones = monitorizacionStub.consumosDireccion(respuesta);
        for (String direccion : direcciones) {
            peticiones.onNext(DireccionRequest.newBuilder().setDireccion(direccion).build());
            v.traza("Enviada direccion: " + direccion, Ventana.VERDE);
        }
        peticiones.onCompleted();

        try {
            latch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            v.traza("Interrumpido esperando respuesta", Ventana.ROJO);
        }

        return total1.get();
    }
	
    public static void main(String[] args) {
        ClienteConsultor cliente = new ClienteConsultor();
        cliente.iniciarCanal(Config.HOST_MONITORIZACION, Config.PUERTO_MONITORIZACION);
        try {
            cliente.consultarDemandaSolar(1);//ID de la zona
            cliente.consultarConsumosDireccion(Arrays.asList("Sagitario, 24", "Berna, 11", "Roma, 2"));//En estas direcciones me devuelve si hay al menos un consumo solar
        } finally {
            cliente.cerrarCanal();
        }
    }
}
