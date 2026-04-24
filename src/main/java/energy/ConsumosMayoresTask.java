package energy;

import java.util.concurrent.RecursiveTask;
import java.util.ArrayList;
import java.util.List;



public class ConsumosMayoresTask extends RecursiveTask<List<Consumo>>{
    private static final int max = 10;
    private final List<Consumo> consumos;
    private final int inicio;
    private final int fin;

    public ConsumosMayoresTask(List<Consumo> consumos, int inicio, int fin){
        this.consumos = consumos;
        this.inicio = inicio;
        this.fin = fin;
    }
    @Override
    protected List<Consumo> compute(){
        int size = fin - inicio;
        if(size < max){
            List<Consumo> resultado = new ArrayList<>();
            for(int i = inicio; i < fin ; i++){
                Consumo c = consumos.get(i);
                if(c.getTotalKWh() > 20){
                    resultado.add(c);
                }
            }
            return resultado;
        }
        int mitad = inicio + size/2;
        ConsumosMayoresTask left = new ConsumosMayoresTask(consumos, inicio, mitad);
        ConsumosMayoresTask right = new ConsumosMayoresTask(consumos, mitad, fin);

        left.fork();
        List<Consumo> resRight = right.compute();
        List<Consumo> resLeft = left.join();

        resLeft.addAll(resRight);
        return resLeft;
    }
}
