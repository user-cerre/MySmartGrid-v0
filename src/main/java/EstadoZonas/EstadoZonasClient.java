package EstadoZonas;


import java.util.ArrayList;
import java.util.List;

import grpc.EstadoZonasGrpc;
import grpc.EstadoZonasGrpc.EstadoZonasBlockingStub;
import grpc.EstadoZonasProto.EstadoZonasRequest;
import grpc.EstadoZonasProto.ZonaSaturadaReply;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import main.Config;

import pcd.util.Ventana;

public class EstadoZonasClient {
	// Canal de comunicación con el servidor y ventana de interfaz de usuario para mostrar mensajes.
    ManagedChannel channel;
    Ventana v;

    // Constructor del cliente, inicializa la ventana de la interfaz de usuario.
    public EstadoZonasClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE ESTADO ZONAS");
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
    public List<ZonaSaturadaReply> serverStream() {
        
        
        // Creación de un stub bloqueante, adecuado para llamadas unarias o de streaming del servidor.
        EstadoZonasBlockingStub stub = EstadoZonasGrpc.newBlockingStub(channel);
        List<ZonaSaturadaReply> zonasSaturadas = new ArrayList<>();
        // Creación de la solicitud.
        EstadoZonasRequest solicitud = EstadoZonasRequest.newBuilder().setApellidos("PAREDES RUIZ").setIP("192.168.191.104").setNIF("78119308S")
        		.build();
        v.traza("  [Cliente >>> ]:" + solicitud.getApellidos()+ " "+solicitud.getNIF()+ " "+ solicitud.getIP());
        // Llamada al método de stream del servidor y manejo de cada respuesta recibida.
        stub.zonasSaturadas(solicitud).forEachRemaining(response ->{
        	v.traza("  [>>>Cliente ]:" + response.getIdZona() + "RECHAZADO, MOTIVO : "+ response.getMotivo());
        	zonasSaturadas.add(response);
        });

        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
        return zonasSaturadas;
    }
    public static void main(String[] args) {

        // Crear una instancia del cliente.
    	EstadoZonasClient cliente = new EstadoZonasClient();

        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
        cliente.iniciarCanal("192.168.191.125", 9996);
        
        // Realizar las distintas llamadas al servidor.
        /* To do: Llamada unaria */
        cliente.serverStream();
        
        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
        cliente.cerrarCanal();
    }
}
