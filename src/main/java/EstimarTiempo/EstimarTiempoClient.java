package EstimarTiempo;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import energy.Consumo;
import grpc.EstimadorEnergeticoGrpc;
import grpc.EstimadorEnergeticoGrpc.EstimadorEnergeticoStub;
import grpc.EstimadorOuterClass.ConsumoRequest;
import grpc.EstimadorOuterClass.EstimacionReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class EstimarTiempoClient {
	// Canal de comunicación con el servidor y ventana de interfaz de usuario para mostrar mensajes.
    ManagedChannel channel;
    Ventana v;

    // Constructor del cliente, inicializa la ventana de la interfaz de usuario.
    public EstimarTiempoClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE ESTIMAR TIEMPO");
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
    public void bidirectional(List<Consumo> listaConsumos) {
      
        // Creación de un stub no bloqueante, adecuado para streaming bidireccional.
        EstimadorEnergeticoStub stub =  EstimadorEnergeticoGrpc.newStub(channel);

        // Preparar un CountDownLatch para esperar hasta que el servidor haya terminado de enviar mensajes.
        CountDownLatch latch = new CountDownLatch(1);

        // Iniciar el stream bidireccional y manejar las respuestas del servidor con un StreamObserver.
        StreamObserver<ConsumoRequest> respuestaClientObserver = stub.estimarTiempo(new StreamObserver<EstimacionReply>() {
            @Override
            public void onNext(EstimacionReply respuesta) {
                // Mostrar cada mensaje recibido del servidor en la interfaz de usuario.
            	v.traza("   [>>>CLiente ]: "+ respuesta.getIdConsumo()+"ESTIMADO"+ respuesta.getTiempoEstimado());
            	v.traza(" ");
            }

            @Override
            public void onError(Throwable t) {
                // En caso de error, imprimir el stack trace y disminuir el latch.
                t.printStackTrace();
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                // Al completarse la comunicación por parte del servidor, mostrar el fin de la operación en la interfaz de usuario.
                v.traza("\n  ¡Fin!");
                latch.countDown();
            }
        });

        // Enviar varios mensajes al servidor. Aquí, se envía la palabra "mundo" letra por letra.
        	// Espera un poco antes de enviar el siguiente mensaje.
        for(Consumo c: listaConsumos) {
        	ConsumoRequest request = ConsumoRequest.newBuilder().setIdConsumo(c.getIdConsumo()).setTotalKwh((int) c.getTotalKWh()).setZona(c.getZona())
        			.build();
        	respuestaClientObserver.onNext(request);
        	
        	v.traza(" [Cliente >>>> ] "+request.getIdConsumo());
        }
        

        // Indicar al servidor que el cliente ha terminado de enviar mensajes.
        respuestaClientObserver.onCompleted();

        try {
            // Esperar hasta que el servidor haya terminado de enviar mensajes.
        	latch.await(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.traza("\n\n");
    }

    // Método principal que ejecuta el cliente.
    public static void main(String[] args) {

        // Crear una instancia del cliente.
    	EstimarTiempoClient cliente = new EstimarTiempoClient();

        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
        cliente.iniciarCanal("localhost", 9096);
        
        // Realizar las distintas llamadas al servidor.
        /* To do: Llamada unaria */
        List<Consumo> listaConsumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
        cliente.bidirectional(listaConsumos);
        
        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
        cliente.cerrarCanal();
    }
}
