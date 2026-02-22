

package energy;

import java.util.Objects;

public class ConsumoPendiente {

    private final Consumo consumo;
    private String resultado;
    private boolean processed;
    private Throwable error;

    public ConsumoPendiente(Consumo _c){
        this.consumo = _c;
        this.resultado = null;
        this.processed = false;
        this.error = null;
    }

    public Consumo getConsumo(){
        return this.consumo;
    }
    
    public synchronized void awaitProcessed() throws InterruptedException {
        while(!processed){
            wait();
        }
    }
    
    public synchronized void markProcessed (String _r){
        this.resultado = _r;
        this.processed = true;
        notify();
    }
    
    public synchronized String getResultado(){
        return this.resultado;
    }
    
    public synchronized boolean isProcessed(){
        return this.processed;
    }
    
    public synchronized Throwable getError(){
        return this.error;
    }
    
    public synchronized void markFailed(Throwable t){
        this.error = t;
        this.resultado = "ERROR: " + (t != null ? t.getMessage() : "desconocido");
        this.processed = true;
        notify();
    }
}