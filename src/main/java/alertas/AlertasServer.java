package alertas;

import java.io.IOException;
import java.util.List;

import energy.Consumo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class AlertasServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE ALERTAS");
		List<Consumo> listaConsumos= Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new AlertasService(v, listaConsumos))
				.build();
		
		server.start();
		v.traza("El servidor de ALERTAS se abrió en el puerto "+ server.getPort(), Ventana.VERDE);
		v.traza("...");
		server.awaitTermination();
	}
}
