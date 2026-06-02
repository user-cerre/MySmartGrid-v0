package GestorPagosEnergeticos;

import java.util.List;

import energy.Consumo;
import grpc.GestorPagosGrpc;
import grpc.GestorPagosGrpc.GestorPagosBlockingStub;
import grpc.PagosOuterClass.PagoReply;
import grpc.PagosOuterClass.PagoRequest;
import grpc.PagosOuterClass.ZonaRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import main.Config;

import pcd.util.Ventana;

public class GestorPagosEnergeticosClient {
	// Canal de comunicación con el servidor y ventana de interfaz de usuario para mostrar mensajes.
    ManagedChannel channel;
    private static Ventana v;

    // Constructor del cliente, inicializa la ventana de la interfaz de usuario.
    public GestorPagosEnergeticosClient() {

        v = new Ventana(Config.TAMAÑO_VENTANA, 1, Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA, "CLIENTE DE GESTOR DE PAGOS ENERGETICOS");
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
    public boolean pagarConsumo(String idConsumo, int zona, double importe) {
        // Mostrar inicio de la operación unaria en la interfaz de usuario.
        v.traza("Solicitano pago para" +idConsumo);
            	
    	// Creación de un stub bloqueante, adecuado para llamadas unarias o de streaming del servidor.
        GestorPagosBlockingStub stub = GestorPagosGrpc.newBlockingStub(channel); 

        // Creación de la solicitud y envío del mensaje "mundo" al servidor.
        PagoRequest request= PagoRequest.newBuilder().setIdConsumo(idConsumo).setZona(zona).setImporte(importe)
        		.build();
        
        // Llamada al método unario del servidor y recepción de la respuesta.
        PagoReply reply = stub.pagar(request);

        // Mostrar la respuesta del servidor en la interfaz de usuario.


        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
        return reply.getResultado();
    }
    public void consultarRechazados(int idZona) {
        // Mostrar inicio de la operación de stream del servidor en la interfaz de usuario.
        
        
        // Creación de un stub bloqueante, adecuado para llamadas unarias o de streaming del servidor.
        GestorPagosBlockingStub stub = GestorPagosGrpc.newBlockingStub(channel);
        
        // Creación de la solicitud.
        
        ZonaRequest request = ZonaRequest.newBuilder().setZona(idZona).build();
        v.traza("  [Cliente >>> ]:" + idZona);
        // Llamada al método de stream del servidor y manejo de cada respuesta recibida.
        stub.rechazadosPorZona(request).forEachRemaining(s-> v.traza("RECHAZADO DETECTADO CON ID"+ s.getIdConsumo()+ " COSTE"+ s.getImporte()));

        v.traza("\n  ¡Fin!");
        v.traza("\n\n");
    }
    public static void main(String[] args) {

        // Crear una instancia del cliente.
        GestorPagosEnergeticosClient cliente = new GestorPagosEnergeticosClient();
        List<Consumo> listaConsumos = Consumo.consumosDesdeFichero(Config.FICHERO_CONSUMOS);
        
        // Iniciar el canal de comunicación con el servidor especificando la IP y el puerto.
        cliente.iniciarCanal("localhost", 9095);
        
        // Realizar las distintas llamadas al servidor.
        for(Consumo c: listaConsumos) {
        	boolean pagoAceptado = cliente.pagarConsumo(c.getIdConsumo(), c.getZona(), c.getTotalKWh());
        	if(pagoAceptado) {
        		v.traza("Tramitando consumo"+ c.getIdConsumo());
        	}else {
        		v.traza("consumo inferior al mínimo "+ c.getIdConsumo());
        	}
        }
        for(int zona = 0; zona <= 3; zona++) {
        	cliente.consultarRechazados(zona);
        }
        
        // Cerrar el canal de comunicación con el servidor una vez finalizadas las operaciones.
        cliente.cerrarCanal();
    }


}
