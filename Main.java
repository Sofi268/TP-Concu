/**
 * Clase Principal del programa: Se manejan los hilos en ejecucion.
 */
public class Main {
    /**
     * constructor
     * @param args
     */
    public static void main(String[] args) {
        Thread hilos[] = new Thread[11]; //Arreglo para 11 hilos
        //classes comunes:
        long tiempo = 10 * 1000  ;  //(ms) setamos cuanto tiempo queremos que dure el proceso. 10 para probar los proceso. 5 para probar el programa.
        Testeo prueba = new Testeo(tiempo);  //para comenzar con las pruebas.
        //DES-COMENTAR METODO PARA PROBAR PROCESO ELEGIDO. parametro: cantidad de hilos que deseo que carguen las imagenes.
        //for(int i=0;i<20;i++){  //para repetir 20 veces las pruebas.
        //prueba.test_Cargar(2);
        //prueba.test_Mejorar(3);
        //prueba.test_Ajustar(3);
        //prueba.test_CargarCopia(2);    //}

        ContenedorInicial ci = new ContenedorInicial();  //donde se cargaran las imagenes.
        ContenedorFinal cf = new ContenedorFinal();
        //creacion de procesos.
        Cargar carga = new Cargar(tiempo,ci,2);    //Proceso 1:cargar las imagenes.
        Mejorar mejora = new Mejorar(tiempo,ci,3); //Proceso 2:Mejorar iluminacion de las imagenes.
        Ajustar ajuste = new Ajustar(tiempo,ci,3); //Proceso 3:Ajustar el tamaño de las imagenes.
        CargarCopia cargaCopia = new CargarCopia(tiempo,ci,cf,2); //Proceso 4:CargarCopia de las imagenes.

        //Creacion de Hilos. nota: modificar parametros a demanda.
        Thread hilo1 = new Thread(carga); hilo1.setPriority(10); hilos[1]=hilo1; //P1:Carga.
        Thread hilo2 = new Thread(carga); hilo2.setPriority(10); hilos[2]=hilo2; //P1:Carga.
        Thread hilo3 = new Thread(mejora); hilo3.setPriority(8); hilos[3]=hilo3; //P2:Mejora de iluminacion.
        Thread hilo4 = new Thread(mejora); hilo4.setPriority(8); hilos[4]=hilo4; //P2:Mejora de iluminacion.
        Thread hilo5 = new Thread(mejora); hilo5.setPriority(8); hilos[5]=hilo5; //P2:Mejora de iluminacion.
        Thread hilo6 = new Thread(ajuste); hilo6.setPriority(5); hilos[6]=hilo6; //P3:Ajuste de tamanio.
        Thread hilo7 = new Thread(ajuste); hilo7.setPriority(5); hilos[7]=hilo7; //P3:Ajuste de tamanio.
        Thread hilo8 = new Thread(ajuste); hilo8.setPriority(5); hilos[8]=hilo8; //P3:Ajuste de tamanio.
        Thread hilo9 = new Thread(cargaCopia); hilo9.setPriority(3); hilos[9]=hilo9;   //P4:Copiar.
        Thread hilo10 = new Thread(cargaCopia); hilo10.setPriority(3); hilos[10]=hilo10; //P4:Copiar.

        Estadistico log = new Estadistico(carga,mejora,ajuste,cargaCopia,hilos);
        Thread hilo0 = new Thread(log); hilo0.setPriority(1); hilos[0]=hilo0 ; //Estadistico.

        long startTimeMain = System.currentTimeMillis();
        for (int i=0;i<=10;i++){   //iniciamos los hilos.
            hilos[i].start();
        }

        try{  //Detenemos los hilos.
            for (int i=1;i<=10;i++){ //iniciamos los hilos.
                hilos[i].join();
            }
            hilo0.join(); //hilo del estadistico
        }catch(InterruptedException e){ throw new RuntimeException(e); }
        long endTimeMain = System.currentTimeMillis();
        long t = endTimeMain - startTimeMain; //tiempo en miliSegundos.
        prueba.test_COMPLETO(ci,cf,t);
    }
}