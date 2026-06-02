package ControlDeConsumosTramitados;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import main.Config;
import pcd.util.Ventana;

public class ControlDeConsumosTramitadosServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE CONTROL DE CONSUMOS TRAMITADOS");
		
		Server server = ServerBuilder.forPort(9095)
				.addService(new ControlDeConsumosTramitadosService(v))
				.build();
		
		server.start();
		v.traza("INICIANDO SERVIDOR EN EL PUERTO" + server.getPort());
		server.awaitTermination();
		v.traza("CERRANDO EL SERVIDOR", Ventana.GRIS);
	}

}
