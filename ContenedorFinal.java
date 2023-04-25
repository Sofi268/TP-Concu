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
    private int imagenesCopiadas;
    public ContenedorFinal(double tiempo) {
        contenedorFinal = new ArrayList<Imagen>(100);
        rand = new Random();
        this.tiempo = tiempo;
        imagenesCopiadas = 0;
    }
    public synchronized void pegarImagen(Imagen auxIm){
        auxIm.setImprovements(3,  Thread.currentThread().getName());
        auxIm.setNombre(auxIm.getNombre()+".copia");
        contenedorFinal.add(auxIm);
        imagenesCopiadas++;
        try {
            TimeUnit.MILLISECONDS.sleep((long)(tiempo/2)); //tiempo en copiar
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("imagen copiada: %s\n",auxIm.getNombre());
    }
    public synchronized int getImagenesCopiadas() {
        return imagenesCopiadas;
    }
    //Proceso 4:
    public synchronized ArrayList<Imagen> getContenedorFinal(){
        return contenedorFinal;
    }
}
