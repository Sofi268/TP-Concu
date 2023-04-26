/**
 * Mejorar: Proceso encargado de la mejora de la iluminacion de la imagen de manera sincronizada
 * Hilos que ejecutan (3) : hilo 3/4/5
 * Acciones que realiza: *completar.
 */
public class Mejorar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesMejoradas;

    public Mejorar(long tiempo) {
        this.tiempo = tiempo;
        listo = false;
        imagenesMejoradas = 0;
    }
    @Override
    public void run() {

    }
}
