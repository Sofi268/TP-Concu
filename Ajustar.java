/**
 * Ajustar: Proceso encargado de ajustar el tamaño de la imagen de manera sincronizada
 * hilos que ejecutan: hilo 6/7/8
 * Acciones que realiza: -agarro una imagen random y pregunto si listo es false y si esa imagen random fue ajustada de lo contrario la ajusto.
 */
public class Ajustar implements Runnable{
    private long tiempo;
    private boolean listo;
    private int imagenesAjustadas;
    protected ContenedorInicial c;

    public Ajustar(long tiempo) {
        this.tiempo = tiempo;
        listo = false;
        imagenesAjustadas = 0;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int indiceImagenaleatoria = rand.nextInt(c.lenght);// de forma aleatoria elijo un indice
        String imagenAleatoria = c[indiceImagenaleatoria];  // busco la imagen en ese indice 
        
             while(listo == false && //que esa imagen no este ajustada ){
                  ajustar(5,2); 
                 
             }
    }
    
    
    public void setListo(int i){
        if(i == 100){
            listo = true;
        }
    }
    
    public void ajustar(int l, int a)
    {
         imagenesAjustadas ++;
         System.out.println("Imagen Ajustada: " + s); //dudas
         c.ajuste(l,a); // esta en contenedor inicial no se si se implementa ahi o que
         
         setListo(imagenesAjustadas); //me fijo si son 100 o que
    }
}
