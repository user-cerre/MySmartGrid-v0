package gestorDemandas;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class GestorDemandasServer {
	public static void main(String[] args) throws IOException,InterruptedException {
		//LA CREACIÓN DE LOS SERVIDORES NO CAMBIA NUNCA, ES SIEMPRE EL MISMO
	    Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1,Config.TAMAÑO_VENTANA,Config.TAMAÑO_VENTANA,"SERVIDOR GESTOR DEMANDA");
	    Server server = ServerBuilder
	      .forPort(Config.PUERTO_DEFENSA)
	      .addService(new GestorDemandasService(v))//SERVICIO CONCRETO
	      .build();
	    server.start();
	    v.traza("EL SERVIDOR SE ABRIÓ EN EL PUERTO: "+server.getPort(),Ventana.VERDE);
	    server.awaitTermination();
  }
}

