import java.util.Random;

public class AjustarTamanio implements Runnable{
    private ContenedorInicial c;
    private Random rand;
    private boolean listo;
    private Integer imagenesTocadas;
    public AjustarTamanio(ContenedorInicial c) {
        this.c = c;
        rand = new Random();
        listo = false;
        imagenesTocadas = 0;
    }

    @Override
    public void run() {
        while(!listo){
            ajustarTamanio(4, 2);
        }
    }
    public void ajustarTamanio(int l, int a){  //largo y  ancho
        c.getImagenAAjustar(rand.nextInt(100), l, a);
        setListo();
    }
    public void setListo(){
        if(c.getImagenesAjustadas() == 100){
            listo = true;
        }
    }
}