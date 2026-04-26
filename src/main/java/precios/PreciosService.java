package precios;

import grpc.PreciosGrpc.PreciosImplBase;
import grpc.PreciosProto.DemandaRequest;
import grpc.PreciosProto.PreciosReply;
import grpc.PreciosProto.PreciosRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class PreciosService extends PreciosImplBase{
	
	Ventana v;
	public PreciosService(Ventana v) {
		this.v = v;
	}
	public StreamObserver<PreciosRequest> calcularPrecios( StreamObserver<PreciosReply> responseObserver) {
		return new StreamObserver<PreciosRequest>() {

			@Override
			public void onNext(PreciosRequest request) {
				v.traza("[calcularPrecios] Recibido id=" + request.getIdConsumo() + " zona=" + request.getIdZona(), Ventana.AZUL);
                double precioAcumulado = 0.0;
                for (DemandaRequest demanda : request.getDemandasList()) {
                    double costeKwh;
                    String tipo = demanda.getIdTipo().toLowerCase();
                    
                    if(tipo.contains("solar")) {
                    	costeKwh = 0.08;
                    } else if (tipo.contains("eólico") || tipo.contains("eolico")) {
                        costeKwh = 0.10;
                    } else {
                        costeKwh = 0.15; // Tarifa normal
                    }
                    precioAcumulado += demanda.getKWh() * costeKwh;
					v.traza("[calcularPrecios] Demanda tipo=" + demanda.getIdTipo()
							+ " kWh=" + demanda.getKWh()
							+ " coste=" + costeKwh, Ventana.NEGRO);
                }
				v.traza("[calcularPrecios] Precio total id=" + request.getIdConsumo() + " -> " + precioAcumulado, Ventana.VERDE);

                PreciosReply reply = PreciosReply.newBuilder()
                        .setIdConsumo(request.getIdConsumo())
                        .setPrecio(precioAcumulado)
                        .build();

                responseObserver.onNext(reply);
            }

			@Override
			public void onError(Throwable t) {
				v.traza("[calcularPrecios] ERROR: " + t.getMessage(), Ventana.ROJO);
				
				
			}

			@Override
			public void onCompleted() {
				v.traza("[calcularPrecios] Stream finalizado", Ventana.GRIS);
				responseObserver.onCompleted();
				
			}
			
		};
	}
}
