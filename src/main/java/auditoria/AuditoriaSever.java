package auditoria;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import pcd.util.Ventana;
import main.Config;

/*Crea un servicio llamado Auditoria con un método obtenerResumen. 
El cliente enviará el identificador de una zona y el servidor 
devolverá el total de kWh acumulados en esa zona y el número 
de consumos procesados hasta el momento.
*/
public class AuditoriaSever {
	public static void main(String[] args)throws IOException, InterruptedException {
		Ventana v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "SERVIDOR DE AUDITORIA");
		
		Server server = ServerBuilder.forPort(Config.PUERTO_DEFENSA)
				.addService(new AuditoriaService(v))
				.build();
		server.start();
		v.traza("El servidor de AUDITORIA se abrió en el puerto "+ server.getPort(), Ventana.VERDE);
		v.traza("...");
		server.awaitTermination();
	}

}
