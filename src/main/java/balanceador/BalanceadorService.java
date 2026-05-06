package balanceador;

import grpc.BalanceadorGrpc.BalanceadorImplBase;
import grpc.BalanceadorOuterClass.EstadoBateria;
import grpc.BalanceadorOuterClass.InstruccionCarga;
import io.grpc.stub.StreamObserver;
import pcd.util.Ventana;
/*
 * Crea un servicio llamado Balanceador con un método ajustarCarga. 
 * El cliente envía constantemente el estado de una batería (id y porcentaje actual). 
 * El servidor, en tiempo real, responde con una instrucción ("CARGAR", "DESCARGAR" o "MANTENER")
 * basada en ese porcentaje. La comunicación permanece abierta hasta que el sistema se apaga.
 */
public class BalanceadorService extends BalanceadorImplBase{
	Ventana v;
	public BalanceadorService(Ventana v) {
		this.v = v;
	}
	//BIDIRECCIONAL N:N
	@Override
	public StreamObserver<EstadoBateria> ajustarCarga(StreamObserver<InstruccionCarga> responseObserver){
		return new StreamObserver<EstadoBateria>() {
			
			@Override
			public void onNext(EstadoBateria value) {
				v.traza("[INICIANDO SERVICIO DE AJUSTE]...", Ventana.VERDE);
				v.traza("Recogido el consumo "+ value.getIdBateria()+ " con % de bateria "+ value.getPorcentaje(), Ventana.AZUL);
				InstruccionCarga instruccion;
				if(value.getPorcentaje() > 75) {
					instruccion = InstruccionCarga.newBuilder()
							.setAccion("DESCARGAR")
							.build();				
				}else if (value.getPorcentaje() >50) {
					instruccion = InstruccionCarga.newBuilder()
							.setAccion("MANTENER")
							.build();	
				}else {
					instruccion = InstruccionCarga.newBuilder()
							.setAccion("CARGAR")
							.build();	
				}
				
				v.traza("SE HA ENVIADO LA INSTRUCCION "+ instruccion.getAccion()+ " A LA BATERÍA "+ value.getIdBateria(), Ventana.NEGRO);
				responseObserver.onNext(instruccion);
				
			}
			
			@Override
			public void onError(Throwable t) {
				v.traza("ERROR EN EL SERVICIO DE BALANCEADOR "+ t.getMessage(), Ventana.ROJO);
				responseObserver.onError(t);
				
			}
			
			@Override
			public void onCompleted() {
				v.traza("SERVICIO COMPLETADO",Ventana.GRIS);
				responseObserver.onCompleted();
				
			}
		};
	}

}
