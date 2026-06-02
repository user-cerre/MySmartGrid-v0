package procesador;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import client.MyClient;
import energy.Consumo;

import grpc.ProcesadorGrpc;
import grpc.ProcesadorGrpc.ProcesadorBlockingStub;
import grpc.ProcesadorGrpc.ProcesadorStub;
import grpc.ProcesadorOuterClass.ProcesarReply;
import grpc.ProcesadorOuterClass.ProcesarRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class ProcesadorClient {
	ManagedChannel channel;
	ProcesadorBlockingStub blockingStub;
	ProcesadorStub stub;
	Ventana v;
	
	public ProcesadorClient() {
		// TODO Auto-generated constructor stub
		v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE PROCESADOR");
	}
	public void iniciarCanal(String ip , int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		blockingStub = ProcesadorGrpc.newBlockingStub(channel);//unary y server streaming
		stub = ProcesadorGrpc.newStub(channel);//client streaming y bidireccional
		v.traza("Canal abierto con ip: " + ip+ ":" + port, Ventana.VERDE);
	}
	public void cerrarCanal() {
		if(channel != null) {
			channel.shutdown();
			v.traza("Canal cerrado", Ventana.GRIS);
		}
	}
	public int filtrar(List<Consumo> consumos) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger totalZona0 = new AtomicInteger(0);
        // Creación de un stub no bloqueante, adecuado para streaming del cliente.
        ProcesadorStub stub  = ProcesadorGrpc.newStub(channel);

        // Iniciar el stream del cliente y manejar las respuestas del servidor con un StreamObserver.
        StreamObserver<ProcesarRequest> respuestaClientObserver = stub.procesar(new StreamObserver<ProcesarReply>() {
            @Override
            public void onNext(ProcesarReply respuesta) {
                // Mostrar la respuesta del servidor en la interfaz de usuario.
            	v.traza(" ");
            	totalZona0.set(respuesta.getNumConsumos());
            	v.traza(" [>>> Cliente]: "+ totalZona0);
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
        for(Consumo c: consumos) {
        	ProcesarRequest request = ProcesarRequest.newBuilder().setIdConsumo(c.getIdConsumo()).setZona(c.getZona())
        			.build();
        	respuestaClientObserver.onNext(request);
        	v.traza("ENVIADO " +c.getIdConsumo());
        	Thread.sleep(100);
        }

        // Indicar al servidor que el cliente ha terminado de enviar mensajes.
        v.traza("FIN DE ENVÍO");
        respuestaClientObserver.onCompleted();

        try {
            // Esperar hasta que el servidor haya terminado de enviar respuestas.
        	latch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.traza("\n\n");
			
        return totalZona0.get();
			}
	 public static void main(String[] args) throws InterruptedException {

	        // Crear una instancia del cliente.
	       ProcesadorClient cliente = new ProcesadorClient();

	        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
	        cliente.iniciarCanal("localhost", 9096);
	        
	        // Realizar las distintas llamadas al servidor.
	        /* To do: Llamada unaria */
	        List<Consumo> listaConsumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
	        int totalZona0 = cliente.filtrar(listaConsumos);
	        System.out.println(totalZona0);
	        
	        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
	        cliente.cerrarCanal();
	    }
	}


