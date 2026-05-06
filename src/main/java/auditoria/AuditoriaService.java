package auditoria;

import grpc.AuditoriaGrpc.AuditoriaImplBase;
import grpc.AuditoriaOuterClass.ResumenReply;
import grpc.AuditoriaOuterClass.ZonaRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;
/*Crea un servicio llamado Auditoria con un método obtenerResumen. 
El cliente enviará el identificador de una zona y el servidor 
devolverá el total de kWh acumulados en esa zona y el número 
de consumos procesados hasta el momento.
*/
public class AuditoriaService extends AuditoriaImplBase{
	Ventana v;
	public AuditoriaService(Ventana v) {
		this.v = v;
	}
	//UNARY 1:1
	@Override
	public void obtenerResumen(ZonaRequest request,StreamObserver<ResumenReply> responseObserver) {
		v.traza("[INICIANDO AUDITORIA..]", Ventana.VERDE);
		
		//Extreameos la zona
		int idZona = request.getIdZona();
		v.traza("PETICIÓN RECIBIDA PARA ZONA : " +idZona, Ventana.NEGRO);
		double totalKwh = 150.0;
		int cantidad = 10;
		
		//Construimos la respuesta
		ResumenReply reply = ResumenReply.newBuilder()
				.setIdZona(idZona)
				.setNumConsumos(cantidad)
				.setTotalKWh(totalKwh)
				.build();
		//Enviamos la respuesta
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
			
	}
}
