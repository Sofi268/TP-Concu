/**
 * Lleva la cuenta de cuantas imagenes se encuentran en cada proceso.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Lleva la cuenta de cuantas imagenes se encuentran en cada proceso.
 */
public class Estadistico implements Runnable{
    private boolean listo;
    private Thread hilos[] = new Thread[10];
    private Cargar carga;
    private Mejorar mejora;
    private Ajustar ajuste;
    private CargarCopia cargaCopia;
    private int imagenesCargadas, imagenesMejoradas, imagenesAjustadas,imagenesCopiadas,cantidadDeMuestras;

    public Estadistico(Cargar carga, Mejorar mejora, Ajustar ajuste, CargarCopia cargaCopia, Thread hilos[]){
        this.carga = carga;
        this.mejora = mejora;
        this.ajuste = ajuste;
        this.cargaCopia = cargaCopia;
        for(int i=0;i<10;i++) {
            this.hilos[i] = hilos[i+1];
        }
        listo = false;
        cantidadDeMuestras = 0;
        imagenesCargadas=0;
        imagenesMejoradas=0;
        imagenesAjustadas=0;
        imagenesCopiadas=0;
    }
    @Override
    public void run() {
        try(FileWriter file = new FileWriter("D:\\UNC-Ing.Compu\\4to año\\Programacion concurrente\\TP1\\Trabajo_Practicos01\\src\\log.txt");
            PrintWriter pw = new PrintWriter(file); ) {
            pw.printf("************** Inicio del registro: %s ********* ", new Date());
            while (!listo) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500); //tiempo que tardara en realizar la impresiones.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                actualizarDatos();
                imprimir(pw);
                cantidadDeMuestras ++;
                if (imagenesCopiadas == 100) {
                    listo = true;
                }
            }
            actualizarDatos();
            imprimir(pw);
        }catch (IOException e){}
    }
    private void actualizarDatos(){
        imagenesCargadas = carga.getImagenesCargadas();
        imagenesMejoradas = mejora.getImagenesMejoradas();
        imagenesAjustadas = ajuste.getImagenesAjustadas();
        imagenesCopiadas = cargaCopia.getImagenesCopiadas();
    }
    private void imprimir(PrintWriter pw){
        pw.printf("------------------------------------------------------------------------\n");
        pw.printf("P1-Cantidad de imagenes insertadas en el contenedor incial: %s\n",imagenesCargadas);
        pw.printf("P2-Cantidad de imagenes mejoradas completamente: %s\n",imagenesMejoradas);
        pw.printf("P3-cantidad de imagenes ajustadas: %s\n",imagenesAjustadas);
        pw.printf("P4-Cantidad de imagenes copiadas al contenedor final: %s\n",imagenesCopiadas);
        for(int i=0;i<10;i++){
            pw.printf("Estado %s : %s \n",hilos[i].getName(),hilos[i].getState());
        }
    }
}