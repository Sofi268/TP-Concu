import java.util.ArrayList;
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
