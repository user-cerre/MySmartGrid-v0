package alertas;



import grpc.AlertasGrpc;
import grpc.AlertasGrpc.AlertasBlockingStub;
import grpc.AlertasGrpc.AlertasStub;
import grpc.AlertasOuterClass.UmbralRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import main.Config;
import pcd.util.Ventana;

public class AlertasClient {
	ManagedChannel channel;
	AlertasBlockingStub alertasBlockingStub;
	AlertasStub alertasStub;
	Ventana v;
	
	public AlertasClient() {
		this .v = new Ventana(Config.TAMAÑO_VENTANA*2, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE FILTRO");
	}
	public void iniciarCanal(String ip, int port) {
		channel = ManagedChannelBuilder.forAddress(ip, port)
				.usePlaintext().build();
		alertasBlockingStub = AlertasGrpc.newBlockingStub(channel);
		alertasStub = AlertasGrpc.newStub(channel);
		v.traza("CANAL ABIERTO CON IP "+ ip+ ":"+ port, Ventana.VERDE);
	}
	public void cerrarCanal(){
		if (channel != null) {
			channel.shutdown();
			v.traza("CANAL CERRADO", Ventana.GRIS);
		}
	}
	public void consultarAlertas(int umbral) {
		AlertasBlockingStub stub = AlertasGrpc.newBlockingStub(channel);
		v.traza("CONSULTANDO CONSUMOS CRÍTICOS (Umbal: "+umbral+ ")", Ventana.AZUL);
		try {
			UmbralRequest request = UmbralRequest.newBuilder()
					.setValorCorte(umbral)
					.build();
			stub.listarConsumosCriticos(request).forEachRemaining(response ->{
				v.traza("Umbral crítico en: "+ response.getIdConsumo() ,Ventana.NEGRO);
			});
			v.traza("FIN DE LA COMUNICACIÓN", Ventana.GRIS);
		} catch (Exception e) {
			v.traza("ERROR AL INICIAR EL CLIENTE", Ventana.ROJO);
		}
	}
	public static void main (String[] args) {
		AlertasClient client = new AlertasClient();
		client.iniciarCanal(Config.HOST_FILTRO, Config.PUERTO_DEFENSA);
		try {
			client.consultarAlertas(30);
		}catch (Exception e) {
			client.cerrarCanal();
		}
	}
}
