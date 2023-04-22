import java.util.Random;

public class AjustarTamanio implements Runnable{
    private ContenedorInicial c;
    private Random rand;
    private boolean listo;
    public AjustarTamanio(ContenedorInicial c) {
        this.c = c;
        rand = new Random();
        listo = false;
    }

    @Override
    public void run() {
        while(!listo){
            ajustarTamanio(4, 2);
        }
    }
    public void ajustarTamanio(int l, int a){
        c.getImagenAAjustar(rand.nextInt(100), l, a);
        setListo();
    }
    public void setListo(){
        if(c.getImagenesAjustadas() == 100){
            listo = true;
        }
    }
}
