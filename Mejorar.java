/**
 * Mejorar: Proceso encargado de la mejora de la iluminacion de la imagen de manera sincronizada
 * Hilos que ejecutan (3) : hilo 3/4/5
 * Acciones que realiza: *completar.
 */
public class Mejorar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesMejoradas;
    private ContenedorInicial ci;
    public Mejorar(long tiempo, ContenedorInicial ci,int cantidad_de_hilos) {
        this.tiempo = (tiempo*cantidad_de_hilos)/(100); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesMejoradas = 0;
        this.ci = ci;
    }
    @Override
    public void run() {

    }

    public int getImagenesMejoradas() {
        return imagenesMejoradas;
    }
}
