package estadisticas;

import grpc.EstadisticasGrpc.EstadisticasImplBase;
import grpc.EstadisticasOuterClass.DatoRequest;
import grpc.EstadisticasOuterClass.MediaReply;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class EstadisticasService extends EstadisticasImplBase {
    Ventana v;

    public EstadisticasService(Ventana v) {
        this.v = v;
    }
    //CLIENT-STREAMING N:1
	@Override
	public StreamObserver<DatoRequest> calcularMedia(StreamObserver<MediaReply> respObserver){
		v.traza("[SERVIDOR] Iniciando flujo de estadísticas...", Ventana.VERDE);
		return new StreamObserver<DatoRequest>() {
			double sumaTotal = 0.0;
			int cont = 0;
			@Override
			public void onNext(DatoRequest value) {
				sumaTotal+=value.getKwh();
				cont++;
				v.traza("Dato recibido: "+value.getKwh()+"Total: "+cont, Ventana.AZUL);
				
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("ERROR EN EL SERVICIO DE ESTADÍSTICAS",Ventana.ROJO);
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("Finalizado el flujo de envío",Ventana.NEGRO);
				double media;
				if(cont > 0) {
					media = sumaTotal/cont;
				}else {
					media = 0.0;
				}
				MediaReply reply = MediaReply.newBuilder()
						.setMediaGlobal(media)
						.setTotalProcesados(cont)
						.build();
				respObserver.onNext(reply);
				respObserver.onCompleted();
			}
		};			
	}
}
