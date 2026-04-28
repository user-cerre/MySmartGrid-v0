package precios;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class PreciosServer {
	public static void main(String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA,1,Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA,"Servidor de Precios");
		v.traza("Comenzando Servidor Precios",Ventana.VERDE);
		Server server = ServerBuilder.forPort(Config.PUERTO_PRECIOS)
				.addService(new PreciosService (v))
				.build();
		server.start();
		v.traza("El servidor Precios se abrió en " + server.getPort());
		v.traza("...");
		server.awaitTermination();
		
	}

}
