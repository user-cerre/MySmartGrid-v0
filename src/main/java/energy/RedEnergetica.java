package energy;

import operators.CentroControl;
import pcd.util.Ventana;
import storage.Bateria;
import storage.Eolica;
import storage.Solar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.Config;

public class RedEnergetica {
    private final List<ZonaEnergetica> zonas;
    private final List<CentroControl> centros;
    ZonaEnergetica zona = null;

    public RedEnergetica(int numZonas, double capacidadBateria, double nivelInicialBateria) {
        if (numZonas <= 0) throw new IllegalArgumentException("numZonas debe ser > 0");

        List<ZonaEnergetica> tmp = new ArrayList<>(numZonas);
        List<CentroControl> ccs = new ArrayList<>(numZonas);
        Bateria bateria = null;
        Eolica eolica = null;
        Solar solar = null;
        
        for (int i = 0; i < numZonas; i++) {
            CentroControl cc = new CentroControl();
            ccs.add(cc);
            CuentaEnergetica cuenta = new CuentaEnergetica(0.0);
            bateria = new Bateria(capacidadBateria, nivelInicialBateria);
            eolica = new Eolica(capacidadBateria, nivelInicialBateria);
            solar = new Solar(capacidadBateria, nivelInicialBateria);
            Ventana ventanaZona = new Ventana (i*Config.TAMAÑO_VENTANA,1,Config.TAMAÑO_VENTANA, Config.TAMAÑO_VENTANA,"Zona "+i);
            zona = new ZonaEnergetica(i, cuenta, bateria,eolica, solar, cc, ventanaZona);
            bateria.setVentana(ventanaZona);
            cc.setZona(zona);
            tmp.add(zona);

        }

        this.zonas = Collections.unmodifiableList(tmp);
        this.centros = Collections.unmodifiableList(ccs);
    }

    public List<ZonaEnergetica> getZonas() { return zonas; }
    public ZonaEnergetica getZona(int idZona) { return zonas.get(idZona); }
    public CentroControl getCentroControl(int idZona) { return centros.get(idZona); }

    public double auditoriaBalanceTotal() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getCuenta().getBalanceKWh();
        return sum;
    }
    public double BalanceRapida() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getCuenta().getBalanceRapidaKWh();
        return sum;
    }
    public double BalanceEolica() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getCuenta().getBalanceEolicaKWh();
        return sum;
    }
    public double BalanceSolar() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getCuenta().getBalanceSolarKWh();
        return sum;
    }

    public double auditoriaEnergiaDisponibleTotal() {
        return totalEnergiaRapida() + totalEnergiaEolica() + totalEnergiaSolar();
    }
    public double totalEnergiaRapida() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getBateria().getNivelActualKWh();
        return sum;
    }
    public double totalEnergiaEolica() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getEolica().getNivelActualKWh();
        return sum;
    }
    public double totalEnergiaSolar() {
        double sum = 0.0;
        for (ZonaEnergetica z : zonas) sum += z.getSolar().getNivelActualKWh();
        return sum;
    }
    public void imprimeAuditoria() {
        System.out.println("\n=== AUDITORIA RED ===");
        zonas.parallelStream()
        .forEachOrdered(z->{
            System.out.println("Zona " + z.getIdZona()
                    + " | consumidos=" + fmt(z.getCuenta().getBalanceKWh()) + " kWh"
                    + " | rapida=" + fmt(z.getBateria().getNivelActualKWh()) + " kWh"
                    + " | solar=" + fmt(z.getSolar().getNivelActualKWh()) + " kWh"
                    + " | eolica=" + fmt(z.getEolica().getNivelActualKWh()) + " kWh");
        });
        System.out.println("=====================");
        System.out.println("CONSUMOS | Rapida: "+ fmt(BalanceRapida())+ " kWh"+" |Eolica: "+fmt(BalanceEolica())+ " kWh"+" |Solar:"+ fmt(BalanceSolar())+ " kWh");
        System.out.println("DISPONIBLE | Rapida: "+ fmt(totalEnergiaRapida())+ " kWh"+" |Eolica: "+fmt(totalEnergiaEolica())+ " kWh"+" |Solar:"+ fmt(totalEnergiaSolar())+ " kWh");
        System.out.println("=====================");
        System.out.println("Consumo total: " + fmt(auditoriaBalanceTotal()) + " kWh");
        System.out.println("Energia disponible total: " + fmt(auditoriaEnergiaDisponibleTotal()) + " kWh");
        System.out.println("=====================");
    }

    private String fmt(double x) {
        return String.format(java.util.Locale.ROOT, "%.2f", x);
    }
}
