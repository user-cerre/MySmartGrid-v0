package energy;

import java.util.List;
import java.util.concurrent.Callable;

public class MaxConsumoTask implements Callable <Consumo> {
    private final List<Consumo> consumos;

    public MaxConsumoTask(List<Consumo> consumos){
        this.consumos = consumos;
    }
    @Override
    public Consumo call(){
        if(consumos == null || consumos.isEmpty()){
            return null;
        }
        return consumos.stream()
        .max((c1, c2) -> Double.compare(c1.getTotalKWh(), c2.getTotalKWh())).orElse(null);
    }
    
}
