import java.util.ArrayList;

public class Cargar implements Runnable{
    ContenedorInicial ci;
    ContenedorFinal cf;
    private boolean listo;
    public Cargar(ContenedorInicial ci, ContenedorFinal cf) {
        this.ci = ci;
        listo = false;
    }

    @Override
    public void run() {
        while (!listo){
            ci.agregarImagen(new Imagen());
            if(ci.cantidadDeImagenes() == 100){
                listo = true;
            }
        }
    }
}
