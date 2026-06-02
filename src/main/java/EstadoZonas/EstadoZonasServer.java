package EstadoZonas;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class EstadoZonasServer {
	public static void main(String[] args) throws IOException, InterruptedException {
        // Creación de una ventana para mostrar los mensajes de log del servidor.
        Ventana v = new Ventana (Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE ESTADO ZONAS");
        
        // Construcción del servidor gRPC en el puerto 50051 y añadiendo el servicio definido por 'MyService'.
        Server server = ServerBuilder.forPort(9996).addService(new EstadoZonasService(v))
        		.build();
        server.start();
        
        v.traza("");
        v.traza("Servidor iniciado y escuchando en puerto 50051");
        v.traza("...");
        
        // El servidor permanece activo esperando conexiones entrantes.
        server.awaitTermination();
       }
}
