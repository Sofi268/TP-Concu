/**
 * Proceso 4: Hacer una copia de las imagenes ya mejoradas y ajustadas en contenedor final, y luego las elimina del contenedor inicial.en un tiempo aleatorio.
 * cantidad de hilos: 2
 */
public class CargarCopia implements Runnable {
    private ContenedorInicial ci;
    private ContenedorFinal cf;
    private boolean listo;
    public CargarCopia(ContenedorInicial ci, ContenedorFinal cf) {
        this.ci = ci;
        this.cf = cf;
        listo = false;
    }

    @Override
    public void run() {
        System.out.println("inicio de copiado............");
        while(!listo){
            cf.Copiar(ci);
            if(cf.getImagenesCopiadas() == 100){
                listo = true;
                System.out.printf("%s : copiado completado.\n",Thread.currentThread().getName());
            }
        }
    }
}
