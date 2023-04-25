/**
 * Proceso 2: Mejorar la iluminacion de cada imagen, en un tiempo aleatorio. cada imagen debe ser mejorada por los 3 hilos.
 * cantidad de hilos: 3
 */
public class MejoraIluminacion extends Edicion implements Runnable{
    public MejoraIluminacion(ContenedorInicial c) {
        super(c);
    }

    @Override
    public void run() {
        while(!listo){
            mejorarIluminacion();
        }
    }

    public void mejorarIluminacion(){ //syncronized
        c.getImagenAMejorar();
        setListo(c.getImagenesMejoradas());
    }
}
