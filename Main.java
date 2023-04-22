/*
* Trabajo Practico n°1: Mejoramiento de iluminacion y ajuste de tamaño de 100 imagenes.
* */
public class Main {
    public static void main(String[] args) {
        ContenedorInicial ci = new ContenedorInicial();
        ContenedorFinal cf = new ContenedorFinal();
        Cargar c = new Cargar(ci, cf);
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
    }
}