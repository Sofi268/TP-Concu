import java.util.ArrayList;
import java.util.Random;

public class Cargar implements Runnable{
    ContenedorInicial ci;
    private boolean listo;
    private Random rand;
    public Cargar(ContenedorInicial ci) {
        this.ci = ci;
        rand = new Random();
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
