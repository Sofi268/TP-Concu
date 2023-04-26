/**
 * CargarCopia: Proceso encargado de copiar la imagen en el contenedor final
 *      y de eliminarla en el contenedor inicial de manera sincronizada.
 * Acciones que realiza: *completar
 */
public class CargarCopia implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesCopiadas;
    public CargarCopia(long tiempo) {
        this.tiempo = tiempo;
        listo = false;
        imagenesCopiadas = 0;
    }
    @Override
    public void run() {

    }
}
