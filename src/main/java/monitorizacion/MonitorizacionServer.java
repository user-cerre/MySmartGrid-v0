package monitorizacion;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class MonitorizacionServer {
	public static void main(String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA,1,Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA,"Servidor de Monitorización");
		v.traza("Comenzando Servidor Monitorizacion",Ventana.VERDE);
		Server server = ServerBuilder.forPort(Config.PUERTO_MONITORIZACION)
				.addService(new MonitorizacionService (v))
				.build();
		server.start();
		v.traza("El servidor Monitorización se abrió en " + server.getPort());
		v.traza("...");
		server.awaitTermination();
	}

}
