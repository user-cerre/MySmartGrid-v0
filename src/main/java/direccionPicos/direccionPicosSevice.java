package direccionPicos;

import grpc.AnalisisRedGrpc.AnalisisRedImplBase;
import grpc.DireccionPicos.PicoReply;
import grpc.DireccionPicos.PicoRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class direccionPicosSevice extends AnalisisRedImplBase{
	// BIDIRECCIONAL
	Ventana v;
	public direccionPicosSevice(Ventana v) {
		this.v = v;
	}
	
	@Override
	public StreamObserver<PicoRequest> detectarPicos(StreamObserver<PicoReply> streamObserver){
		return new StreamObserver<PicoRequest>() {
			
			@Override
			public void onNext(PicoRequest value) {
				// TODO Auto-generated method stub

				
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
