import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Estadisticos implements Runnable{
    private ContenedorInicial ci;
    private ContenedorFinal cf;
    private boolean listo;
    private int[][] contadorEstadistico;
    public Estadisticos(ContenedorInicial ci, ContenedorFinal cf){
        this.ci = ci;
        this.cf = cf;
        listo = false;
        contadorEstadistico = new int[][] {  //fila:proceso , columna:hilo.
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };
    }

    @Override
    public void run() {
        try {
            FileWriter file = new FileWriter("D:\\UNC-Ing.Compu\\4to año\\Programacion concurrente\\TP1\\Carpeta compartida\\src\\LOG.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println("************** Inicio del registro: *********");
            while(!listo){
                imprimir(pw);
                try {
                    TimeUnit.MILLISECONDS.sleep(500); //timepo que tardara en realizar la impresiones.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void imprimir(PrintWriter pw){
        unificar(pw);
    }
    public void unificar(PrintWriter pw){ //unifica las 100 imagenes. desde el contenedorInicial y el ContenedorFinal.
        ArrayList<Imagen> ArregloAux = new ArrayList<>(100); //arreglo auxialiar
        ArregloAux.addAll(ci.getContenedorInicial());
        ArregloAux.addAll(cf.getContenedorFinal());
        int[][] contadorAux= new int[][];

        for(Imagen aux : ArregloAux){
            contadorAux = (aux.getContador());
            for(int i=0;i<4;i++){ //filas: procesos
                for(int j=0;j<10;j++){ //columnas hilos.
                    contadorEstadistico[i][j] += contadorAux[i][j]; //obtener arreglo del ci
                }
            }
        }

        for(Imagen aux : ArregloAux){
            contadorAux = (aux.getContador());
            for(int i=0;i<4;i++){ //filas: procesos
                for(int j=0;j<10;j++){ //columnas hilos.
                    System.out.print( contadorAux[i][j] + " ");
                }
                System.out.println("");
            }
        }

        System.out.println("--- cantidad de elementos de estadistica: " + ArregloAux.size());
        //obtener arreglo del cf
    }
}


/*
D:\UNC-Ing.Compu\4to año\Programacion concurrente\TP1\Carpeta compartida\src
    FileWriter file = new FileWriter("D:\\UNC-Ing.Compu\\4to año\\Programacion concurrente\\clase 2\\registro_clase02.txt"); //direccion de la carpeta.
    PrintWriter pw = new PrintWriter(file); //escritor para operar sobre el archivo.
    pw.printf(); o pw.println(); // para escribir en el archivo .txt

 * 5) en un LOG con fines estadisticos, cada 500 ms.
 *   Cantidad de imagenes insertadas en el contenedor:
 *   Cantidad de imagenes mejoradas completamente.
 *   cantidad de imagenes ajustadas.
 *   Cantidad de imagenes que han finalizado el ultimo proceso.
 *   El estado de cada hilo del sistema: (10 hilos.)
 *       for(int i=0;i<10;i++){
 *           ("Estado del Hilo %s: ",i,h[i].state());
 *       }
 * */