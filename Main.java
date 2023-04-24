/*
* Trabajo Practico n°1: Mejoramiento de iluminacion y ajuste de tamaño de 100 imagenes.
* */
public class Main {
    public static void main(String[] args) {
        //Runtime.getRuntime().availableProcessors(); //cantidad de hilos del sistema.
        //CREACION DE HILOS:
        Thread hilos[] = new Thread[10]; //Definimos el tamaño 10 para los hilos.
        ContenedorInicial ci = new ContenedorInicial();
        ContenedorFinal cf = new ContenedorFinal();

        Cargar c = new Cargar(ci);                          //proceso 1
        Thread h0 = new Thread(c); hilos[0] = h0;
        Thread h1 = new Thread(c); hilos[1] = h1;

        MejoraIluminacion mi = new MejoraIluminacion(ci);   //proceso 2
        AjustarTamanio at = new AjustarTamanio(ci);         //proceso 3
        Thread h2 = new Thread(mi); hilos[2] = h2;
        Thread h3 = new Thread(mi); hilos[3] = h3;
        Thread h4 = new Thread(mi); hilos[4] = h4;
        Thread h5 = new Thread(at); hilos[5] = h5;
        Thread h6 = new Thread(at); hilos[6] = h6;
        Thread h7 = new Thread(at); hilos[7] = h7;

        CargarCopia c2 = new CargarCopia(ci, cf,hilos);     //proceso 4
        Thread h8 = new Thread(c2); hilos[8] = h8;
        Thread h9 = new Thread(c2); hilos[9] = h9;

        Estadisticos est = new Estadisticos(ci,cf,hilos);       //encargado de hacer el LOG estadistico.
        Thread h10 = new Thread(est);
            //h10.setDaemon(true); //hilo daemon: fue descartado ya que queremos cerrar el programa con un resumen final de los procesos terminados y completados.

        //COMIENZO DE EJECUCION DE HILOS:
        h10.start();
        h0.start();
        h1.start();
        try {
            h0.join();
            h1.join();
        } catch (InterruptedException e) { throw new RuntimeException(e);}

        h2.start();
        h3.start();
        h4.start();
        h5.start();
        h6.start();
        h7.start();
        try {
            h2.join();
            h3.join();
            h4.join();
            h5.join();
            h6.join();
            h7.join();
        } catch (InterruptedException e) { throw new RuntimeException(e); }

        h8.start();
        h9.start();

        //FINALIZACION DE HILOS:
        try {
            /*h0.join();
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
            h6.join();
            h7.join();*/
            h8.join();
            h9.join();
            h10.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
