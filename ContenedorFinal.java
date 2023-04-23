import java.util.ArrayList;

public class ContenedorFinal {
    private ArrayList<Imagen> contenedorFinal;
    public ContenedorFinal() {
        contenedorFinal = new ArrayList<Imagen>(100);
    }
    public synchronized void Copiar(Imagen i){
        i.setNombre(i.getNombre()+".copia");
        contenedorFinal.add(i);
    }
    public synchronized int getImagenesCopiadas() {
        return contenedorFinal.size();
    }
}
