/*
* Trabajo Practico n°1: Mejoramiento de iluminacion y ajuste de tamaño de 100 imagenes.
* */
public class Main {
    public static void main(String[] args) {
        ContenedorInicial ci = new ContenedorInicial();
        ContenedorFinal cf = new ContenedorFinal();
        Cargar c = new Cargar(ci);

        Thread h0 = new Thread(c);
        Thread h1 = new Thread(c);

        h0.start();
        h1.start();

        try {
            h0.join();
            h1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Imagenes en contenedor origen : %d\n",ci.getCantidadImagenes());
        System.out.printf("Imagenes copiadas : %d \n",cf.getImagenesCopiadas());

        MejoraIluminacion mi = new MejoraIluminacion(ci);
        AjustarTamanio at = new AjustarTamanio(ci);
        Thread h2 = new Thread(mi);
        Thread h3 = new Thread(mi);
        Thread h4 = new Thread(mi);

        Thread h5 = new Thread(at);
        Thread h6 = new Thread(at);
        Thread h7 = new Thread(at);

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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        CargarCopia c2 = new CargarCopia(ci, cf);
        Thread h8 = new Thread(c2);
        Thread h9 = new Thread(c2);

        h8.start();
        h9.start();

        try {
            h8.join();
            h9.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Imagenes en contenedor origen : %d\n",ci.getCantidadImagenes());
        System.out.printf("Imagenes copiadas : %d \n",cf.getImagenesCopiadas());

    }
}

/*
 * Consideraciones:
 * 1) La imagen tiene un atributo "implements" el cual registra cuantos hilos la tomaron.
 * 2)L Se deben definir tiempo NO NULO en los procesos.
 * 3)L Las imagenes no pueden ser ajustadas sin antes no ser mejoradas por los tres hilos del proceso 2
 * 4) contabilizar cuantos imagenes->
 *    -> Ajusto cada hilo del 3er proceso.
 *    -> Movio cada hilo en el 4to proceso.
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