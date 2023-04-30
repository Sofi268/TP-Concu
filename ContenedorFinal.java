import java.util.ArrayList;

/**
 * ContenedorFinal: Administra el uso sincronico de la Lista contenedorFinal.
 * Para Leer y escribir los cambios que se les haga a los elementos de la lista.
 * acciones sobre los elementos: Agregar.
 */
public class ContenedorFinal {
    private ArrayList<Imagen> contenedorFinal;
    /**
     * Constructor de la clase ContenedorFinal.
     */
    public ContenedorFinal() {
        contenedorFinal = new ArrayList<>(100);
    }

    /**
     * Para agregar una imagen al contenedor final.
     */
    public synchronized void copiar(){

    }
    public synchronized ArrayList<Imagen> getContenedorFinal() {
        return contenedorFinal;
    }
}
