package gestorDemandas;

import grpc.GestorDemandaGrpc.GestorDemandaImplBase;
import grpc.GestorDemandaOuterClass.ClasificacionReply;
import grpc.GestorDemandaOuterClass.ConsumoRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class GestorDemandasService extends GestorDemandaImplBase{
	Ventana v;
	
	public GestorDemandasService(Ventana v) {
		this.v = v;
	}
	//BIDIRECCIONAL N:N
	@Override
	public StreamObserver<ConsumoRequest> clasificarDemanda(StreamObserver<ClasificacionReply> responseObserver){
		
		return new StreamObserver<ConsumoRequest>() {
			
			@Override
			public void onNext(ConsumoRequest value) {
				v.traza("Recogiendo consumo" +value.getIdConsumo()+ " con kwh: "+value.getTotalKWh());
				ClasificacionReply reply = ClasificacionReply.newBuilder()
						.setIdConsumo(value.getIdConsumo())
						.setCategoria("NO SE QUE PONER AQUI")
						.build();
				responseObserver.onNext(reply);
				
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("EEROR:"+ t.getMessage(),Ventana.ROJO);
				responseObserver.onError(t);
				
			}
			
			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
				
			}
		};
		
	}

}
