package energy;

public class Task implements Runnable{
    private Consumo c;
    private RedEnergetica red;
    public Task(Consumo c, RedEnergetica r){
        this.c = c;
        this.red = r;
    }    
    public void run(){
        ConsumoRunnable cRunnable = new ConsumoRunnable(c, red);
        cRunnable.run();    
    }
    
}
