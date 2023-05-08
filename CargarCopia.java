import java.util.concurrent.TimeUnit;

/**
 * CargarCopia: Proceso encargado de copiar la imagen en el contenedor final
 *      y de eliminarla en el contenedor inicial de manera sincronizada.
 * Acciones que realiza: Saca y elimina una imagen de indice random (si ya esta mejorada y ajustada) del ci y la pega en el cf.
 */
public class CargarCopia implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesCopiadas;
    private ContenedorInicial ci;
    private ContenedorFinal cf;
    public CargarCopia(long tiempo, ContenedorInicial ci, ContenedorFinal cf,int cantidad_de_hilos) {
        this.tiempo = ( tiempo*cantidad_de_hilos )/(100); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesCopiadas = 0;
        this.ci = ci;
        this.cf = cf;
    }
    @Override
    public void run() {
        while(!listo){
            Imagen respuesta = null;
            //probar copia.
            synchronized (this){
                if(getImagenesCopiadas()<100){
                    respuesta = ci.borrar();
                    if(respuesta != null){
                        cf.copiar(respuesta);
                        imagenesCopiadas++;
                    }
                }else{ listo = true;}
            }
            if (respuesta != null) {
                try { TimeUnit.MILLISECONDS.sleep(tiempo);}catch(InterruptedException e) { throw new RuntimeException(e); }
            }
        }
    }

    public int getImagenesCopiadas() {
        return imagenesCopiadas;
    }
}
