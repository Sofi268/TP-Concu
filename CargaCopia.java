public class CargaCopia implements Runnable{
    private ContenedorInicial ci;
    private ContenedorFinal cf;
    private boolean listo;

    public CargaCopia(ContenedorInicial ci, ContenedorFinal cf) {
        this.ci = ci;
        this.cf = cf;
        this.listo = false;
    }

    public void copiarImagen(){
        //int can_aux = cf.getImagenesCopiadas();
        Imagen aux = ci.sacarImagen(rand.nextInt(100));
        if(aux != null){
            cf.Copiar(aux);
            setListo();
        }
    }
    public void setListo(){
        if(cf.getImagenesCopiadas() == 100){
            listo = true;
            System.out.println("Se termino el copiado....");
        }
    }
}


                System.out.println("......inicio de copiado.....");
                while(!listo){
                copiarImagen();
                }
                System.out.printf("cantidad de imagenes copiadas %d\n",cf.getImagenesCopiadas());
                }