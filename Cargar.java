import java.util.ArrayList;

/**
 * Proceso 1: Cargar imagenes en un contenedor inicial, en un tiempo aleatorio
 * Cantidad de hilos: 2
 */
public class Cargar implements Runnable{
    ContenedorInicial ci;
    private boolean listo;
    public Cargar(ContenedorInicial ci) {
        this.ci = ci;
        listo = false;
    }

    @Override
    public void run() {
        while (!listo) {
            ci.agregarImagen(new Imagen());
            if (ci.cantidadDeImagenes() == 100) {
                listo = true;
            }
        }
    }
}
