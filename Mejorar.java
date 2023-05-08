import java.util.concurrent.TimeUnit;
/**
 * Mejorar: Proceso encargado de la mejora de la iluminacion de la imagen de manera sincronizada
 * Hilos que ejecutan (3) : hilo 3/4/5
 * Acciones que realiza: *completar.
 */
public class Mejorar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesMejoradas;
    private ContenedorInicial ci;
    public Mejorar(long tiempo, ContenedorInicial ci,int cantidad_de_hilos) {
        this.tiempo = ((tiempo)*cantidad_de_hilos)/(100*3); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesMejoradas = 0;
        this.ci = ci;
    }
    @Override
    public void run() {
        while(!listo) {
            int respuesta=0;
            //probar mejora
            synchronized (this) {
                if (getImagenesMejoradas() < 100) { //si se puede seguir mejorando imagenes.
                    respuesta = ci.mejora();
                    //System.out.println(respuesta);
                    if(respuesta == 2){ //imagen completa por los tres hilos.
                        imagenesMejoradas++;
                    }
                } else {
                    listo = true; //para que salga del while.termino su trabajo.
                }
            }
            if ((respuesta ==1) || (respuesta ==2)) { //si se encontro una imagen para mejorar.
                try { TimeUnit.MILLISECONDS.sleep(tiempo);}catch(InterruptedException e) { throw new RuntimeException(e); }
            }
        }
    }

    public int getImagenesMejoradas() {
        return imagenesMejoradas;
    }
}
