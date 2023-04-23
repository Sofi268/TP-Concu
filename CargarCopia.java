
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
