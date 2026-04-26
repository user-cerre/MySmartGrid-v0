package monitorizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import grpc.MonitorizacionGrpc.MonitorizacionImplBase;
import grpc.MonitorizacionProto.*;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class MonitorizacionService extends MonitorizacionImplBase{
	
	private final List<ConsumoRequest> historialConsumos = new ArrayList<>();
	private final ConcurrentHashMap<Integer, Double> totalPorZonas = new ConcurrentHashMap<>();
	private double totalRed = 0.0;
	Ventana v;
	public MonitorizacionService(Ventana v) {
		this.v = v;
	}
	@Override
	public void anotarConsumo(ConsumoRequest request, StreamObserver<ConsumoReply> responseObserver) {
		synchronized (this) {
			historialConsumos.add(request);
			v.traza("[anotarConsumo] Recibido id=" + request.getIdConsumo()
					+ " zona=" + request.getIdZona()
					+ " kWh=" + request.getKWh()
					+ " direccion=" + request.getDireccion()
					+ " solar=" + request.getSolar(), Ventana.AZUL);
			totalPorZonas.merge(request.getIdZona(), request.getKWh(), (a, b) -> a + b);
			double totalZona = totalPorZonas.get(request.getIdZona());
			totalRed += request.getKWh();
			v.traza("[anotarConsumo] Totales -> zona " + request.getIdZona()
					+ ": " + totalZona + " | red: " + totalRed, Ventana.VERDE);
			
			ConsumoReply reply = ConsumoReply.newBuilder()
					.setIdZona(request.getIdZona())
					.setTotalZona(totalZona)
					.setTotalRed(totalRed)
					.build();
			responseObserver.onNext(reply);
			responseObserver.onCompleted();
			
		}
	}
	@Override
	public void demandaSolar(DemandaRequest request, StreamObserver<DemandaReply> responseObserver) {
		int zona = request.getIdZona();
		v.traza("[demandaSolar] Consulta recibida para zona " + zona, Ventana.AZUL);
		synchronized(this){
			for(ConsumoRequest c : historialConsumos) {
				if(c.getIdZona() == zona && c.getSolar()) {
					DemandaReply reply = DemandaReply.newBuilder()
							.setIdConsumo(c.getIdConsumo())
							.build();
					v.traza("[demandaSolar] Enviado idConsumo=" + c.getIdConsumo(), Ventana.NEGRO);
					responseObserver.onNext(reply);
				}
			}
		}
		v.traza("[demandaSolar] Stream finalizado para zona " + zona, Ventana.GRIS);
		responseObserver.onCompleted();
	}
	@Override
	public StreamObserver<DireccionRequest> consumosDireccion(StreamObserver<DireccionReply> responseObserver) {
		return new StreamObserver<DireccionRequest>() {
			int cont = 0;// Contador solar
			@Override
			public void onNext(DireccionRequest value) {
				v.traza("[consumosDireccion] Direccion recibida: " + value.getDireccion(), Ventana.AZUL);
				synchronized(MonitorizacionService.this){
					for(ConsumoRequest c : historialConsumos) {
						if(c.getDireccion().equals(value.getDireccion()) && c.getSolar()) {
							cont++;
							v.traza("[consumosDireccion] Coincidencia solar en direccion=" + value.getDireccion()
									+ " (total=" + cont + ")", Ventana.VERDE);
							break;
						}
					}
				}
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("[consumosDireccion] ERROR: " + t.getMessage(), Ventana.ROJO);
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("[consumosDireccion] Stream finalizado. Total=" + cont, Ventana.GRIS);
				DireccionReply reply = DireccionReply.newBuilder()
						.setTotal(cont)
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
				
			}
		};
	}
}
