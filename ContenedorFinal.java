import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * ContenedorFinal:
 */
public class ContenedorFinal {
    private ArrayList<Imagen> contenedorFinal;
    private Random rand;
    private double tiempo;
    public ContenedorFinal(double tiempo) {
        contenedorFinal = new ArrayList<Imagen>(100);
        rand = new Random();
        this.tiempo = tiempo;
    }
    public synchronized void Copiar(ContenedorInicial ci){
        Imagen auxIm = ci.sacarImagen(rand.nextInt(100));//eliminar elemento del contenedor inicial.
        if(auxIm != null){
            String aux = Thread.currentThread().getName();
            auxIm.setImprovements(3, aux);
            auxIm.setNombre(auxIm.getNombre()+".copia");
            contenedorFinal.add(auxIm);
            try {
                TimeUnit.MILLISECONDS.sleep((long)(tiempo/2)); //tiempo en copiar
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("imagen copiada: %s\n",auxIm.getNombre());
        }
    }
    public synchronized int getImagenesCopiadas() {
        return contenedorFinal.size();
    }
    //Proceso 4:
    public synchronized ArrayList<Imagen> getContenedorFinal(){
        return contenedorFinal;
    }
}
