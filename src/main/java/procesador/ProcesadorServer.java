package procesador;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class ProcesadorServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE PROCESADO");
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new ProcesadorService(v))
				.build();
		
		server.start();
		v.traza("INICIANDO SERVIDOR SUMATORIO ZONAL EN EL PUERTO" + server.getPort());
		server.awaitTermination();
		v.traza("CERRANDO EL SERVIDOR", Ventana.GRIS);
	}
}
