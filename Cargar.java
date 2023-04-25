import java.util.ArrayList;
/**
 * Proceso 1: Cargar imagenes en un contenedor inicial,
 * Cantidad de hilos:2
 */
public class Cargar implements Runnable{
    private ContenedorInicial ci;
    private boolean listo;
    public Cargar(ContenedorInicial ci) {
        this.ci = ci;
        listo = true;
    }

    @Override
    public void run() {
        while (listo) {
            listo = ci.agregarImagen(new Imagen());
        }
    }
}
