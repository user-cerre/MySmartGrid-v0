package procesador;

import java.util.concurrent.atomic.AtomicInteger;

import grpc.ProcesadorGrpc.ProcesadorImplBase;
import grpc.ProcesadorOuterClass.ProcesarReply;
import grpc.ProcesadorOuterClass.ProcesarRequest;
import io.grpc.stub.StreamObserver;

import pcd.util.Ventana;

public class ProcesadorService extends ProcesadorImplBase{
	Ventana v;
	public ProcesadorService(Ventana v) {
		this.v = v;
	}
	//N:1 --> SERVER STREAMING
	@Override
	public StreamObserver<ProcesarRequest> procesar (StreamObserver<ProcesarReply> responseObserver) {
		return new StreamObserver<ProcesarRequest>() {
			AtomicInteger cont = new AtomicInteger(0);

            @Override
            public void onNext(ProcesarRequest value) {
                // Añade el mensaje recibido al acumulador y muestra el mensaje en la interfaz de usuario.
            	if(value.getZona() == 0) {
            		v.traza("RECIBIDO CONSUMO DE ZONA 0");
            		cont.incrementAndGet();
            	}
            }

            @Override
            public void onError(Throwable t) {
                // En caso de error durante la transmisión.
            }

            @Override
            public void onCompleted() {
                // Procesa todos los mensajes recibidos después de que el cliente termina de enviarlos.
                
            	int totalZona0 = cont.get();
                ProcesarReply reply = ProcesarReply.newBuilder().setNumConsumos(totalZona0).build();
                
                // Envía la respuesta acumulada al cliente.
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
                
                // Muestra la respuesta enviada al cliente en la interfaz de usuario.
                v.traza("  [ Servidor >>> ]:  TOTAL:" + totalZona0 );        		
                v.traza("\n  ¡Fin!");   
                v.traza("\n\n");              
            }
        };
		
		
	}

}
