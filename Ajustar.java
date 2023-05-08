import java.util.concurrent.TimeUnit;

/**
 * Ajustar: Proceso encargado de ajustar el tamaño de la imagen de manera sincronizada
 * hilos que ejecutan: hilo 6/7/8
 * Acciones que realiza: -agarro una imagen random y pregunto si isTamanioOriginal es false y si esa imagen random fue ajustada de lo contrario la ajusto.
 */
public class Ajustar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesAjustadas;
    protected ContenedorInicial ci;

    public Ajustar(long tiempo,ContenedorInicial ci,int cantidad_de_hilos) {
        this.tiempo = ( tiempo*cantidad_de_hilos )/(100 ); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesAjustadas = 0;
        this.ci = ci;
    }
    @Override
    public void run() {
        while(!listo) {
            boolean respuesta=false;
            //probar Ajustar
            synchronized (this) {
                if (getImagenesAjustadas() < 100) { //si se puede seguir mejorando imagenes.
                    respuesta = ci.ajuste();
                    if(respuesta){ imagenesAjustadas++; }
                } else {
                    listo = true; //para que salga del while.termino su trabajo.
                }
            }
            if (respuesta) { //si se encontro una imagen para Ajustar.
                try { TimeUnit.MILLISECONDS.sleep(tiempo);}catch(InterruptedException e) { throw new RuntimeException(e); }
            }
        }
    }
    public int getImagenesAjustadas() {
        return imagenesAjustadas;
    }
}