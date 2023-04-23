import java.util.Random;

/**
 * Proceso 2: Mejorar la iluminacion de cada imagen, en un tiempo aleatorio. cada imagen debe ser mejorada por los 3 hilos.
 * cantidad de hilos: 3
 */
public class MejoraIluminacion implements Runnable{
    private ContenedorInicial c;
    private Random rand;
    private boolean listo;
    public MejoraIluminacion(ContenedorInicial c) {
        this.c = c;
        rand = new Random();
        listo = false;
    }

    @Override
    public void run() {
        while(!listo){
            mejorarIluminacion();
        }
    }

    public void mejorarIluminacion(){ //syncronized
        c.getImagenAMejorar(rand.nextInt(100));
        setListo();
    }

    public void setListo(){
        if(c.getImagenesMejoradas() == 100){
            listo = true;
        }
    }
}
