package Filtrado;


import grpc.FiltradoGrpc.FiltradoImplBase;
import grpc.FiltroOuterClass.FiltroReply;
import grpc.FiltroOuterClass.FiltroRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class FiltradoService extends FiltradoImplBase{
	Ventana v; 
	public FiltradoService(Ventana v) {
		this.v = v;
	}
	@Override
	public StreamObserver<FiltroRequest> filtrar(StreamObserver<FiltroReply> responseObserver) {        
        /*
        Retorna un nuevo observador de solicitud. Este manejará los mensajes recibidos del cliente.
        	(1) Sin atributos
        	(2) Implementar los métodos:
        		- onNext:  
        			* Muestra el mensaje recibido y envía una respuesta transformada (en mayúsculas) al cliente.
        			* Transforma el mensaje recibido a mayúsculas para la respuesta.
        			* Muestra la respuesta enviada en la interfaz de usuario.
        		- onError (opcional)
        		- onCompleted: Completa la comunicación después de recibir todos los mensajes del cliente.
        */         
        
        // Retorna un observador de solicitud. 
        return new StreamObserver<FiltroRequest>() {
            @Override
            public void onNext(FiltroRequest request) {
            	boolean valido = request.getKwh() > 10;
            	if(valido) {
            		FiltroReply reply= FiltroReply.newBuilder().setIdConsumo(request.getIdConsumo()).setKwh(request.getKwh())
                			.build();
                	responseObserver.onNext(reply);
                	v.traza("ENVIADO: " +request.getIdConsumo()+ "CON CONSUMO" + request.getKwh());
            	}
            	
            	
//            	v.addText("   [Servidor >>> ]: "+ respuesta.getMensaje());
//            	v.addText("");
            }

            @Override
            public void onError(Throwable t) {
                v.traza("ERROR");
            }

            @Override
            public void onCompleted() {
            	responseObserver.onCompleted();
                v.traza("FIN DE LA COMUNICACIÓN");
                v.traza("\n\n");             	
            }
        };        
     
    }     
}
