import java.util.ArrayList;
import java.util.Random;

public class ContenedorFinal {
    private ArrayList<Imagen> contenedorFinal;
    private Random rand;
    public ContenedorFinal() {
        contenedorFinal = new ArrayList<Imagen>(100);
        rand = new Random();
    }
    public synchronized void Copiar(ContenedorInicial ci){
        Imagen auxIm = ci.sacarImagen(rand.nextInt(100));//eliminar elemento del contenedor inicial.
        if(auxIm != null){
            auxIm.setNombre(auxIm.getNombre()+".copia");
            contenedorFinal.add(auxIm);
            System.out.printf("imagen copiada: %s\n",auxIm.getNombre());
        }
    }
    public synchronized int getImagenesCopiadas() {
        return contenedorFinal.size();
    }
}
