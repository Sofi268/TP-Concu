import java.util.concurrent.TimeUnit;

/**
 * Cargar: Proceso encargado de cargar imagenes nuevas en el contenedor inicial asignandole un nombre
 *      especial a cada uno de acuerdo al nªde imagen cargada que es.
 * Hilos que ejecutan: Hilos 1/2
 * Acciones que realiza: *completar.
 */
public class Cargar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesCargadas;

    public Cargar(long tiempo) {
        this.tiempo = (tiempo*2)/(100); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesCargadas = 0;
    }

    @Override
    public void run() {
        while(!listo){
            setImagenCargada();
            if (getImagenesCargadas() == 100){             //condicion de salir
                listo = true;
                break; //sale
            }
            //accion de cargar
            try {
                TimeUnit.MILLISECONDS.sleep(tiempo);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf(" %s : %d imagenes\n",Thread.currentThread().getName(),getImagenesCargadas());
    }

    public synchronized void setImagenCargada() {
        imagenesCargadas += 1;
    }

    public synchronized int getImagenesCargadas() {
        return imagenesCargadas;
    }
}