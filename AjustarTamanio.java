/**
 * Proceso 3: Ajustar tamanio de las imagenes a un tamanio solicitado, en un tiempo aleatorio.
 * cantidad de hilos: 3
 */
public class AjustarTamanio extends Edicion implements Runnable{
    
    public AjustarTamanio(ContenedorInicial c) {
        super(c);
    }

    @Override
    public void run() {
        while(!listo){
            ajustarTamanio(4, 2);  //l: largo, a: ancho
        }
    }
    public void ajustarTamanio(int l, int a){  //largo y  ancho
        c.getImagenAAjustar(rand.nextInt(100), l, a);
        setListo(c.getImagenesMejoradas());
    }
}
