package GestorPagos;

import java.util.ArrayList;
import java.util.List;

import grpc.GestorPagosGrpc.GestorPagosImplBase;
import grpc.PagosOuterClass.PagoReply;
import grpc.PagosOuterClass.PagoRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class GestorPagosService extends GestorPagosImplBase{
	Ventana v;
	public GestorPagosService(Ventana v) {
		this.v = v;
	}
	@Override
	public StreamObserver<PagoRequest> gestionarPagos(StreamObserver<PagoReply> responseObserver) {
		return new StreamObserver<PagoRequest>(){
			
			@Override
			public void onNext(PagoRequest value) {
				boolean valido = value.getImporte() > 0.5;
				PagoReply reply = PagoReply.newBuilder()
                        //EROR AL IMPORTAR LO QUE ES EL MISMO GRPC EL 1 Y 2
                        .setResultado(valido)
                        .build();
				responseObserver.onNext(reply);
				v.traza("Enviado respuesta de "+ value.getIdConsumo());
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
				v.traza("FIN");
				
			}
		};
	}

}
