/*
* Trabajo Practico n°1: Mejoramiento de iluminacion y ajuste de tamaño de 100 imagenes.
* */
public class Main {
    public static void main(String[] args) {
        //creacion de hilos:
        ContenedorInicial ci = new ContenedorInicial();
        ContenedorFinal cf = new ContenedorFinal();

        Cargar c = new Cargar(ci);                          //proceso 1
        Thread h0 = new Thread(c);
        Thread h1 = new Thread(c);

        MejoraIluminacion mi = new MejoraIluminacion(ci);   //proceso 2
        AjustarTamanio at = new AjustarTamanio(ci);         //proceso 3
        Thread h2 = new Thread(mi);
        Thread h3 = new Thread(mi);
        Thread h4 = new Thread(mi);
        Thread h5 = new Thread(at);
        Thread h6 = new Thread(at);
        Thread h7 = new Thread(at);

        CargarCopia c2 = new CargarCopia(ci, cf);           //proceso 4
        Thread h8 = new Thread(c2);
        Thread h9 = new Thread(c2);

        Estadisticos est = new Estadisticos(ci,cf);//encargado de hacer el LOG estadistico:
        Thread h10 = new Thread(est);
        h10.setDaemon(true); //hilo daemon, hilo que se ejecutara en segundo plano. y finalizara cuando terminen los otros 10 hilos en ejecucion.
        h10.start();

        //manejo de hilos:
        h0.start();
        h1.start();
        try {
            h0.join();
            h1.join();
        } catch (InterruptedException e) { throw new RuntimeException(e);}

        System.out.printf("Imagenes en contenedor origen : %d\n",ci.getCantidadImagenes());
        System.out.printf("Imagenes copiadas : %d \n",cf.getImagenesCopiadas());

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
