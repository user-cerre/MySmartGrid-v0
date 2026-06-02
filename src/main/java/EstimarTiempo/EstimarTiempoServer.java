package EstimarTiempo;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class EstimarTiempoServer {
	public static void main(String[] args) throws IOException, InterruptedException {
        // Creación de una ventana para mostrar los mensajes de log del servidor.
        Ventana v = new Ventana (Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE FILTRO");
        
        // Construcción del servidor gRPC en el puerto 50051 y añadiendo el servicio definido por 'MyService'.
        Server server = ServerBuilder.forPort(9096).addService(new EstimarTiempoService(v))
        		.build();

        
        
       

        
        // Inicia el servidor gRPC y muestra un mensaje en la ventana indicando que el servidor está en funcionamiento.
        server.start();
        
        v.traza("");
        v.traza("Servidor iniciado y escuchando en puerto 9096");
        v.traza("...");
        
        // El servidor permanece activo esperando conexiones entrantes.
        server.awaitTermination();
    }
}
