import java.util.ArrayList;

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
     * #nota: 1)cambiar nombre carga a gusto.
     *        2)completar param y return.
     * Agrega un elemento a la lista.
     * @param imagen a agregar
     * @return
     */
    public void carga() {
       //implementar codigo jeje.
    }

    /**
     * #nota: 1)cambiar nombre Proceso_2 a gusto.
     *        2)completar param y return.
     *  Marcar como mejorada directamente la imagen. o borrar en caso de no usarse.
     * @param
     * @return
     */
    public synchronized void mejora() {
        //implementar codigo jeje.
    }

    /**
     * #nota: 1)cambiar nombre Proceso_3 a gusto.
     *        2)completar param y return. o borrar en caso de no usarse.
     * Marcar directamente como ajustada la imagen.
     * @param
     * @return
     */
    public synchronized void ajuste() {
        //implementar codigo jeje.
    }

    /**
     * #nota: 1)cambiar nombre "borrar" a gusto.
     *        2)completar param y return.
     * Aqui solo se elimina de la lista.
     * @return imagen a copiar.
     */
    public synchronized Imagen borrar() {
        //implementar codigo jeje.
        return null;
    }

    //metodos get:

    /**
     * Metodo encargado de devolver la lista de contenedorInicial
     * La unica razon del syncronized es para sincronizar el Estadistico con los procesos.
     * @return
     */
    public synchronized ArrayList<Imagen> getContenedorInicial() {
        return contenedorInicial;
    }
}
/*
 * notas:
 *   1) NO colocar tiempos en esta clase, los tiempos van es sus respectivos procesos.
 *   2) Busqueda aleatoria: se crea el rand en el bloque sincronizado de set.
 *           el rango establecido sera la cantidad de elementos que contenga la lista en ese momento.
 *           EJ: rand.nextInt(getContenedorInicial().size()-1);  //genera un n random del 0 a la cantidad de elementos. el -1: por la diferencia entre el indice de la lista que comienza en 0.
 *   3) Reformular la parde Doc a gusto del Programador.
 *   4) tiempo: Cada proceso se encargara de administrar con ese tiempo, cuanto deberan tardar cada Accion.formula: (tiempo * hilos )/( 100 * actividades_sobre_cada_imagen )
 *  */