package alertas;

import grpc.AlertasGrpc.AlertasImplBase;
import grpc.AlertasOuterClass.AlertaReply;
import grpc.AlertasOuterClass.UmbralRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

import java.util.List;

import energy.Consumo;
/*
 * SERVER STREAMING
 * Crea un servicio llamado Alertas con un método listarConsumosCriticos. 
 * El cliente envía un umbral de kWh (por ejemplo, 40). 
 * El servidor buscará en su base de datos o lista interna y enviará,
 *  uno a uno, todos los consumos que superen ese umbral. 
 *  El cliente debe imprimir cada alerta a medida que llega.
 */
public class AlertasService extends AlertasImplBase{
	Ventana v;
	List<Consumo> listaConsumos;
	public AlertasService(Ventana v, List<Consumo> listaConsumos) {
		this.v = v;
		this.listaConsumos = listaConsumos;
		
	}
	//SERVER STREAMING 1:N
	@Override
	public void listarConsumosCriticos(UmbralRequest request, StreamObserver<AlertaReply> responseObserver){
		v.traza("[INICIANDO ALERTAS ...]", Ventana.VERDE);
		
		
		try {
			int kwh = request.getValorCorte();
			v.traza("RECIBIDO VALOR DE CORTE "+ kwh, Ventana.NEGRO);
			
			if (listaConsumos == null || listaConsumos.isEmpty()) {
				v.traza("LA LISTA DE CONSUMOS ESTÁ VACÍA", Ventana.ROJO);
			}else {
				for (Consumo c: listaConsumos) {
					if(c.getTotalKWh() > kwh) {
						AlertaReply alerta = AlertaReply.newBuilder()
								.setIdConsumo(c.getIdConsumo())
								.setKwh(kwh)
								.setMensaje("¡ALERTA! CONSUMO CRÍTICO ENVIADO")
								.build();
						responseObserver.onNext(alerta);
					}
				}		
			}
			responseObserver.onCompleted();
			v.traza("FLUJO DE ALERTA ENVIADO CORRECTAMENTE", Ventana.GRIS);
		} catch (Exception e) {
			v.traza("ERROR AL COMENZAR EL FLUJO DE ALERTAS", Ventana.ROJO);
			responseObserver.onError(e);
		}
	}
}