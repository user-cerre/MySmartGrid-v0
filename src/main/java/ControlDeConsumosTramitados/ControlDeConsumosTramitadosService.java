package ControlDeConsumosTramitados;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import grpc.ControlDeConsumosTramitadosGrpc.ControlDeConsumosTramitadosImplBase;
import grpc.RegistroOuterClass.RegistroReply;
import grpc.RegistroOuterClass.RegistroRequest;
import grpc.RegistroOuterClass.TramitadosReply;
import grpc.RegistroOuterClass.TramitadosRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class ControlDeConsumosTramitadosService extends ControlDeConsumosTramitadosImplBase{
	Ventana v;
	private final ConcurrentMap<Integer, Integer> consumosPorZona = new ConcurrentHashMap<>();
	public ControlDeConsumosTramitadosService(Ventana v) {
		this.v = v;
	}
	@Override
    public void registrarConsumo(RegistroRequest solicitud, StreamObserver<RegistroReply> respuestaObserver) {

        
        // Muestra el mensaje recibido del cliente.
        v.traza("  [>>>>Servidor ]: TRAMITADO CONSUMO"+ solicitud.getZona());        
        int zona = solicitud.getZona();
        consumosPorZona.merge(zona, 1, Integer::sum);
        // Procesa la solicitud y crea una respuesta.
        RegistroReply response = RegistroReply.newBuilder().setResultado(true)
        		.build();
        
        // Envía la respuesta al cliente.
        respuestaObserver.onNext(response);
        
        // Notifica que la respuesta ha sido completamente enviada.
        respuestaObserver.onCompleted();
        
        // Muestra la respuesta enviada al cliente en la interfaz de usuario.
        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
    }
	@Override
    public void obtenerConsumosTramitados(TramitadosRequest solicitud, StreamObserver<TramitadosReply> respuestaServerObserver) {
        // Muestra mensajes en la interfaz de usuario indicando el inicio del streaming del servidor.
        
        
        // Muestra el mensaje recibido del cliente
        v.traza("  [>>>Servidor ]: " + solicitud.getPregunta());
        v.traza("");
        
        /*
        Envía varios mensajes al cliente, uno por cada carácter de la solicitud.
        	Convierte el mensaje en un flujo de caracteres.
        	Convierte cada entero a su caracter correspondiente.
        	Para cada caracter, crea una respuesta y la envía al cliente:
        		- Muestra el caracter enviado al cliente.
        		- Espera un poco antes de enviar el siguiente mensaje.
        */
        if(solicitud.getPregunta()) {
        	consumosPorZona.forEach((zona,total)->{
        		TramitadosReply reply = TramitadosReply.newBuilder().setZona(zona).setNumeroConsumos(total)
        				.build();
        		respuestaServerObserver.onNext(reply);
        	});
        }
        
        
        // Una vez enviados todos los caracteres, completa la comunicación.
        respuestaServerObserver.onCompleted();
        
        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
    } 
}
