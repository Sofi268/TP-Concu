import java.util.ArrayList;
import java.util.Random;
/**
 * ContenedorInicial: Administra el uso sincronico de la Lista contenedorInicial.
 * Para Leer y escribir los cambios que se les haga a los elementos de la lista.
 * acciones sobre los elementos: Agregar, Mejorar, Ajustar, eliminar. posiblemente:Buscar
 */
public class ContenedorInicial {
    private ArrayList<Imagen> contenedorInicial;
    /**
     * Constructor de la clase ContenedorInicial.
     */
    public ContenedorInicial() {
        contenedorInicial = new ArrayList<>(100); //creamos una Lista con capacidad de 100 elementos.
    }
    //metodos Propios de cada proceso:
    //  los 4 metodos poseen Syncrinized para que mantenga una correcta sincronizacion de la lista
    //  EJ: para que 2 hilo no quieran Mejorar iluminacion de una imagen al mismo tiempo, ya que se obtuvieron datos desincronizados de la imagen.

    /**
     * Agrega un elemento a la lista.
     * @param Im : imagen que se desea agregar al contenedor inicial.
     */
    public void carga(Imagen Im) {
        synchronized(this) {
            contenedorInicial.add(Im);
        }
    }

    public int mejora() {
        synchronized(this) {
            Random rand = new Random();
            try {
                int indice = rand.nextInt(contenedorInicial.size());
                String name_hilo = Thread.currentThread().getName();
                if (contenedorInicial.get(indice).isIluminacionOriginal() && !contenedorInicial.get(indice).getImprovement().contains(name_hilo)) { //que no este mejorada, y que no haya sido mejorada por este hilo.
                    contenedorInicial.get(indice).setImprovement(name_hilo); //agregamos a la lista de hilos que tocaron la imagen.hilos 3y4y5
                    if (contenedorInicial.get(indice).getImprovement().size() == 4) { //se completo la cantidad de mejoras.
                        contenedorInicial.get(indice).setIluminacionOriginal(false);
                        return 2; //se termino de mejorar una imagen.
                    }
                    return 1; //se mejoro una imagen.
                }
            } catch (IllegalArgumentException e) { }
            return 0;
        }
    }

    public boolean ajuste() {
        synchronized(this) {
            Random rand = new Random();
            try {
                int indice = rand.nextInt(contenedorInicial.size());
                String name_hilo = Thread.currentThread().getName();
                if (!contenedorInicial.get(indice).isIluminacionOriginal() && contenedorInicial.get(indice).isTamanioOriginal()) { //que si este mejorada y que no este ajustada.
                    contenedorInicial.get(indice).setImprovement(name_hilo); //agregamos a la lista de hilos que tocaron la imagen. hilo 6o7o8
                    contenedorInicial.get(indice).setTamanioOriginal(false);
                    return true; //se termino de Ajustar una imagen.
                }
            } catch (IllegalArgumentException e) {}
            return false;
        }
    }

    /**
     * Aqui solo se elimina de la lista y se retorna la imagen a copiar
     * @return imagen a copiar.
     */
    public Imagen borrar() {
        synchronized(this) {
            Random rand = new Random();
            try {
                int indice = rand.nextInt(contenedorInicial.size());
                String name_hilo = Thread.currentThread().getName();
                if (!contenedorInicial.get(indice).isIluminacionOriginal() && !contenedorInicial.get(indice).isTamanioOriginal()) { //que si este mejorada y que esta ajustada.
                    contenedorInicial.get(indice).setImprovement(name_hilo); //agregamos a la lista de hilos que tocaron la imagen. hilo 9o10
                    Imagen aux = contenedorInicial.get(indice);
                    contenedorInicial.remove(indice);
                    return aux; //retorno la imagen lista.
                }
            } catch (IllegalArgumentException e) { }
            return null;
        }
    }

    //metodos get:

    /**
     * Metodo encargado de devolver la lista de contenedorInicial
     * La unica razon del syncronized es para sincronizar el Estadistico con los procesos.
     * @return
     */
    public ArrayList<Imagen> getContenedorInicial() {
        synchronized(this) {
            return contenedorInicial;
        }
    }
}
/*
 * notas:
 *   1) NO colocar tiempos en esta clase, los tiempos van es sus respectivos procesos.
 *   2) Busqueda aleatoria: se crea el rand en el bloque sincronizado de set.
 *           el rango establecido sera la cantidad de elementos que contenga la lista en ese momento.
 *           EJ: rand.nextInt(getContenedorInicial().size());  //genera un n random del 0 a la cantidad de elementos.
 *   3) Reformular la parde Doc a gusto del Programador.
 *   4) tiempo: Cada proceso se encargara de administrar con ese tiempo, cuanto deberan tardar cada Accion.formula: (tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
 *  sincrozar de individual: cargar, modificar y copiar.
 * */