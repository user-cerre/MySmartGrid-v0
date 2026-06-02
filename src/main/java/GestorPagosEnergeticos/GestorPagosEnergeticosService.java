package GestorPagosEnergeticos;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import grpc.GestorPagosGrpc.GestorPagosImplBase;
import grpc.PagosOuterClass.PagoReply;
import grpc.PagosOuterClass.PagoRequest;
import grpc.PagosOuterClass.ZonaReply;
import grpc.PagosOuterClass.ZonaRequest;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;

public class GestorPagosEnergeticosService extends GestorPagosImplBase{
	Ventana v;
	private final ConcurrentMap<Integer, List<ZonaReply>> rechazados = new ConcurrentHashMap<>();//IMPORTANTE !!!
	public GestorPagosEnergeticosService(Ventana v) {
		this.v = v;
	}
	@Override
	public void pagar(PagoRequest request, StreamObserver<PagoReply> responseObserver) {
		v.traza("Solicitado: "+request.getIdConsumo());
		boolean cumplido = request.getImporte() > 5;
		if(!cumplido) {
			//IMPORTANTE ESTO !!!
			ZonaReply rechazado = ZonaReply.newBuilder().setIdConsumo(request.getIdConsumo()).setImporte(request.getImporte()).build();
			rechazados.computeIfAbsent(request.getZona(), k -> new CopyOnWriteArrayList<>())
            .add(rechazado);

		}
		PagoReply reply = PagoReply.newBuilder().setResultado(cumplido).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	@Override
	public void rechazadosPorZona(ZonaRequest request, StreamObserver<ZonaReply> responseObserver) {
		int zona = request.getZona();
		v.traza("Buscando en zona "+ zona);
		List<ZonaReply> listaRechazados = rechazados.getOrDefault(zona, new CopyOnWriteArrayList<>());
		
		for (ZonaReply reply: listaRechazados) {
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
		
	}

}
