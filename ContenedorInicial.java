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
    private int imagenesCargadas;
    private int imagenesMejoradas;
    private int imagenesAjustadas;
    private double tiempo;
    private Object controlModificaciones; //controlar la zona critica de la cuenta de imagenes mejoradas.

    public ContenedorInicial(double tiempo) {
        contenedorInicial = new ArrayList<Imagen>(100);
        imagenesCargadas = 0;
        imagenesMejoradas = 0;
        imagenesAjustadas = 0;
        this.tiempo = tiempo;
        controlModificaciones =  new Object();
    }

    //Proceso 1: cargar las 100 imagenes en el contenedor inicial.
    /**
     * Este metodo controla que los hilos ingresen y lean la cantidad de imagenes de a uno por vez
     * @param i : imagen a cargar
     */
    public synchronized boolean agregarImagen(Imagen i){ //carga una imagenNueva.
        if(getImagenesCargadas() < 100){
            i.setNombre("Imagen" + (getImagenesCargadas()+1));               //cambia el nombre de cada imagen
            i.setImprovements(0,Thread.currentThread().getName());    //contabiliza la interaccion hilo/proceso.
            contenedorInicial.add(i);
            try {
                TimeUnit.MILLISECONDS.sleep((long)(tiempo));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hilo " + Thread.currentThread().getName() + " : " + i.getNombre() ); //mostar por pantalla.
            imagenesCargadas++;
            if(getImagenesCargadas() < 100){
                return true;
            }
        }
        return false;  //no pudiste entrar.
    }
    /**
     * Lee la cantidad de imagenes que fueron cargadas.
     * @return imagenesCargadas : cantidad de imagenes que se cargaron.
     */
    public int getImagenesCargadas(){
        return imagenesCargadas;
    }

    //proceso 2: Mejorar la iluminacion de las imagenes.
    /**
     * Este metodo nos ofrece el control para la mejora de una imgen.
     * Mientras la imagen esta tomada, no permite que otro hilo la modifique.
     * los 3 hilos puede procesar imagenes al mismo tiempo.
     * Si ya fue modificada por los tres hilos, no puede ser vuelta a tomar
     */
    public synchronized void getImagenAMejorar(){
        int indice = 0;
        boolean encuentra = false;
        for (Imagen aux : getContenedorInicial()) {
            if (!aux.getIluminacion()) { //verifica si ya fue mejorada por los 3 hilos.
                encuentra = true;
                break;
            }
            indice++;
        }
        if( !encuentra ){ return; } //no encontro nada para trabajar.

        synchronized (contenedorInicial.get(indice)) { //se toma la llave de la imagen.por si justo otra imagen quiere tomar la misma.
            contenedorInicial.get(indice).setImprovements();  //contabiliza que el hilo toco esta imagen.
            try {
                TimeUnit.MILLISECONDS.sleep((long) (tiempo / 2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }  //tiempo de la mejora de iluminacion.
            if (contenedorInicial.get(indice).getIluminacion()) {
                String s = contenedorInicial.get(indice).getNombre();
                setImagenesMejoradas(s);
            }
        }
    }

    /**
     * Este metodo aumenta un conteo de imagenes con la iluminacion mejorada
     */
    public void setImagenesMejoradas(String s){
        imagenesMejoradas ++;
        System.out.println("Imagen Mejorada: " + s);
    }
    /**
     * Este metodo retorna la cantidad de imagenes con la iluminacion mejorada.
     * @return imagenesMejoradas : cantidad de imagenes mejoradas en su totalidad (por los 3 hilos).
     */
    public int getImagenesMejoradas() {
        synchronized (this) {
            return imagenesMejoradas;
        }
    }
    //proceso 3: Ajusta la imagen.
    public synchronized void getImagenAAjustar(int l, int a){
        int indice2 = 0;
        boolean encuentra2 = false;
        for (Imagen aux : getContenedorInicial()) {    //si esta vacio lanzara exception.
            if ( (aux.getIluminacion()) && (!aux.getTamanio()) ) { //si: fue mejorada la iluminacion, no fue moidificado su tamaño.
                encuentra2 = true;
                break;  //sale del for. y conserva el valor del indice encontrado.
            }
            indice2++;
        }
        if (!encuentra2) { return; }  //no encontro ninguno para trabajar. sale del metodo.

        synchronized (contenedorInicial.get(indice2)) {
            contenedorInicial.get(indice2).setImprovements(2, Thread.currentThread().getName());  //contabilizar interaccion hiloXproceso.
            contenedorInicial.get(indice2).setTamanio(l, a);    //ajustar el tamanio de la imagen.
            try {
                TimeUnit.MILLISECONDS.sleep((long)(tiempo*3) );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }  //tiempo en ajustar.

            String s = contenedorInicial.get(indice2).getNombre();
            setImagenesAjustadas(s);
        }
    }
    public void setImagenesAjustadas(String s){
        imagenesAjustadas ++;
        System.out.println("Imagen Ajustada: " + s);
    }
    public int getImagenesAjustadas() {
        synchronized (this){
            return imagenesAjustadas;
        }
    }

    // Proceso 4: Realilzar copia en contenedor final y borrar el original.
    public void copiar(ContenedorFinal cf) {
        int indice3 = 0;
        boolean encuentra3 = false;
        synchronized (this) {
            for (Imagen aux : getContenedorInicial()) {    //si esta vacio lanzara exception.
                if ((aux.getIluminacion()) && (aux.getTamanio())) { //si: fue mejorada la iluminacion, fue moidificado su tamaño.
                    encuentra3 = true;
                    break;  //sale del for. y conserva el valor del indice encontrado.
                }
                indice3++;
            }
            if (!encuentra3) {
                return;
            }  //no encontro ninguno para trabajar. sale del metodo.
            synchronized (contenedorInicial.get(indice3)) {
                Imagen AUX = contenedorInicial.get(indice3);
                contenedorInicial.remove(indice3);
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (tiempo / 2));//tiempo en sacar la imagen del contenedor inicial.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cf.pegarImagen(AUX);
            }
        }
    }
    public ArrayList<Imagen> getContenedorInicial(){
        return contenedorInicial;
    }
}
