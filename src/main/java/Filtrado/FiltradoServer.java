package Filtrado;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class FiltradoServer {
	public static void main(String[] args) throws IOException, InterruptedException {
        // Creación de una ventana para mostrar los mensajes de log del servidor.
        Ventana v = new Ventana (Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE FILTRADO");
        
        // Construcción del servidor gRPC en el puerto 50051 y añadiendo el servicio definido por 'MyService'.
        Server server = ServerBuilder.forPort(9096).addService(new FiltradoService(v))
        		.build();

        // Añade texto a la ventana para indicar el inicio de la lista de servicios y métodos disponibles.


        
        // Inicia el servidor gRPC y muestra un mensaje en la ventana indicando que el servidor está en funcionamiento.
        server.start();
        
        v.traza("");
        v.traza("Servidor iniciado y escuchando en puerto 9096");
        v.traza("...");
        server.awaitTermination();
	}
}
