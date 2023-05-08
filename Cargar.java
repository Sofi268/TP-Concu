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
    private int limite; //setea el limite de imagenes que se desean procesar.
    private ContenedorInicial ci;


    /**
     * constructor del Proceso 1 de carga.
     * @param tiempo tiempo en ms que debe durar el proceso.
     * @param ci Donde se guardara el objeto de ContenedorInicial.
     */
    public Cargar(long tiempo,ContenedorInicial ci,int cantidad_de_hilos) {
        this.tiempo = (tiempo*cantidad_de_hilos)/(100); //(tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
        listo = false;
        imagenesCargadas = 0;
        this.ci = ci;
        limite = 100;  //por default:100. para que sea codigo reusable.
    }

    /**
     * Pasos:
     * 1) El hilo comprueba cuantas imagen se van cargando... de forma sincronica.
     *      <100 : carga la imagen en contenedor inical y espera...
     *      =100 : sale del while.
     * 2) Para enviar la imagen, se copia en el indice el numero de imagen carga que es
     *      y se le agrega al nombre de la imagen agregada.
     */
    @Override
    public void run() {
        while(!listo){
            int indice = probarCargar(); //para encontrar error.
            if(indice != -1){  //true: se puede agregar una imagen.
                Imagen ImAux = new Imagen(); //creamos una nueva imagen.
                ImAux.setNombre(ImAux.getNombre()+(indice+1)); //modificamos el nombre
                ImAux.setImprovement(Thread.currentThread().getName());
                ci.carga(ImAux);
                try { TimeUnit.MILLISECONDS.sleep(tiempo);
                } catch (InterruptedException e) { throw new RuntimeException(e); }
            }else{  //false: no se pueden agregar mas imagenes.
                listo = true;
            }
        }
    }

    /**
     * Solo un hilo puede ingresar a esta parte.
     * @return true : (se pueden seguir agregando imagenes).
     * @return false : (se llego al limite no se puede seguir agregando imagenes al contenedor).
     */
    public synchronized int probarCargar(){
        int indice = getImagenesCargadas();
        if(indice < limite){     //aun no se llego al limite.
            setImagenCargada(); //aumentamos en uno las cantidad de imagenes procesadas.
            return indice;
        }else{  //se llego al limite.
            return -1; //no se pueden agregar mas imagenes.
        }
    }

    public void setImagenCargada() {
        imagenesCargadas += 1;
    }

    public int getImagenesCargadas() {
        return imagenesCargadas;
    }
}