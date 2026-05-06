package balanceador;

import java.io.IOException;
import java.util.List;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerProvider.NewServerBuilderResult;
import main.Config;
import pcd.util.Ventana;
import storage.Bateria;

public class BalanceadorServer {
	public static void main (String[] args) throws IOException, InterruptedException{
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE BALANCEADOR");
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new BalanceadorService(v))
				.build();
		
		server.start();
		v.traza("INICIANDO SERVIDOR BIDIRECCIONAL EN EL PUERTO" + server.getPort());
		server.awaitTermination();
		v.traza("CERRANDO EL SERVIDOR", Ventana.GRIS);
	}

}