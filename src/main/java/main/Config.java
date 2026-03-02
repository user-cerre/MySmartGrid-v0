package main;

public final class Config {
    private Config() {}

    // --- Configuracion general ---
    public static final int NUM_OPERADORES_POR_ZONA = 3;// Número de operarios 
    public static final int NUM_ZONAS = 5;
    
    // --- Ventanas ---
    public static final int TAMAÑO_VENTANA = 250;

    // Baterias (kWh)
    public static final double CAPACIDAD_BATERIA = 1000.0;//1000 (estaba a 200.0)
    public static final double NIVEL_INICIAL_BATERIA = 1000.0;//1000 (estaba a 120.0)

    // Ficheros
    public static final String FICHERO_CONSUMOS = "consumos5.bin";//era el 5

    // Generacion de consumos
    public static final int NUM_CONSUMOS_A_GENERAR = 50;

    // Semilla para generacion reproducible
    public static final long SEED = 12345L;

    // Operarios de carga
    public static final int NUM_OPERARIOS_CARGA_POR_ZONA = 2;  // Número de operarios de carga por zona
    public static final double CANTIDAD_RECARGA_POR_CICLO = 100.0;  // kWh que recarga cada operario en cada ciclo
}
