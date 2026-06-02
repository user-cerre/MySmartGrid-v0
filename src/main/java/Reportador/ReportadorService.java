package Reportador;

import java.util.ArrayList;
import java.util.List;

import grpc.ReportadorGrpc.ReportadorImplBase;
import grpc.ReportadorOuterClass.ConsumoRequest;
import grpc.ReportadorOuterClass.ReporteReply;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class ReportadorService extends ReportadorImplBase{
	Ventana v; 
	public ReportadorService(Ventana v) {
		this.v = v;
	}
	@Override
    public StreamObserver<ConsumoRequest> reportar(StreamObserver<ReporteReply> respuestaObserver) {
          
        
        /*
        Retorna un nuevo observador de solicitud. Este observador procesará los mensajes enviados por el cliente.
        	(1) Debe tener un atributo que acumule los mensajes recibidos
        	(2) Implementar los métodos:
        		- onNext: Añade el mensaje recibido al acumulador y muestra el mensaje en la interfaz de usuario. 
        		- onError (opcional)
        		- onCompleted: 
        			* Procesa todos los mensajes recibidos después de que el cliente termina de enviarlos. 
        			* Envía la respuesta acumulada al cliente.
        			* Muestra la respuesta enviada al cliente en la interfaz de usuario.
        */  
        return new StreamObserver<ConsumoRequest>() {

        	private final List<ConsumoRequest> listaConsumos = new ArrayList<>();// Acumula los mensajes recibidos.

            @Override
            public void onNext(ConsumoRequest value) {
                // Añade el mensaje recibido al acumulador y muestra el mensaje en la interfaz de usuario.
            	listaConsumos.add(value);
            	v.traza("  [>>>Servidor ]: "+value.getIdConsumo());
            }

            @Override
            public void onError(Throwable t) {
                // En caso de error durante la transmisión.
            }

            @Override
            public void onCompleted() {
                // Procesa todos los mensajes recibidos después de que el cliente termina de enviarlos.
                StringBuilder buffer = new StringBuilder("BUFFER DE RESPUESTAS");
                for(ConsumoRequest c : listaConsumos) {
                	buffer.append("Canal ID: "+c.getCanalId() +"ID CONSUMO: "+ c.getIdConsumo()+"ZONA: "+c.getZona()+ "TOTAL: "+ c.getTotalKwh());
                }
                ReporteReply respuesta = ReporteReply.newBuilder().setResumen(buffer.toString())
                		.build();
                
                // Envía la respuesta acumulada al cliente.
                respuestaObserver.onNext(respuesta);
                respuestaObserver.onCompleted();
                
                // Muestra la respuesta enviada al cliente en la interfaz de usuario.
                v.traza("  [ Servidor >>> ]:  " + buffer.toString());        		
                v.traza("\n  ¡Fin!");   
                v.traza("\n\n");              
            }
        };
    }    
}
