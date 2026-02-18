package energy;

public class ConsumoRunnable implements Runnable{
    private Consumo c;
    private RedEnergetica red;
    private String resultado;
    public ConsumoRunnable(Consumo c, RedEnergetica r){
        this.resultado = " ";
        this.c = c;
        this.red = r;
    }
    public synchronized void run(){
        resultado = red.getZona(c.getZona()).tramitarConsumo(c);
        red.getZona(c.getZona()).getVentana().traza (c.getIdConsumo()+ " - Tramitado: "+resultado);
        
    }
}
