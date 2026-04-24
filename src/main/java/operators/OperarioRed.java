
package operators;

import main.Config;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import energy.ConsumoPendiente;

public class OperarioRed implements Runnable {

    private CentroControl centro;
    private int idOperario;
    private CountDownLatch arranqueRedLatch;

    public OperarioRed(CentroControl _c, int _i, CountDownLatch latch){
        this.centro = _c;
        this.idOperario = _i;
        this.arranqueRedLatch = Objects.requireNonNull(latch);
    }
    @Override
    public void run(){
        try{
            if(Config.MODO_DE_ARRANQUE_OPERARIOS == 0){
                centro.esperarArranque();
            }else if( Config.MODO_DE_ARRANQUE_OPERARIOS == 1){
                centro.esperarArranqueBarreras();
            }
            arranqueRedLatch.countDown();
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
