package ControlDeConsumosTramitados;

import java.util.ArrayList;
import java.util.List;

import client.MyClient;
import energy.Consumo;
import grpc.ControlDeConsumosTramitadosGrpc;
import grpc.ControlDeConsumosTramitadosGrpc.ControlDeConsumosTramitadosBlockingStub;
import grpc.RegistroOuterClass.RegistroReply;
import grpc.RegistroOuterClass.RegistroRequest;
import grpc.RegistroOuterClass.TramitadosReply;
import grpc.RegistroOuterClass.TramitadosRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import main.Config;
import main.SimpleServiceGrpc;
import main.SimpleServiceGrpc.SimpleServiceBlockingStub;
import main.SimpleServiceOuterClass.Solicitud;
import pcd.util.Ventana;

public class ControlDeConsumosTramitadosClient {
	 // Canal de comunicación con el servidor y ventana de interfaz de usuario para mostrar mensajes.
    ManagedChannel channel;
    Ventana v;

    // Constructor del cliente, inicializa la ventana de la interfaz de usuario.
    public ControlDeConsumosTramitadosClient() {
        v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE CONTROL DE CONSUMOS TRAMITADOS");
    }
    
    // Método para iniciar el canal de comunicación con el servidor especificando IP y puerto.
    public void iniciarCanal(String ip, int port) {
        // Construye y establece el canal de comunicación. Usa texto plano para la conexión.
    	channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
    }

    // Método para cerrar el canal de comunicación con el servidor.
    public void cerrarCanal() {
    	channel.shutdown(); 
    }
    public void consultarTramite(Consumo c) {
       
            	
    	// Creación de un stub bloqueante, adecuado para llamadas unarias o de streaming del servidor.
        ControlDeConsumosTramitadosBlockingStub stub = ControlDeConsumosTramitadosGrpc.newBlockingStub(channel); 

        // Creación de la solicitud y envío del mensaje "mundo" al servidor.
        RegistroRequest solicitud = RegistroRequest.newBuilder().setZona(c.getZona())
        		.build();
        
        // Llamada al método unario del servidor y recepción de la respuesta.
        RegistroReply respuesta = stub.registrarConsumo(solicitud);
        
        if(respuesta.getResultado()) {
        	v.traza("RECOGIDO :" +c.getIdConsumo()+ " "+ c.getZona());

        }
        
        
        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
    }
    public List<TramitadosReply> obtenerConsumosTramitados() {

        
        // Creación de un stub bloqueante, adecuado para llamadas unarias o de streaming del servidor.
    	ControlDeConsumosTramitadosBlockingStub stub = ControlDeConsumosTramitadosGrpc.newBlockingStub(channel);
        List<TramitadosReply> listaTramitados = new ArrayList<>();
        // Creación de la solicitud.
        TramitadosRequest solicitud = TramitadosRequest.newBuilder().setPregunta(true).build();
        v.traza("  [Cliente >>> ]: Solicitando lista de consumos realizados para auditoria");
        // Llamada al método de stream del servidor y manejo de cada respuesta recibida.
        stub.obtenerConsumosTramitados(solicitud).forEachRemaining(response ->{
        	v.traza("  [>>>Cliente ]:" + response.getNumeroConsumos());
        	listaTramitados.add(response);
        	
        });
        v.traza("Recepcion finalizada");
        return listaTramitados;
    }
    public static void main(String[] args) {

        // Crear una instancia del cliente.
    	ControlDeConsumosTramitadosClient cliente = new ControlDeConsumosTramitadosClient();

        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
        cliente.iniciarCanal("localhost", 9095);
        
        // Realizar las distintas llamadas al servidor.
        /* To do: Llamada unaria */
        List<Consumo> listaConsumos= Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
        for(Consumo c : listaConsumos) {
            cliente.consultarTramite(c);        	
        }
        List<TramitadosReply> consumosTramitados = cliente.obtenerConsumosTramitados();
        for(TramitadosReply t: consumosTramitados) {
        	System.out.println("Consumo en zona" +t.getZona()+ " : " + t.getNumeroConsumos());
        }
        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
        cliente.cerrarCanal();
    }

}
