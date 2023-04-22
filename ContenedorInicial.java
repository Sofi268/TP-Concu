import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase simula un contendedor dentro de una aplicacion para editar fotos, tanto en Tamaño como en Iluminacion.
 * LLeva el control para que las variables compartidas/atributos de la imagen no se corrompan,
 * como asi tambien, la secuencia en que cada imagen debe ser editada.
 *
 */
public class ContenedorInicial{
    private ArrayList<Imagen> contenedorInicial;
    private int imagenesMejoradas;
    private int imagenesAjustadas;
    private boolean listo;
    public ContenedorInicial() {
        contenedorInicial = new ArrayList<Imagen>(100);
        listo = false;
        imagenesMejoradas = 0;
        imagenesAjustadas = 0;
    }


    /**
     * Este metodo controla que los hilos ingresen y lean la cantidad de imagenes de a uno por vez
     * @param i
     */
    public synchronized void agregarImagen(Imagen i){
        if(cantidadDeImagenes() != 100){
            i.setNombre("Imagen" + cantidadDeImagenes());
            contenedorInicial.add(i);
            try {
                TimeUnit.MILLISECONDS.sleep(25);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hilo " + Thread.currentThread().getName() + ": imagen-" + cantidadDeImagenes());
        }

    }

    /**
     * Este metodo retorna la cantidad de imagenes que los hilos cargan al contenedor (Sin ser modificadas)
     * @return contenedorInicial.size()
     */
    public synchronized int cantidadDeImagenes(){
        return contenedorInicial.size();
    }

    /**
     * Este metodo nos ofrece el control para la mejora de una imgen.
     * Mientras la imagen esta tomada, no permite que otro hilo la modifique.
     * Si ya fue modificada por los tres hilos, no puede ser vuelta a tomar
     */
    public void getImagenAMejorar(int indice){
        synchronized (contenedorInicial.get(indice)){
            if(!contenedorInicial.get(indice).getIluminacion()){
                contenedorInicial.get(indice).setImprovements();
                try {
                    TimeUnit.MICROSECONDS.sleep(8333);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(contenedorInicial.get(indice).getIluminacion()){

                    String s = contenedorInicial.get(indice).getNombre();
                    setImagenesMejoradas(s);
                }
            }
        }
    }

    /**
     * Este metodo aumenta un conteo de imagenes con la iluminacion mejorada
     */
    public void setImagenesMejoradas(String s){
        synchronized (this){
            imagenesMejoradas ++;
            System.out.println("Imagen Mejorada: " + s);
        }
    }

    /**
     * Este metodo retorna la cantidad de imagenes con la iluminacion mejorada
     * @return
     */
    public int getImagenesMejoradas() {
        synchronized (this){
            return imagenesMejoradas;
        }
    }

    public void getImagenAAjustar(int indice, int l, int a){
        synchronized (contenedorInicial.get(indice)){
            if(contenedorInicial.get(indice).getIluminacion() && !contenedorInicial.get(indice).getTamanio()){
                contenedorInicial.get(indice).setTamanio(l, a);
                try {
                    TimeUnit.MICROSECONDS.sleep(8333);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String s = contenedorInicial.get(indice).getNombre();
                setImagenesAjustadas(s);
            }
        }
    }
    public void setImagenesAjustadas(String s){
        synchronized (this){
            imagenesAjustadas ++;
            System.out.println("Imagen Ajustada: " + s);
        }
    }
    public int getImagenesAjustadas() {
        synchronized (this){
            return imagenesAjustadas;
        }
    }

    public Imagen editada(int indice){
        if(contenedorInicial.get(indice).getIluminacion() && contenedorInicial.get(indice).getTamanio()){

        }
        return null;
    }

}
