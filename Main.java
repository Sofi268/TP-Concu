/**
 * Clase Principal del programa: Se manejan los hilos en ejecucion.
 */
public class Main {
    private static long tiempo;
    /**
     * constructor
     * @param args
     */
    public static void main(String[] args) {
        Thread hilos[] = new Thread[11]; //Arreglo para 11 hilos
        //clases comunes:
        tiempo = 12000;  //setamos cuanto tiempo queremos que dure el proceso.en milisegundos (ms)
        Testeo prueba = new Testeo(tiempo);  //para comenzar con las pruebas.
        prueba.test_Cargar();

        ContenedorInicial ci = new ContenedorInicial();  //donde se cargaran las imagenes.
        ContenedorFinal cf = new ContenedorFinal();
        //creacion de procesos.
        Estadistico log = new Estadistico();
        Cargar carga = new Cargar(tiempo);    //Proceso 1:cargar las imagenes.
        Mejorar mejora = new Mejorar(tiempo); //Proceso 2:Mejorar iluminacion de las imagenes.
        Ajustar ajuste = new Ajustar(tiempo); //Proceso 3:Ajustar el tamaño de las imagenes.
        CargarCopia cargaCopia = new CargarCopia(tiempo); //Proceso 4:CargarCopia de las imagenes.

        //Creacion de Hilos. nota: modificar parametros a demanda.
        Thread hilo0 = new Thread();  hilos[0]=hilo0 ; //Estadistico.
        Thread hilo1 = new Thread();  hilos[1]=hilo1; //P1:Carga.
        Thread hilo2 = new Thread();  hilos[2]=hilo2; //P1:Carga.
        Thread hilo3 = new Thread();  hilos[3]=hilo3; //P2:Mejora de iluminacion.
        Thread hilo4 = new Thread();  hilos[4]=hilo4; //P2:Mejora de iluminacion.
        Thread hilo5 = new Thread();  hilos[5]=hilo5; //P2:Mejora de iluminacion.
        Thread hilo6 = new Thread();  hilos[6]=hilo6; //P3:Ajuste de tamanio.
        Thread hilo7 = new Thread();  hilos[7]=hilo7; //P3:Ajuste de tamanio.
        Thread hilo8 = new Thread();  hilos[8]=hilo8; //P3:Ajuste de tamanio.
        Thread hilo9 = new Thread();  hilos[9]=hilo9;   //P4:Copiar.
        Thread hilo10 = new Thread(); hilos[10]=hilo10; //P4:Copiar.

        for (int i=0;i<10;i++){ //iniciamos los hilos.
            hilos[i].start();
        }

        try{  //Detenemos los hilos.
            for (int i=0;i<10;i++){ //iniciamos los hilos.
                hilos[i].join();
            }
        }catch(InterruptedException e){ throw new RuntimeException(e);}

    }
}