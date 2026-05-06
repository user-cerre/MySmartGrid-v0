package sumatorioZonal;

import grpc.SumatorioZonalGrpc.SumatorioZonalImplBase;
import grpc.SumatorioZonalProto.ConsumoZonalRequest;
import grpc.SumatorioZonalProto.SumaReply;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class sumatorioZonalService extends SumatorioZonalImplBase{
	Ventana v;
	public sumatorioZonalService(Ventana v) {
		this.v = v;
	}
	@Override
	public StreamObserver<ConsumoZonalRequest> calcularSuma (StreamObserver<SumaReply> responseObserver){
		
		return new StreamObserver<ConsumoZonalRequest>() {
			double sumaDeValores = 0.0;
			@Override
			public void onNext(ConsumoZonalRequest value) {
				sumaDeValores+= value.getKWh();
				v.traza("SUMANDO EL VALOR "+value.getKWh());
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("ERRROR");
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("Finalizado el flujo de envío",Ventana.NEGRO);
				SumaReply reply = SumaReply.newBuilder()
						.setTotalKWh(sumaDeValores)
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}
		};
	}
}
