package EstadoZonas;

import energy.Consumo;
import grpc.EstadoZonasGrpc.EstadoZonasImplBase;
import grpc.EstadoZonasProto.EstadoZonasRequest;
import grpc.EstadoZonasProto.ZonaSaturadaReply;
import io.grpc.stub.StreamObserver;
import main.Config;
import pcd.util.Ventana;

public class EstadoZonasService extends EstadoZonasImplBase{
	Ventana v;
	public EstadoZonasService(Ventana v) {
		this.v = v;
	}
	//1:N
	@Override
    public void zonasSaturadas(EstadoZonasRequest solicitud, StreamObserver<ZonaSaturadaReply> respuestaServerObserver) {
        // Muestra mensajes en la interfaz de usuario indicando el inicio del streaming del servidor.

        // Muestra el mensaje recibido del cliente
        v.traza("  [>>>Servidor ]: " + solicitud.getNIF() + " "+ solicitud.getApellidos() + " " + solicitud.getIP());
        v.traza("");
        
        /*
        Envía varios mensajes al cliente, uno por cada carácter de la solicitud.
        	Convierte el mensaje en un flujo de caracteres.
        	Convierte cada entero a su caracter correspondiente.
        	Para cada caracter, crea una respuesta y la envía al cliente:
        		- Muestra el caracter enviado al cliente.
        		- Espera un poco antes de enviar el siguiente mensaje.
        */
//        String mensaje = "¡Hola "+solicitud.getMensaje()+"!";
//        mensaje.chars().mapToObj(c-> (char) c).forEach(c ->{
//        	Respuesta response = Respuesta.newBuilder().setMensaje(mensaje).build();
//        	respuestaServerObserver.onNext(response);
//        	v.addText("  [Servidor >>> ]: " +c);
        for(Consumo c: Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS)) {
        	if(c.getZona() == 1 || c.getZona() == 2) {
        		ZonaSaturadaReply reply = ZonaSaturadaReply.newBuilder().setIdZona(c.getZona()).setMotivo("ZONA SATURADA").build();
        		respuestaServerObserver.onNext(reply);
        		}
        }
        
//        	try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        });
        
        // Una vez enviados todos los caracteres, completa la comunicación.
        respuestaServerObserver.onCompleted();
        
        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
    }   
	
	
	

}
