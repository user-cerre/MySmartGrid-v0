package estadisticas;

import java.io.IOException;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class EstadisticasServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE ESTADÍSTICAS");
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new EstadisticasService(v))
				.build();
		
		server.start();
		v.traza("El servidor de ALERTAS se abrió en el puerto "+ server.getPort(), Ventana.VERDE);
		v.traza("...");
		server.awaitTermination();
	}
}
