import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Contenedor Inicial:
 * Esta clase simula un contendedor dentro de una aplicacion para editar fotos, tanto en Tamaño como en Iluminacion.
 * LLeva el control para que las variables compartidas/atributos de la imagen no se corrompan,
 * como asi tambien, la secuencia en que cada imagen debe ser editada.
 */
public class ContenedorInicial{
    private ArrayList<Imagen> contenedorInicial;
    private int imagenesMejoradas;
    private int imagenesAjustadas;
    private double tiempo;
    public ContenedorInicial(double tiempo) {
        contenedorInicial = new ArrayList<Imagen>(100);
        imagenesMejoradas = 0;
        imagenesAjustadas = 0;
        this.tiempo = tiempo;
    }

    //Proceso 1: cargar las 100 imagenes en el contenedor inicial.
    /**
     * Este metodo controla que los hilos ingresen y lean la cantidad de imagenes de a uno por vez
     * @param i
     */
    public synchronized void agregarImagen(Imagen i){
        if(cantidadDeImagenes() != 100){
            i.setNombre("Imagen" + (cantidadDeImagenes()+1)); //cambia el nombre de cada imagen

            String aux = Thread.currentThread().getName();
                i.setImprovements(0,aux);
            contenedorInicial.add(i);
            try {
                TimeUnit.MILLISECONDS.sleep((long)(tiempo));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hilo " + Thread.currentThread().getName() + " : " + i.getNombre() ); //mostar por pantalla.
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
        try {
            synchronized (contenedorInicial.get(indice)) {
                if (!contenedorInicial.get(indice).getIluminacion()) { //verifica si ya fue mejorada por los 3 hilos.
                    contenedorInicial.get(indice).setImprovements();
                    try {
                        TimeUnit.MILLISECONDS.sleep((long)(tiempo/2));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (contenedorInicial.get(indice).getIluminacion()) {
                        String s = contenedorInicial.get(indice).getNombre();
                        setImagenesMejoradas(s);
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){}
    }

    /**
     * Este metodo aumenta un conteo de imagenes con la iluminacion mejorada
     */
    public void setImagenesMejoradas(String s){
        synchronized (this){         //para que se lleve una correcta cuenta de las imagenes mejoradas.
            imagenesMejoradas ++;
            System.out.println("Imagen Mejorada: " + s);
        }
    }
//diferencia: tiempo de ejecucion. recursos.
    /**
     * Este metodo retorna la cantidad de imagenes con la iluminacion mejorada
     * @return
     */
    public int getImagenesMejoradas() {
        synchronized (this) {
            return imagenesMejoradas;
        }
    }
    public void getImagenAAjustar(int indice, int l, int a){
        try{
            synchronized (contenedorInicial.get(indice)) {
                if (contenedorInicial.get(indice).getIluminacion() && !contenedorInicial.get(indice).getTamanio()) {
                    contenedorInicial.get(indice).setTamanio(l, a);

                    String aux = Thread.currentThread().getName();
                    contenedorInicial.get(indice).setImprovements(2, aux);

                    try {
                        TimeUnit.MILLISECONDS.sleep((long)(tiempo*3) );
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String s = contenedorInicial.get(indice).getNombre();
                    setImagenesAjustadas(s);
                }
            }
        }catch (IndexOutOfBoundsException e){}
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
    // Proceso 4: Realilzar copia en contenedor final y borrar el original.
    public Imagen sacarImagen(int indice) {
        try{
            if(contenedorInicial.get(indice).getIluminacion() && contenedorInicial.get(indice).getTamanio()){
                try {
                    TimeUnit.MILLISECONDS.sleep((long)(tiempo/2));//tiempo en sacar la imagen del contenedor inicial.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Imagen AUX = contenedorInicial.get(indice);
                contenedorInicial.remove(indice);
                return AUX;
            }
        }catch (IndexOutOfBoundsException e){}
        return null;
    }
    public int getCantidadImagenes(){
        return contenedorInicial.size();
    }
    //proceso 4
    public ArrayList<Imagen> getContenedorInicial(){
        return contenedorInicial;
    }
}