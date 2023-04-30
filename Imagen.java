import java.util.ArrayList;
/**
 * Imagen: es la clase que modela los atributos, caracteristicas.DA INFORMACION sobre la imagen
 * Se le puede pedir: sus datos basicos(nombre)
 */
public class Imagen {
    private String nombre;
    private boolean iluminacionOriginal;
    private boolean tamanioOriginal;
    private ArrayList<String> improvement; //llevar la cuenta de que hilos tocaron la imagen.tamaño inicial es 10 pero se agranda a demanda.

    /**
     * Constructor de la clase Imagen:
     *  se definen los valores por defecto al crear una instancia/objeto de la clase.
     */
    public Imagen() {   //valores Default.
        this.nombre = "Imagen";
        this.iluminacionOriginal = true;
        this.tamanioOriginal = true;
        improvement =new ArrayList<>();
    }

    //Metodos Set:
    /**
     * Para Setear el nombre de la imagen.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Para indicar que la imagen fue Mejorada.
     * @param modificacion
     */
    public void setIluminacionOriginal(boolean modificacion) {
        this.iluminacionOriginal = modificacion;
    }

    /**
     * Para indicar que el tamanio de la imagen fue ajustado.
     * @param modificacion
     */
    public void setTamanioOriginal(boolean modificacion) {
        this.tamanioOriginal = modificacion;
    }

    /**
     * Para registrar la interaccion de un hilo con la imagen.
     * @param nombreHilo
     */
    public void setImprovement(String nombreHilo) {
        this.improvement.add(nombreHilo);
    }

    //Metodos Get:
    /**
     * Dicho nombre puede ser:
     *      imagen+cantidadDeImagenesCargadas en el proceso de Carga P1
     *      imagen+cantidadDeImagenesCopiadas en el proceso de Copia P2
     * @return Nombre de la imagen
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return true: si no se Mejoro la iluminacion de la imagen.
     */
    public boolean isIluminacionOriginal() {
        return iluminacionOriginal;
    }

    /**
     * @return true: si no se Ajusto el tamanio de la imagen
     */
    public boolean isTamanioOriginal() {
        return tamanioOriginal;
    }

    /**
     * Metodo usado fundamentalmente en la verificacion de mejora por un hilo a la imagen en cuestion.
     * @return lista de nombres de los hilos que interactuaron con este elemento.
     */
    public ArrayList<String> getImprovement() {
        return improvement;
    }
}
