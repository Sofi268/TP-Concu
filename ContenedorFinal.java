import java.util.ArrayList;

/**
 * ContenedorFinal: Administra el uso sincronico de la Lista contenedorFinal.
 * Para Leer y escribir los cambios que se les haga a los elementos de la lista.
 * acciones sobre los elementos: Agregar.
 */
public class ContenedorFinal {
    private ArrayList<Imagen> contenedorInicial;
    /**
     * Constructor de la clase ContenedorFinal.
     * @param contenedorInicial
     */
    public ContenedorFinal() {
        contenedorInicial = new ArrayList<>(100);
    }

    /**
     * Para agregar una imagen al contenedor final.
     */
    public synchronized void copiar(){

    }
    public synchronized ArrayList<Imagen> getContenedorInicial() {
        return contenedorInicial;
    }
}
