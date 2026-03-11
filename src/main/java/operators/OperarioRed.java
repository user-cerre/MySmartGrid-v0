
package operators;

import energy.ConsumoPendiente;
import java.util.concurrent.locks.*;

public class OperarioRed implements Runnable {

    private CentroControl centro;
    private int idOperario;

    public OperarioRed(CentroControl _c, int _i){
        this.centro = _c;
        this.idOperario = _i;
    }
    @Override
    public void run(){
        try{
            centro.esperarArranque();
            while (true) {
                ConsumoPendiente cp = centro.tomar();
                if (cp == null){
                    System.out.println("[Operario " + idOperario + " Z" + centro.getIdZona() + "] Finalizando");
                    break;
                }
                centro.adquirirSlotConsumo();
                try{
                String resultado = centro.procesarConsumo(cp.getConsumo());
                cp.markProcessed(resultado);
                }finally{
                    centro.liberarSlotConsumo();
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.err.println("[Operario " + idOperario + "] Interrumpido");

        }
    }
}
