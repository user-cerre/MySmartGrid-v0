package energy;

public class CuentaEnergetica {
    private double balanceKWh;
    private double balanceRapidaKWh;
    private double balanceEolicaKWh;
    private double balanceSolarKWh;

    public CuentaEnergetica(double balanceInicialKWh) {
        this.balanceKWh = balanceInicialKWh;
    }

    public synchronized void anotaConsumo(double rapida, double eolica, double solar) {
        this.balanceKWh += rapida+eolica+solar;
        this.balanceRapidaKWh += rapida;
        this.balanceEolicaKWh += eolica;
        this.balanceSolarKWh += solar;
    }

    public double getBalanceRapidaKWh() {
        return balanceRapidaKWh;
    }
    public double getBalanceEolicaKWh() {
        return balanceEolicaKWh;
    }public double getBalanceSolarKWh() {
        return balanceSolarKWh;
    }public double getBalanceKWh() {
        return balanceKWh;
    }
    
    @Override
    public String toString() {
        return "CuentaEnergetica{balanceKWh=" + balanceKWh + "}";
    }
}
