
/**
 * Lleva la cuenta de cuantas imagenes se encuentran en cada proceso.
 */
public class Estadistico implements Runnable{
    private boolean listo;
    private Thread hilos[] = new Thread[10];
    private int cantidadDeMuestras;
    private int imagenesCargadas, imagenesMejoradas1,imagenesMejoradas2,imagenesMejoradas3, imagenesAjustadas,imagenesCopiadas;
    public Estadistico(Thread hilos[]){
        listo = false;
        for(int i=0;i<10;i++){
            this.hilos[i] = hilos[i];
        }
        imagenesCargadas = hilos[1].getImagenesCargadas()+hilos[2].getImagenesCargadas();
        imagenesMejoradas1 =hilos[3].getImagenesMejoradas();
        imagenesMejoradas2 =hilos[4].getImagenesMejoradas();
        imagenesMejoradas3 =hilos[5].getImagenesMejoradas();
        imagenesAjustadas = hilos[6].getImagenesAjustadas()+hilos[7].getImagenesAjustadas()+hilos[8].getImagenesAjustadas();
        imagenesCopiadas = hilos[9].getImagenesCopiadas()+hilos[10].getImagenesCopiadas();
        cantidadDeMuestras = 0;
    }
    @Override
    public void run() {
        try(FileWriter file = new FileWriter("\\log.txt");
            PrintWriter pw = new PrintWriter(file); ) {
            pw.printf("************** Inicio del registro: %s ********* ", new Date());
            while (!listo) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500); //tiempo que tardara en realizar la impresiones.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                imprimir(pw);
                cantidadDeMuestras ++;
                if (imagenesCopiadas == 100) {
                    listo = true;
                }
            }
        }catch (IOException e){}
    }
    public void imprimir(PrintWriter pw){
        pw.printf("------------------------------------------------------------------------\n");
        pw.printf("P1-Cantidad de imagenes insertadas en el contenedor: %s\n",imagenesCargadas);
        pw.printf("P2-Cantidad de imagenes mejoradas completamente por hilo: HILO_3:(%s) - HILO_4:(%s)  - HILO_5:(%s)  \n",imagenesMejoradas1,imagenesMejoradas2,imagenesMejoradas3);
        pw.printf("P3-cantidad de imagenes ajustadas: %s\n",imagenesAjustadas);
        pw.printf("P4-Cantidad de imagenes insertadas en el contenedor: %s\n",imagenesCopiadas);
        for(int i=0;i<10;i++){
            pw.printf("Estado %s : %s \n",hilos[i].getName(),hilos[i].getState());
        }
    }
    //         System.out.printf("Cantidad de muestras: %d ",cantidadDeMuestras);
}
