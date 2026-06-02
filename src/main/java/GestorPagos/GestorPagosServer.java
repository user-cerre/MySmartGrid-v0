package GestorPagos;

import java.io.IOException;
import java.util.List;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class GestorPagosServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE GESTOR DE PAGOS");
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new GestorPagosService(v))
				.build();
		
		server.start();
		v.traza("El servidor de GESTOR DE PAGOS se abrió en el puerto "+ server.getPort(), Ventana.VERDE);
		v.traza("...");
		server.awaitTermination();
	}

}
