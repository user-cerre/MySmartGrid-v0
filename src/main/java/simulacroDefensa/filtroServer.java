package simulacroDefensa;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import  main.Config;
import pcd.util.Ventana;


public class filtroServer{
	public static void main(String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE FILTRO");
		v.traza("[COMENZANDO SERVIDOR MONITORIZACIÓN]", Ventana.VERDE);
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new filtroService(v))
				.build();
		server.start();
		v.traza("El servidor de FILTRO se abrió en " + server.getPort());
		v.traza("...");
		server.awaitTermination();
		v.traza("CERRANDO SERVER DE FILTRADO", Ventana.GRIS);
	}

}
