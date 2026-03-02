package energy;

import operators.CentroControl;

public class ConsumoRunnable implements Runnable{
    private Consumo c;
    private RedEnergetica red;
    private String resultado;
    public ConsumoRunnable(Consumo c, RedEnergetica r){
        this.resultado = " ";
        this.c = c;
        this.red = r;
    }
    public void run(){
        CentroControl centro = red.getCentroControl(c.getZona());
        centro.producerStarted();
        try{
            ConsumoPendiente cp = new ConsumoPendiente(c);
            centro.depositar(cp);
            cp.awaitProcessed();
            resultado = cp.getResultado();
            red.getZona(c.getZona()).getVentana().traza (c.getIdConsumo()+ " - Tramitado: "+resultado);
            
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            System.err.println("Productor interrumpido: " + c.getIdConsumo());
        }finally{
            centro.producerFinished();
            resultado = red.getZona(c.getZona()).tramitarConsumo(c);
        }
        // 
        
        
    }
}
