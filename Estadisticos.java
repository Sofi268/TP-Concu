import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Estadisticos implements Runnable{
    private ContenedorInicial ci;
    private ContenedorFinal cf;
    private boolean listo;
    private int[][] contadorEstadistico;
    private Thread hilos[] = new Thread[10];
    public Estadisticos(ContenedorInicial ci, ContenedorFinal cf,Thread hilos[]){
        this.ci = ci;
        this.cf = cf;
        listo = false;
        contadorEstadistico = new int[][] {  //fila:proceso , columna:hilo.
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
        for(int i=0;i<10;i++){
            this.hilos[i] = hilos[i];
        }
    }

    @Override
    public void run() {
        try(FileWriter file = new FileWriter("D:\\UNC-Ing.Compu\\4to año\\Programacion concurrente\\TP1\\Carpeta compartida\\log.txt");
            PrintWriter pw = new PrintWriter(file); ) {
            pw.printf("************** Inicio del registro: %s *********", new Date());
            while (!listo) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500); //timepo que tardara en realizar la impresiones.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                imprimir(pw);
                if (cf.getImagenesCopiadas() == 100) {
                    listo = true;
                }
            }
        }catch (IOException e){}
    }
    public void imprimir(PrintWriter pw){
        unificar();  //actualiza la matriz contadorEstadistico.
        pw.printf("------------------------------------------------------------------------\n");
        pw.printf("P1-Cantidad de imagenes insertadas en el contenedor: %s\n",(contadorEstadistico[0][0]+contadorEstadistico[0][1]));
        pw.printf("P2-Cantidad de imagenes mejoradas completamente por hilo: HILO_2:(%s) - HILO_3:(%s)  - HILO_4:(%s)  \n",contadorEstadistico[1][2],contadorEstadistico[1][3],contadorEstadistico[1][4]);
        pw.printf("P3-cantidad de imagenes ajustadas: %s\n",(contadorEstadistico[2][5]+contadorEstadistico[2][6])+contadorEstadistico[2][7]);
        pw.printf("P4-Cantidad de imagenes insertadas en el contenedor: %s\n",(contadorEstadistico[3][8]+contadorEstadistico[3][9]));
        for(int i=0;i<10;i++){
            pw.printf("Estado %s : %s \n",hilos[i].getName(),hilos[i].getState());
        }
    }
    public void unificar(){ //unifica las 100 imagenes. desde el contenedorInicial y el ContenedorFinal.
        ArrayList<Imagen> listaAux = new ArrayList<>(100); //arreglo auxialiar
        listaAux.addAll(ci.getContenedorInicial());
        listaAux.addAll(cf.getContenedorFinal());
        resetContador(); //borra la matriz contador, para cargar los nuevos valores...
        int[][] contadorAux;
        for(Imagen aux : listaAux){
            contadorAux = (aux.getImprovements());
            for(int i=0;i<4;i++){ //filas: procesos
                for(int j=0;j<10;j++){ //columnas hilos.
                    contadorEstadistico[i][j] += contadorAux[i][j]; //obtener arreglo del ci
                }
            }
        }
        //mostrar();
    }
    public void resetContador(){
        for(int i=0;i<4;i++){ //filas: procesos
            for(int j=0;j<10;j++){ //columnas hilos.
                contadorEstadistico[i][j] = 0; //obtener arreglo del ci
            }
        }
    }
    public void mostrar(){  //a modo de prueba.
        for(int i=0;i<4;i++) { //filas: procesos
            for (int j = 0; j < 10; j++) { //columnas hilos.
                System.out.print(contadorEstadistico[i][j] + " ");
            }
            System.out.println("");
        }
    }
}