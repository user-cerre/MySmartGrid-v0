package EstimarTiempo;

import grpc.EstimadorEnergeticoGrpc.EstimadorEnergeticoImplBase;
import grpc.EstimadorOuterClass.ConsumoRequest;
import grpc.EstimadorOuterClass.EstimacionReply;
import io.grpc.stub.StreamObserver;

import pcd.util.Ventana;

public class EstimarTiempoService extends EstimadorEnergeticoImplBase{
	Ventana v;
	public EstimarTiempoService(Ventana v) {
		this.v = v;
	}
	@Override
	public StreamObserver<ConsumoRequest> estimarTiempo (StreamObserver<EstimacionReply> respuestaObserver) {
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
        return new StreamObserver<ConsumoRequest>() {
            @Override
            public void onNext(ConsumoRequest solicitud) {
            	v.traza("   [>>>Servidor ]: "+solicitud.getIdConsumo());
            	
            	EstimacionReply respuesta = EstimacionReply.newBuilder().setIdConsumo(solicitud.getIdConsumo())
            			.setTiempoEstimado((solicitud.getTotalKwh() * 3) + ((solicitud.getZona() % 5) +1) * 2)
            			.build();
            	respuestaObserver.onNext(respuesta);
            	
            	v.traza("   [Servidor >>> ]: TIEMPO ESTIMADO"+ respuesta.getTiempoEstimado());
            	v.traza("");
            }

            @Override
            public void onError(Throwable t) {
                // Manejo de errores en la comunicación.
            }

            @Override
            public void onCompleted() {
            	respuestaObserver.onCompleted();
                v.traza("\n  ¡Fin!");   
                v.traza("\n\n");             	
            }
        };        
     
    }     
}

