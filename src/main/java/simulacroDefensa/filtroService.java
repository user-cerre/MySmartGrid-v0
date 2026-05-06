package simulacroDefensa;

import grpc.FiltradoGrpc.FiltradoImplBase;
import grpc.FiltroOuterClass.FiltroReply;
import grpc.FiltroOuterClass.FiltroRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class filtroService extends FiltradoImplBase{
	Ventana v;
	public filtroService(Ventana v) {
		this .v = v;
	}
	//BIDIRECCIONAL N:N
	@Override
	public StreamObserver<FiltroRequest> filtrar (StreamObserver<FiltroReply> responseObserver){
		return new StreamObserver<FiltroRequest>() {
			
			@Override
			public void onNext(FiltroRequest value) {
				v.traza("[Simulacro de defensa]");
				int kwh = value.getTotalKWh();
				if (kwh >= 5) {
					FiltroReply reply = FiltroReply.newBuilder()
							.setIdConsumo(value.getIdConsumo())
							.setTotalKWh(kwh)
							.build();
					responseObserver.onNext(reply);
					v.traza("Se ha enviado el consumo con ID "+ value.getIdConsumo()+"CONSUMO = "+ value.getTotalKWh());
							
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("[Simulacro de defensa] ERROR: " + t.getMessage(), Ventana.ROJO);
				responseObserver.onError(t);
				
			}
			
			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
				
			}
		};
	}
	

}
