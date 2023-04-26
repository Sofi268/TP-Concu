/**
 * Ajustar: Proceso encargado de ajustar el tamaño de la imagen de manera sincronizada
 * hilos que ejecutan: hilo 6/7/8
 * Acciones que realiza: *completar.
 */
public class Ajustar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesAjustadas;

    public Ajustar(long tiempo) {
        this.tiempo = tiempo;
        listo = false;
        imagenesAjustadas = 0;
    }

    @Override
    public void run() {

    }
}
