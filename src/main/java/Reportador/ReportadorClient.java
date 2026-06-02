package Reportador;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import client.MyClient;
import energy.CanalConsumo;
import energy.Consumo;
import grpc.ReportadorGrpc;
import grpc.ReportadorGrpc.ReportadorStub;
import grpc.ReportadorOuterClass.ConsumoRequest;
import grpc.ReportadorOuterClass.ReporteReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class ReportadorClient {
	 // Canal de comunicación con el servidor y ventana de interfaz de usuario para mostrar mensajes.
    ManagedChannel channel;
    Ventana v;

    // Constructor del cliente, inicializa la ventana de la interfaz de usuario.
    public ReportadorClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE REPORTADOR");
    }
    
    // Método para iniciar el canal de comunicación con el servidor especificando IP y puerto.
    public void iniciarCanal(String ip, int port) {
        // Construye y establece el canal de comunicación. Usa texto plano para la conexión.
    	channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
    }

    // Método para cerrar el canal de comunicación con el servidor.
    public void cerrarCanal() {
    	channel.shutdown(); 
    }
    public void clientStream(List<Consumo> listaConsumos) {
        
        // Preparar un CountDownLatch para esperar la respuesta del servidor.
        CountDownLatch latch = new CountDownLatch(1);
        
        // Creación de un stub no bloqueante, adecuado para streaming del cliente.
        ReportadorStub stub = ReportadorGrpc.newStub(channel);

        // Iniciar el stream del cliente y manejar las respuestas del servidor con un StreamObserver.
        StreamObserver<ConsumoRequest> respuestaClientObserver = stub.reportar(new StreamObserver<ReporteReply>() {
            @Override
            public void onNext(ReporteReply respuesta) {
                // Mostrar la respuesta del servidor en la interfaz de usuario.
            	v.traza("");
            	v.traza(" [>>> Cliente]: "+ respuesta.getResumen());
            }

            @Override
            public void onError(Throwable t) {
                // En caso de error, imprimir el stack trace y disminuir el latch.
                t.printStackTrace();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                // Al completarse la respuesta del servidor, mostrar el fin de la operación en la interfaz de usuario.
                v.traza("\n  ¡Fin!");
                latch.countDown();
            }
        });

        // Enviar varios mensajes al servidor. Aquí, se envía la palabra "mundo" letra por letra.
        	// Espera un poco antes de enviar el siguiente mensaje para simular procesamiento.
        for(Consumo c: listaConsumos) {
        	ConsumoRequest request = ConsumoRequest.newBuilder().setCanalId(0).setIdConsumo(c.getIdConsumo()).setTotalKwh((int)c.getTotalKWh()).setZona(c.getZona()).build();
        	respuestaClientObserver.onNext(request);
        }

        // Indicar al servidor que el cliente ha terminado de enviar mensajes.
        respuestaClientObserver.onCompleted();

        try {
            // Esperar hasta que el servidor haya terminado de enviar respuestas.
        	latch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.traza("\n\n");
    }
    public static void main(String[] args) {

        // Crear una instancia del cliente.
    	ReportadorClient cliente = new ReportadorClient();

        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
        cliente.iniciarCanal("localhost", 9096);
        
        // Realizar las distintas llamadas al servidor.
        /* To do: Llamada unaria */

        cliente.clientStream(Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS));

        
        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
        cliente.cerrarCanal();
    }
}
