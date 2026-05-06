package auditoria;

import grpc.AuditoriaGrpc;
import grpc.AuditoriaGrpc.AuditoriaBlockingStub;
import grpc.AuditoriaGrpc.AuditoriaStub;
import grpc.AuditoriaOuterClass.ResumenReply;
import grpc.AuditoriaOuterClass.ZonaRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import main.Config;
import pcd.util.Ventana;
/*Crea un servicio llamado Auditoria con un método obtenerResumen. 
El cliente enviará el identificador de una zona y el servidor 
devolverá el total de kWh acumulados en esa zona y el número 
de consumos procesados hasta el momento.
*/
public class AuditoriaClient {
	ManagedChannel channel;
	AuditoriaBlockingStub auditoriaBlockingStub;
	AuditoriaStub auditoriaStub;
	Ventana v;
	
	public AuditoriaClient() {
		this.v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE AUDITORIA");
	}
	public void iniciarCanal(String ip , int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		auditoriaBlockingStub = AuditoriaGrpc.newBlockingStub(channel);
		auditoriaStub = AuditoriaGrpc.newStub(channel);
		v.traza("Canal abierto con ip: " + ip+ ":" + port, Ventana.VERDE);
	}
	public void cerrarCanal() {
		if(channel != null) {
			channel.shutdown();
			v.traza("Canal cerrado", Ventana.GRIS);
		}
	}
	public void realizarConsulta(int idZona) {
		v.traza("Solicitando auditoria para zona: "+ idZona, Ventana.AZUL);
		try {
			ZonaRequest request = ZonaRequest.newBuilder()
					.setIdZona(idZona)
					.build();
			
			ResumenReply response = auditoriaBlockingStub.obtenerResumen(request);
			
			v.traza("RESULTADO RECIBIDO", Ventana.NEGRO);
			v.traza("TOTAL KWH: "+response.getTotalKWh()+ "Num consumo" + response.getNumConsumos());
		} catch (Exception e) {
			v.traza("ERROR EN LA CONSULTA " + e.getMessage(),Ventana.ROJO);
		}
	}
	public static void main(String[] args) {
		AuditoriaClient cliente = new AuditoriaClient();
		cliente.iniciarCanal(Config.HOST_FILTRO, Config.PUERTO_DEFENSA);
		try {
			cliente.realizarConsulta(1);
			
		} catch (Exception e) {
			cliente.cerrarCanal();
		}
	}
}
